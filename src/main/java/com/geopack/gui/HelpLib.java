package com.geopack.gui;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public class HelpLib {
    public static void setGroupAlignmentX(JComponent[] cs, float alignment) {
        for (int i = 0; i < cs.length; i++) {
            cs[i].setAlignmentX(alignment);
        }
    }

    public static void setGroupAlignmentY(JComponent[] cs, float alignment) {
        for (int i = 0; i < cs.length; i++) {
            cs[i].setAlignmentY(alignment);
        }
    }

    public static JPanel createVerticalPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        return p;
    }

    public static JPanel createHorizontalPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        return p;
    }

    public static void  createRecommendedMargin(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            Insets margin = buttons[i].getMargin();
            margin.left = 12;
            margin.right = 12;
            buttons[i].setMargin(margin);
        }
    }

    public static void makeSameSize(JComponent[] components) {
        int[] sizes = new int[components.length];
        int[] sizes2 = new int[components.length];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = components[i].getPreferredSize().width;
            sizes2[i] = components[i].getPreferredSize().height;
        }

        int maxSizePos = maximumElementPosition(sizes);
        int maxSizePos2 = maximumElementPosition(sizes);
        Dimension maxSize = components[maxSizePos].getPreferredSize();
        maxSize.setSize(components[maxSizePos].getPreferredSize().width, components[maxSizePos2].getPreferredSize().height);
        //System.out.println("PREF: " + maxSize.height + " " + maxSize.width);

        for (int i = 0; i < components.length; i++) {
            //maxSize.height = components[i].getPreferredSize().height;
            components[i].setPreferredSize(maxSize);
            //maxSize.height = components[i].getMinimumSize().height;
            //components[i].setMinimumSize(maxSize);
            //maxSize.height = components[i].getMaximumSize().height;
            components[i].setMaximumSize(maxSize);
        }
    }

    public static void fixTextFieldSize(JTextField field) {
        Dimension size = field.getPreferredSize();
        size.width = field.getMaximumSize().width;
        field.setMaximumSize(size);
    }

    public static void fixTextFieldSize2(JTextField field) {
        Dimension size = field.getPreferredSize();
        size.height = field.getMaximumSize().height;
        field.setMaximumSize(size);
    }

    private static int maximumElementPosition(int[] array) {
        int maxPos = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxPos]) maxPos = i;
        }
        return maxPos;
    }
}