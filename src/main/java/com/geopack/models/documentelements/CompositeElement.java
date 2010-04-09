package com.geopack.models.documentelements;

import com.geopack.models.attributes.IExtAttributes;
import com.geopack.models.attributes.StringAttribute;

import java.util.List;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class CompositeElement implements IExtAttributes {

	private List<IElement> elements;
	private List<CompositeElement> containers;

	private List<StringAttribute> extAttributes;

	public CompositeElement() {
	}

	public List<IElement> getElements() {
		return elements;
	}

	public void setElements(List<IElement> elements) {
		this.elements = elements;
	}

	public List<CompositeElement> getContainers() {
		return containers;
	}

	public void setContainers(List<CompositeElement> containers) {
		this.containers = containers;
	}

	public List<StringAttribute> getExtAttributes() {
		return extAttributes;
	}

	public void setExtAttributes(List<StringAttribute> extAttributes) {
		this.extAttributes = extAttributes;
	}
}
