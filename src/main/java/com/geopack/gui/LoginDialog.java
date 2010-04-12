/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geopack.gui;

import com.geopack.tabs.TbLayout;
import com.geopack.utils.ApplicationParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * @author pavel.shatrov
 */
public class LoginDialog extends JFrame {
    private static Logger log = Logger.getLogger(LoginDialog.class.getName());
    public JTextField nameField = null;
    public JPasswordField passwordField = null;
    private JLabel label = null;
    private JComboBox layoutCombo = null;
    Preferences prefs = null;

    // Виды деятельности
    private Object[] vids = {"Проекты", "Ввод данных"};

    /**
     * Creates a new instance of LoginDialog
     */
    public LoginDialog() {
        super("ГеоПАК  Вход в систему");
        setResizable(false);
        prefs = Preferences.userRoot();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ClassLoader cl = this.getClass().getClassLoader();
        JPanel winPanel = HelpLib.createVerticalPanel();
        add(winPanel);
        winPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel loginPanel = new JPanel();
        //loginPanel.setBorder(BorderFactory.createTitledBorder(":"));
        org.jdesktop.layout.GroupLayout gLayOut = new org.jdesktop.layout.GroupLayout(loginPanel);
        gLayOut.setAutocreateGaps(true);
        gLayOut.setAutocreateContainerGaps(true);
        loginPanel.setLayout(gLayOut);
        winPanel.add(loginPanel);


        // имя
        JLabel nameLabel = new JLabel("Имя:");
        nameField = new JTextField("", 20);

        // Пароль
        JLabel labelPass = new JLabel("Пароль:");
        passwordField = new JPasswordField("", 20);
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    StartApp();
                }
            }
        });

        // Вид деятельности
        JLabel dLabel = new JLabel("Вид деятельности:");

        layoutCombo = new JComboBox();
        ComboBoxModel comboBoxModel = new DefaultComboBoxModel(ApplicationParams.getInstance().getLoadedLayouts().toArray());
        layoutCombo.setModel(comboBoxModel);
        layoutCombo.setRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(((TbLayout) value).getName());
            }
        });

        org.jdesktop.layout.GroupLayout.SequentialGroup hGroup = gLayOut.createSequentialGroup();
        hGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING).add(nameLabel).add(labelPass).add(dLabel));
        hGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(nameField).add(passwordField).add(layoutCombo));
        gLayOut.setHorizontalGroup(hGroup);

        org.jdesktop.layout.GroupLayout.SequentialGroup vGroup = gLayOut.createSequentialGroup();
        vGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(nameLabel).add(nameField));
        vGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(labelPass).add(passwordField));
        vGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(dLabel).add(layoutCombo));
        gLayOut.setVerticalGroup(vGroup);

        //
        JPanel closePanel = new JPanel();
        closePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton loginButton = new JButton("Вход");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ApplicationParams.getInstance().setSelectedLayout((TbLayout) layoutCombo.getSelectedObjects()[0]);
                final MainForm form = new MainForm();
                form.setVisible(true);
            }
        });


        loginButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    StartApp();
                }
            }
        });

        closePanel.add(loginButton);

        winPanel.add(Box.createVerticalStrut(12));
        winPanel.add(closePanel);

        JPanel status = new JPanel();
        status.setPreferredSize(new Dimension(status.getWidth(), 23));
        status.setLayout(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel("");
        status.add(label);
        winPanel.add(new JSeparator());
        winPanel.add(status);

        HelpLib.makeSameSize(new JComponent[]{nameLabel, labelPass, labelPass});

        passwordField.setText(prefs.get("/geopak/login/login", ""));
        nameField.setText(prefs.get("/geopak/login/server", ""));

        // Если не первый запуск то фокус сразу на пароль
        if (prefs.getBoolean("/geopak/login/first", false)) {
            //InitialFocusSetter initial = new InitialFocusSetter ();
            //initial.setInitialFocus (this, passwordField);
        }

        pack();
        setLocation(getGraphicsConfiguration().getBounds().width / 2 - getBounds().width / 2, getGraphicsConfiguration().getBounds().height / 2 - getSize().height / 2);
        setVisible(true);
    }

    private void StartApp() //throws REDataException
    {
        if (passwordField.getText().compareToIgnoreCase("postgres") == 0) {
            label.setText("Недопустимое имя пользователя");
        }

    }

    public void saveFields() {
        prefs.put("/geopak/login/login", passwordField.getText());
        prefs.put("/geopak/login/server", nameField.getText());
        prefs.putBoolean("/geopak/login/first", true);
	}
}
