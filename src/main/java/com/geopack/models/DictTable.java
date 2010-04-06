package com.geopack.models;

import com.geopack.utils.TableCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 15:01:10
 */

/**
 * Таблица данных справочника
 */
public class DictTable {

	private DictTableModel model;
	private List<DictRow> rows = new ArrayList<DictRow>();

	public DictTable(DictTableModel model) {
		this.model = model;
	}

	public DictTableModel getModel() {
		return model;
	}

	public List<DictRow> getRows() {
		return Collections.unmodifiableList(rows);
	}

	public DictRow getRow(int index) {
		return rows.get(index);
	}

	public void addRow(DictRow row) {
		rows.add(row);
	}

	public void addRows(List<DictRow> rows) {
		this.rows.addAll(rows);
	}

	public void setRowAt(int index, DictRow row) {
		rows.set(index, row);
	}

	public void deleteRow(DictRow row) {
		rows.remove(row);
	}

	public void deleteRow(int index) {
		rows.remove(index);
	}

	public DictData getValue(int rowIndex, int columnIndex) {
		return rows.get(rowIndex).getDataList().get(columnIndex);
	}

	public DictData getValue(int rowIndex, String columnName) {
		return getValue(rowIndex, model.getColumnIndex(columnName));
	}

	/**
	 * @param rowIndex    номер сточки в справочнике
	 * @param columnIndex номер столбца в справочнике
	 * @return Рашифрованное значение записи
	 */
	public Object getDecodedValue(int rowIndex, int columnIndex) {
		DictData data = getValue(rowIndex, columnIndex);
		final DictColumnModel columnModel = getColumnModel(columnIndex);
		Object value = data.getValue(columnModel);
		if (value instanceof DictRow) {
			final DictRow srcRow = (DictRow) value;
			DictTable srcTable = TableCache.getInstance().get(columnModel.getSrcTableName());
			final int sourceViewColumnIndex = srcTable.getModel().getColumnIndex(columnModel.getSrcTableColumnViewName());
			return srcRow.getData(sourceViewColumnIndex).getRawData();
		} else {
			return value;
		}
	}

	public Object getDecodedValue(int rowIndex, String columnName) {
		return getDecodedValue(rowIndex, model.getColumnIndex(columnName));
	}

	public DictColumnModel getColumnModel(int columnIndex) {
		return model.getColumnModels().get(columnIndex);
	}

	public DictColumnModel getColumnModel(String columnName) {
		return model.getColumnModel(columnName);
	}

	public String getName() {
		return model.getName();
	}

	public int getRowCount() {
		return rows.size();
	}
}
