/*
 * Created by JFormDesigner on Fri Apr 09 19:53:30 MSD 2010
 */

package com.geopack.gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.swingx.*;

/**
 * @author Nick Tate
 */
public class DocEditDialog extends JDialog {
	public DocEditDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public DocEditDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nick Tate
        ResourceBundle bundle = ResourceBundle.getBundle("com.geopack.gui.lang");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        label1 = new JLabel();
        textArea1 = new JTextArea();
        label6 = new JLabel();
        textArea2 = new JTextArea();
        button1 = new JButton();
        label3 = new JLabel();
        textField1 = new JTextField();
        label4 = new JLabel();
        xDatePicker1 = new JXDatePicker();
        xDatePicker1.setDate(new Date());
        label5 = new JLabel();
        textArea3 = new JTextArea();
        button2 = new JButton();
        buttonBar2 = new JPanel();
        okButton2 = new JButton();
        cancelButton2 = new JButton();

        //======== this ========
        setTitle(bundle.getString("DocEditDialog.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new GridBagLayout());
                    ((GridBagLayout)contentPanel2.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
                    ((GridBagLayout)contentPanel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout)contentPanel2.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
                    ((GridBagLayout)contentPanel2.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0E-4};

                    //---- label1 ----
                    label1.setText(bundle.getString("DocEditDialog.label1.text"));
                    contentPanel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- textArea1 ----
                    textArea1.setRows(2);
                    textArea1.setColumns(40);
                    textArea1.setText("\u0441\u043a\u0432\u0430\u0436\u0438\u043d\u0430, \u043b\u0443\u043d\u043d\u043e\u0435 \u043c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0435, \u0434\u043e\u0431\u044b\u0447\u0430");
                    contentPanel2.add(textArea1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- label6 ----
                    label6.setText(bundle.getString("DocEditDialog.label6.text"));
                    contentPanel2.add(label6, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- textArea2 ----
                    textArea2.setRows(2);
                    textArea2.setColumns(40);
                    textArea2.setToolTipText("\u0421\u043f\u0440\u0430\u0432\u043e\u0447\u043d\u0438\u043a \u043c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0439->\u041c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0435 \u041b\u0443\u043d\u043d\u043e\u0435 ");
                    textArea2.setText("\u0421\u043f\u0440\u0430\u0432\u043e\u0447\u043d\u0438\u043a \u043c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0439->\u041c\u0435\u0441\u0442\u043e\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u0435 \u041b\u0443\u043d\u043d\u043e\u0435 ");
                    contentPanel2.add(textArea2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- button1 ----
                    button1.setText(bundle.getString("DocEditDialog.button1.text"));
                    contentPanel2.add(button1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 0), 0, 0));

                    //---- label3 ----
                    label3.setText(bundle.getString("DocEditDialog.label3.text"));
                    contentPanel2.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- textField1 ----
                    textField1.setColumns(20);
                    textField1.setEnabled(false);
                    textField1.setText(bundle.getString("DocEditDialog.textField1.text"));
                    contentPanel2.add(textField1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- label4 ----
                    label4.setText(bundle.getString("DocEditDialog.label4.text"));
                    contentPanel2.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));
                    contentPanel2.add(xDatePicker1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- label5 ----
                    label5.setText(bundle.getString("DocEditDialog.label5.text"));
                    contentPanel2.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- textArea3 ----
                    textArea3.setRows(2);
                    textArea3.setColumns(40);
                    textArea3.setText("\u0421\u043a\u0430\u043d\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u044b\u0439 \u043e\u0442\u0447\u0435\u0442 \u043e \u0433\u0435\u043e\u043b\u043e\u0433\u043e\u0440\u0430\u0437\u0432\u0435\u0434\u043a\u0435 .doc");
                    textArea3.setEditable(false);
                    textArea3.setBorder(LineBorder.createBlackLineBorder());
                    contentPanel2.add(textArea3, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 10), 0, 0));

                    //---- button2 ----
                    button2.setText(bundle.getString("DocEditDialog.button2.text"));
                    contentPanel2.add(button2, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 0), 0, 0));
                }
                contentPanel.add(contentPanel2, BorderLayout.CENTER);

                //======== buttonBar2 ========
                {
                    buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar2.setLayout(new GridBagLayout());
                    ((GridBagLayout)buttonBar2.getLayout()).columnWidths = new int[] {0, 85, 80};
                    ((GridBagLayout)buttonBar2.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                    //---- okButton2 ----
                    okButton2.setText(bundle.getString("DocEditDialog.okButton2.text"));
                    buttonBar2.add(okButton2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- cancelButton2 ----
                    cancelButton2.setText(bundle.getString("DocEditDialog.cancelButton2.text"));
                    buttonBar2.add(cancelButton2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                contentPanel.add(buttonBar2, BorderLayout.SOUTH);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nick Tate
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextArea textArea1;
    private JLabel label6;
    private JTextArea textArea2;
    private JButton button1;
    private JLabel label3;
    private JTextField textField1;
    private JLabel label4;
    private JXDatePicker xDatePicker1;
    private JLabel label5;
    private JTextArea textArea3;
    private JButton button2;
    private JPanel buttonBar2;
    private JButton okButton2;
    private JButton cancelButton2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
