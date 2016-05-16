package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import UserManagement.Employee;
import UserManagement.EmployeeCatalogue;

public class main {
	   // The entry main() method
	  public static void main(String[] args) {

		    final LoginPage form = new LoginPage();

		    JButton submit = new JButton("Login");
		    JButton signup = new JButton("Sign Up");

		    final JFrame f = new JFrame("Login Page");
		    submit.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println(form.getText(0) + " " + form.getText(1));
		        Employee emp = new Employee();
				if(!(emp.login(form.getText(0),form.getText(1)))){
					System.out.println("huraaa");
					form.failed_label.setVisible(true);


				}else{
					System.out.println("shit");
				}
				if(emp.login(form.getText(0),form.getText(1))){
					System.out.println(emp.getName());
					System.out.println(emp.loggedin);
				    UserPage userpage = new UserPage(emp);
				    userpage.setVisible(true);
					f.dispose();
				}
		      }
		    });

		    signup.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				    final JFrame signup = new JFrame("Signup Page");
				    String[] labels = { "Employee Name","Employee id","Section ID","Username","Password"};
				    int[] widths = { 15, 15,15,15,15};
	    		    JButton submitsignup = new JButton("Submit");


	    		    final TextForm signupform = new TextForm(labels,widths);
	    		    signup.getContentPane().add(signupform, BorderLayout.NORTH);
	    		    JPanel p = new JPanel();
	    		    p.add(submitsignup);
	    		    signup.getContentPane().add(p, BorderLayout.SOUTH);
	    		    signup.pack();
	    		    signup.setVisible(true);
	    		    
	    		    submitsignup.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							EmployeeCatalogue empcat = new EmployeeCatalogue();
							System.out.println(signupform.getText(0)+" "+signupform.getText(1)+" "+signupform.getText(2)+" "+signupform.getText(3)+" "+signupform.getText(4));
							
							empcat.addEmployee(Integer.parseInt(signupform.getText(1)), false, signupform.getText(0), "newuser",Integer.parseInt(signupform.getText(2)),signupform.getText(3), signupform.getText(4),false);
							empcat.readAllEmployees();
						}
					});

					
				}
			});
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    f.getContentPane().add(form, BorderLayout.NORTH);
		    JPanel p = new JPanel();
		    p.add(submit);
		    p.add(signup);
		    f.getContentPane().add(p, BorderLayout.SOUTH);
		    f.pack();
		    f.setVisible(true);
		  }
}
