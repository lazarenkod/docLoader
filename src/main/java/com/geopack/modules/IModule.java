package com.geopack.modules;

import javax.swing.*;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 13:59:15
 */

/**
 * Интрерфейс, который должны реализовывать все модули системы
 */
public interface IModule {
	/**
	 * Системное имя модуля
	 * @return
	 */
	String getName();

	/**
	 * Возвращает панель содержимого панели модуля
	 * @return
	 */
	JPanel getContentPanel();

	/**
	 * Должен вызывать ApplicationParams.getInstance().registerModule(this)
	 */
	void registerInModuleList();
}
