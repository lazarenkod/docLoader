package com.geopack.dataloader;

import com.geopack.dataloader.nodes.XmlTableModel;
import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 14:20:21
 */
public class LayerParser {

	private Log log = LogFactory.getLog(LayerParser.class);


	public XmlTableModel parse(String fileName) throws IOException, SAXException {
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
		return (XmlTableModel) digester.parse(new File(fileName));
	}


}
