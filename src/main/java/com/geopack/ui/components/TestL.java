/*
 * Created by JFormDesigner on Thu Apr 08 14:49:46 MSD 2010
 */

package com.geopack.ui.components;

import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;

/**
 * @author Nick Tate
 */
public class TestL extends JPanel {
	public TestL() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Nick Tate
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		panel2 = new JPanel();
		label2 = new JLabel();
		comboBox1 = new JComboBox();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new VerticalLayout(10));

		//======== panel1 ========
		{
			panel1.setLayout(new HorizontalLayout(10));

			//---- label1 ----
			label1.setText("textdf");
			panel1.add(label1);

			//---- textField1 ----
			textField1.setColumns(15);
			panel1.add(textField1);
		}
		add(panel1);

		//======== panel2 ========
		{
			panel2.setLayout(new HorizontalLayout(10));

			//---- label2 ----
			label2.setText("text");
			panel2.add(label2);
			panel2.add(comboBox1);
		}
		add(panel2);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Nick Tate
	private JPanel panel1;
	private JLabel label1;
	private JTextField textField1;
	private JPanel panel2;
	private JLabel label2;
	private JComboBox comboBox1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
