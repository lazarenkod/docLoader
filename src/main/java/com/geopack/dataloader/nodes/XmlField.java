package com.geopack.dataloader.nodes;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 14:36:09
 */

/**
 * Описывает поле field в xml схеме бд
 */
public class XmlField {
//	<field id="orgName" desc="Название организации" type="String" key="0" keyTable="" keyField="" keyDescField=""/>
	private String id;
	private String desc;
	private String type;
	private String key;
	private String keyTable;
	private String keyField;
	private String keyDescField;

	public XmlField() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyTable() {
		return keyTable;
	}

	public void setKeyTable(String keyTable) {
		this.keyTable = keyTable;
	}

	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	public String getKeyDescField() {
		return keyDescField;
	}

	public void setKeyDescField(String keyDescField) {
		this.keyDescField = keyDescField;
	}
}
