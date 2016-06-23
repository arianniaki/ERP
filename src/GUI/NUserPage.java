package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import GUI.Form.ComboBoxJPanel;
import GUI.Form.Field;
import GUI.Form.FieldPanel;
import GUI.Form.Form;
import GUI.Form.PanelBuilder;
import ProjectEmployee.AuthenticatedEmployee;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.SubSystem.SubSystemCatalogue;
import ResourceManagement.Section.Resource.FinancialResourceCatalogue;
import ResourceManagement.Section.Resource.InformationResourceCatalogue;
import ResourceManagement.Section.Resource.ModuleCatalogue;
import ResourceManagement.Section.Resource.PhysicalResourceCatalogue;
import ResourceManagement.Section.Resource.ResourceCatalogue;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListModel;
import javax.swing.JTable;

public class NUserPage {

	private JFrame userpageFrame;
	private JTextField editname_textField;
	private JTextField editfamilyname_textField;
	private ArrayList<HashMap<String, String>> allmodules;
	private ArrayList<HashMap<String, String>> allphysicals;
	private ArrayList<HashMap<String, String>> allfinance;
	private ArrayList<HashMap<String, String>> allinformation;
	private ArrayList<HashMap<String, String>> allres;
	private ArrayList<HashMap<String, String>> allprojects;
	private ArrayList<HashMap<String, String>> allemployees;
	private ArrayList<HashMap<String, String>> allsubsystems;

	
	private JTable finan_table;
	private JTable information_table;
	private JTable module_table;
	private JTable physical_table;
	private JTable allresource_table;
	private JTable project_table;
	private JTable subsystem_table;
	private JTable accessright_table;
	private JTable registered_table;
	private JTable human_table;
	private JTable maintaining_table;
	
