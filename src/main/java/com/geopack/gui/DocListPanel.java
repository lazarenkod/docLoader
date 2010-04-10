/*
 * Created by JFormDesigner on Fri Apr 09 18:10:14 MSD 2010
 */

package com.geopack.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * @author Nick Tate
 */
public class DocListPanel extends JPanel {
	public DocListPanel() {
		initComponents();
	}

	private void docListTableMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nick Tate
        scrollPane1 = new JScrollPane();
        docListTable = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(450, 418));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- docListTable ----
            docListTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {"\u041c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0435 \u043b\u0443\u043d\u043d\u043e\u0435", "\u0418\u0432\u0430\u043d\u043e\u0432", new Date(1269118800000L) /* 21.03.2010 0:00:00 */, "\u041b\u0443\u043d\u043d\u043e\u0435, \u041c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0435, \u041f\u043e\u0438\u0441\u043a", 1, 3},
                    {"\u041c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0435 \u043a\u0438\u0435\u0432\u0441\u043a\u043e\u0435", "\u0412\u0430\u0441\u0438\u043b\u044c\u0435\u0432", new Date(1269464400000L) /* 25.03.2010 0:00:00 */, null, 10, 0},
                },
                new String[] {
                    "\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435", "\u0421\u043e\u0437\u0434\u0430\u0442\u0435\u043b\u044c", "\u0414\u0430\u0442\u0430", "\u0410\u0442\u0440\u0438\u0431\u0443\u0442\u044b", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0441\u0442\u0440\u0430\u043d\u0438\u0446", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0432\u043b\u043e\u0436\u0435\u043d\u0438\u0439"
                }
            ) {
                Class[] columnTypes = new Class[] {
                    Object.class, Object.class, Date.class, Object.class, Integer.class, Integer.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = docListTable.getColumnModel();
                cm.getColumn(0).setMinWidth(150);
                cm.getColumn(1).setMinWidth(100);
                cm.getColumn(3).setMinWidth(100);
                cm.getColumn(4).setMinWidth(100);
                cm.getColumn(5).setMinWidth(100);
            }
            docListTable.setPreferredSize(new Dimension(565, 48));
            docListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            docListTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    docListTableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(docListTable);
        }
        add(scrollPane1, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nick Tate
    private JScrollPane scrollPane1;
    private JTable docListTable;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
