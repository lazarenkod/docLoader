package com.geopack.models.documents;

import java.util.Set;

/**
 * User: Mostovoy.Vladislav
 * Date: 09.04.2010
 */
public class ElementModel {
	private int id;
	private boolean visible;

	private Set<String> keywords;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Set<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
}
