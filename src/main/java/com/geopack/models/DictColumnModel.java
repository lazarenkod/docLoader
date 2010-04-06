package com.geopack.models;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 12:53:37
 */

/**
 * Модель колонки таблицы
 */
public class DictColumnModel {
	private String name;
	private String description;
	private boolean keyField;
	private Class type;
	private String srcTableName;
	private String srcTableColumsName;
	private String srcTableColumnViewName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isKeyField() {
		return keyField;
	}

	public void setKeyField(boolean keyField) {
		this.keyField = keyField;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}

	public String getSrcTableName() {
		return srcTableName;
	}

	public void setSrcTableName(String srcTableName) {
		this.srcTableName = srcTableName;
	}

	public String getSrcTableColumsName() {
		return srcTableColumsName;
	}

	public void setSrcTableColumsName(String srcTableColumsName) {
		this.srcTableColumsName = srcTableColumsName;
	}

	public String getSrcTableColumnViewName() {
		return srcTableColumnViewName;
	}

	public void setSrcTableColumnViewName(String srcTableColumnViewName) {
		this.srcTableColumnViewName = srcTableColumnViewName;
	}
}
