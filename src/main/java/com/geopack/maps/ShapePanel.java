/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geopack.maps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.swing.JMapPane;
import org.geotools.swing.StatusBar;
import org.geotools.swing.action.PanAction;
import org.geotools.swing.action.ResetAction;
import org.geotools.swing.action.ZoomInAction;
import org.geotools.swing.action.ZoomOutAction;
import org.geotools.swing.event.MapMouseAdapter;
import org.geotools.swing.event.MapMouseEvent;

/**
 *
 * @author pavel.shatrov
 */
public class ShapePanel extends JPanel
{
    private JMapPane mapPane;
    private JLabel coordsLabel = new JLabel();

    public ShapePanel(JMapPane m)
    {
        mapPane = m;

        StatusBarEx bar = new StatusBarEx(m);

        setLayout(new BorderLayout());

        // toolbar
        JToolBar tool = new JToolBar();
        tool.setFloatable(false);

        JButton zoomInBtn = new JButton(new ZoomInAction(mapPane));
        tool.add(zoomInBtn);
        JButton zoomOutBtn = new JButton(new ZoomOutAction(mapPane));
        tool.add(zoomOutBtn);
        JButton panBtn = new JButton(new PanAction(mapPane));
        tool.add(panBtn);
        JButton resetBtn = new JButton(new ResetAction(mapPane));
        tool.add(resetBtn);

        add(tool, BorderLayout.NORTH);
        add(mapPane, BorderLayout.CENTER);

        JPanel wrap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrap.add(coordsLabel);
        add(bar, BorderLayout.SOUTH);
    }
}
