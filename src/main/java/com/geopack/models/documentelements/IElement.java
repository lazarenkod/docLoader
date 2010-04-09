package com.geopack.models.documentelements;

import com.geopack.models.attributes.AttributeRawTypes;

import java.text.ParseException;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public interface IElement {
	int getSeqNum();

	String getName();

	String getDescription();

	AttributeRawTypes getType();

	Object getValue() throws ParseException;
}
