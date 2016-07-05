package GUI;

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

public class AboutPage {
	private JFrame aboutFrame;
	private Color color = new Color(0, 150, 130);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutPage window = new AboutPage();
					window.aboutFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AboutPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		aboutFrame = new JFrame("About ERP");
		aboutFrame.setBounds(100, 100, 450, 275);
		aboutFrame.getContentPane().setLayout(null);
		String head = "<html><h2>About the ERP software</h2></html>";
		JLabel headLab = new JLabel(head);
		headLab.setBounds(28, 10, 300, 50);
		aboutFrame.getContentPane().add(headLab);
		String text = "<html><p>Enterprise Resource Planning is a software written for the course project of the Object Oriented Design Course. This Software is developed by a group of three.<br>Spring 2016.</p></html>";
		JLabel info = new JLabel(text);
		info.setBounds(28,50,280,100);
		aboutFrame.getContentPane().add(info);


		aboutFrame.getContentPane().setBackground(color);
		aboutFrame.setResizable(false);

		JLabel lblImage = new JLabel();
		lblImage.setBounds(306, 6, 123, 125);

		lblImage.setIcon(new ImageIcon(new ImageIcon("images/erp.png")
				.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
		aboutFrame.getContentPane().add(lblImage);
		
		JLabel label = new JLabel("<html><p>Developer Team:<br> Arian Niaki<br> Negar Ghorbani<br> Mohammad Motiei </p></html>");
		label.setBounds(28, 175, 280, 72);
		aboutFrame.getContentPane().add(label);

	}

	public JFrame getaboutpageFrame() {
		return aboutFrame;
	}
}
