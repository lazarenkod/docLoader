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
	//id поля
	private String id;
	//человеческое название
	private String desc;
	//тип значение
	private String type;
	//признак ключевого поля 1- ключевое
	private String key;
	//название таблицы - источника для ключевого поля
	private String keyTable;
	//название поля в таблице - источнике для ключевого поля
	private String keyField;
	//название поля-расшифровки в таблице-источнике для ключевого поля
	private String keyDescField;

	public XmlField() {
	}

	/**
	 *
	 * @return id поля
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return человеческое название
	 */
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 *
	 * @return тип значение
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * @return признак ключевого поля 1- ключевое
	 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 *
	 * @return название таблицы - источника для ключевого поля
	 */
	public String getKeyTable() {
		return keyTable;
	}

	public void setKeyTable(String keyTable) {
		this.keyTable = keyTable;
	}

	/**
	 *
	 * @return название поля в таблице - источнике для ключевого поля
	 */
	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	/**
	 *
	 * @return название поля-расшифровки в таблице-источнике для ключевого поля
	 */
	public String getKeyDescField() {
		return keyDescField;
	}

	public void setKeyDescField(String keyDescField) {
		this.keyDescField = keyDescField;
	}
}
