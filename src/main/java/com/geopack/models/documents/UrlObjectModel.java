package com.geopack.models.documents;

import java.text.ParseException;

/**
 * User: Mostovoy.Vladislav
 * Date: 08.04.2010
 */
public class UrlObjectModel extends ObjectModel {

	protected UrlObjectModel() {
		super();
	}

	@Override
	public Object getValue() throws ParseException {
		/*if (format == null)
			return rawValue;

		return format.parseObject(rawValue);*/
		return null;
	}
}