package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.TextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import UserManagement.Employee;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NLoginPage {

	private JFrame loginFrame;
	private JTextField username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NLoginPage window = new NLoginPage();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NLoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.setBounds(100, 100, 450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		loginFrame.getContentPane().setLayout(springLayout);
		
		username = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, username, -183, SpringLayout.SOUTH, loginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, username, -139, SpringLayout.EAST, loginFrame.getContentPane());
		loginFrame.getContentPane().add(username);
		username.setColumns(10);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 16, SpringLayout.SOUTH, username);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 135, SpringLayout.WEST, loginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, username);
		loginFrame.getContentPane().add(passwordField);
		
		final JLabel lblLoginFailed = DefaultComponentFactory.getInstance().createLabel("Login Failed");
		springLayout.putConstraint(SpringLayout.NORTH, lblLoginFailed, 97, SpringLayout.NORTH, loginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblLoginFailed, 30, SpringLayout.EAST, passwordField);
		lblLoginFailed.setForeground(Color.RED);
		lblLoginFailed.setVisible(false);
		loginFrame.getContentPane().add(lblLoginFailed);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblLoginFailed.setVisible(false);

				 Employee emp = new Employee();
				 String pass = new String(passwordField.getPassword());
					if(!(emp.login(username.getText(),pass))){
						System.out.println("pass false");
						lblLoginFailed.setVisible(true);

					}else{
						NUserPage userpageWindow = new NUserPage();
						
//						UserPage userpage = new UserPage(emp);
						userpageWindow.getUserpageFrame().setVisible(true);
						loginFrame.dispose();
					}
					}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 6, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, 0, SpringLayout.EAST, username);
		loginFrame.getContentPane().add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NSignupPage signupWindow = new NSignupPage();
				signupWindow.getSignupFrame().setVisible(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSignUp, 6, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.EAST, btnSignUp, -6, SpringLayout.WEST, btnLogin);
		loginFrame.getContentPane().add(btnSignUp);
		
		JLabel lblUsername = DefaultComponentFactory.getInstance().createLabel("Username");
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -340, SpringLayout.EAST, loginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, username, 25, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 6, SpringLayout.NORTH, username);
		loginFrame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = DefaultComponentFactory.getInstance().createLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 6, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUsername);
		loginFrame.getContentPane().add(lblPassword);
		
	
	}
}
