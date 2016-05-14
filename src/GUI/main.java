package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import UserManagement.Employee;

public class main {
	   // The entry main() method
	  public static void main(String[] args) {
		    String[] labels = { "Username", "Password"};
//		    char[] mnemonics = { 'F', 'M', 'L', 'A' };
		    int[] widths = { 15, 15 };
		    String[] descs = { "Username", "Password" };

		    final LoginPage form = new LoginPage(labels, widths, descs);

		    JButton submit = new JButton("Submit Form");

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

				}
		      }
		    });

		    JFrame f = new JFrame("Text Form Example");
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    f.getContentPane().add(form, BorderLayout.NORTH);
		    JPanel p = new JPanel();
		    p.add(submit);
		    f.getContentPane().add(p, BorderLayout.SOUTH);
		    f.pack();
		    f.setVisible(true);
		  }
}
