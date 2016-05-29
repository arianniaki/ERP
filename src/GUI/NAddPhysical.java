package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Resource.PhysicalResourceCatalogue;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NAddPhysical {

	private JFrame addphysresFrame;
	private JTextField physresName;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NAddPhysical window = new NAddPhysical();
					window.addphysresFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NAddPhysical() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addphysresFrame = new JFrame();
		addphysresFrame.setBounds(100, 100, 450, 300);
		addphysresFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		addphysresFrame.getContentPane().setLayout(springLayout);
		
		physresName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, physresName, 29, SpringLayout.NORTH, addphysresFrame.getContentPane());
		physresName.setText("Name");
		addphysresFrame.getContentPane().add(physresName);
		physresName.setColumns(10);
		
		JLabel lblName = DefaultComponentFactory.getInstance().createLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 6, SpringLayout.NORTH, physresName);
		addphysresFrame.getContentPane().add(lblName);
		
		txtId = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtId, 17, SpringLayout.SOUTH, physresName);
		springLayout.putConstraint(SpringLayout.WEST, physresName, 0, SpringLayout.WEST, txtId);
		springLayout.putConstraint(SpringLayout.EAST, txtId, -152, SpringLayout.EAST, addphysresFrame.getContentPane());
		txtId.setText("ID");
		addphysresFrame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblPhysicalResourceId = DefaultComponentFactory.getInstance().createLabel("Physical Resource ID");
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, lblPhysicalResourceId);
		springLayout.putConstraint(SpringLayout.NORTH, lblPhysicalResourceId, 6, SpringLayout.NORTH, txtId);
		springLayout.putConstraint(SpringLayout.WEST, lblPhysicalResourceId, 6, SpringLayout.EAST, txtId);
		addphysresFrame.getContentPane().add(lblPhysicalResourceId);
		
		JButton btnSubmitPhysicalRes = new JButton("Submit");
		btnSubmitPhysicalRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhysicalResourceCatalogue physrscat = new PhysicalResourceCatalogue();
				
				physrscat.addResource(14,14,physresName.getText());
				physrscat.readAllResources();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnSubmitPhysicalRes, 10, SpringLayout.WEST, addphysresFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmitPhysicalRes, -10, SpringLayout.SOUTH, addphysresFrame.getContentPane());
		addphysresFrame.getContentPane().add(btnSubmitPhysicalRes);
	}
	
	public JFrame getAddPhysResFrame() {
		return addphysresFrame;
	}

}
