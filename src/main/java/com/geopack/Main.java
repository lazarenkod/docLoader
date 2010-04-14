/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geopack;

import com.geopack.dataloader.LayoutTabLoader;
import com.geopack.dataloader.PresentationTabLoader;
import com.geopack.gui.LoginDialog;
import com.jidesoft.plaf.LookAndFeelFactory;

import javax.swing.*;

/**
 * @author pavel.shatrov
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    try {

		LookAndFeelFactory.setDefaultStyle(LookAndFeelFactory.OFFICE2003_STYLE);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		LookAndFeelFactory.installJideExtension();
	    } catch (UnsupportedLookAndFeelException e) {
		    e.printStackTrace();
	    } catch (ClassNotFoundException e) {
		    e.printStackTrace();
	    } catch (InstantiationException e) {
		    e.printStackTrace();
	    } catch (IllegalAccessException e) {
		    e.printStackTrace();
	    }

	    LayoutTabLoader tabLoader=new LayoutTabLoader();
        tabLoader.loadLayouts();
        PresentationTabLoader presentationTabLoader=new PresentationTabLoader();
        presentationTabLoader.loadModulesSlides();
        LoginDialog loginDialog=new LoginDialog();
        loginDialog.setVisible(true);
    }

}
