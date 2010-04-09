package com.geopack.models.documents;

import java.util.List;

/**
 * User: Mostovoy.Vladislav
 * Date: 09.04.2010
 */
public class PageModel extends ElementModel {

	private int num;
	private String layoutName;

	private List<ContentModel> contentModels;

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
}
