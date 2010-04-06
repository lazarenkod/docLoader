package com.geopack.init;

import com.geopack.dataloader.DataLoader;
import com.geopack.dataloader.SchemeLoader;
import com.geopack.models.DictRow;
import com.geopack.models.DictTable;
import com.geopack.utils.TableCache;

import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 17:38:01
 */

/**
 * Класс-инициализатор приложения
 * вызывает загрузку схемы базы и загрузку данных в таблицы
 */
public class AppInit {
	public AppInit() {
	}

	public void doInit() {
		SchemeLoader sl = new SchemeLoader();
		sl.loadSchemes();
		DataLoader dataLoader = new DataLoader();
		final List<DictTable> tables = TableCache.getInstance().getAll();
		for (DictTable table : tables) {
			final List<DictRow> rows = dataLoader.readDataFile(table.getModel());
			table.addRows(rows);
		}
	}
}
