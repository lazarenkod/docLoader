/*
 * Created by JFormDesigner on Fri Apr 09 12:56:17 MSD 2010
 */

package com.geopack.gui;

import com.geopack.maps.ShapeFiles;
import com.geopack.maps.ShapePanel;
import com.geopack.tabs.LayoutTab;
import com.geopack.tabs.PresentationTab;
import com.geopack.utils.ApplicationParams;
import org.geotools.swing.JMapPane;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 * @author Nick Tate
 */
public class MainForm extends JFrame {
    public MainForm() {
        initComponents();
        setLayoutTabs(ApplicationParams.getInstance().getSelectedLayout().getTabList());
        addDocumentTree();

    }

    private void addNewTab(String tabName, Component component) {
        for (int i = 0; i < mainTabbedPane.getTabCount(); i++) {
            if (mainTabbedPane.getTitleAt(i).equals(tabName)) {
                mainTabbedPane.setSelectedComponent(mainTabbedPane.getComponentAt(i));
                return;
            }
        }
        mainTabbedPane.addTab(tabName, component);
        mainTabbedPane.setSelectedComponent(component);
    }

    public void setLayoutTabs(List<LayoutTab> layoutTabs) {
        for (int i = 0, layoutTabsSize = layoutTabs.size(); i < layoutTabsSize; i++) {
            LayoutTab layoutTab = layoutTabs.get(i);
            sphereTabs.addTab(layoutTab.getName(), new LayoutTabPanel(layoutTab));
            sphereTabs.setToolTipTextAt(i, layoutTab.getHint());
            sphereTabs.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                }
            });
        }
    }

    public void setTabSlideshow(List<PresentationTab> presentationTabs) {

    }

    private JPanel addDocListPanel(String docName) {
        DocListPanel panel = new DocListPanel();
        addNewTab(docName, panel);
        return panel;
    }

    private JPanel addDicListPanel(String docName) {
        DictListForm panel = new DictListForm();
        addNewTab(docName, panel);
        return panel;
    }

    private ShapePanel addShapePanel(final String docName) {
        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                return new ShapeFiles();

            }

            @Override
            protected void done() {
                try {
                    ShapePanel shapePanel = new ShapePanel((JMapPane) get());
                    addNewTab(docName, shapePanel);
                    progressLabel.setBusy(false);
                    progressLabel.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ExecutionException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        };
        progressLabel.setText("Идет загрузка данных");
        progressLabel.setBusy(true);
        sw.execute();
        return null;
    }

    private void addDocumentTree() {
        navigationTree.setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode("(root)") {
                    {
                        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("Документы");
                        final DefaultMutableTreeNode doc1 = new DefaultMutableTreeNode("Первичные документы");
                        treeNode.add(doc1);
                        final DefaultMutableTreeNode doc2 = new DefaultMutableTreeNode("Вторичные документы");
                        treeNode.add(doc2);
                        add(treeNode);
                        treeNode = new DefaultMutableTreeNode("Справочники и классификаторы");
                        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Общие");
                        final DefaultMutableTreeNode dictKtad = new DefaultMutableTreeNode("Классификатор территориально-административного деления");
                        node2.add(dictKtad);
                        final DefaultMutableTreeNode dictOrg = new DefaultMutableTreeNode("Справочник организаций");
                        node2.add(dictOrg);
                        final DefaultMutableTreeNode dictSource = new DefaultMutableTreeNode("Справочник месторождений");
                        node2.add(dictSource);
                        final DefaultMutableTreeNode dictSourceZone = new DefaultMutableTreeNode("Справочник зон месторождений");
                        node2.add(dictSourceZone);
                        final DefaultMutableTreeNode dictTeritory = new DefaultMutableTreeNode("Справочник территорий");
                        node2.add(dictTeritory);
                        final DefaultMutableTreeNode dictGeologObjects = new DefaultMutableTreeNode("Справочник геологических объектов");
                        node2.add(dictGeologObjects);
                        treeNode.add(node2);
                        node2 = new DefaultMutableTreeNode("Прикладные");
                        final DefaultMutableTreeNode dictSkvag = new DefaultMutableTreeNode("Справочник скважин");
                        node2.add(dictSkvag);
                        final DefaultMutableTreeNode dictInclometry = new DefaultMutableTreeNode("Справочник инклинометрии скважин");
                        node2.add(dictInclometry);
                        treeNode.add(node2);
                        add(treeNode);
                        treeNode = new DefaultMutableTreeNode("Отчеты");
                        treeNode.add(new DefaultMutableTreeNode("Отчет о геологической экспедиции"));
                        add(treeNode);
                        treeNode = new DefaultMutableTreeNode("Карты");
                        treeNode.add(new DefaultMutableTreeNode("Карта 1"));
                        add(treeNode);
                    }
                }));

        navigationTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                final DefaultMutableTreeNode selectedPath = (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();
                if (selectedPath != null) {
                    final Object nodeText = selectedPath.getUserObject();
                    statusLabel.setText("Текущий документ: " + nodeText);
                    if (nodeText.equals("Первичные документы") || nodeText.equals("Вторичные документы")) {
                        addNewButton.setEnabled(true);
                        editButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        linkPanel.setVisible(true);
                        addDocListPanel(nodeText.toString());
                    }
                    if (nodeText.equals("Справочник организаций")) {
                        addNewButton.setEnabled(true);
                        editButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        addDicListPanel(nodeText.toString());
                    }
                    if (nodeText.equals("Карта 1")) {
                        addShapePanel(nodeText.toString());
                    }
                }

            }
        });
    }

    private void exitMenuItemActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void addNewButtonActionPerformed(ActionEvent e) {
        final DefaultMutableTreeNode selectedPath = (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();
        if (selectedPath != null) {
            final Object nodeText = selectedPath.getUserObject();
            if (nodeText.equals("Первичные документы") || nodeText.equals("Вторичные документы")) {
                DocCreateDialog createDialog = new DocCreateDialog(MainForm.this);
                createDialog.setModal(true);
                createDialog.setVisible(true);
            }
        }

    }

    private void editButtonActionPerformed(ActionEvent e) {
        DocEditDialog editDialog = new DocEditDialog(MainForm.this);
        editDialog.setModal(true);
        editDialog.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nick Tate
        ResourceBundle bundle = ResourceBundle.getBundle("com.geopack.gui.lang");
        mainMenuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitMenuItem = new JMenuItem();
        toolsMenu = new JMenu();
        menuItem1 = new JMenuItem();
        settingsMenu = new JMenu();
        helpMenu = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem2 = new JMenuItem();
        splitPane1 = new JSplitPane();
        scrollPane1 = new JScrollPane();
        navigationTree = new JTree();
        mainTabbedPane = new JTabbedPane();
        toolBarPanel = new JPanel();
        spherePanel = new JPanel();
        sphereTabs = new JTabbedPane();
        actionsToolBar = new JToolBar();
        addNewButton = new JButton();
        editButton = new JButton();
        deleteButton = new JButton();
        statusPanel = new JPanel();
        statusLabel = new JLabel();
        progressLabel = new JXBusyLabel();
        linkPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        list1 = new JList();
        label2 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(800, 500));
        setIconImage(new ImageIcon(getClass().getResource("/com/geopack/pict/document_new.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("MainForm.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainMenuBar ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("\u0424\u0430\u0439\u043b");

                //---- exitMenuItem ----
                exitMenuItem.setText("\u0412\u044b\u0445\u043e\u0434");
                exitMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        exitMenuItemActionPerformed(e);
                    }
                });
                fileMenu.add(exitMenuItem);
            }
            mainMenuBar.add(fileMenu);

            //======== toolsMenu ========
            {
                toolsMenu.setText("\u0418\u043d\u0441\u0442\u0440\u0443\u043c\u0435\u043d\u0442\u044b");

                //---- menuItem1 ----
                menuItem1.setText("\u041f\u043e\u0438\u0441\u043a");
                toolsMenu.add(menuItem1);
            }
            mainMenuBar.add(toolsMenu);

            //======== settingsMenu ========
            {
                settingsMenu.setText("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438");
            }
            mainMenuBar.add(settingsMenu);

            //======== helpMenu ========
            {
                helpMenu.setText("\u041f\u043e\u043c\u043e\u0449\u044c");

                //---- menuItem4 ----
                menuItem4.setText("\u0418\u043d\u0442\u0435\u0440\u0430\u043a\u0442\u0438\u0432\u043d\u0430\u044f \u0441\u043f\u0440\u0430\u0432\u043a\u0430");
                helpMenu.add(menuItem4);

                //---- menuItem2 ----
                menuItem2.setText("\u041e \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u0435");
                helpMenu.add(menuItem2);
            }
            mainMenuBar.add(helpMenu);
        }
        setJMenuBar(mainMenuBar);

        //======== splitPane1 ========
        {
            splitPane1.setDividerLocation(153);

            //======== scrollPane1 ========
            {

                //---- navigationTree ----
                navigationTree.setRootVisible(false);
                navigationTree.setShowsRootHandles(true);
                navigationTree.setToggleClickCount(1);
                scrollPane1.setViewportView(navigationTree);
            }
            splitPane1.setLeftComponent(scrollPane1);
            splitPane1.setRightComponent(mainTabbedPane);
        }
        contentPane.add(splitPane1, BorderLayout.CENTER);

        //======== toolBarPanel ========
        {

            // JFormDesigner evaluation mark
            toolBarPanel.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), toolBarPanel.getBorder()));
            toolBarPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });

            toolBarPanel.setLayout(new VerticalLayout());

            //======== spherePanel ========
            {
                spherePanel.setLayout(new BorderLayout());

                //======== sphereTabs ========
                {
                    sphereTabs.setMinimumSize(new Dimension(178, 18));
                    sphereTabs.setPreferredSize(new Dimension(178, 20));
                    sphereTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                    sphereTabs.setRequestFocusEnabled(false);
                    sphereTabs.setBorder(null);
                }
                spherePanel.add(sphereTabs, BorderLayout.CENTER);
            }
            toolBarPanel.add(spherePanel);

            //======== actionsToolBar ========
            {
                actionsToolBar.setRollover(true);

                //---- addNewButton ----
                addNewButton.setIcon(new ImageIcon(getClass().getResource("/com/geopack/pict/document_new.png")));
                addNewButton.setToolTipText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c");
                addNewButton.setMargin(new Insets(0, 0, 0, 0));
                addNewButton.setEnabled(false);
                addNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addNewButtonActionPerformed(e);
                    }
                });
                actionsToolBar.add(addNewButton);

                //---- editButton ----
                editButton.setIcon(new ImageIcon(getClass().getResource("/com/geopack/pict/edit.png")));
                editButton.setToolTipText("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c");
                editButton.setMargin(new Insets(0, 0, 0, 0));
                editButton.setEnabled(false);
                editButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        editButtonActionPerformed(e);
                    }
                });
                actionsToolBar.add(editButton);

                //---- deleteButton ----
                deleteButton.setIcon(new ImageIcon(getClass().getResource("/com/geopack/pict/document_delete.png")));
                deleteButton.setToolTipText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c");
                deleteButton.setPreferredSize(new Dimension(53, 53));
                deleteButton.setMaximumSize(new Dimension(59, 59));
                deleteButton.setMargin(new Insets(0, 0, 0, 0));
                deleteButton.setMinimumSize(new Dimension(59, 59));
                deleteButton.setEnabled(false);
                actionsToolBar.add(deleteButton);
            }
            toolBarPanel.add(actionsToolBar);
        }
        contentPane.add(toolBarPanel, BorderLayout.NORTH);

        //======== statusPanel ========
        {
            statusPanel.setLayout(new FlowLayout());

            //---- statusLabel ----
            statusLabel.setText("\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0434\u043e\u043a\u0443\u043c\u0435\u043d\u0442:");
            statusPanel.add(statusLabel);
            statusPanel.add(progressLabel);
        }
        contentPane.add(statusPanel, BorderLayout.SOUTH);

        //======== linkPanel ========
        {
            linkPanel.setVisible(false);
            linkPanel.setLayout(new BorderLayout());

            //======== scrollPane2 ========
            {

                //---- list1 ----
                list1.setMinimumSize(new Dimension(100, 48));
                list1.setPreferredSize(new Dimension(100, 48));
                list1.setModel(new AbstractListModel() {
                    String[] values = {
                            "\u0414\u043e\u043a\u0443\u043c\u0435\u043d\u04421",
                            "\u0422\u0435\u0441\u0442\u043e\u0432\u044b\u0439 \u0434\u043e\u043a\u0443\u043c\u0435\u043d\u0442"
                    };

                    public int getSize() {
                        return values.length;
                    }

                    public Object getElementAt(int i) {
                        return values[i];
                    }
                });
                scrollPane2.setViewportView(list1);
            }
            linkPanel.add(scrollPane2, BorderLayout.CENTER);

            //---- label2 ----
            label2.setText(bundle.getString("MainForm.label2.text"));
            linkPanel.add(label2, BorderLayout.NORTH);
        }
        contentPane.add(linkPanel, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nick Tate
    private JMenuBar mainMenuBar;
    private JMenu fileMenu;
    private JMenuItem exitMenuItem;
    private JMenu toolsMenu;
    private JMenuItem menuItem1;
    private JMenu settingsMenu;
    private JMenu helpMenu;
    private JMenuItem menuItem4;
    private JMenuItem menuItem2;
    private JSplitPane splitPane1;
    private JScrollPane scrollPane1;
    private JTree navigationTree;
    private JTabbedPane mainTabbedPane;
    private JPanel toolBarPanel;
    private JPanel spherePanel;
    private JTabbedPane sphereTabs;
    private JToolBar actionsToolBar;
    private JButton addNewButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JXBusyLabel progressLabel;
    private JPanel linkPanel;
    private JScrollPane scrollPane2;
    private JList list1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private static class LayoutTabPanel extends JPanel {
        private LayoutTab layoutTab;

        private LayoutTabPanel(LayoutTab layout) {
            this.layoutTab = layout;
        }

        public LayoutTab getLayoutTab() {
            return layoutTab;
        }
    }
}
