/*
 * Created by JFormDesigner on Fri Apr 09 12:56:17 MSD 2010
 */

package com.geopack.gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author Nick Tate
 */
public class MainForm extends JFrame {
	public MainForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Nick Tate
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menu2 = new JMenu();
		menu3 = new JMenu();
		menu4 = new JMenu();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("\u0424\u0430\u0439\u043b");
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("\u0418\u043d\u0441\u0442\u0440\u0443\u043c\u0435\u043d\u0442\u044b");
			}
			menuBar1.add(menu2);

			//======== menu3 ========
			{
				menu3.setText("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438");
			}
			menuBar1.add(menu3);

			//======== menu4 ========
			{
				menu4.setText("\u041f\u043e\u043c\u043e\u0449\u044c");
			}
			menuBar1.add(menu4);
		}
		setJMenuBar(menuBar1);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Nick Tate
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenu menu2;
	private JMenu menu3;
	private JMenu menu4;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
