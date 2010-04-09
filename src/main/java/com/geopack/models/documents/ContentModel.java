package com.geopack.models.documents;

import java.util.List;

/**
 * User: Mostovoy.Vladislav
 * Date: 09.04.2010
 */
public class ContentModel extends ElementModel {
	private List<ElementModel> children;

	public List<ElementModel> getChildren() {
		return children;
	}

	public void setChildren(List<ElementModel> children) {
		this.children = children;
	}
}
