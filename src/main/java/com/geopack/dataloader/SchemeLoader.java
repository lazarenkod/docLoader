package com.geopack.dataloader;

import com.geopack.dataloader.nodes.XmlField;
import com.geopack.dataloader.nodes.XmlTableModel;
import com.geopack.models.DictColumnModel;
import com.geopack.models.DictTable;
import com.geopack.models.DictTableModel;
import com.geopack.utils.TableCache;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 12:33:24
 */

/**
 * Загрузчик данных из xml описания
 */

public class SchemeLoader {
	public static final String SCHEME_PATH = "data";

	public SchemeLoader() {
	}

	private XmlTableModel loadScheme(File file) throws IOException, SAXException {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("tablemodel", "com.geopack.dataloader.nodes.XmlTableModel");
		digester.addSetProperties("tablemodel", "id", "id");
		digester.addSetProperties("tablemodel", "desc", "desc");
		digester.addSetProperties("tablemodel", "srcFile", "srcFile");
		digester.addObjectCreate("*/field", "com.geopack.dataloader.nodes.XmlField");
		digester.addSetProperties("*/field", "id", "id");
		digester.addSetProperties("*/field", "desc", "desc");
		digester.addSetProperties("*/field", "type", "type");
		digester.addSetProperties("*/field", "key", "key");
		digester.addSetProperties("*/field", "keyTable", "keyTable");
		digester.addSetProperties("*/field", "keyField", "keyField");
		digester.addSetProperties("*/field", "keyDescField", "keyDescField");
		digester.addSetNext("*/field", "addField");
		return (XmlTableModel) digester.parse(file);

	}

	public void loadSchemes() {
		File schemeDirectory = new File(SCHEME_PATH);
		if (schemeDirectory.isDirectory()) {
			final File[] schemeFiles = schemeDirectory.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".shm.xml");
				}
			});
			for (File file : schemeFiles) {
				try {
					final XmlTableModel xmlTableModel = loadScheme(file);
					DictTableModel dictTableModel = new DictTableModel(xmlTableModel.getId(), xmlTableModel.getDesc(), SCHEME_PATH + "/" + xmlTableModel.getSrcFile());
					for (XmlField xmlField : xmlTableModel.getFields()) {
						DictColumnModel columnModel = new DictColumnModel();
						columnModel.setName(xmlField.getId());
						columnModel.setDescription(xmlField.getDesc());
						columnModel.setKeyField("1".equals(xmlField.getKey()));
						//зануляем пустые необязательные поля
						columnModel.setSrcTableName((StringUtils.isEmpty(xmlField.getKeyTable()) ? null : xmlField.getKeyTable()));
						columnModel.setSrcTableColumsName(StringUtils.isEmpty(xmlField.getKeyField()) ? null : xmlField.getKeyField());
						columnModel.setSrcTableColumnViewName(StringUtils.isEmpty(xmlField.getKeyDescField()) ? null : xmlField.getKeyDescField());

						final String valueType = xmlField.getType();
						if (valueType.equals("String")) {
							columnModel.setType(String.class);
						} else if (valueType.equals("Integer")) {
							columnModel.setType(Integer.class);
						} else if (valueType.equals("Float")) {
							columnModel.setType(BigDecimal.class);
						} else if (valueType.equals("Date")) {
							columnModel.setType(Date.class);
						}

						dictTableModel.addColumnModel(columnModel);
					}

					DictTable dictTable = new DictTable(dictTableModel);
					TableCache.getInstance().put(dictTable);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
