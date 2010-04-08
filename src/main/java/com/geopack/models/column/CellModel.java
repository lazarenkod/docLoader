package com.geopack.models.column;

/**
 * User: vladkimo@gmail.com
 */

/**
 * Модель колонки таблицы
 */
public class CellModel {
    private int id;
    private int seqNum;
	private String name;
	private String description;
	private CellType type;

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
}