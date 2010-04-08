package com.geopack.models.attributes;

import java.text.Format;
import java.text.ParseException;

public class Attribute<F extends Format> {

	private int id;
	private int seqNum;

	private String name;
	private String description;

	private AttributeRawTypes type;

	private String rawValue;

	protected F format;

	protected Attribute(int id, int seqNum, String name,
	                    AttributeRawTypes type, F format) {
		this.id = id;
		this.seqNum = seqNum;

		this.name = name;

		this.type = type;
		this.format = format;
	}

	public int getId() {
		return id;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AttributeRawTypes getType() {
		return type;
	}

	public String getRawValue() {
		return rawValue;
	}

	public void setRawValue(String rawValue) {
		this.rawValue = rawValue;
	}

	public Object getValue() throws ParseException {
		if (format == null)
			return rawValue;

		return format.parseObject(rawValue);
	}
}