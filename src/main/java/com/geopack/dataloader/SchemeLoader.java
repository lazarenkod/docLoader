package com.geopack.dataloader;

import com.geopack.dataloader.nodes.XmlTableModel;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 12:33:24
 */
public class SchemeLoader {
	public static final String SCHEME_PATH = "data";

	public SchemeLoader() {
	}

	private XmlTableModel loadScheme(File file)throws IOException, SAXException {
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
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
