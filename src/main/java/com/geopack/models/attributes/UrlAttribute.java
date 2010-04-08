package com.geopack.models.attributes;

import java.text.Format;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class UrlAttribute extends Attribute<Format> {

	protected UrlAttribute(int id, int seqNum, String name) {
		super(id, seqNum, name, AttributeRawTypes.URL, null);
	}
}