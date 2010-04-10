/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geopack.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pavel.shatrov
 */
public class JStatusPanel extends JPanel {

    private JLabel status = new JLabel("статусная строка");
    public void setStatus(String s)
    {
        status.setText(s);
    }
    public JStatusPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(status);
        setPreferredSize(new Dimension(getWidth( ), 24));
        
    }


}
