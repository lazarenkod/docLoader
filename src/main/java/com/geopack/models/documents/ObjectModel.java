package com.geopack.models.documents;

import java.text.Format;
import java.text.ParseException;

/**
 * User: Mostovoy.Vladislav
 * Date: 09.04.2010
 */
public class ObjectModel extends ElementModel {

	private String name;
	private ObjectTypes type;

	private ObjectValueTypes valueType;
	private String rawValue;
	protected Format format;

	private String style;

	public ObjectModel() {
		this.type = ObjectTypes.VALUE;
	}

	public ObjectModel(ObjectValueTypes valueType, Format format) {
		this();
		this.valueType = valueType;
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectValueTypes getValueType() {
		return valueType;
	}

	public void setValueType(ObjectValueTypes valueType) {
		this.valueType = valueType;
	}

	public String getRawValue() {
		return rawValue;
	}

	public void setRawValue(String rawValue) {
		this.rawValue = rawValue;
	}


	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Object getValue() throws ParseException {
		if (format == null)
			return rawValue;

		return format.parseObject(rawValue);
	}
}