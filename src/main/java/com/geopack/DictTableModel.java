package com.geopack;

import java.util.*;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 12:53:29
 */
public class DictTableModel {
	private String name;
	private String description;
	private Map<String,DictColumnModel> columnModels = new HashMap<String,DictColumnModel>();

	public DictTableModel(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public DictTableModel(String name, String description, Map<String,DictColumnModel> columnModels) {
		this.name = name;
		this.description = description;
		this.columnModels.clear();
		this.columnModels.putAll(columnModels);
	}

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

	public Map<String,DictColumnModel> getColumnModels() {
		return Collections.unmodifiableMap(columnModels);
	}

	public DictColumnModel getColumnModel(String name) {
		return columnModels.get(name);
	}

	public int getColumnIndex(String name) {
		for (int i = 0; i < columnModels.size(); i++) {
			DictColumnModel dictColumnModel = columnModels.get(i);
			if (dictColumnModel.getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public void addColumnModel(DictColumnModel columnModel) {
		columnModels.put(columnModel.getName(),columnModel);
	}

	public void removeColumnModel(DictColumnModel columnModel) {
		columnModels.remove(columnModel);
	}

}
