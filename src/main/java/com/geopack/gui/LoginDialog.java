/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geopack.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/** @author pavel.shatrov */
public class LoginDialog extends JFrame {
//private static final Logger log = Logger.getLogger(LoginDialog.class);
	private static Logger log = Logger.getLogger(LoginDialog.class.getName());
	private static LoginDialog instance = null;
	//private static boolean second = false;
	public JTextField nameField = null;
	public JPasswordField passField2 = null;
	private JLabel label = null;
	Preferences prefs = null;

	// Виды деятельности
	private Object[] vids = {"Проекты", "Ввод данных"};

	public static LoginDialog getInstance(boolean s) {
		if (instance == null) {
			instance = new LoginDialog();
		}
		//second = s;
		return instance;
	}

	public void Show() {
		if (!instance.isVisible()) {
			instance.setVisible(true);
		}
	}

	/** Creates a new instance of LoginDialog */
	protected LoginDialog() {
		super("ГеоПАК  Вход в систему");

		setResizable(false);

		prefs = Preferences.userRoot();

		ClassLoader cl = this.getClass().getClassLoader();
		//ImageIcon icon = new ImageIcon(cl.getResource("icon.png"));
		//setIconImage(icon.getImage());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
				System.exit(0);
			}
		});

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
		passField2 = new JPasswordField("", 20);
		passField2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					StartApp();
				}
			}
		});

		// Вид деятельности
		JLabel dLabel = new JLabel("Вид деятельности:");
		JComboBox dCombo = new JComboBox(vids);


		org.jdesktop.layout.GroupLayout.SequentialGroup hGroup = gLayOut.createSequentialGroup();
		hGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING).add(nameLabel).add(labelPass).add(dLabel));
		hGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(nameField).add(passField2).add(dCombo));
		gLayOut.setHorizontalGroup(hGroup);

		org.jdesktop.layout.GroupLayout.SequentialGroup vGroup = gLayOut.createSequentialGroup();
		vGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(nameLabel).add(nameField));
		vGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(labelPass).add(passField2));
		vGroup.add(gLayOut.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(dLabel).add(dCombo));
		gLayOut.setVerticalGroup(vGroup);

		//
		JPanel closePanel = new JPanel();
		closePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton clButton = new JButton("Вход");
		clButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
//				new MainFrame();
				final MainForm form = new MainForm();
				form.setVisible(true);
			}
		});

		/*clButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						StartApp();
					}
				});*/

		clButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					StartApp();
				}
			}
		});

		closePanel.add(clButton);

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

		passField2.setText(prefs.get("/geopak/login/login", ""));
		nameField.setText(prefs.get("/geopak/login/server", ""));

		// Если не первый запуск то фокус сразу на пароль
		if (prefs.getBoolean("/geopak/login/first", false) == true) {
			//InitialFocusSetter initial = new InitialFocusSetter ();
			//initial.setInitialFocus (this, passField2);
		}

		pack();
		setLocation(getGraphicsConfiguration().getBounds().width / 2 - getBounds().width / 2, getGraphicsConfiguration().getBounds().height / 2 - getSize().height / 2);
		setVisible(true);
	}

	private void StartApp() //throws REDataException
	{
		if (passField2.getText().compareToIgnoreCase("postgres") == 0) {
			label.setText("Недопустимое имя пользователя");
		}

	}

	public void saveFields() {
		prefs.put("/geopak/login/login", passField2.getText());
		prefs.put("/geopak/login/server", nameField.getText());
		prefs.putBoolean("/geopak/login/first", true);
	}
}
