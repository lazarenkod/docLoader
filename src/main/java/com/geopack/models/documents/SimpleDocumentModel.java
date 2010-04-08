package com.geopack.models.documents;

import com.geopack.models.documentelements.CompositeElementlModel;

import java.util.Date;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class SimpleDocumentModel extends AbstractDocumentModel {

	public SimpleDocumentModel(CompositeElementlModel root) {
		super(root);
	}

	public String getName() {
		return "";
	}

	public String getDescription() {
		return "";
	}

	public String getAuthor() {
		return "";
	}

	public Date getDate() {
		return null;
	}

	//public 
}
