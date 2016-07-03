package setup;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


import GUI.NotificationPage;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SetupPage {
	private Setup setup;
	private JFrame setupFrame;
	private JTextField username;
	private JTextField port;
	private JTextField passwordField;
	private Color color = new Color(0, 150, 130);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupPage window = new SetupPage();
					window.setupFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SetupPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setupFrame = new JFrame("ERP System Setup");
		setupFrame.setBounds(100, 100, 450, 400);
		setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame.getContentPane().setLayout(null);
		String head = "<html><h2>Setting Up the ERP</h2></html>";
		JLabel headLab = new JLabel(head);
		headLab.setBounds(28, 10, 300, 50);
		setupFrame.add(headLab);
		String text = "<html><p>For installation please, first enter the username and password of your Postgres as well as your desired port for using by this program, then click on the install button below.</p></html>";
		JLabel info = new JLabel(text);
		info.setBounds(28,50,280,100);
		setupFrame.add(info);

		username = new JTextField();
		username.setBounds(280, 156, 140, 28);
		setupFrame.getContentPane().add(username);
		username.setColumns(10);

		final JButton btnInstall = new JButton("Install");
		btnInstall.setBounds(280, 330, 140, 29);

		passwordField = new JTextField();
		passwordField.setBounds(280, 205, 140, 28);
		setupFrame.getContentPane().add(passwordField);
		
		port = new JTextField();
		port.setBounds(280, 255, 140, 28);
		setupFrame.getContentPane().add(port);
		
		final JLabel lblAuthFailed = DefaultComponentFactory.getInstance()
				.createLabel("Authentication Failed");
		lblAuthFailed.setBounds(28, 300, 176, 16);
		lblAuthFailed.setForeground(Color.RED);
		lblAuthFailed.setVisible(false);
		setupFrame.getContentPane().add(lblAuthFailed);
		
		final JButton btnSampleData = new JButton("Add Sample Data");
		btnSampleData.setBounds(28, 330, 140, 29);
		btnSampleData.setEnabled(false);
		

		btnInstall.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				lblAuthFailed.setVisible(false);
				try {
					setup = new Setup(username.getText(),passwordField.getText().toString(),port.getText());
					PrintWriter writer = new PrintWriter("userInfo.txt", "UTF-8");
					writer.println(username.getText());
					writer.println(passwordField.getText().toString());
					writer.println(port.getText());
					writer.close();
					btnInstall.setEnabled(false);
					btnSampleData.setEnabled(true);
					NotificationPage np = new NotificationPage(setupFrame, "ERP Installation", "ERP installed successfully. You can now add sample data and enjoy using ERP");
				} catch (Exception e1) {
					lblAuthFailed.setVisible(true);
					System.out.println(e);
				}
			}
		});
		
		btnSampleData.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					setup.addSampleData();
				} catch (FileNotFoundException | SQLException e1) {
					
				}
			}
		});

		setupFrame.getContentPane().add(btnInstall);
		setupFrame.getContentPane().add(btnSampleData);


		setupFrame.getContentPane().setBackground(color);
		setupFrame.setResizable(false);

		JLabel lblUsername = DefaultComponentFactory.getInstance().createLabel(
				"Postgres Username");
		lblUsername.setBounds(28, 162, 150, 16);
		setupFrame.getContentPane().add(lblUsername);

		JLabel lblPassword = DefaultComponentFactory.getInstance().createLabel(
				"Postgres Password");
		lblPassword.setBounds(28, 211, 150, 16);
		setupFrame.getContentPane().add(lblPassword);
		
		JLabel lblPort = DefaultComponentFactory.getInstance().createLabel(
				"Postgres Port");
		lblPort.setBounds(28, 261, 150, 16);
		setupFrame.getContentPane().add(lblPort);

		JLabel lblImage = new JLabel();
		lblImage.setBounds(306, 6, 123, 125);

		lblImage.setIcon(new ImageIcon(new ImageIcon("images/erp.png")
				.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
		setupFrame.getContentPane().add(lblImage);

	}

	public JFrame getloginpageFrame() {
		return setupFrame;
	}
}
