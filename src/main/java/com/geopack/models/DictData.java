package com.geopack.models;

import com.geopack.utils.TableCache;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 16:45:24
 */
public class DictData {
	private Object rawData;

	public DictData(Object rawData) {
		this.rawData = rawData;
	}

	/**
	 *
	 * @param columnModel
	 * @return «начение в €чейке таблицы, либо ссылку на строку в другой таблице,
	 * котора€ €вл€етс€ расшифровкой данного значени€
	 */
	public Object getValue(DictColumnModel columnModel) {
		if (columnModel.isKeyField()) {
			DictTable srcTable = TableCache.getInstance().get(columnModel.getSrcTableName());
			for (int i = 0; i < srcTable.getRowCount(); i++) {
				if (srcTable.getValue(i, columnModel.getSrcTableColumsName()).equals(rawData))
					return srcTable.getRow(i);
			}
			return null;

		} else {
			return rawData;
		}
	}

	public Object getRawData() {
		return rawData;
	}

	public void setRawData(Object rawData) {
		this.rawData = rawData;
	}


	@Override
	public String toString() {
		return rawData.toString();
	}


	
}
