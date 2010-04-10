/*
 * Created by JFormDesigner on Fri Apr 09 19:32:52 MSD 2010
 */

package com.geopack.gui;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Nick Tate
 */
public class DocCreateDialog extends JDialog {
	public DocCreateDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public DocCreateDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nick Tate
        ResourceBundle bundle = ResourceBundle.getBundle("com.geopack.gui.lang");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
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
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle(bundle.getString("DocCreateDialog.this.title"));
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
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText(bundle.getString("DocCreateDialog.label1.text"));
                contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- textArea1 ----
                textArea1.setRows(2);
                textArea1.setColumns(40);
                contentPanel.add(textArea1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- label6 ----
                label6.setText(bundle.getString("DocCreateDialog.label6.text"));
                contentPanel.add(label6, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- textArea2 ----
                textArea2.setRows(2);
                textArea2.setColumns(40);
                contentPanel.add(textArea2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- button1 ----
                button1.setText(bundle.getString("DocCreateDialog.button1.text"));
                contentPanel.add(button1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 0), 0, 0));

                //---- label3 ----
                label3.setText(bundle.getString("DocCreateDialog.label3.text"));
                contentPanel.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- textField1 ----
                textField1.setColumns(20);
                textField1.setEnabled(false);
                textField1.setText(bundle.getString("DocCreateDialog.textField1.text"));
                contentPanel.add(textField1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- label4 ----
                label4.setText(bundle.getString("DocCreateDialog.label4.text"));
                contentPanel.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));
                contentPanel.add(xDatePicker1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- label5 ----
                label5.setText(bundle.getString("DocCreateDialog.label5.text"));
                contentPanel.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- textArea3 ----
                textArea3.setRows(2);
                textArea3.setColumns(40);
                contentPanel.add(textArea3, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 10), 0, 0));

                //---- button2 ----
                button2.setText(bundle.getString("DocCreateDialog.button2.text"));
                contentPanel.add(button2, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 10, 0), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText(bundle.getString("DocCreateDialog.okButton.text"));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText(bundle.getString("DocCreateDialog.cancelButton.text"));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
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
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
