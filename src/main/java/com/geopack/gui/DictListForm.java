/*
 * Created by JFormDesigner on Fri Apr 09 20:01:45 MSD 2010
 */

package com.geopack.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Nick Tate
 */
public class DictListForm extends JPanel {
	public DictListForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Nick Tate
		scrollPane1 = new JScrollPane();
		table1 = new JTable();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new BorderLayout());

		//======== scrollPane1 ========
		{

			//---- table1 ----
			table1.setModel(new DefaultTableModel(
				new Object[][] {
					{"936", "88195,40 ", "201212,4", "1113,2", "111,0 ", null, "58,8"},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"\u0421\u043a\u0432\u0430\u0436\u0438\u043d\u0430 ", "X", "Y", "Z", "\u0413\u043b\u0443\u0431\u0438\u043d\u0430 ", "\u0417\u043e\u043d\u0430 ", "\u041f\u0440\u043e\u0444\u0438\u043b\u044c "
				}
			));
			scrollPane1.setViewportView(table1);
		}
		add(scrollPane1, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Nick Tate
	private JScrollPane scrollPane1;
	private JTable table1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
