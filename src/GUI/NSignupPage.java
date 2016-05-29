package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import UserManagement.EmployeeCatalogue;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class NSignupPage {

	private JFrame signupFrame;
	private JTextField usernameField;
	private JTextField emailField;
	private JTextField nameField;
	private JTextField familynameField;
	private JPasswordField passwordField;
	private JPasswordField againpasswordField;
	private JButton btnSignUp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NSignupPage window = new NSignupPage();
					window.getSignupFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NSignupPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setSignupFrame(new JFrame());
		getSignupFrame().setBounds(100, 100, 450, 300);
		getSignupFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getSignupFrame().getContentPane().setLayout(springLayout);
		
		usernameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, usernameField, 42, SpringLayout.NORTH, getSignupFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, usernameField, -95, SpringLayout.EAST, getSignupFrame().getContentPane());
		getSignupFrame().getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, emailField, 42, SpringLayout.NORTH, getSignupFrame().getContentPane());
		getSignupFrame().getContentPane().add(emailField);
		emailField.setColumns(10);
		
		nameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nameField, 6, SpringLayout.SOUTH, usernameField);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, usernameField);
		getSignupFrame().getContentPane().add(nameField);
		nameField.setColumns(10);
		
		familynameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, familynameField, 8, SpringLayout.SOUTH, emailField);
		springLayout.putConstraint(SpringLayout.WEST, emailField, 0, SpringLayout.WEST, familynameField);
		springLayout.putConstraint(SpringLayout.WEST, familynameField, 10, SpringLayout.WEST, getSignupFrame().getContentPane());
		getSignupFrame().getContentPane().add(familynameField);
		familynameField.setColumns(10);
		
		JLabel label_4 = DefaultComponentFactory.getInstance().createLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, label_4, 148, SpringLayout.NORTH, getSignupFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label_4, -27, SpringLayout.EAST, getSignupFrame().getContentPane());
		getSignupFrame().getContentPane().add(label_4);
		
		JLabel label_5 = DefaultComponentFactory.getInstance().createLabel("Repeat Password");
		springLayout.putConstraint(SpringLayout.NORTH, label_5, 19, SpringLayout.SOUTH, label_4);
		springLayout.putConstraint(SpringLayout.EAST, label_5, -27, SpringLayout.EAST, getSignupFrame().getContentPane());
		getSignupFrame().getContentPane().add(label_5);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 38, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 212, SpringLayout.WEST, getSignupFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, -108, SpringLayout.SOUTH, getSignupFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -52, SpringLayout.WEST, label_4);
		getSignupFrame().getContentPane().add(passwordField);
		
		againpasswordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, againpasswordField, 6, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, againpasswordField, 212, SpringLayout.WEST, getSignupFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, againpasswordField, -6, SpringLayout.WEST, label_5);
		getSignupFrame().getContentPane().add(againpasswordField);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				String pass = new String(passwordField.getPassword());
				empcat.addEmployee(14, false, nameField.getText(), "newuser",1 ,usernameField.getText(), pass ,false);
				empcat.readAllEmployees();
				getSignupFrame().dispose();
				NLoginPage loginpage = new NLoginPage();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnSignUp, 0, SpringLayout.WEST, emailField);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSignUp, -10, SpringLayout.SOUTH, getSignupFrame().getContentPane());
		getSignupFrame().getContentPane().add(btnSignUp);
		
		
		JLabel lblName = DefaultComponentFactory.getInstance().createLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 6, SpringLayout.NORTH, nameField);
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, label_4);
		getSignupFrame().getContentPane().add(lblName);
		
		JLabel lblUsername = DefaultComponentFactory.getInstance().createLabel("Username");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 6, SpringLayout.NORTH, usernameField);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 6, SpringLayout.EAST, usernameField);
		getSignupFrame().getContentPane().add(lblUsername);
		
		JLabel lblLastName = DefaultComponentFactory.getInstance().createLabel("Last Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblLastName, 6, SpringLayout.NORTH, nameField);
		springLayout.putConstraint(SpringLayout.WEST, lblLastName, 6, SpringLayout.EAST, familynameField);
		getSignupFrame().getContentPane().add(lblLastName);
		
		JLabel lblEmail = DefaultComponentFactory.getInstance().createLabel("Email");
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 6, SpringLayout.NORTH, usernameField);
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, -6, SpringLayout.WEST, usernameField);
		getSignupFrame().getContentPane().add(lblEmail);
		
		JLabel lblSignupForm = DefaultComponentFactory.getInstance().createLabel("Signup Form");
		lblSignupForm.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		springLayout.putConstraint(SpringLayout.NORTH, lblSignupForm, 10, SpringLayout.NORTH, getSignupFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSignupForm, 0, SpringLayout.WEST, lblLastName);
		getSignupFrame().getContentPane().add(lblSignupForm);
		
	
	}

	public JFrame getSignupFrame() {
		return signupFrame;
	}

	public void setSignupFrame(JFrame signupFrame) {
		this.signupFrame = signupFrame;
	}
}
