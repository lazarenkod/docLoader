package com.geopack.dataloader.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 14:32:55
 */

/** Описывает корневую структуру в xml */
public class XmlTableModel {
	private String id;
	private String desc;
	private String srcFile;
	private List<XmlField> fields = new ArrayList<XmlField>();

	public XmlTableModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(String srcFile) {
		this.srcFile = srcFile;
	}

	public List<XmlField> getFields() {
		return fields;
	}

	public void setFields(List<XmlField> xmlFields) {
		this.fields = xmlFields;
	}

	public void addField(XmlField xmlField) {
		fields.add(xmlField);
	}
}
