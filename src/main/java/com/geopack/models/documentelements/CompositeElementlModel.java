package com.geopack.models.documentelements;

import com.geopack.models.attributes.IExtAttributes;
import com.geopack.models.attributes.StringAttribute;

import java.util.List;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class CompositeElementlModel implements IExtAttributes {

	private List<IElementModel> elements;
	private List<CompositeElementlModel> containers;

	private List<StringAttribute> extAttributes;

	public CompositeElementlModel() {
	}

	public List<IElementModel> getElements() {
		return elements;
	}

	public void setElements(List<IElementModel> elements) {
		this.elements = elements;
	}

	public List<CompositeElementlModel> getContainers() {
		return containers;
	}

	public void setContainers(List<CompositeElementlModel> containers) {
		this.containers = containers;
	}

	public List<StringAttribute> getExtAttributes() {
		return extAttributes;
	}

	public void setExtAttributes(List<StringAttribute> extAttributes) {
		this.extAttributes = extAttributes;
	}
}
