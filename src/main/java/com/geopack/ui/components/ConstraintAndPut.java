package com.geopack.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 24.07.2006
 * Time: 15:42:35
 */
public class ConstraintAndPut {
    private static final Dimension minS = new Dimension(19, 19);

    public static void doIt(Component cmp, Container jpnl, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty,
                            int anchor, int fill, Insets insets, int ipadx, int ipady, GridBagConstraints gbc) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = insets;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        putInPanel(cmp, jpnl, gbc);
    }

    public static void doIt(ComponentWithLabel cmp, Container jpnl, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty,
                            int anchor, int fill, Insets insets, int ipadx, int ipady, GridBagConstraints gbc) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = insets;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        putInPanel(cmp.getComponent(gbc.weightx, gbc.weighty, gbc.fill), jpnl, gbc);
    }


    private static void putInPanel(Component cmp, Container jpnl, GridBagConstraints gbc) {
        GridBagLayout gbln = (GridBagLayout) jpnl.getLayout();
        gbln.setConstraints(cmp, gbc);
        jpnl.add(cmp);
    }


    public static class ComponentWithLabel {
        private JLabel label;
        private JComponent comp;
        private boolean vertical;
        private int compFill = -1;

        public ComponentWithLabel(String labelText, JComponent comp, boolean vertical) {
            label = new JLabel(labelText);
            this.comp = comp;
            this.comp.setName(labelText);
            label.setMinimumSize(minS);
            this.comp.setMinimumSize(minS);
            this.vertical = vertical;
        }

        public ComponentWithLabel(JLabel label, JComponent comp, boolean vertical) {
            this.label = label;
            this.comp = comp;
            this.comp.setName(label.getText());
            label.setMinimumSize(minS);
            this.comp.setMinimumSize(minS);
            this.vertical = vertical;
        }

        public ComponentWithLabel(String labelText, JComponent comp, boolean vertical, int compFill) {
            label = new JLabel(labelText);
            this.comp = comp;
            this.comp.setName(labelText);
            label.setMinimumSize(minS);
            this.comp.setMinimumSize(minS);
            this.vertical = vertical;
            this.compFill = compFill;
        }

        public JComponent getComponent(double weightx, double weighty, int fill) {
            FontMetrics fm = label.getFontMetrics(label.getFont());
			int fontHeight = fm.getHeight();
            label.setMinimumSize(new Dimension(fm.stringWidth(label.getText() + " "), fontHeight));
            JPanel pnl = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            int lbl_anchor, comp_anchor, comp_gridx, comp_gridy;
            if (vertical) {
                lbl_anchor = GridBagConstraints.SOUTHWEST;
                comp_anchor = GridBagConstraints.NORTHWEST;
                comp_gridx = 0; comp_gridy = 1 ;
            } else {
                lbl_anchor = GridBagConstraints.EAST;
                comp_anchor = GridBagConstraints.WEST;
                comp_gridx = 1; comp_gridy = 0;
            }
            if (compFill==-1) compFill = fill;
            doIt(label, pnl, 0, 0, 1, 1, 1.0, 1.0, lbl_anchor, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0, constraints);
            doIt(comp, pnl, comp_gridx, comp_gridy, 1, 1, weightx, weighty, comp_anchor, compFill, new Insets(0, 0, 0, 0), 0, 0, constraints);
            return pnl;
        }
    }
}
