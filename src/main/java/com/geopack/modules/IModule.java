package com.geopack.modules;

import javax.swing.*;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 13:59:15
 */

/**
 * ����������, ������� ������ ������������� ��� ������ �������
 */
public interface IModule {
	/**
	 * ��������� ��� ������
	 * @return
	 */
	String getName();

	/**
	 * ���������� ������ ����������� ������ ������
	 * @return
	 */
	JPanel getContentPanel();

	/**
	 * ������ �������� ApplicationParams.getInstance().registerModule(this)
	 */
	void registerInModuleList();
}
