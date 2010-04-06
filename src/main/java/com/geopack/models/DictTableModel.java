package com.geopack.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 12:53:29
 */
public class DictTableModel {
	private String name;
	private String description;
	private List<DictColumnModel> columnModels = new ArrayList<DictColumnModel>();
	private String srcFile;

	public DictTableModel(String name, String description, String srcFile) {
		this.name = name;
		this.description = description;
		this.srcFile = srcFile;
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

	public List<DictColumnModel> getColumnModels() {
		return Collections.unmodifiableList(columnModels);
	}

	public DictColumnModel getColumnModel(String name) {
		for (DictColumnModel columnModel : columnModels) {
			if (columnModel.getName().equals(name))
				return columnModel;
		}
		return null;
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
		columnModels.add(columnModel);
	}

	public void removeColumnModel(DictColumnModel columnModel) {
		columnModels.remove(columnModel);
	}

	public String getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(String srcFile) {
		this.srcFile = srcFile;
	}
}
