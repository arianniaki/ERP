package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class NUserPage {

	private JFrame frame;
	private JTextField txtSearch;
	private JTextField reqSearchbar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUserPage window = new NUserPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUserPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 850, 600);
		frame.getContentPane().add(tabbedPane);
		
		JPanel editPanel = new JPanel();
		tabbedPane.addTab("Edit Info", null, editPanel, null);
		editPanel.setLayout(new SpringLayout());
		
		JPanel accessrightPanel = new JPanel();
		tabbedPane.addTab("AccessRight Management", null, accessrightPanel, null);
		SpringLayout sl_accessrightPanel = new SpringLayout();
		accessrightPanel.setLayout(sl_accessrightPanel);
		
		JPanel panel_1 = new JPanel();
		sl_accessrightPanel.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.NORTH, accessrightPanel);
		sl_accessrightPanel.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, accessrightPanel);
		sl_accessrightPanel.putConstraint(SpringLayout.SOUTH, panel_1, 40, SpringLayout.NORTH, accessrightPanel);
		sl_accessrightPanel.putConstraint(SpringLayout.EAST, panel_1, 819, SpringLayout.WEST, accessrightPanel);
		accessrightPanel.add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JButton btnAssignAccessright = new JButton("Assign AccessRight");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnAssignAccessright, 0, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnAssignAccessright, 0, SpringLayout.EAST, panel_1);
		panel_1.add(btnAssignAccessright);
		
		JPanel requirementPanel = new JPanel();
		tabbedPane.addTab("Requirment Management", null, requirementPanel, null);
		SpringLayout sl_requirementPanel = new SpringLayout();
		requirementPanel.setLayout(sl_requirementPanel);
		
		JPanel panel = new JPanel();
		sl_requirementPanel.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, requirementPanel);
		sl_requirementPanel.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, requirementPanel);
		sl_requirementPanel.putConstraint(SpringLayout.SOUTH, panel, 40, SpringLayout.NORTH, requirementPanel);
		sl_requirementPanel.putConstraint(SpringLayout.EAST, panel, 819, SpringLayout.WEST, requirementPanel);
		requirementPanel.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JButton addreqBtn = new JButton("Add Requirement");
		sl_panel.putConstraint(SpringLayout.NORTH, addreqBtn, 2, SpringLayout.NORTH, panel);
		addreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_panel.putConstraint(SpringLayout.EAST, addreqBtn, -10, SpringLayout.EAST, panel);
		panel.add(addreqBtn);
		
		reqSearchbar = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, reqSearchbar, 1, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, reqSearchbar, 254, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, reqSearchbar, -162, SpringLayout.WEST, addreqBtn);
		panel.add(reqSearchbar);
		reqSearchbar.setColumns(10);
		
		JButton searchreqBtn = new JButton("Search");
		sl_panel.putConstraint(SpringLayout.NORTH, searchreqBtn, 0, SpringLayout.NORTH, addreqBtn);
		sl_panel.putConstraint(SpringLayout.EAST, searchreqBtn, -6, SpringLayout.WEST, reqSearchbar);
		searchreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(searchreqBtn);
		
		JPanel resourcePanel = new JPanel();
		tabbedPane.addTab("Resource Management", null, resourcePanel, null);
		SpringLayout sl_resourcePanel = new SpringLayout();
		resourcePanel.setLayout(sl_resourcePanel);
		
		JTabbedPane resourcesTab = new JTabbedPane(JTabbedPane.TOP);
		sl_resourcePanel.putConstraint(SpringLayout.NORTH, resourcesTab, 10, SpringLayout.NORTH, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.WEST, resourcesTab, 10, SpringLayout.WEST, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.SOUTH, resourcesTab, 525, SpringLayout.NORTH, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.EAST, resourcesTab, 819, SpringLayout.WEST, resourcePanel);
		resourcePanel.add(resourcesTab);
		
		JPanel modulePanel = new JPanel();
		resourcesTab.addTab("Module", null, modulePanel, null);
		
		JPanel informationPanel = new JPanel();
		resourcesTab.addTab("Information", null, informationPanel, null);
		
		JPanel financialPanel = new JPanel();
		resourcesTab.addTab("Financial", null, financialPanel, null);
		
		JPanel physicalPanel = new JPanel();
		resourcesTab.addTab("Physical", null, physicalPanel, null);
		
		JPanel allPanel = new JPanel();
		resourcesTab.addTab("All", null, allPanel, null);
		
		JPanel projectPanel = new JPanel();
		tabbedPane.addTab("Project Management", null, projectPanel, null);
		SpringLayout sl_projectPanel = new SpringLayout();
		projectPanel.setLayout(sl_projectPanel);
		
		DefaultListModel<String> projectlistModel = new DefaultListModel<String>();

		JList<String> projectList = new JList<String>(projectlistModel);
		sl_projectPanel.putConstraint(SpringLayout.NORTH, projectList, 134, SpringLayout.NORTH, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.WEST, projectList, 90, SpringLayout.WEST, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.SOUTH, projectList, -127, SpringLayout.SOUTH, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.EAST, projectList, 737, SpringLayout.WEST, projectPanel);
		projectPanel.add(projectList);
		projectlistModel.addElement("hello");
		
		JPanel panel_2 = new JPanel();
		sl_projectPanel.putConstraint(SpringLayout.NORTH, panel_2, 10, SpringLayout.NORTH, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.WEST, panel_2, 10, SpringLayout.WEST, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.SOUTH, panel_2, 40, SpringLayout.NORTH, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.EAST, panel_2, 819, SpringLayout.WEST, projectPanel);
		projectPanel.add(panel_2);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);
		
		JButton addprojectBtn = new JButton("Add Project");
		sl_panel_2.putConstraint(SpringLayout.NORTH, addprojectBtn, 1, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, addprojectBtn, 0, SpringLayout.EAST, panel_2);
		sl_projectPanel.putConstraint(SpringLayout.EAST, addprojectBtn, 0, SpringLayout.EAST, panel_2);
		panel_2.add(addprojectBtn);
		addprojectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		txtSearch = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, txtSearch, -1, SpringLayout.NORTH, addprojectBtn);
		sl_panel_2.putConstraint(SpringLayout.WEST, txtSearch, -441, SpringLayout.WEST, addprojectBtn);
		sl_panel_2.putConstraint(SpringLayout.EAST, txtSearch, -204, SpringLayout.WEST, addprojectBtn);
		sl_projectPanel.putConstraint(SpringLayout.EAST, txtSearch, -168, SpringLayout.WEST, addprojectBtn);
		panel_2.add(txtSearch);
		sl_projectPanel.putConstraint(SpringLayout.SOUTH, txtSearch, -21, SpringLayout.NORTH, projectList);
		txtSearch.setText("Search");
		txtSearch.setColumns(10);
		sl_projectPanel.putConstraint(SpringLayout.NORTH, addprojectBtn, 1, SpringLayout.NORTH, txtSearch);
		
		JButton projsearchBtn = new JButton("Search");
		sl_panel_2.putConstraint(SpringLayout.NORTH, projsearchBtn, 0, SpringLayout.NORTH, addprojectBtn);
		sl_panel_2.putConstraint(SpringLayout.EAST, projsearchBtn, -1, SpringLayout.WEST, txtSearch);
		panel_2.add(projsearchBtn);
	}
}
