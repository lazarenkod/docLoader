package com.geopack.models.attributes;

import java.text.DecimalFormat;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class IntegerAttribute extends Attribute<DecimalFormat> {

	protected IntegerAttribute(int id, int seqNum, String name) {
		super(id, seqNum, name, AttributeRawTypes.INT, new DecimalFormat());
		format.setParseIntegerOnly(true);
	}
}