	private int selected_project_forsubsystem;

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
	 * 
	 * @param loggedin_user
	 */
	public NUserPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		userpageFrame = new JFrame();
		userpageFrame.setBounds(100, 100, 870, 585);
		userpageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userpageFrame.getContentPane().setLayout(null);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 858, 551);
		userpageFrame.getContentPane().add(tabbedPane);

		JPanel editPanel = new JPanel();
		tabbedPane.addTab("Edit Info", null, editPanel, null);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AuthenticatedEmployee.getInstance().getEmployee().logout()){
					System.out.println("logged out");
					userpageFrame.dispose();
					NLoginPage loginWindow = new NLoginPage();
					loginWindow.getloginpageFrame().setVisible(true);

				}
			}
		});

		JLabel lblName = new JLabel("Name");

		editname_textField = new JTextField();
		editname_textField.setColumns(10);
		editname_textField.setText("TO BE SET LATER");
		editfamilyname_textField = new JTextField();
		editfamilyname_textField.setColumns(10);

		JLabel lblFamilyName = new JLabel("Family Name");
		GroupLayout gl_editPanel = new GroupLayout(editPanel);
		gl_editPanel.setHorizontalGroup(gl_editPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_editPanel.createSequentialGroup().addContainerGap(575, Short.MAX_VALUE)
						.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(editname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(editfamilyname_textField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(40).addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFamilyName).addComponent(lblName).addComponent(btnLogout))));
		gl_editPanel.setVerticalGroup(gl_editPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_editPanel
				.createSequentialGroup().addContainerGap().addComponent(btnLogout).addGap(59)
				.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(editname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
				.addGap(26)
				.addGroup(gl_editPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(editfamilyname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFamilyName))
				.addContainerGap(329, Short.MAX_VALUE)));
		editPanel.setLayout(gl_editPanel);

		JPanel accessrightPanel = new JPanel();
		if(AuthenticatedEmployee.getInstance().getEmployee().getAccessRight().getName().equals("super")  ){
			tabbedPane.addTab("AccessRight Management", null, accessrightPanel, null);
		}

		JButton btnAssignAccessright = new JButton("Assign AccessRight");
		btnAssignAccessright.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> accessrights_types = new ArrayList<String>();
				accessrights_types.add("Default");
				accessrights_types.add("Intermediate");
				accessrights_types.add("Super");
				ArrayList<Field> acessright_assignFields = new ArrayList<Field>();
				Field access_right = new Field("comboBox", "accessrights", accessrights_types, 20, "items");

				acessright_assignFields.add(access_right);
				
				Form accessright_Form = new Form(acessright_assignFields, "AccessRight Form");
				final PanelBuilder accessright_assignPanel = new PanelBuilder(accessright_Form);
				accessright_assignPanel.makeForm();

				JFrame Assign_AccessRightPage = new JFrame("Assign Access Right Form");
				Assign_AccessRightPage.getContentPane().add(accessright_assignPanel.getJPanel(), BorderLayout.NORTH);

				JButton submitaddrequeirementBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddrequeirementBtn);
				Assign_AccessRightPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Assign_AccessRightPage.pack();
				Assign_AccessRightPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_accessright = (ComboBoxJPanel) accessright_assignPanel.getJPanel().getComponent(0);
				final JComboBox accessright_type = comboBoxpanel_accessright.getComboBox();

				accessright_type.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(accessright_type.getSelectedItem() + " ino select kardi access right");
					}
				});
			}
		});

		JScrollPane accessright_scrollPane = new JScrollPane();
		GroupLayout gl_accessrightPanel = new GroupLayout(accessrightPanel);
		gl_accessrightPanel.setHorizontalGroup(
			gl_accessrightPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(btnAssignAccessright))
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addGap(41)
					.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
					.addGap(39))
		);
		gl_accessrightPanel.setVerticalGroup(
			gl_accessrightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAssignAccessright)
					.addGap(48)
					.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
					.addGap(32))
		);
		
		
		// get employee list
		final DefaultListModel<String> employeelistModel = new DefaultListModel<String>();

		EmployeeCatalogue empcat = new EmployeeCatalogue();
		System.out.println("all : ");
		allemployees = empcat.readAllEmployees();

		for (int i = 0; i < allemployees.size(); i++) {
			System.out.println(allemployees.get(i));
			employeelistModel.addElement("" + allemployees.get(i).get("username"));
		}
		// end employee list

		
		
		String[] accessright_columns = new String[] { "Id", "Username","AccessRight" };

		final DefaultTableModel accessright_tableModel = new DefaultTableModel(accessright_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		
		
		accessright_table = new JTable(accessright_tableModel);
		for (int i = 0; i < allemployees.size(); i++) {
			Object[] objs = { allemployees.get(i).get("empid"), allemployees.get(i).get("empname") };
			accessright_tableModel.addRow(objs);
		}

		accessright_scrollPane.setViewportView(accessright_table);
		accessrightPanel.setLayout(gl_accessrightPanel);

		// project mgmt panel
		final JPanel projectPanel = new JPanel();

		//
		
		// subsystem panel
		final JPanel subsystemPanel = new JPanel();
		tabbedPane.addTab("SubSystem", null, subsystemPanel, null);
		tabbedPane.remove(tabbedPane.getTabCount()-1); //remove subsystem tab
		JScrollPane subsystem_scrollPane = new JScrollPane();
		
		JButton btnBacktoProject = new JButton("Back");
		btnBacktoProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("change tab to project mgmt");
				System.out.println("Change JPanel");
				int selected_index= tabbedPane.getSelectedIndex();
				tabbedPane.remove(selected_index);
				tabbedPane.insertTab("Project Management", null, projectPanel, null, selected_index);
				tabbedPane.setSelectedComponent(projectPanel);				
			}
		});
		
		
		
		String[] subsystem_columns = new String[] { "Name","ProjectId" };

		final DefaultTableModel subsystem_tableModel = new DefaultTableModel(subsystem_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		//THIS SHOULD BE CHANGED +++
		SubSystemCatalogue subsyscat = new SubSystemCatalogue();
		allsubsystems = subsyscat.getSubSystems();
		for (int i = 0; i < allsubsystems.size(); i++) {
			Object[] objs = { allsubsystems.get(i).get("sname"), allsubsystems.get(i).get("pid") };
			subsystem_tableModel.addRow(objs);
		}
		
		
		JButton addsubsystemBtn = new JButton("Add Subsystem");
		addsubsystemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> subsystem_addFields = new ArrayList<Field>();

				Field subsystem_name = new Field("text", "Subsystem Name", "", 10, "name");
				Field subsystem_desc = new Field("text", "Subsystem Description", "", 30, "desc");

				subsystem_addFields.add(subsystem_name);
				subsystem_addFields.add(subsystem_desc);

				Form subsystem_Form = new Form(subsystem_addFields, "Subsystem Form");
				final PanelBuilder subsystemAdd_Panel = new PanelBuilder(subsystem_Form);
				subsystemAdd_Panel.makeForm();

				JFrame Add_SubsystemPage = new JFrame("Add Subsystem Form");
				Add_SubsystemPage.getContentPane().add(subsystemAdd_Panel.getJPanel(), BorderLayout.NORTH);

				JButton submitaddsubsystemBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddsubsystemBtn);
				Add_SubsystemPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_SubsystemPage.pack();
				Add_SubsystemPage.setVisible(true);
				submitaddsubsystemBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						SubSystemCatalogue subsyscat = new SubSystemCatalogue();
						System.out.println("all : ");
						subsyscat.getSubSystems();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < subsystemAdd_Panel.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) subsystemAdd_Panel.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " subsystesm");
						}
						
						subsyscat.addSubSystem(inputs.get(0).toString(), selected_project_forsubsystem);
						
						allsubsystems = subsyscat.getSubSystems();
						int rowcount= subsystem_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							subsystem_tableModel.removeRow(j);
						}
						System.out.println(subsystem_tableModel.getRowCount()+" ---");
						for (int i = 0; i < allsubsystems.size(); i++) {
							Object[] objs = { allsubsystems.get(i).get("sname"), allsubsystems.get(i).get("pid") };
							subsystem_tableModel.addRow(objs);
						}
						
						
					}
				});
				
				
				
			}
		});
		GroupLayout gl_subsystemPanel = new GroupLayout(subsystemPanel);
		gl_subsystemPanel.setHorizontalGroup(
			gl_subsystemPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_subsystemPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(subsystem_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
					.addGap(40))
				.addGroup(gl_subsystemPanel.createSequentialGroup()
					.addComponent(btnBacktoProject)
					.addPreferredGap(ComponentPlacement.RELATED, 591, Short.MAX_VALUE)
					.addComponent(addsubsystemBtn, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_subsystemPanel.setVerticalGroup(
			gl_subsystemPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_subsystemPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_subsystemPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBacktoProject)
						.addComponent(addsubsystemBtn))
					.addGap(5)
					.addComponent(subsystem_scrollPane, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addGap(40))
		);
		
		subsystem_table = new JTable(subsystem_tableModel);
		subsystem_scrollPane.setViewportView(subsystem_table);
		subsystemPanel.setLayout(gl_subsystemPanel);
		//
		JPanel requirementPanel = new JPanel();
		tabbedPane.addTab("Requirment Management", null, requirementPanel, null);

		JButton addreqBtn = new JButton("Add Requirement");
		
		addreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> resource_types = new ArrayList<String>();
				final ArrayList<String> resources = new ArrayList<String>();
				resource_types.add("Information");
				resource_types.add("Financial");
				resource_types.add("Physical");
				resource_types.add("Employee");
				ArrayList<Field> requirement_moduleFields = new ArrayList<Field>();
				Field reqname = new Field("text", "req name       ", "", 10, "name");
				Field req_res_type = new Field("comboBox", "resource types", resource_types, 20, "items");
				Field req_res = new Field("comboBox", "resources", resources, 20, "items");

				requirement_moduleFields.add(reqname);
				requirement_moduleFields.add(req_res_type);
				requirement_moduleFields.add(req_res);

				Form requirement_Form = new Form(requirement_moduleFields, "Requirement Form");
				final PanelBuilder requirement_Panel = new PanelBuilder(requirement_Form);
				requirement_Panel.makeForm();

				JFrame Add_RequirementPage = new JFrame("Add Requirement Module Form");
				Add_RequirementPage.getContentPane().add(requirement_Panel.getJPanel(), BorderLayout.NORTH);

				JButton submitaddrequeirementBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddrequeirementBtn);
				Add_RequirementPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_RequirementPage.pack();
				Add_RequirementPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_restype = (ComboBoxJPanel) requirement_Panel.getJPanel().getComponent(1);
				ComboBoxJPanel comboBoxpane_res = (ComboBoxJPanel) requirement_Panel.getJPanel().getComponent(2);
				final JComboBox resource_type = comboBoxpanel_restype.getComboBox();
				final JComboBox resourceCombo = comboBoxpane_res.getComboBox();

				resourceCombo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(resourceCombo.getSelectedItem() + " ino select kardi balla");
					}
				});

				resource_type.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (resource_type.getSelectedItem().toString().equals("Financial")) {
							resourceCombo.removeAllItems();
							FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
							ArrayList<HashMap<String, String>> financial_resource = financat.readAllResources();
							for (int i = 0; i < financial_resource.size(); i++) {
								resourceCombo.addItem(financial_resource.get(i).toString());
							}
						}
						if (resource_type.getSelectedItem().toString().equals("Physical")) {
							resourceCombo.removeAllItems();
							PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
							ArrayList<HashMap<String, String>> physical_resource = physcat.readAllResources();
							for (int i = 0; i < physical_resource.size(); i++) {
								resourceCombo.addItem(physical_resource.get(i).toString());
							}

						}
						if (resource_type.getSelectedItem().toString().equals("Information")) {
							resourceCombo.removeAllItems();
							InformationResourceCatalogue infocat = new InformationResourceCatalogue();
							ArrayList<HashMap<String, String>> information_resource = infocat.readAllResources();
							for (int i = 0; i < information_resource.size(); i++) {
								resourceCombo.addItem(information_resource.get(i).toString());
							}

						}
						if (resource_type.getSelectedItem().toString().equals("Employee")) {
							resourceCombo.removeAllItems();
							EmployeeCatalogue empcat = new EmployeeCatalogue();
							ArrayList<HashMap<String, String>> employee_resource = empcat.readAllEmployees();
							for (int i = 0; i < employee_resource.size(); i++) {
								resourceCombo.addItem(employee_resource.get(i).toString());
							}
						}
					}
				});
				submitaddrequeirementBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < requirement_Panel.getJPanel().getComponentCount(); i++) {
							// System.out.println(fpanel.selected_Choice);
						}

					}
				});

			}
		});

		JButton searchreqBtn = new JButton("Search");

		JScrollPane requirement_scrollPane = new JScrollPane();
		
		JButton requirement_btnEdit = new JButton("Edit");
		
		JButton requirement_btnSatisfy = new JButton("Satisfy");
		GroupLayout gl_requirementPanel = new GroupLayout(requirementPanel);
		gl_requirementPanel.setHorizontalGroup(
			gl_requirementPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_requirementPanel.createSequentialGroup()
					.addComponent(requirement_btnEdit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(requirement_btnSatisfy)
					.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
					.addComponent(searchreqBtn)
					.addGap(242)
					.addComponent(addreqBtn))
				.addGroup(gl_requirementPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
					.addGap(40))
		);
		gl_requirementPanel.setVerticalGroup(
			gl_requirementPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_requirementPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_requirementPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchreqBtn)
						.addComponent(addreqBtn)
						.addComponent(requirement_btnEdit)
						.addComponent(requirement_btnSatisfy))
					.addGap(40)
					.addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
					.addGap(40))
		);

		JList<String> requirement_list = new JList<String>();
		requirement_scrollPane.setViewportView(requirement_list);
		requirement_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		requirementPanel.setLayout(gl_requirementPanel);
		searchreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JPanel resourcePanel = new JPanel();
		tabbedPane.addTab("Resource Management", null, resourcePanel, null);
		SpringLayout sl_resourcePanel = new SpringLayout();
		resourcePanel.setLayout(sl_resourcePanel);

		final JTabbedPane resourcesTab = new JTabbedPane(JTabbedPane.TOP);
		sl_resourcePanel.putConstraint(SpringLayout.NORTH, resourcesTab, 10, SpringLayout.NORTH, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.WEST, resourcesTab, 10, SpringLayout.WEST, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.SOUTH, resourcesTab, 525, SpringLayout.NORTH, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.EAST, resourcesTab, 827, SpringLayout.WEST, resourcePanel);
		resourcePanel.add(resourcesTab);

		
		
		//
		// get information list
		final DefaultListModel<String> humanlistModel = new DefaultListModel<String>();

		EmployeeCatalogue employeecat = new EmployeeCatalogue();
		System.out.println("all : ");
		allemployees = employeecat.readAllEmployees();

		for (int i = 0; i < allemployees.size(); i++) {
			System.out.println(allemployees.get(i));
			humanlistModel.addElement("" + allemployees.get(i).get("irname") + "\t" + allemployees.get(i).get("rid"));
		}
		
		
		
		JPanel humanPanel = new JPanel();
		resourcesTab.addTab("Human", null, humanPanel, null);
		
		JScrollPane human_scrollPane = new JScrollPane();
		
		JButton human_btnEdit = new JButton("Edit");
		human_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton human_btnDelete = new JButton("Delete");
		human_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_humanPanel = new GroupLayout(humanPanel);
		gl_humanPanel.setHorizontalGroup(
			gl_humanPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_humanPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(human_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
				.addGroup(gl_humanPanel.createSequentialGroup()
					.addComponent(human_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(human_btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(640, Short.MAX_VALUE))
		);
		gl_humanPanel.setVerticalGroup(
			gl_humanPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_humanPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_humanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(human_btnEdit)
						.addComponent(human_btnDelete))
					.addGap(30)
					.addComponent(human_scrollPane, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
					.addGap(30))
		);

		String[] human_columns = new String[] { "Id", "Name" };

		final DefaultTableModel human_tableModel = new DefaultTableModel(human_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		
		
		human_table = new JTable(human_tableModel);
		human_scrollPane.setViewportView(human_table);
		humanPanel.setLayout(gl_humanPanel);

		for (int i = 0; i < allemployees.size(); i++) {
			Object[] objs = { allemployees.get(i).get("empid"), allemployees.get(i).get("empname") };
			human_tableModel.addRow(objs);
		}
		
		
		
		
		//
		
		
		// get information list
		final DefaultListModel<String> informationlistModel = new DefaultListModel<String>();

		InformationResourceCatalogue infocat = new InformationResourceCatalogue();
		System.out.println("all : ");
		allinformation = infocat.readAllResources();

		for (int i = 0; i < allinformation.size(); i++) {
			System.out.println(allinformation.get(i));
			informationlistModel
					.addElement("" + allinformation.get(i).get("irname") + "\t" + allinformation.get(i).get("rid"));
		}
		
	
		
		// end get information list

		JPanel informationPanel = new JPanel();
		resourcesTab.addTab("Information", null, informationPanel, null);

		JButton btnAddInformation = new JButton("Add Information Resource");
		
		JScrollPane information_scrollPane = new JScrollPane();
		String[] info_columns = new String[] { "Id", "Name" };

		final DefaultTableModel information_tableModel = new DefaultTableModel(info_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		information_table = new JTable(information_tableModel);
		for (int i = 0; i < allinformation.size(); i++) {
			Object[] objs = { allinformation.get(i).get("rid"), allinformation.get(i).get("irname") };
			information_tableModel.addRow(objs);
		}

		information_scrollPane.setViewportView(information_table);

		JButton information_btnEdit = new JButton("Edit");
		information_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(information_tableModel.getDataVector().elementAt(information_table.getSelectedRow()).toString().contains("1"));

			}
		});
		
		JButton information_btnDelete = new JButton("Delete");
		GroupLayout gl_informationPanel = new GroupLayout(informationPanel);
		gl_informationPanel.setHorizontalGroup(
			gl_informationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addComponent(information_btnEdit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(information_btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
					.addComponent(btnAddInformation))
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(information_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_informationPanel.setVerticalGroup(
			gl_informationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_informationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddInformation)
						.addComponent(information_btnEdit)
						.addComponent(information_btnDelete))
					.addGap(30)
					.addComponent(information_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(30))
		);

		informationPanel.setLayout(gl_informationPanel);
		btnAddInformation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> information_moduleFields = new ArrayList<Field>();
				information_moduleFields.add(new Field("text", "infosname", "", 20, "name"));

				Form information_moduleForm = new Form(information_moduleFields, "Information Module Form");
				final PanelBuilder information_modulePanel = new PanelBuilder(information_moduleForm);
				information_modulePanel.makeForm();
				JFrame Add_InformationModulePage = new JFrame("Add Information Module Form");
				Add_InformationModulePage.getContentPane().add(information_modulePanel.getJPanel(), BorderLayout.NORTH);

				JButton submitaddinformationmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddinformationmoduleBtn);
				Add_InformationModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_InformationModulePage.pack();
				Add_InformationModulePage.setVisible(true);

				submitaddinformationmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						InformationResourceCatalogue infocat = new InformationResourceCatalogue();
						System.out.println("all : ");
						infocat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < information_modulePanel.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) information_modulePanel.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " information");
						}
						infocat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
						allinformation.clear();
						allinformation = infocat.readAllResources();
						System.out.println(information_tableModel.getRowCount()+" ---");
						int rowcount= information_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							information_tableModel.removeRow(j);
						}
						System.out.println(information_tableModel.getRowCount()+" ---");
						for (int i = 0; i < allinformation.size(); i++) {
							Object[] objs = { allinformation.get(i).get("rid"), allinformation.get(i).get("irname") };
							information_tableModel.addRow(objs);
						}
					}
				});
			}
		});

		// get financial list
		final DefaultListModel<String> financiallistModel = new DefaultListModel<String>();

		FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
		System.out.println("all : ");
		allfinance = financat.readAllResources();

		for (int i = 0; i < allfinance.size(); i++) {
			System.out.println(allfinance.get(i));
			financiallistModel.addElement("" + allfinance.get(i).get("finanname"));
		}

		// end get financial list

		JPanel financialPanel = new JPanel();
		resourcesTab.addTab("Financial", null, financialPanel, null);
		JButton btnAddFinancial = new JButton("Add Financial Resource");
		JButton financial_btnEdit = new JButton("Edit");
		
		for (int i = 0; i < allfinance.size(); i++) {
			financiallistModel.addElement("" + allfinance.get(i).get("finanname"));
		}
		String[] finan_columns = new String[] { "Id", "Name" };

		final DefaultTableModel financial_tableModel = new DefaultTableModel(finan_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		JScrollPane financial_table_scrollPane = new JScrollPane();
		finan_table = new JTable(financial_tableModel);
		for (int i = 0; i < allfinance.size(); i++) {
			Object[] objs = { allfinance.get(i).get("rid"), allfinance.get(i).get("finanname") };
			financial_tableModel.addRow(objs);
		}

		financial_table_scrollPane.setViewportView(finan_table);
		
		JButton financial_btnDelete = new JButton("Delete");

		GroupLayout gl_financialPanel = new GroupLayout(financialPanel);
		gl_financialPanel.setHorizontalGroup(
			gl_financialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addComponent(financial_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(financial_btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(461)
					.addComponent(btnAddFinancial))
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(financial_table_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_financialPanel.setVerticalGroup(
			gl_financialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_financialPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddFinancial)
						.addComponent(financial_btnEdit)
						.addComponent(financial_btnDelete))
					.addGap(30)
					.addComponent(financial_table_scrollPane, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addGap(30))
		);

		financialPanel.setLayout(gl_financialPanel);
		btnAddFinancial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Field> financial_moduleFields = new ArrayList<Field>();
				financial_moduleFields.add(new Field("text", "financename", "", 20, "name"));

				Form financial_moduleForm = new Form(financial_moduleFields, "Information Module Form");
				final PanelBuilder financial_modulePanel = new PanelBuilder(financial_moduleForm);
				financial_modulePanel.makeForm();
				JFrame Add_FinancialModulePage = new JFrame("Add Financial Module Form");
				Add_FinancialModulePage.getContentPane().add(financial_modulePanel.getJPanel(), BorderLayout.NORTH);

				JButton submitaddfinancialmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddfinancialmoduleBtn);
				Add_FinancialModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_FinancialModulePage.pack();
				Add_FinancialModulePage.setVisible(true);
				submitaddfinancialmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
						System.out.println("all : ");
						financat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < financial_modulePanel.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) financial_modulePanel.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " financial");
						}
						financat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
						allfinance.clear();
						allfinance = financat.readAllResources();
						System.out.println(financial_tableModel.getRowCount()+" ---");
						int rowcount= financial_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							System.out.println(j);
							financial_tableModel.removeRow(j);
						}
						System.out.println(financial_tableModel.getRowCount()+" ---");
						for (int i = 0; i < allfinance.size(); i++) {
							Object[] objs = { allfinance.get(i).get("rid"), allfinance.get(i).get("finanname") };
							financial_tableModel.addRow(objs);
						}


					}
				});
			}
		});

		// get module list
		final DefaultListModel<String> modulelistModel = new DefaultListModel<String>();

		ModuleCatalogue mcat = new ModuleCatalogue();
		System.out.println("all : ");
		allmodules = mcat.readAllResources();

		for (int i = 0; i < allmodules.size(); i++) {
			System.out.println(allmodules.get(i));
			modulelistModel.addElement("" + allmodules.get(i).get("modname"));
		}
		// end module list
		final JPanel modulePanel = new JPanel();
		resourcesTab.addTab("Module", null, modulePanel, null);
		String[] module_columns = new String[] { "Id", "Name" };

		final DefaultTableModel module_tableModel = new DefaultTableModel(module_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		JButton btnAddModule = new JButton("Add Module");
		btnAddModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Field> moduleFields = new ArrayList<Field>();
				moduleFields.add(new Field("text", "name", "", 10, "name"));

				Form moduleForm = new Form(moduleFields, "Module Form");
				final PanelBuilder modulePanel = new PanelBuilder(moduleForm);
				modulePanel.makeForm();
				JFrame AddModulePage = new JFrame("Add Module Form");
				AddModulePage.getContentPane().add(modulePanel.getJPanel(), BorderLayout.NORTH);

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
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < modulePanel.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) modulePanel.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}
						mcat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
						allmodules.clear();
						allmodules = mcat.readAllResources();
						System.out.println(module_tableModel.getRowCount()+" ---");
						int rowcount= module_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							System.out.println(j);
							module_tableModel.removeRow(j);
						}
						System.out.println(module_tableModel.getRowCount()+" ---");
						for (int i = 0; i < allmodules.size(); i++) {
							Object[] objs = { allmodules.get(i).get("rid"), allmodules.get(i).get("modname") };
							module_tableModel.addRow(objs);
						}
					}
				});

			}
		});

		
		
		JScrollPane module_scrollPane = new JScrollPane();

		JButton module_btnEdit = new JButton("Edit");
		
		JButton module_btnDelete = new JButton("Delete");
		final JPanel maintaining_panel = new JPanel();

		JButton btnViewMaintaning = new JButton("View Maintaning");
		btnViewMaintaning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("-----");
			      int rowIndex = module_table.getSelectedRow();
			      int colIndex = module_table.getSelectedColumn();

			    String Table_click = (module_table.getModel().getValueAt(rowIndex, 0).toString()); //return the thing in the 0st column
			    System.out.println(Table_click);
			    System.out.println("-----");
				System.out.println("Change JPanel");
				int selected_index= resourcesTab.getSelectedIndex();
				resourcesTab.remove(selected_index);
				resourcesTab.insertTab("Maintaining", null, maintaining_panel , null, selected_index);
				resourcesTab.setSelectedComponent(maintaining_panel);				
			}
		});
		GroupLayout gl_modulePanel_1 = new GroupLayout(modulePanel);
		gl_modulePanel_1.setHorizontalGroup(
			gl_modulePanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modulePanel_1.createSequentialGroup()
					.addComponent(module_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(module_btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 411, Short.MAX_VALUE)
					.addComponent(btnViewMaintaning)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddModule))
				.addGroup(gl_modulePanel_1.createSequentialGroup()
					.addGap(30)
					.addComponent(module_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_modulePanel_1.setVerticalGroup(
			gl_modulePanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modulePanel_1.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddModule)
						.addComponent(module_btnEdit)
						.addComponent(module_btnDelete)
						.addComponent(btnViewMaintaning))
					.addGap(30)
					.addComponent(module_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(30))
		);
		


		module_table = new JTable(module_tableModel);
		for (int i = 0; i < allmodules.size(); i++) {
			Object[] objs = { allmodules.get(i).get("rid"), allmodules.get(i).get("modname") };
			module_tableModel.addRow(objs);
		}
		module_scrollPane.setViewportView(module_table);
		modulePanel.setLayout(gl_modulePanel_1);

		// get phys res list
		final DefaultListModel<String> physicalreslistModel = new DefaultListModel<String>();

		PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
		System.out.println("all : ");
		allphysicals = physcat.readAllResources();

		for (int i = 0; i < allphysicals.size(); i++) {
			System.out.println(allphysicals.get(i));
			physicalreslistModel.addElement("" + allphysicals.get(i).get("physname"));
		}
		
		resourcesTab.addTab("Maintaining Module", null, maintaining_panel, null);
		resourcesTab.remove(resourcesTab.getTabCount()-1); //remove maintaining tab

		JScrollPane maintaining_scrollPane = new JScrollPane();
		
		JButton maintaining_btnEdit = new JButton("Edit");
		
		JButton maintaining_btnDelete = new JButton("Delete");
		maintaining_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAddMaintaining = new JButton("Add Maintaining");
		btnAddMaintaining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnBacktoModule = new JButton("Back");
		btnBacktoModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("change tab to module");
				System.out.println("Change JPanel");
				int selected_index= resourcesTab.getSelectedIndex();
				resourcesTab.remove(selected_index);
				resourcesTab.insertTab("Module", null, modulePanel, null, selected_index);
				resourcesTab.setSelectedComponent(modulePanel);	
				
			}
		});
		GroupLayout gl_maintaining_panel = new GroupLayout(maintaining_panel);
		gl_maintaining_panel.setHorizontalGroup(
			gl_maintaining_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_maintaining_panel.createSequentialGroup()
					.addGap(30)
					.addComponent(maintaining_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
				.addGroup(gl_maintaining_panel.createSequentialGroup()
					.addComponent(maintaining_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(maintaining_btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBacktoModule)
					.addPreferredGap(ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
					.addComponent(btnAddMaintaining, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
		);
		gl_maintaining_panel.setVerticalGroup(
			gl_maintaining_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_maintaining_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_maintaining_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(maintaining_btnEdit)
						.addComponent(maintaining_btnDelete)
						.addComponent(btnAddMaintaining)
						.addComponent(btnBacktoModule))
					.addGap(30)
					.addComponent(maintaining_scrollPane, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addGap(30))
		);
		
		maintaining_table = new JTable();
		maintaining_scrollPane.setViewportView(maintaining_table);
		maintaining_panel.setLayout(gl_maintaining_panel);

		//end phys res 
		
		JPanel physicalPanel = new JPanel();
		resourcesTab.addTab("Physical", null, physicalPanel, null);
		String[] physical_columns = new String[] { "Id", "Name" };

		final DefaultTableModel phyiscal_tableModel = new DefaultTableModel(physical_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		for (int i = 0; i < allphysicals.size(); i++) {
			Object[] objs = { allphysicals.get(i).get("rid"), allphysicals.get(i).get("physname") };
			phyiscal_tableModel.addRow(objs);
		}

		
		

		JButton btnAddPhysicalResource = new JButton("Add Physical Resource");
		btnAddPhysicalResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> physical_moduleFields = new ArrayList<Field>();
				physical_moduleFields.add(new Field("text", "physname", "", 20, "name"));

				Form physical_moduleForm = new Form(physical_moduleFields, "Physical Module Form");
				final PanelBuilder physical_modulePanel = new PanelBuilder(physical_moduleForm);
				physical_modulePanel.makeForm();
				JFrame Add_PhysicalModulePage = new JFrame("Add Physical Module Form");
				Add_PhysicalModulePage.getContentPane().add(physical_modulePanel.getJPanel(), BorderLayout.NORTH);

				JButton submitaddphysicalmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddphysicalmoduleBtn);
				Add_PhysicalModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_PhysicalModulePage.pack();
				Add_PhysicalModulePage.setVisible(true);
				submitaddphysicalmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
						System.out.println("all : ");
						physcat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < physical_modulePanel.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) physical_modulePanel.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " physical");
						}
						physcat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
						allphysicals.clear();
						allphysicals = physcat.readAllResources();
						System.out.println(phyiscal_tableModel.getRowCount()+" ---");
						int rowcount= phyiscal_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							phyiscal_tableModel.removeRow(j);
						}
						System.out.println(phyiscal_tableModel.getRowCount()+" ---");
						for (int i = 0; i < allphysicals.size(); i++) {
							Object[] objs = { allphysicals.get(i).get("rid"), allphysicals.get(i).get("physname") };
							phyiscal_tableModel.addRow(objs);
						}

					}
				});
			}
		});

		JScrollPane physical_scrollPane = new JScrollPane();

		JButton physical_btnEdit = new JButton("Edit");
		
		JButton physical_btnDelete = new JButton("Delete");
		
		
		
		
		GroupLayout gl_physicalPanel = new GroupLayout(physicalPanel);
		gl_physicalPanel.setHorizontalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addComponent(physical_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(physical_btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
					.addComponent(btnAddPhysicalResource))
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_physicalPanel.setVerticalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_physicalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddPhysicalResource)
						.addComponent(physical_btnEdit)
						.addComponent(physical_btnDelete))
					.addGap(30)
					.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addGap(30))
		);
		
		
		physical_table = new JTable(phyiscal_tableModel);
		physical_scrollPane.setViewportView(physical_table);
		physicalPanel.setLayout(gl_physicalPanel);

		JPanel allPanel = new JPanel();
		resourcesTab.addTab("All", null, allPanel, null);

		final DefaultListModel<String> allreslistModel = new DefaultListModel<String>();
		// get all res list
		final ResourceCatalogue rcat = new ResourceCatalogue();
		System.out.println("all : ");
		allres = rcat.readAllResources();

		for (int i = 0; i < allres.size(); i++) {
			System.out.println(allres.get(i));
			allreslistModel.addElement("" + allres.get(i).get("rname"));
		}

		JScrollPane allres_scrollPane = new JScrollPane();
		String[] allres_columns = new String[] { "Id", "Name" };

		final DefaultTableModel allres_tableModel = new DefaultTableModel(allres_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		

		
		JButton button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//
				allres.clear();
				allres = rcat.readAllResources();
				System.out.println(allres_tableModel.getRowCount()+" ---");
				int rowcount= allres_tableModel.getRowCount();
				for (int j = rowcount - 1; j >= 0; j--) {
					allres_tableModel.removeRow(j);
				}
				System.out.println(allres_tableModel.getRowCount()+" ---");
				for (int i = 0; i < allres.size(); i++) {
					Object[] objs = { allres.get(i).get("rid"), allres.get(i).get("rname") };
					allres_tableModel.addRow(objs);
				}
				

			}
		});
		GroupLayout gl_allPanel = new GroupLayout(allPanel);
		gl_allPanel.setHorizontalGroup(gl_allPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allPanel.createSequentialGroup().addGap(30)
						.addComponent(allres_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE).addGap(30))
				.addGroup(Alignment.TRAILING, gl_allPanel.createSequentialGroup().addContainerGap(588, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)));
		gl_allPanel
				.setVerticalGroup(gl_allPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_allPanel.createSequentialGroup().addContainerGap().addComponent(button).addGap(30)
								.addComponent(allres_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(30)));
		
		
		
		for (int i = 0; i <allres.size(); i++) {
			Object[] objs = { allres.get(i).get("rid"), allres.get(i).get("rname") };
			allres_tableModel.addRow(objs);
		}

		
		allresource_table = new JTable(allres_tableModel);
		allres_scrollPane.setViewportView(allresource_table);
		allPanel.setLayout(gl_allPanel);

		tabbedPane.addTab("Project Management", null, projectPanel, null);

		DefaultListModel<String> projectlistModel = new DefaultListModel<String>();
		projectlistModel.addElement("hello");
		
		String[] allproject_columns = new String[] { "Id", "Name" };

		final DefaultTableModel allproject_tableModel = new DefaultTableModel(allproject_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		
		JButton addprojectBtn = new JButton("Add Project");
		
		
		addprojectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> projectFields = new ArrayList<Field>();
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				ArrayList<HashMap<String, String>> employees_fromcatalouge = empcat.readAllEmployees();
				ArrayList<String> employees = new ArrayList<String>();

				projectFields.add(new Field("text", "projname", "", 20, "name"));
				
				for (int i = 0; i < employees_fromcatalouge.size(); i++) {
					employees.add("id:"+employees_fromcatalouge.get(i).get("empid").toString()+" "+employees_fromcatalouge.get(i).get("empname").toString());
				}
				System.out.println(employees+" 00");
				projectFields.add(new Field("comboBox", "project manager", employees, 20, "project manager"));

				Form projectForm = new Form(projectFields, "Project Form");
				final PanelBuilder project_addPanel = new PanelBuilder(projectForm);
				project_addPanel.makeForm();
				JFrame Add_ProjectPage = new JFrame("Add Project Form");
				Add_ProjectPage.getContentPane().add(project_addPanel.getJPanel(), BorderLayout.NORTH);

				
				ComboBoxJPanel comboBoxpane = (ComboBoxJPanel) project_addPanel.getJPanel().getComponent(1);
				final JComboBox employees_comboBox = comboBoxpane.getComboBox();

				
				JButton submitaddprojectBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddprojectBtn);
				Add_ProjectPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_ProjectPage.pack();
				Add_ProjectPage.setVisible(true);
				
				employees_comboBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(employees_comboBox.getSelectedItem() + " ino select kardi project");
					}
				});

				
				submitaddprojectBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ProjectCatalogue projcat = new ProjectCatalogue();
						System.out.println("all : ");
						projcat.getProjects();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < project_addPanel.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) project_addPanel.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " project");
						}
						int employeeID= Integer.parseInt((inputs.get(1).substring(0, 4).replace("id:", "")));
						EmployeeCatalogue empcat = new EmployeeCatalogue();
						Employee proj_manager= empcat.getEmployee(employeeID);
						System.out.println(proj_manager.getName());
						projcat.addProject(inputs.get(0).toString(), proj_manager);
						// tu resource ham bayad insert she
						allprojects = projcat.getProjects();
						
						int rowcount= allproject_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							allproject_tableModel.removeRow(j);
						}
						System.out.println(allproject_tableModel.getRowCount()+" ---");
						for (int i = 0; i < allprojects.size(); i++) {
							Object[] objs = { allprojects.get(i).get("projid"), allprojects.get(i).get("projname") };
							allproject_tableModel.addRow(objs);
						}

					
				
			}
		});
			}});

		JButton projsearchBtn = new JButton("Search");

		JScrollPane project_scrollPane = new JScrollPane();
		
		JButton viewsubsys_Btn = new JButton("View SubSystem");
		viewsubsys_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("-----");
			      int rowIndex = project_table.getSelectedRow();
			      int colIndex = project_table.getSelectedColumn();

			    String Table_click = (project_table.getModel().getValueAt(rowIndex, 0).toString()); //return the thing in the 0st column
			    System.out.println(Table_click);
			    selected_project_forsubsystem = Integer.parseInt(Table_click.trim());
			    System.out.println("-----");
				System.out.println("Change JPanel");
				int selected_index= tabbedPane.getSelectedIndex();
				tabbedPane.remove(selected_index);
				tabbedPane.insertTab("Subsystem", null, subsystemPanel, null, selected_index);
				tabbedPane.setSelectedComponent(subsystemPanel);				

				
			}
		});
		GroupLayout gl_projectPanel = new GroupLayout(projectPanel);
		gl_projectPanel.setHorizontalGroup(
			gl_projectPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_projectPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
					.addGap(40))
				.addGroup(gl_projectPanel.createSequentialGroup()
					.addGap(300)
					.addComponent(projsearchBtn)
					.addPreferredGap(ComponentPlacement.RELATED, 330, Short.MAX_VALUE)
					.addGroup(gl_projectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(viewsubsys_Btn)
						.addComponent(addprojectBtn))
					.addContainerGap())
		);
		gl_projectPanel.setVerticalGroup(
			gl_projectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_projectPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_projectPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(addprojectBtn)
						.addComponent(projsearchBtn))
					.addGap(5)
					.addComponent(viewsubsys_Btn)
					.addGap(40)
					.addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
					.addGap(40))
		);
		
		final DefaultListModel<String> allprojectlistModel = new DefaultListModel<String>();
		// get all res list
		final ProjectCatalogue pcat = new ProjectCatalogue();
		System.out.println("all : ");
		allprojects = pcat.getProjects();

		for (int i = 0; i < allprojects.size(); i++) {
			System.out.println(allprojects.get(i));
			allprojectlistModel.addElement("" + allprojects.get(i).get("projname"));
		}
		
		

		for (int i = 0; i <allprojects.size(); i++) {
			Object[] objs = { allprojects.get(i).get("projid"), allprojects.get(i).get("projname") };
			allproject_tableModel.addRow(objs);
		}

		
		project_table = new JTable(allproject_tableModel);
	    project_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		project_scrollPane.setViewportView(project_table);
		projectPanel.setLayout(gl_projectPanel);
		
		JPanel reportPanel = new JPanel();
		if(AuthenticatedEmployee.getInstance().getEmployee().getAccessRight().getName().equals("super")  ){
		tabbedPane.addTab("Report", null, reportPanel, null);
		}		
		JPanel RegisteredUserspanel = new JPanel();
		if(AuthenticatedEmployee.getInstance().getEmployee().getAccessRight().getName().equals("super")  ){
		tabbedPane.addTab("Registered Users", null, RegisteredUserspanel, null);
		}		
		JScrollPane registered_scrollPane = new JScrollPane();
		
		JButton btnConfirm = new JButton("Confirm");
		
		JButton btnDeny = new JButton("Deny");
		GroupLayout gl_RegisteredUserspanel = new GroupLayout(RegisteredUserspanel);
		gl_RegisteredUserspanel.setHorizontalGroup(
			gl_RegisteredUserspanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_RegisteredUserspanel.createSequentialGroup()
					.addGap(40)
					.addComponent(registered_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
					.addGap(40))
				.addGroup(Alignment.TRAILING, gl_RegisteredUserspanel.createSequentialGroup()
					.addContainerGap(619, Short.MAX_VALUE)
					.addComponent(btnDeny)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnConfirm))
		);
		gl_RegisteredUserspanel.setVerticalGroup(
			gl_RegisteredUserspanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RegisteredUserspanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_RegisteredUserspanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirm)
						.addComponent(btnDeny))
					.addGap(40)
					.addComponent(registered_scrollPane, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addGap(40))
		);
		
		registered_table = new JTable();
		registered_scrollPane.setViewportView(registered_table);
		RegisteredUserspanel.setLayout(gl_RegisteredUserspanel);
		if (AuthenticatedEmployee.getInstance().getEmployee().getAccessRight()
				.getName().equals("default")) {
			addreqBtn.setEnabled(false);
			btnAddInformation.setEnabled(false);
			information_btnEdit.setEnabled(false);
			btnAddFinancial.setEnabled(false);
			financial_btnEdit.setEnabled(false);
			btnAddPhysicalResource.setEnabled(false);
			physical_btnEdit.setEnabled(false);
			addprojectBtn.setEnabled(false);
			addsubsystemBtn.setEnabled(false);
		}
		
	}

	public JFrame getUserpageFrame() {
		return userpageFrame;
	}
}
