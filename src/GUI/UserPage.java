package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;

import Resource.ResourceCatalogue;
import UserManagement.Employee;
public class UserPage extends JFrame {
    protected JButton addmodulebtn, addresourcebtn,logout;
    // Constructor sets features of the frame, creates buttons, adds them to the frame, and
    // assigns an object to listen to them
    
    public UserPage(final Employee emp) {
        super("Users Page"); // set title bar
        setSize(400,200); // sets the size of the window
        addmodulebtn = new JButton("Add Module"); // create two new buttons w/labels start and stop
        addresourcebtn = new JButton("Add Resource");
        logout = new JButton("Logout");
        final JFrame ine = this;
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout()); // layout objects from left to right
                                                 // until fill up row and then go to next row
        contentPane.add(addmodulebtn);
        contentPane.add(addresourcebtn);
        contentPane.add(logout);
        // create an object to listen to both buttons:
        addmodulebtn.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println("clicked add module");
		      }});
        addresourcebtn.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println("clicked add resource");
		        final JFrame fresource = new JFrame("Add Resource pop up");
		      String[] labels = { "Resource Name" };
		      int[] widths = { 15, 15};

    		    final TextForm resourceform = new TextForm(labels,widths);
    		    JButton submitres = new JButton("Submit Resource");

//    		    fresource.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		    fresource.getContentPane().add(resourceform, BorderLayout.NORTH);
    		    JPanel p = new JPanel();
    		    p.add(submitres);
    		    fresource.getContentPane().add(p, BorderLayout.SOUTH);
    		    fresource.pack();
    		    fresource.setVisible(true);
//    			ine.dispose();
    		    
    			submitres.addActionListener(new ActionListener() {
  			      public void actionPerformed(ActionEvent e) {
  			        System.out.println(resourceform.getText(0));
  					ResourceCatalogue rscat = new ResourceCatalogue();
  					rscat = new ResourceCatalogue();
  					rscat.addResource(resourceform.getText(0));
  					rscat.readAllResources();
  			      }
  			    });
    			
		      }});
        
        logout.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    		emp.logout();
		    			System.out.println(emp.loggedin);
		    		    final JFrame f = new JFrame("Login Page");
		    		    
		    		    final LoginPage form = new LoginPage();
		    		    JButton submit = new JButton("Submit Form");

		    		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    		    f.getContentPane().add(form, BorderLayout.NORTH);
		    		    JPanel p = new JPanel();
		    		    p.add(submit);
		    		    f.getContentPane().add(p, BorderLayout.SOUTH);
		    		    f.pack();
		    		    f.setVisible(true);
		    			ine.dispose();
		    			submit.addActionListener(new ActionListener() {
		    			      public void actionPerformed(ActionEvent e) {
		    			        System.out.println(form.getText(0) + " " + form.getText(1));
		    			        Employee emp = new Employee();
		    					if(!(emp.login(form.getText(0),form.getText(1)))){
		    						System.out.println("huraaa");
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

		      }});
}
}