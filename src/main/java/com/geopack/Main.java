/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geopack;

import com.geopack.gui.LoginDialog;

import javax.swing.*;

/** @author pavel.shatrov */
public class Main {

	/** @param args the command line arguments */
	public static void main(String[] args) {
		// TODO code application logic here
		try {

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException classNotFoundException) {
		} catch (InstantiationException instantiationException) {
		} catch (IllegalAccessException illegalAccessException) {
		} catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
		}
		LoginDialog.getInstance(true);
	}

}
