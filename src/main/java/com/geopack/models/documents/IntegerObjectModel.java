package com.geopack.models.documents;

import java.text.DecimalFormat;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class IntegerObjectModel extends ObjectModel {

	protected IntegerObjectModel() {
		super(ObjectValueTypes.INT, new DecimalFormat());
		((DecimalFormat) format).setParseIntegerOnly(true);
	}
}
