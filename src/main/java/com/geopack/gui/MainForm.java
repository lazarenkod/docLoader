/*
 * Created by JFormDesigner on Fri Apr 09 12:56:17 MSD 2010
 */

package com.geopack.gui;

import com.geopack.maps.ShapeFiles;
import com.geopack.modules.IModule;
import com.geopack.modules.dicts.DictsModule;
import com.geopack.modules.documents.DocEditDialog;
import com.geopack.modules.documents.DocumentsModule;
import com.geopack.modules.gis.MapsModule;
import com.geopack.modules.knowbase.KnowledgeBaseModule;
import com.geopack.modules.slideshow.SlideShowPanel;
import com.geopack.tabs.LayoutTab;
import com.geopack.tabs.PresentationTab;
import com.geopack.utils.ApplicationParams;
import com.jidesoft.swing.JideTabbedPane;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private int selectedDocTabIndex = -1;

    public MainForm() {
        initComponents();
        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                return new ShapeFiles();

            }

            @Override
            protected void done() {
                try {
                    ApplicationParams.getInstance().setLoadedShapes((ShapeFiles) get());
                    progressLabel.setBusy(false);
                    progressLabel.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ExecutionException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        };
        mainTabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    selectedDocTabIndex = mainTabbedPane.getSelectedIndex();
                    if (!mainTabbedPane.getTitleAt(selectedDocTabIndex).startsWith("* ")) {
                        pinMenuItem.setText("Закрепить вкладку");
                    } else {
                        pinMenuItem.setText("Открепить вкладку");
                    }
                    tabsPopupMenu.show(mainTabbedPane, e.getX(), e.getY());
                }
            }
        });
        progressLabel.setBusy(true);
        progressLabel.setVisible(true);
        sw.execute();
        registerModules();
        setLayoutTabs(ApplicationParams.getInstance().getSelectedLayout().getTabList());
	    ApplicationParams.getInstance().setMainForm(this);

    }

    private void registerModules() {
        new DocumentsModule().registerInModuleList();
        new MapsModule().registerInModuleList();
        new DictsModule().registerInModuleList();
        new KnowledgeBaseModule().registerInModuleList();
    }

    public void addNewTab(String tabName, Component component,boolean needCloseOther) {
    if (needCloseOther)
        for (int i = 0; i < mainTabbedPane.getTabCount(); i++) {
            if (!mainTabbedPane.getTitleAt(i).startsWith("* "))
                mainTabbedPane.removeTabAt(i);
        }

        mainTabbedPane.addTab(tabName, component);
        mainTabbedPane.setSelectedComponent(component);
    }


    public void setLayoutTabs(final List<LayoutTab> layoutTabs) {
        final JMenu menu = new JMenu("Модули");
	    ButtonGroup buttonGroup=new ButtonGroup();
        for (int i = 0, layoutTabsSize = layoutTabs.size(); i < layoutTabsSize; i++) {
            LayoutTab layoutTab = layoutTabs.get(i);
            LayoutMenuItem menuItem = new LayoutMenuItem(layoutTab);
	        buttonGroup.add(menuItem);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LayoutMenuItem selectedItem = (LayoutMenuItem) e.getSource();
                    IModule module = ApplicationParams.getInstance().getModule(selectedItem.getLayoutTab().getModuleName());
                    if (module != null) {
                        addNewTab(selectedItem.getLayoutTab().getHint(), module.getContentPanel(),true);
                    } else {
                        addNewTab(selectedItem.getLayoutTab().getHint(), showSlides(selectedItem.getLayoutTab().getModuleName()),true);
                    }
                    for (int j = 0; j < sphereTabs.getTabCount(); j++) {
                        LayoutTabPanel tabPanel = (LayoutTabPanel) sphereTabs.getComponentAt(j);
                        if (tabPanel.getLayoutTab().equals(selectedItem.getLayoutTab())) {
                            sphereTabs.setSelectedIndex(j);
                        }

                    }
                }
            });
            menu.add(menuItem);
            LayoutTabPanel layoutTabPanel = new LayoutTabPanel(layoutTab);

            sphereTabs.addTab(layoutTab.getName(), layoutTabPanel);
            sphereTabs.setToolTipTextAt(i, layoutTab.getHint());
        }
        sphereTabs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LayoutTabPanel selectedModuleTab = (LayoutTabPanel) sphereTabs.getSelectedComponent();
                if (selectedModuleTab != null) {
                    IModule module = ApplicationParams.getInstance().getModule(selectedModuleTab.getLayoutTab().getModuleName());
                    if (module != null) {
                        addNewTab(selectedModuleTab.getLayoutTab().getHint(), module.getContentPanel(),true);
                    } else {
                        addNewTab(selectedModuleTab.getLayoutTab().getHint(), showSlides(selectedModuleTab.getLayoutTab().getModuleName()),true);
                    }
	                for (Component menuItem : menu.getMenuComponents()) {
		                if (((LayoutMenuItem) menuItem).getLayoutTab().equals(selectedModuleTab.getLayoutTab())) {
			                ((LayoutMenuItem) menuItem).setSelected(true);
		                }

	                }
                }
            }
        });
        sphereTabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
