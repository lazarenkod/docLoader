package com.geopack.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 15:18:45
 */
public class DictRow {
	private List<DictData> dataList = new ArrayList<DictData>();

	public List<DictData> getDataList() {
		return Collections.unmodifiableList(dataList);
	}

	public DictRow() {

	}

    public String[] toArray() {
        String[] result=new String[dataList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i]=dataList.get(i).getRawData().toString();
        }
        return result;
    }

	public void addData(DictData data) {
		dataList.add(data);
	}

	public DictData getData(int columnIndex) {
		return dataList.get(columnIndex);
	}

	@Override
	public String toString() {
		return "DictRow{" +
		       "dataList=" + dataList +
		       '}';
	}
}
