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

import com.jgoodies.forms.factories.DefaultComponentFactory;

import GUI.Form.Field;
import GUI.Form.FieldPanel;
import GUI.Form.Form;
import GUI.Form.PanelBuilder;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		loginFrame.getContentPane().setLayout(null);

		username = new JTextField();
		username.setBounds(115, 66, 176, 28);
		loginFrame.getContentPane().add(username);
		username.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(115, 115, 176, 28);
		loginFrame.getContentPane().add(passwordField);

		final JLabel lblLoginFailed = DefaultComponentFactory.getInstance().createLabel("Login Failed");
		lblLoginFailed.setBounds(341, 160, 76, 16);
		lblLoginFailed.setForeground(Color.RED);
		lblLoginFailed.setVisible(false);
		loginFrame.getContentPane().add(lblLoginFailed);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(212, 155, 79, 29);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblLoginFailed.setVisible(false);

				Employee emp = new Employee();
				String pass = new String(passwordField.getPassword());
				if (!(emp.login(username.getText(), pass))) {
					System.out.println("pass false");
					lblLoginFailed.setVisible(true);

				} else {
					NUserPage userpageWindow = new NUserPage();

					// UserPage userpage = new UserPage(emp);
					userpageWindow.getUserpageFrame().setVisible(true);
					loginFrame.dispose();
				}
			}
		});
		loginFrame.getContentPane().add(btnLogin);

		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.setBounds(115, 155, 91, 29);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Field> signupFields = new ArrayList<Field>();
				signupFields.add(new Field("text", "email", "", 10, "email"));
				signupFields.add(new Field("text", "username", "", 10, "username"));
				signupFields.add(new Field("text", "name", "", 10, "name"));

				signupFields.add(new Field("text", "lastname", "", 10, "lastname"));
				signupFields.add(new Field("text", "password", "", 10, "password"));
				signupFields.add(new Field("text", "repeat password", "", 10, "repassword"));

				final Form signupForm = new Form(signupFields, "Sign up Form");
				final PanelBuilder signupPanel = new PanelBuilder(signupForm);
				signupPanel.makeForm();
				JFrame SignupPage = new JFrame("Sign up Form");
				SignupPage.getContentPane().add(signupForm.getJPanel(), BorderLayout.NORTH);

				JButton submit = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submit);
				SignupPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

				SignupPage.pack();
				SignupPage.setVisible(true);
				// NSignupPage signupWindow = new NSignupPage();
				// signupWindow.getSignupFrame().setVisible(true);
				submit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < signupForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) signupForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}
						EmployeeCatalogue empcat = new EmployeeCatalogue();
						//++section e default mikhaim ke badan avaz she
						Employee added_emp= empcat.addEmployee( false, inputs.get(2)+""+inputs.get(3), "Default post", 1, inputs.get(1), inputs.get(4), false, false);
						added_emp.setDefaultAccessRight();
						System.out.println("Employee added");
						NotificationPage confirmation = new NotificationPage(new JFrame(), "Notification", "You have been successfully signed up!");
					}
				});
			}
		});
		loginFrame.getContentPane().add(btnSignUp);

		JLabel lblUsername = DefaultComponentFactory.getInstance().createLabel("Username");
		lblUsername.setBounds(28, 72, 62, 16);
		loginFrame.getContentPane().add(lblUsername);

		JLabel lblPassword = DefaultComponentFactory.getInstance().createLabel("Password");
		lblPassword.setBounds(28, 121, 59, 16);
		loginFrame.getContentPane().add(lblPassword);
		
		JLabel lblImage = new JLabel();
		lblImage.setBounds(306, 6, 123, 125);

		lblImage.setIcon(new ImageIcon(new ImageIcon("src/images/erp.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
		loginFrame.getContentPane().add(lblImage);

	}

	public JFrame getloginpageFrame() {
		return loginFrame;
	}
}