//                    if (lastSelectedModuleIndex != sphereTabs.getSelectedIndex()) {
//                        LayoutTabPanel selectedModuleTab = (LayoutTabPanel) sphereTabs.getSelectedComponent();
//                        lastSelectedModuleIndex = sphereTabs.getSelectedIndex();
//                        if (selectedModuleTab != null) {
//                            IModule module = ApplicationParams.getInstance().getModule(selectedModuleTab.getLayoutTab().getModuleName());
//                            if (module != null) {
//                                addNewTab(selectedModuleTab.getLayoutTab().getHint(), module.getContentPanel());
//                            } else {
//                                addNewTab(selectedModuleTab.getLayoutTab().getHint(), showSlides());
//                            }
//                        }
//                    }

            }
        });
        mainMenuBar.add(menu);
    }


    private JPanel showSlides(String moduleName) {
        for (PresentationTab presentationTab : ApplicationParams.getInstance().getPresentationTabs()) {
            if (presentationTab.getModuleName().equals(moduleName)) {
                SlideShowPanel slideShowPanel = new SlideShowPanel();
                slideShowPanel.setSlides(presentationTab.getSlides());
                slideShowPanel.showSlides(presentationTab.getDelay());
                return slideShowPanel;
            }
        }

        return new JPanel() {{
            setLayout(new BorderLayout());
            add(new JLabel("В процессе реализации"));
        }};

    }





    private void exitMenuItemActionPerformed(ActionEvent e) {
        System.exit(0);
    }



    private void editButtonActionPerformed(ActionEvent e) {
        DocEditDialog editDialog = new DocEditDialog(MainForm.this);
        editDialog.setModal(true);
        editDialog.setVisible(true);
    }

    private void pinMenuItemActionPerformed(ActionEvent e) {
        int index = mainTabbedPane.getSelectedIndex();
        if (!mainTabbedPane.getTitleAt(index).startsWith("* "))
            mainTabbedPane.setTitleAt(index, "* " + mainTabbedPane.getTitleAt(index));
        else
            mainTabbedPane.setTitleAt(index, mainTabbedPane.getTitleAt(index).substring(2));
    }

    private void closeMenuItemActionPerformed(ActionEvent e) {
        int index = mainTabbedPane.getSelectedIndex();
        mainTabbedPane.removeTabAt(index);
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
		toolBarPanel = new JPanel();
		spherePanel = new JPanel();
		sphereTabs = new JideTabbedPane();
		statusPanel = new JPanel();
		statusLabel = new JLabel();
		progressLabel = new JXBusyLabel();
		linkPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		list1 = new JList();
		label2 = new JLabel();
		mainTabbedPane = new JideTabbedPane();
		tabsPopupMenu = new JPopupMenu();
		pinMenuItem = new JMenuItem();
		closeMenuItem = new JMenuItem();

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

		//======== toolBarPanel ========
		{


			toolBarPanel.setLayout(new VerticalLayout());

			//======== spherePanel ========
			{
				spherePanel.setLayout(new BorderLayout());

				//======== sphereTabs ========
				{
					sphereTabs.setBoldActiveTab(true);
					sphereTabs.setContentBorderInsets(new Insets(0, 0, 0, 0));
					sphereTabs.setMinimumSize(new Dimension(172, 22));
					sphereTabs.setPreferredSize(new Dimension(172, 22));
					sphereTabs.setFocusable(false);
					sphereTabs.setRequestFocusEnabled(false);
					sphereTabs.setVerifyInputWhenFocusTarget(false);
					sphereTabs.setColorTheme(1);
					sphereTabs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.black, null));
				}
				spherePanel.add(sphereTabs, BorderLayout.CENTER);
			}
			toolBarPanel.add(spherePanel);
		}
		contentPane.add(toolBarPanel, BorderLayout.NORTH);

		//======== statusPanel ========
		{
			statusPanel.setLayout(new FlowLayout());

			//---- statusLabel ----
			statusLabel.setText("\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0434\u043e\u043a\u0443\u043c\u0435\u043d\u0442:");
			statusPanel.add(statusLabel);

			//---- progressLabel ----
			progressLabel.setVisible(false);
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
					public int getSize() { return values.length; }
					public Object getElementAt(int i) { return values[i]; }
				});
				scrollPane2.setViewportView(list1);
			}
			linkPanel.add(scrollPane2, BorderLayout.CENTER);

			//---- label2 ----
			label2.setText(bundle.getString("MainForm.label2.text"));
			linkPanel.add(label2, BorderLayout.NORTH);
		}
		contentPane.add(linkPanel, BorderLayout.EAST);

		//======== mainTabbedPane ========
		{
			mainTabbedPane.setScrollSelectedTabOnWheel(true);
			mainTabbedPane.setShowCloseButtonOnSelectedTab(true);
			mainTabbedPane.setShowCloseButtonOnTab(true);
			mainTabbedPane.setShowTabButtons(true);
			mainTabbedPane.setFocusable(false);
			mainTabbedPane.setBoldActiveTab(true);
			mainTabbedPane.setTabShape(0);
			mainTabbedPane.setUseDefaultShowIconsOnTab(false);
			mainTabbedPane.setComponentPopupMenu(null);
		}
		contentPane.add(mainTabbedPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

		//======== tabsPopupMenu ========
		{

			//---- pinMenuItem ----
			pinMenuItem.setText("\u0417\u0430\u043a\u0440\u0435\u043f\u0438\u0442\u044c \u0432\u043a\u043b\u0430\u0434\u043a\u0443");
			pinMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pinMenuItemActionPerformed(e);
				}
			});
			tabsPopupMenu.add(pinMenuItem);

			//---- closeMenuItem ----
			closeMenuItem.setText("\u0417\u0430\u043a\u0440\u044b\u0442\u044c");
			closeMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeMenuItemActionPerformed(e);
				}
			});
			tabsPopupMenu.add(closeMenuItem);
		}
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
	private JPanel toolBarPanel;
	private JPanel spherePanel;
	private JideTabbedPane sphereTabs;
	private JPanel statusPanel;
	private JLabel statusLabel;
	private JXBusyLabel progressLabel;
	private JPanel linkPanel;
	private JScrollPane scrollPane2;
	private JList list1;
	private JLabel label2;
	private JideTabbedPane mainTabbedPane;
	private JPopupMenu tabsPopupMenu;
	private JMenuItem pinMenuItem;
	private JMenuItem closeMenuItem;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private class LayoutMenuItem extends JRadioButtonMenuItem {
        private LayoutTab layoutTab;

        public LayoutMenuItem(LayoutTab layoutTab) {
	        super();
	        
            this.layoutTab = layoutTab;
            setText(layoutTab.getName());
            setToolTipText(layoutTab.getHint());
        }

        public LayoutTab getLayoutTab() {
            return layoutTab;
        }
    }

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
