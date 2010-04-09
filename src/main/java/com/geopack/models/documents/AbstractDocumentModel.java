package com.geopack.models.documents;

import com.geopack.models.documentelements.CompositeElement;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class AbstractDocumentModel {

	protected CompositeElement root;

	public AbstractDocumentModel(CompositeElement root) {
		this.root = root;
	}

	public CompositeElement getRoot() {
		return root;
	}

}
