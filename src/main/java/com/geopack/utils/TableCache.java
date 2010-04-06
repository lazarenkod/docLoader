package com.geopack.utils;

import com.geopack.models.DictTable;

import java.util.*;

/**
 * User: Lazarenko.Dmitry
 * Date: 05.04.2010
 * Time: 17:21:59
 */

/**
 * Кэш таблиц данных
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

	public List<DictTable> getAll() {
		return Collections.unmodifiableList(new ArrayList<DictTable>(cache.values()));

	}

	public static TableCache getInstance() {
		if (instance == null)
			instance = new TableCache();
		return instance;
	}
}
