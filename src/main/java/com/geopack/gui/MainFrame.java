/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geopack.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

/**
 *
 * @author pavel.shatrov
 */
public class MainFrame extends JFrame
{

    private Object [] classifikator = new Object[] {"Класс","Класс2","Класс3"};
    private Object [] documents = new Object[] {"Документ1","Документ2","Документ3"};

    public MainFrame()
    {
        setTitle("ГеоПАК - Руководитель");
        createGUI();
    }

    private void createGUI()
    {
        //addWindowListener(new WindowAdapter() {});
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(0,5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12,12,0,12));
        add(mainPanel);

        // Главный тулбар
        JToolBar mainToolBar = new JToolBar();
        mainToolBar.add(new JButton("Файл"));
        mainToolBar.add(new JButton("Инструменты"));
        mainToolBar.add(new JButton("Настройки"));
        mainToolBar.add(new JButton("Помощь"));
        mainToolBar.setFloatable(false);
        Dimension d = mainToolBar.getMaximumSize();
        d.height = mainToolBar.getPreferredSize().height;
        mainToolBar.setMaximumSize(d);

        mainPanel.add(mainToolBar, BorderLayout.NORTH);
        

        JPanel mainFrame = HelpLib.createVerticalPanel();
        mainPanel.add(mainFrame, BorderLayout.CENTER);


        JTabbedPane tabs = new JTabbedPane();
        mainFrame.add(tabs);

  
        tabs.addTab("Проекты", createProjectPanel());

        tabs.addTab("Поиск данных", new JButton("search"));
        tabs.addTab("Добавление данных", new JButton("data"));

        // Статусная строка
        mainPanel.add(new JStatusPanel(), BorderLayout.SOUTH);

        pack();
        setSize(800, getSize().height);
        setLocation(getGraphicsConfiguration().getBounds().width/2 - getBounds().width/2, getGraphicsConfiguration().getBounds().height/2 - getSize().height/2);
        setVisible(true);
    }

    private JPanel createProjectPanel()
    {
              // панель проектов
        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BorderLayout());
        // панель инструментов
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorderPainted(false);
        toolBar.setRollover(true);
        toolBar.add(new JButton("Создать"));
        toolBar.add(new JButton("Редактировать"));
        projectPanel.add(toolBar, BorderLayout.NORTH);

        //
        JSplitPane split = new JSplitPane();
        split.setResizeWeight(0.2);

        // Дерево
        JTree tr = new JTree(classifikator);
        split.setLeftComponent(new JScrollPane(tr));

        // Основное окно
        JPanel windPanel = new JPanel(new BorderLayout());
        JList docsList = new JList(documents);
        windPanel.add(new JScrollPane(docsList), BorderLayout.CENTER);

        // Дополнительная панель
        JList assDocsList = new JList(documents);
        windPanel.add(new JScrollPane(assDocsList), BorderLayout.EAST);

        split.setRightComponent(windPanel);

        projectPanel.add(split, BorderLayout.CENTER);
        
        return projectPanel;
    }

}
