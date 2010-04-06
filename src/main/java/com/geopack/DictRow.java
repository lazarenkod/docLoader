package com.geopack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 15:18:45
 */
public class DictRow {
	private List<DictData> data=new ArrayList<DictData>();

	public List<DictData> getData() {
		return Collections.unmodifiableList(data);
	}

	public DictRow(List<DictData> data) {
		this.data = data;
	}

    public DictData getData(int columnIndex) {
          return data.get(columnIndex);
    }

	@Override
	public String toString() {
		return "DictRow{" +
		       "data=" + data +
		       '}';
	}
}
