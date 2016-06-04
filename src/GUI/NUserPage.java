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
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import GUI.Form.Field;
import GUI.Form.Form;
import GUI.Form.PanelBuilder;
import Resource.ModuleCatalogue;
import Resource.ResourceCatalogue;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class NUserPage {

	private JFrame userpageFrame;
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
					window.userpageFrame.setVisible(true);
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
		userpageFrame = new JFrame();
		userpageFrame.setBounds(100, 100, 850, 600);
		userpageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userpageFrame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 850, 600);
		userpageFrame.getContentPane().add(tabbedPane);
		
		JPanel editPanel = new JPanel();
		tabbedPane.addTab("Edit Info", null, editPanel, null);
		SpringLayout sl_editPanel = new SpringLayout();
		editPanel.setLayout(sl_editPanel);
		
		
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
		SpringLayout sl_modulePanel = new SpringLayout();
		modulePanel.setLayout(sl_modulePanel);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_modulePanel.putConstraint(SpringLayout.NORTH, scrollPane, 87, SpringLayout.NORTH, modulePanel);
		sl_modulePanel.putConstraint(SpringLayout.WEST, scrollPane, -716, SpringLayout.EAST, modulePanel);
		sl_modulePanel.putConstraint(SpringLayout.SOUTH, scrollPane, 293, SpringLayout.NORTH, modulePanel);
		sl_modulePanel.putConstraint(SpringLayout.EAST, scrollPane, -32, SpringLayout.EAST, modulePanel);
		modulePanel.add(scrollPane);
		
		//get module list
		DefaultListModel<String> modulelistModel = new DefaultListModel<String>();

		JList moduleList = new JList(modulelistModel);
		scrollPane.setViewportView(moduleList);
		
		JButton btnAddModule = new JButton("Add Module");
		btnAddModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Field> moduleFields = new ArrayList<Field>();
				moduleFields.add(new Field("text","email","",10));
				moduleFields.add( new Field("text","username","",10));
				moduleFields.add( new Field("text","name","",10));
				
				moduleFields.add(new Field("text","lastname","",10));
				moduleFields.add(new Field("text","password","",10));
				moduleFields.add(new Field("text","repeat password","",10));

				Form moduleForm = new Form(moduleFields,"Sign up Form");
				PanelBuilder modulePanel = new PanelBuilder(moduleForm);
				modulePanel.makeForm();
				JFrame AddModulePage= new JFrame("Add Module Form");
				AddModulePage.getContentPane().add(modulePanel.getJPanel(),BorderLayout.NORTH);
				
				JButton submitaddmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
    		    buttonPanel.add(submitaddmoduleBtn);
    		    AddModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    		    AddModulePage.pack();
				AddModulePage.setVisible(true);
				
				submitaddmoduleBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ModuleCatalogue mcat = new ModuleCatalogue();
						System.out.println("all : ");
						mcat.readAllResources();
				        
//						mcat.addResource(Integer.parseInt(moduleform.getText(2)),Integer.parseInt(moduleform.getText(1)),moduleform.getText(0));
						// tu resource ham bayad insert she
						mcat.readAllResources();
					}
				});
				
					
			}
		});
		sl_modulePanel.putConstraint(SpringLayout.NORTH, btnAddModule, 28, SpringLayout.NORTH, modulePanel);
		sl_modulePanel.putConstraint(SpringLayout.EAST, btnAddModule, -50, SpringLayout.EAST, modulePanel);
		modulePanel.add(btnAddModule);
		
		ModuleCatalogue mcat = new ModuleCatalogue();
		System.out.println("all : ");
		ArrayList<HashMap<String, String>> allmodules = mcat.readAllResources();
		moduleList.removeAll();

		for (int i = 0; i < allmodules.size(); i++) {
			System.out.println(allmodules.get(i));
			modulelistModel.addElement(""+allmodules.get(i).toString().substring(0, 7)+" " +allmodules.get(i).toString().subSequence(7, allmodules.get(i).toString().length()));
		}

		//end get module list
		
		JPanel informationPanel = new JPanel();
		resourcesTab.addTab("Information", null, informationPanel, null);
		
		JButton btnAddInformation = new JButton("Add Information Resource");
		informationPanel.add(btnAddInformation);
		
		JPanel financialPanel = new JPanel();
		resourcesTab.addTab("Financial", null, financialPanel, null);
		
		JButton btnAddFinancial = new JButton("Add Financial Resource");
		financialPanel.add(btnAddFinancial);
		
		JPanel physicalPanel = new JPanel();
		resourcesTab.addTab("Physical", null, physicalPanel, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_physicalPanel = new GroupLayout(physicalPanel);
		gl_physicalPanel.setHorizontalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addGroup(gl_physicalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_physicalPanel.createSequentialGroup()
							.addGap(135)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_physicalPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_physicalPanel.setVerticalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(94)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		SpringLayout sl_panel_3 = new SpringLayout();
		panel_3.setLayout(sl_panel_3);
		
		JButton btnAddPhysicalResource = new JButton("Add Physical Resource");
		btnAddPhysicalResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAddPhysical addphysWindow = new NAddPhysical();
				addphysWindow.getAddPhysResFrame().setVisible(true);
			}
		});
		sl_panel_3.putConstraint(SpringLayout.NORTH, btnAddPhysicalResource, 0, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, btnAddPhysicalResource, 0, SpringLayout.EAST, panel_3);
		panel_3.add(btnAddPhysicalResource);
		
		//get phys res list
		DefaultListModel<String> physicalreslistModel = new DefaultListModel<String>();

		
		JList physicalresList = new JList(physicalreslistModel);
		scrollPane_2.setViewportView(physicalresList);
		physicalPanel.setLayout(gl_physicalPanel);
		
		
		
		//end get phys res list
		
		JPanel allPanel = new JPanel();
		resourcesTab.addTab("All", null, allPanel, null);
		SpringLayout sl_allPanel = new SpringLayout();
		allPanel.setLayout(sl_allPanel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_allPanel.putConstraint(SpringLayout.NORTH, scrollPane_1, 85, SpringLayout.NORTH, allPanel);
		sl_allPanel.putConstraint(SpringLayout.WEST, scrollPane_1, 50, SpringLayout.WEST, allPanel);
		sl_allPanel.putConstraint(SpringLayout.SOUTH, scrollPane_1, 320, SpringLayout.NORTH, allPanel);
		sl_allPanel.putConstraint(SpringLayout.EAST, scrollPane_1, -40, SpringLayout.EAST, allPanel);
		allPanel.add(scrollPane_1);
		
		DefaultListModel<String> allreslistModel = new DefaultListModel<String>();

		JList allresList = new JList(allreslistModel);
		scrollPane_1.setViewportView(allresList);
		
		//get all res list
		ResourceCatalogue rcat = new ResourceCatalogue();
		System.out.println("all : ");
		ArrayList<HashMap<String, String>> allres = rcat.readAllResources();
		allresList.removeAll();
		
		for (int i = 0; i < allres.size(); i++) {
			System.out.println(allres.get(i));
			allreslistModel.addElement(""+allres.get(i).toString().substring(0, 7)+" "+allres.get(i).toString().subSequence(7, allres.get(i).toString().length()));
		}
		
		//end get res list
		
		JPanel projectPanel = new JPanel();
		tabbedPane.addTab("Project Management", null, projectPanel, null);
		tabbedPane.setEnabledAt(4, true);
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
	public JFrame getUserpageFrame() {
		return userpageFrame;
	}
}
