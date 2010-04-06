package com.geopack.utils;

import com.geopack.models.DictTable;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 17:21:59
 */
public class TableCache {
	private static TableCache instance;

	private Map<String, DictTable> cache = new HashMap<String, DictTable>();

	public void put(DictTable table) {
		cache.put(table.getName(), table);
	}

	public DictTable get(String tableName) {
		return cache.get(tableName);
	}

	public static TableCache getInstance() {
		if (instance == null)
			instance = new TableCache();
		return instance;
	}
}
