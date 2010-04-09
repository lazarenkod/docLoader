package com.geopack.models.documentelements;

import com.geopack.models.attributes.Attribute;
import com.geopack.models.attributes.AttributeRawTypes;
import com.geopack.models.attributes.IExtAttributes;
import com.geopack.models.attributes.StringAttribute;

import java.text.ParseException;
import java.util.List;

/**
 * User: vladkimo@gmail.com
 */

/**
 * Модель колонки таблицы
 */
public class Element implements IElement, IExtAttributes {

	private Attribute value;

	private List<StringAttribute> extAttributes;

	public Element(Attribute value) {
		this.value = value;
	}

	public int getSeqNum() {
		return value.getSeqNum();
	}

	public String getName() {
		return value.getName();
	}

	public String getDescription() {
		return value.getDescription();
	}

	public AttributeRawTypes getType() {
		return value.getType();
	}

	public Object getValue() throws ParseException {
		return value.getValue();
	}

	public List<StringAttribute> getExtAttributes() {
		return extAttributes;
	}

	public void setExtAttributes(List<StringAttribute> extAttributes) {
		this.extAttributes = extAttributes;
	}
}