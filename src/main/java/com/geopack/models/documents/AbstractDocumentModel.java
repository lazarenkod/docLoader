package com.geopack.models.documents;

import com.geopack.models.documentelements.CompositeElementlModel;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class AbstractDocumentModel {

	protected CompositeElementlModel root;

	public AbstractDocumentModel(CompositeElementlModel root) {
		this.root = root;
	}

	public CompositeElementlModel getRoot() {
		return root;
	}

}
