package com.geopack.ui.components;

import javax.swing.*;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 18:12:19
 */

public class ComponentFactory {

	public static JTextField createTextField() {
		return new  JTextField(15);
	}

	public static JDateTimeChooser createDateTimeChooser() {
		return new JDateTimeChooser(false); 
	}

//	public JSpinner createIntegerField() {
//		return null;
////		return new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE));
//	}

}
