package com.geopack.models.attributes;

import java.text.Format;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class StringAttribute extends Attribute<Format> {

	public StringAttribute(int id, int seqNum, String name) {
		super(id, seqNum, name, AttributeRawTypes.STRING, null);
	}
}