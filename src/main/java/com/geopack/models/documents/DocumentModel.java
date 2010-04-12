package com.geopack.models.documents;

import java.util.List;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class DocumentModel extends ElementModel {

	private String name;
	private String description;

	private String layoutName;

	private List<ObjectModel> objects;
	private List<PageModel> pages;

	public DocumentModel(String name) {
		this.name = name;
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

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
}