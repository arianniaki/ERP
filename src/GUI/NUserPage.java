package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JMenu;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import GUI.Form.CheckBoxJPanel;
import GUI.Form.ComboBoxJPanel;
import GUI.Form.Field;
import GUI.Form.FieldPanel;
import GUI.Form.Form;
import GUI.Form.PanelBuilder;
import GUI.Form.SingleCheckBoxJPanel;
import ProjectEmployee.AuthenticatedEmployee;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.ProjectEmployeeCatalogue;
import ProjectEmployee.SubSystem.SubSystemCatalogue;
import RequirementUtilization.ProjectResourceUtilization;
import RequirementUtilization.ProjectResourceUtilizationCatalogue;
import RequirementUtilization.ResourceRequirement;
import RequirementUtilization.ResourceRequirementCatalogue;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.FinancialResourceCatalogue;
import ResourceManagement.Section.Resource.InformationResourceCatalogue;
import ResourceManagement.Section.Resource.MaintainModEmpResCatalogue;
import ResourceManagement.Section.Resource.MaintainingModule;
import ResourceManagement.Section.Resource.MaintainingModuleCatalogue;
import ResourceManagement.Section.Resource.MakeModuleCatalogue;
import ResourceManagement.Section.Resource.ModuleCatalogue;
import ResourceManagement.Section.Resource.PhysicalResourceCatalogue;
import ResourceManagement.Section.Resource.ResourceCatalogue;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.ListModel;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Button;

public class NUserPage {

	private Color userpage_color = new Color(0, 150, 130);
	private Color tab_color = new Color(128, 203, 196);

	private JFrame userpageFrame;
	private JTextField editname_textField;
	private ArrayList<HashMap<String, String>> allmodules;
	private ArrayList<HashMap<String, String>> allphysicals;
	private ArrayList<HashMap<String, String>> allfinance;
	private ArrayList<HashMap<String, String>> allinformation;
	private ArrayList<HashMap<String, String>> allres;
	private ArrayList<HashMap<String, String>> allprojects;
	private ArrayList<HashMap<String, String>> allemployees;
	private ArrayList<HashMap<String, String>> allsubsystems;
	// private ArrayList<ResourceRequirement> allresourcerequirements;
	private ArrayList<HashMap<String, String>> allregisteredusers;

	// private JTable finan_table;
	// private JTable information_table;
	// private JTable module_table;
	// private JTable physical_table;
	// private JTable allresource_table;
	// private JTable project_table;
	// private JTable subsystem_table;
	// private JTable accessright_table;
	// private JTable registered_table;
	// private JTable human_table;
	// private JTable maintaining_table;
	// private JTable requirement_table;
	private TableData resrequirement_tabledata;
	private TableData accessright_tabledata;
	private TableData registered_tabledata;
	private TableData project_tabledata;
	private TableData physical_tabledata;
	private TableData information_tabledata;
	private TableData financial_tabledata;
	private TableData module_tabledata;
	private TableData human_tabledata;
	private TableData allresource_tabledata;
	private TableData maintaining_tabledata;
	private TableData resreq_tabledata;
	private TableData circulation_tabledata;
	private TableData resavail_tabledata;
	private TableData subsystem_tabledata;
	private TableData resourceutil_tabledata;
	private TableData moduledetail_tabledata;

	private int selected_project_forsubsystem;
	private int selected_accessright_forassignment;
	private int selected_module;
	private int selected_maintaining_module;
	private int selected_resource_util;
	
	private JTextField search_modulename;
	private JTextField search_financialname;
	private JTextField search_informationname;
	private JTextField search_humanname;
	private JTextField search_physicalname;
	private JTextField search_projectname;
	// private JTable resavail_table;
	// private JTable resreq_table;
	// private JTable cycle_table;
	// private JTable resourceutil_table;
	private JTextField search_maintainingname;
	private JPasswordField passwordField;
	private JPasswordField passwordField_re;
	private JTextField search_projectmanager;
	private JTextField search_projecttech;
	private JTextField search_projectsize;
	private JTextField search_financialmodel;
	private JTextField search_financialvalue;
	private JTextField search_moduleduration;
	private JTextField search_humanpost;
	private JTextField search_humansection;
	private JTextField search_physicalmodel;
	private JTextField search_subsystemname;
	private JTextField search_accessrightname;
	private JTextField search_resourcename;
	private JTextField search_reqresourcename;
	private JTextField search_regemployeename;

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
		userpageFrame = new JFrame("User Page");
		userpageFrame.setBounds(100, 100, 870, 585);
		userpageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userpageFrame.getContentPane().setLayout(null);
		userpageFrame.getContentPane().setBackground(userpage_color);
		userpageFrame.setResizable(false);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 0, 858, 557);
		userpageFrame.getContentPane().add(tabbedPane);

		JPanel editPanel = new JPanel();
		editPanel.setBackground(tab_color);
		tabbedPane.addTab("Edit Info", null, editPanel, null);

		JLabel lblName = new JLabel("First and Last Name");

		editname_textField = new JTextField();
		passwordField = new JPasswordField();

		passwordField_re = new JPasswordField();

		editname_textField.setColumns(10);
		editname_textField.setText(AuthenticatedEmployee.getInstance().getEmployee().getName());

		JLabel password = new JLabel("Password");

		JLabel lblRepassword = DefaultComponentFactory.getInstance().createLabel("Re-Password");

		JButton btnEditInformation = new JButton("Edit Information");
		btnEditInformation.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnEditInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(passwordField.getText() + " " + passwordField_re.getText() + " "
						+ editname_textField.getText());
				if (!(passwordField.getText().equals(passwordField_re.getText()))) {
					NotificationPage confirmation = new NotificationPage(new JFrame(), "Notification",
							"Passwords do not match!");

				} else {
					if (passwordField.getText() != null && passwordField_re.getText() != null) {
						NotificationPage confirmation = new NotificationPage(new JFrame(), "Notification",
								"You have been successfully edited your information!");
					}
				}
			}
		});

		GroupLayout gl_editPanel = new GroupLayout(editPanel);
		gl_editPanel.setHorizontalGroup(
			gl_editPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editPanel.createSequentialGroup()
					.addContainerGap(538, Short.MAX_VALUE)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(passwordField)
						.addComponent(editname_textField)
						.addComponent(passwordField_re, Alignment.LEADING))
					.addGap(40)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRepassword)
						.addComponent(password)
						.addComponent(lblName)))
				.addGroup(gl_editPanel.createSequentialGroup()
					.addComponent(btnEditInformation)
					.addContainerGap(735, Short.MAX_VALUE))
		);
		gl_editPanel.setVerticalGroup(
			gl_editPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editPanel.createSequentialGroup()
					.addGap(94)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(editname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(26)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(password)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepassword)
						.addComponent(passwordField_re, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
					.addComponent(btnEditInformation))
		);
		editPanel.setLayout(gl_editPanel);

		JPanel accessrightPanel = new JPanel();
		accessrightPanel.setBackground(tab_color);
		// if
		// (AuthenticatedEmployee.getInstance().getEmployee().getAccessRight().getName().equals("super"))
		// {
		tabbedPane.addTab("AccessRight Management", null, accessrightPanel, null);
		// }

		// get employee list

		accessright_tabledata = new TableData(new EmployeeCatalogue(), "assign");

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
				Assign_AccessRightPage.getContentPane().add(accessright_Form.getJPanel(), BorderLayout.NORTH);

				JButton submitaccessrightassignmentBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaccessrightassignmentBtn);

				Assign_AccessRightPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Assign_AccessRightPage.pack();
				Assign_AccessRightPage.setVisible(true);
				submitaccessrightassignmentBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("-----");
						int rowIndex = accessright_tabledata.getJdataTable().getSelectedRow();
						int colIndex = accessright_tabledata.getJdataTable().getSelectedColumn();
						if (rowIndex == -1) {
							NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
									"Please Select a User!");
						} else {

							String Table_click = (accessright_tabledata.getJdataTable().getModel()
									.getValueAt(rowIndex, 0).toString());
							System.out.println(Table_click);
							EmployeeCatalogue empcat = new EmployeeCatalogue();
							Employee emp_access = empcat.getEmployee(Integer.parseInt(Table_click));
							System.out.println(emp_access.getName() + " WHAT  " + selected_accessright_forassignment);
							emp_access.setAccessRight(selected_accessright_forassignment);
							System.out.println("ACCESS RIGHT O DADAM");
							accessright_tabledata.update(empcat.readAllEmployees());
						}
					}
				});

				ComboBoxJPanel comboBoxpanel_accessright = (ComboBoxJPanel) accessright_Form.getJPanel()
						.getComponent(0);
				final JComboBox accessright_type = comboBoxpanel_accessright.getComboBox();

				accessright_type.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(accessright_type.getSelectedItem() + " ino select kardi access right "
								+ accessright_type.getSelectedIndex());
						selected_accessright_forassignment = accessright_type.getSelectedIndex() + 1;
					}
				});
			}
		});

		JScrollPane accessright_scrollPane = new JScrollPane();

		JLabel lblAccessrightName = DefaultComponentFactory.getInstance().createLabel("AccessRight Name");

		search_accessrightname = new JTextField();
		search_accessrightname.setColumns(10);

		JButton accessright_btnSearch = new JButton("Search");
		accessright_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();

				System.out.println(search_accessrightname.getText()+" sssssssssssssssss");
				if(search_accessrightname.getText().equals("default"))
					searchVars.put("accessrightid", "\'" + 1 + "\'");

				if(search_accessrightname.getText().equals("super"))
					searchVars.put("accessrightid", "\'" + 3 + "\'");

				if(search_accessrightname.getText().equals("intermediate"))
					searchVars.put("accessrightid", "\'" + 2 + "\'");
				System.out.println("ASDSADASDA");
				System.out.println(searchVars);
				
				if (empcat.SearchEmployee(searchVars).isEmpty()) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification", "No Results Found");
				} else {
					accessright_tabledata.update(empcat.SearchEmployee(searchVars));
				}

				

			}
		});
		
		JButton accessright_btnRefresh = new JButton("Refresh");
		accessright_btnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		accessright_btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				accessright_tabledata.update(empcat.getConfirmedEmployees());

			}
		});

		GroupLayout gl_accessrightPanel = new GroupLayout(accessrightPanel);
		gl_accessrightPanel.setHorizontalGroup(
			gl_accessrightPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
					.addGap(40))
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addContainerGap(669, Short.MAX_VALUE)
					.addComponent(btnAssignAccessright))
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addComponent(accessright_btnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
					.addComponent(accessright_btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(search_accessrightname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAccessrightName)
					.addContainerGap())
		);
		gl_accessrightPanel.setVerticalGroup(
			gl_accessrightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_accessrightPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccessrightName)
						.addComponent(search_accessrightname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(accessright_btnSearch)
						.addComponent(accessright_btnRefresh))
					.addGap(40)
					.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
					.addGap(40)
					.addComponent(btnAssignAccessright))
		);

		accessright_scrollPane.setViewportView(accessright_tabledata.getJdataTable());
		accessrightPanel.setLayout(gl_accessrightPanel);
		// project mgmt panel
		final JPanel projectPanel = new JPanel();
		projectPanel.setBackground(tab_color);

		//

		// subsystem panel
		final JPanel subsystemPanel = new JPanel();
		subsystemPanel.setBackground(tab_color);
		tabbedPane.addTab("SubSystem", null, subsystemPanel, null);
		tabbedPane.remove(tabbedPane.getTabCount() - 1); // remove subsystem tab
		JScrollPane subsystem_scrollPane = new JScrollPane();

		JButton btnBacktoProject = new JButton("Back");
		btnBacktoProject.setIcon(new ImageIcon(
				new ImageIcon("images/back.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnBacktoProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("change tab to project mgmt");
				System.out.println("Change JPanel");
				int selected_index = tabbedPane.getSelectedIndex();
				tabbedPane.remove(selected_index);
				tabbedPane.insertTab("Project Management", null, projectPanel, null, selected_index);
				tabbedPane.setSelectedComponent(projectPanel);
			}
		});

		JButton addsubsystemBtn = new JButton("Add Subsystem");
		addsubsystemBtn.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		addsubsystemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> subsystem_addFields = new ArrayList<Field>();
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field subsystem_name = new Field("text", "Subsystem Name", "", 10, "name");
				Field subsystem_desc = new Field("text", "Subsystem Description", "", 30, "desc");
				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				subsystem_addFields.add(subsystem_name);
				subsystem_addFields.add(subsystem_desc);
				subsystem_addFields.add(sections);

				final Form subsystem_Form = new Form(subsystem_addFields, "Subsystem Form");
				final PanelBuilder subsystemAdd_Panel = new PanelBuilder(subsystem_Form);
				subsystemAdd_Panel.makeForm();

				JFrame Add_SubsystemPage = new JFrame("Add Subsystem Form");
				Add_SubsystemPage.getContentPane().add(subsystem_Form.getJPanel(), BorderLayout.NORTH);

				JButton submitaddsubsystemBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddsubsystemBtn);
				Add_SubsystemPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_SubsystemPage.pack();
				Add_SubsystemPage.setVisible(true);

				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) subsystem_Form.getJPanel().getComponent(2);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				submitaddsubsystemBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < subsystem_Form.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) subsystem_Form.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " subsystem");
						}
						System.out.println(sections_combo.getSelectedItem() + " //////");
						Pattern p = Pattern.compile("sid=\\d+");
						String section = null;
						Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
						if (m.find()) {
							section = m.group();
						}
						System.out.println("sid: " + section);

						SubSystemCatalogue subsyscat = new SubSystemCatalogue();
						subsyscat.addSubSystem(inputs.get(0), selected_project_forsubsystem,
								Integer.parseInt(section.replace("sid=", "")));
						subsystem_tabledata.update(subsyscat.getSubSystemsByProject(selected_project_forsubsystem));
						System.out.println("add shoood ");

					}
				});

			}
		});

		JButton subsystem_btnEdit = new JButton("Edit");
		subsystem_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		subsystem_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> subsystem_addFields = new ArrayList<Field>();
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field subsystem_name = new Field("text", "Subsystem Name", "", 10, "name");
				Field subsystem_desc = new Field("text", "Subsystem Description", "", 30, "desc");
				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				subsystem_addFields.add(subsystem_name);
				subsystem_addFields.add(subsystem_desc);
				subsystem_addFields.add(sections);

				final Form subsystem_Form = new Form(subsystem_addFields, "Subsystem Form");
				final PanelBuilder subsystemAdd_Panel = new PanelBuilder(subsystem_Form);
				subsystemAdd_Panel.makeForm();

				JFrame Add_SubsystemPage = new JFrame("Add Subsystem Form");
				Add_SubsystemPage.getContentPane().add(subsystem_Form.getJPanel(), BorderLayout.NORTH);

				JButton submitaddsubsystemBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddsubsystemBtn);
				Add_SubsystemPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_SubsystemPage.pack();
				Add_SubsystemPage.setVisible(true);
				submitaddsubsystemBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < subsystem_Form.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) subsystem_Form.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " subsystem");
						}

						SubSystemCatalogue subsyscat = new SubSystemCatalogue();
						// subsyscat.addSubSystem(inputs.get(0),
						// selected_project_forsubsystem);
						subsystem_tabledata.update(subsyscat.getSubSystemsByProject(selected_project_forsubsystem));
						System.out.println("add shoood ");
					}
				});

			}
		});

		JButton subsystem_btnDelete = new JButton("Delete");
		subsystem_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		subsystem_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = subsystem_tabledata.getJdataTable().getSelectedRow();
				int colIndex = subsystem_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Resource!");
				} else {

					String Table_click = (subsystem_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");
					SubSystemCatalogue subsyscat = new SubSystemCatalogue();
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						subsyscat.deleteSubSystem(Integer.parseInt(Table_click));
						subsystem_tabledata.update(subsyscat.getSubSystemsByProject(selected_project_forsubsystem));
					}
				}
			}
		});

		JLabel lblSubsystemName = DefaultComponentFactory.getInstance().createLabel("Subsystem Name");

		search_subsystemname = new JTextField();
		search_subsystemname.setColumns(10);

		JButton subsystem_btnSearch = new JButton("Search");
		GroupLayout gl_subsystemPanel = new GroupLayout(subsystemPanel);
		gl_subsystemPanel.setHorizontalGroup(gl_subsystemPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_subsystemPanel.createSequentialGroup().addComponent(subsystem_btnEdit)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(subsystem_btnDelete)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnBacktoProject)
						.addPreferredGap(ComponentPlacement.RELATED, 551, Short.MAX_VALUE)
						.addComponent(addsubsystemBtn, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_subsystemPanel.createSequentialGroup().addGap(40)
						.addComponent(subsystem_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE).addGap(40))
				.addGroup(gl_subsystemPanel.createSequentialGroup().addContainerGap(492, Short.MAX_VALUE)
						.addComponent(subsystem_btnSearch).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(search_subsystemname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblSubsystemName).addContainerGap()));
		gl_subsystemPanel.setVerticalGroup(gl_subsystemPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_subsystemPanel.createSequentialGroup().addGap(12)
						.addGroup(gl_subsystemPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSubsystemName).addComponent(subsystem_btnSearch)
								.addComponent(search_subsystemname, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(40).addComponent(subsystem_scrollPane, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
						.addGap(40)
						.addGroup(gl_subsystemPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(addsubsystemBtn).addComponent(subsystem_btnEdit)
								.addComponent(subsystem_btnDelete).addComponent(btnBacktoProject))));

		subsystem_tabledata = new TableData(new SubSystemCatalogue());
		subsystem_scrollPane.setViewportView(subsystem_tabledata.getJdataTable());
		subsystemPanel.setLayout(gl_subsystemPanel);

		final JPanel resourceutilpanel = new JPanel();
		resourceutilpanel.setBackground(tab_color);
		tabbedPane.addTab("Resource Utilization", null, resourceutilpanel, null);
		tabbedPane.remove(tabbedPane.getTabCount() - 1); // remove resource tab

		JScrollPane resourceutil_scrollPane = new JScrollPane();

		JButton utilbtnBacktoProject = new JButton("Back");
		utilbtnBacktoProject.setIcon(new ImageIcon(
				new ImageIcon("images/back.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		utilbtnBacktoProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("change tab to project mgmt");
				System.out.println("Change JPanel");
				int selected_index = tabbedPane.getSelectedIndex();
				tabbedPane.remove(selected_index);
				tabbedPane.insertTab("Project Management", null, projectPanel, null, selected_index);
				tabbedPane.setSelectedComponent(projectPanel);
			}
		});

		JButton btnAddResourceUtilization = new JButton("Add Resource Utilization");
		btnAddResourceUtilization.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnAddResourceUtilization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				ArrayList<String> resource_types = new ArrayList<String>();
				final ArrayList<String> resources = new ArrayList<String>();
				resource_types.add("Information");
				resource_types.add("Financial");
				resource_types.add("Physical");
				resource_types.add("Employee");
				resource_types.add("Module");
				ArrayList<Field> resutil_Fields = new ArrayList<Field>();
				Field req_res_type = new Field("comboBox", "resource types", resource_types, 20, "items");
				Field req_res = new Field("comboBox", "resources", resources, 20, "items");
				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				resutil_Fields.add(req_res_type);
				resutil_Fields.add(req_res);
				resutil_Fields.add(sections);

				final Form resutil_Form = new Form(resutil_Fields, "Resource Utilization Form");
				final PanelBuilder requirement_Panel = new PanelBuilder(resutil_Form);
				requirement_Panel.makeForm();

				JFrame Add_ResUtilPage = new JFrame("Add Resource Utilization Form");
				Add_ResUtilPage.getContentPane().add(resutil_Form.getJPanel(), BorderLayout.NORTH);

				// adding date
				UtilDateModel modelfor = new UtilDateModel();
				UtilDateModel modelto = new UtilDateModel();

				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				final JDatePanelImpl from_datePanel = new JDatePanelImpl(modelfor, p);
				JDatePanelImpl to_datePanel = new JDatePanelImpl(modelto, p);
				JLabel from = new JLabel("From");
				JLabel to = new JLabel("To");
				final JDatePickerImpl from_datePicker = new JDatePickerImpl(from_datePanel, new DateLabelFormatter());
				final JDatePickerImpl to_datePicker = new JDatePickerImpl(to_datePanel, new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());
				date_panel.add(from);

				date_panel.add(from_datePanel);
				date_panel.add(to);
				date_panel.add(to_datePanel);
				Add_ResUtilPage.getContentPane().add(date_panel, BorderLayout.CENTER);
				// end date

				JButton submitaddresutilBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddresutilBtn);
				Add_ResUtilPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_ResUtilPage.pack();
				Add_ResUtilPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_restype = (ComboBoxJPanel) resutil_Form.getJPanel().getComponent(0);
				ComboBoxJPanel comboBoxpane_res = (ComboBoxJPanel) resutil_Form.getJPanel().getComponent(1);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) resutil_Form.getJPanel().getComponent(2);

				final JComboBox resource_type = comboBoxpanel_restype.getComboBox();
				final JComboBox resourceCombo = comboBoxpane_res.getComboBox();
				final JComboBox sectionCombo = comboBoxpane_sections.getComboBox();

				resourceCombo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						// System.out.println(resourceCombo.getSelectedItem() +
						// " ino select kardi az resource");
						// System.out.println(sectionCombo.getSelectedItem() + "
						// ino select kardi section");
						// System.out.println(projectCombo.getSelectedItem() + "
						// ino select kardi proje");
						// System.out.println(from_datePicker.getJFormattedTextField().getText()
						// + " from date");
						// System.out.println(to_datePicker.getJFormattedTextField().getText()
						// + " to date");
						//
						System.out.println(from_datePicker.getJFormattedTextField().getText() + " from date");
						System.out.println(to_datePicker.getJFormattedTextField().getText() + " to date");

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
						if (resource_type.getSelectedItem().toString().equals("Module")) {
							resourceCombo.removeAllItems();
							ModuleCatalogue modcat = new ModuleCatalogue();
							ArrayList<HashMap<String, String>> module_resource = modcat.readAllResources();
							for (int i = 0; i < module_resource.size(); i++) {
								resourceCombo.addItem(module_resource.get(i).toString());
							}
						}
					}
				});
				submitaddresutilBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for (int i = 0; i < resutil_Form.getJPanel().getComponentCount(); i++) {
							// System.out.println(fpanel.selected_Choice);
						}
						ProjectResourceUtilizationCatalogue presreqCat = new ProjectResourceUtilizationCatalogue();
						System.out.println("all : ");
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < resutil_Form.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) resutil_Form.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						String rid = "";
						Pattern p = Pattern.compile("rid=\\d+");
						Matcher m = p.matcher((CharSequence) resourceCombo.getSelectedItem());
						if (m.find()) {
							rid = m.group();
						}
						String sid = "";
						Pattern p1 = Pattern.compile("sid=\\d+");
						Matcher m1 = p1.matcher((CharSequence) sectionCombo.getSelectedItem());
						if (m1.find()) {
							sid = m1.group();
						}

						String fromdate = from_datePicker.getJFormattedTextField().getText();
						String todate = to_datePicker.getJFormattedTextField().getText();

						System.out.println("--------------");
						System.out.println(rid + " " + sid + " " + fromdate + " " + todate);

						presreqCat.addProjectResourceUtilization(Integer.parseInt(rid.replace("rid=", "")),
								Integer.parseInt(sid.replace("sid=", "")), selected_project_forsubsystem, fromdate,
								todate);

						ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
						ArrayList<ProjectResourceUtilization> allpresutil;
						allpresutil = presreqCat.getProjectResourceUtilizationbyProject(selected_project_forsubsystem);
						for (int i = 0; i < allpresutil.size(); i++) {
							data.add((allpresutil.get(i).toHashMap()));
						}
						resourceutil_tabledata.update(data);

					}
				});
			}

		});

		JButton presutil_btnEdit = new JButton("Edit");
		presutil_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		presutil_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowIndex = resourceutil_tabledata.getJdataTable().getSelectedRow();

				String Table_click = (resourceutil_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
						.toString()); // return
				selected_resource_util = Integer.parseInt(Table_click.trim());

				System.out.println(Table_click);
				System.out.println("---resource util id-- " + selected_resource_util);


				JFrame Edit_ResUtilPage = new JFrame("Edit Resource Utilization Form");

				// adding date
				UtilDateModel modelfor = new UtilDateModel();
				UtilDateModel modelto = new UtilDateModel();

				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				final JDatePanelImpl from_datePanel = new JDatePanelImpl(modelfor, p);
				JDatePanelImpl to_datePanel = new JDatePanelImpl(modelto, p);
				JLabel from = new JLabel("From");
				JLabel to = new JLabel("To");
				final JDatePickerImpl from_datePicker = new JDatePickerImpl(from_datePanel, new DateLabelFormatter());
				final JDatePickerImpl to_datePicker = new JDatePickerImpl(to_datePanel, new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());
				date_panel.add(from);

				date_panel.add(from_datePanel);
				date_panel.add(to);
				date_panel.add(to_datePanel);
				Edit_ResUtilPage.getContentPane().add(date_panel, BorderLayout.CENTER);
				// end date

				JButton submiteditresutilBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submiteditresutilBtn);
				Edit_ResUtilPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Edit_ResUtilPage.pack();
				Edit_ResUtilPage.setVisible(true);

				submiteditresutilBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						ProjectResourceUtilizationCatalogue presreqCat = new ProjectResourceUtilizationCatalogue();

						String fromdate = from_datePicker.getJFormattedTextField().getText();
						String todate = to_datePicker.getJFormattedTextField().getText();


						ProjectResourceUtilization projresutil = presreqCat.getProjectResourceUtilization(selected_resource_util);
						projresutil.edit(fromdate, todate);
						

						ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
						ArrayList<ProjectResourceUtilization> allpresutil;
						allpresutil = presreqCat.getProjectResourceUtilizationbyProject(selected_project_forsubsystem);
						for (int i = 0; i < allpresutil.size(); i++) {
							data.add((allpresutil.get(i).toHashMap()));
						}
						resourceutil_tabledata.update(data);

					}
				});
			}
		});

		JButton presutil_btnDelete = new JButton("Delete");
		presutil_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowIndex = resourceutil_tabledata.getJdataTable().getSelectedRow();
				int colIndex = resourceutil_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Resource Utilization!");
				} else {

					String Table_click = (resourceutil_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						ProjectResourceUtilizationCatalogue projrescat = new ProjectResourceUtilizationCatalogue();
						projrescat.deleteProjectResourceUtilization(Integer.parseInt(Table_click));

						ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
						ArrayList<ProjectResourceUtilization> allpresutil;
						allpresutil = projrescat.getProjectResourceUtilizationbyProject(selected_project_forsubsystem);
						for (int i = 0; i < allpresutil.size(); i++) {
							data.add((allpresutil.get(i).toHashMap()));
						}
						resourceutil_tabledata.update(data);
						
				}
			}
			}
		});
		presutil_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JLabel lblResourceName = DefaultComponentFactory.getInstance().createLabel("Resource Name");

		JButton resutil_btnSearch = new JButton("Search");

		search_resourcename = new JTextField();
		search_resourcename.setColumns(10);

		GroupLayout gl_resourceutilpanel = new GroupLayout(resourceutilpanel);
		gl_resourceutilpanel
				.setHorizontalGroup(
						gl_resourceutilpanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										gl_resourceutilpanel.createSequentialGroup().addGap(40)
												.addComponent(resourceutil_scrollPane, GroupLayout.DEFAULT_SIZE, 757,
														Short.MAX_VALUE)
												.addGap(40))
								.addGroup(gl_resourceutilpanel.createSequentialGroup().addComponent(presutil_btnEdit)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(presutil_btnDelete)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(utilbtnBacktoProject)
										.addPreferredGap(ComponentPlacement.RELATED, 552, Short.MAX_VALUE)
										.addComponent(btnAddResourceUtilization))
								.addGroup(gl_resourceutilpanel.createSequentialGroup()
										.addContainerGap(503, Short.MAX_VALUE).addComponent(resutil_btnSearch)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(search_resourcename, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblResourceName)
										.addContainerGap()));
		gl_resourceutilpanel.setVerticalGroup(gl_resourceutilpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resourceutilpanel.createSequentialGroup().addGap(12)
						.addGroup(gl_resourceutilpanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblResourceName).addComponent(resutil_btnSearch).addComponent(
										search_resourcename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(40)
						.addComponent(resourceutil_scrollPane, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
						.addGap(40)
						.addGroup(gl_resourceutilpanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(utilbtnBacktoProject).addComponent(btnAddResourceUtilization)
								.addComponent(presutil_btnEdit).addComponent(presutil_btnDelete))));

		resourceutil_tabledata = new TableData(new ProjectResourceUtilizationCatalogue());
		resourceutil_scrollPane.setViewportView(resourceutil_tabledata.getJdataTable());
		resourceutilpanel.setLayout(gl_resourceutilpanel);
		//
		JPanel requirementPanel = new JPanel();
		requirementPanel.setBackground(tab_color);
		tabbedPane.addTab("Requirment Management", null, requirementPanel, null);

		JButton addreqBtn = new JButton("Add Requirement");
		addreqBtn.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		addreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> section_arraylist = new ArrayList<String>();
				ArrayList<String> project_arraylist = new ArrayList<String>();

				ProjectCatalogue projcat = new ProjectCatalogue();
				ArrayList<HashMap<String, String>> project_hashmap = projcat.getProjects();
				for (int i = 0; i < project_hashmap.size(); i++) {
					project_arraylist.add(project_hashmap.get(i).toString());
				}
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				ArrayList<String> resource_types = new ArrayList<String>();
				final ArrayList<String> resources = new ArrayList<String>();
				resource_types.add("Information");
				resource_types.add("Financial");
				resource_types.add("Physical");
				resource_types.add("Employee");
				resource_types.add("Module");
				ArrayList<Field> requirement_moduleFields = new ArrayList<Field>();
				Field reqname = new Field("text", "req name       ", "", 10, "name");
				Field req_res_type = new Field("comboBox", "resource types", resource_types, 20, "items");
				Field req_res = new Field("comboBox", "resources", resources, 20, "items");
				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");
				Field projects = new Field("comboBox", "projects", project_arraylist, 20, "items");

				requirement_moduleFields.add(reqname);
				requirement_moduleFields.add(req_res_type);
				requirement_moduleFields.add(req_res);
				requirement_moduleFields.add(sections);
				requirement_moduleFields.add(projects);

				final Form requirement_Form = new Form(requirement_moduleFields, "Requirement Form");
				final PanelBuilder requirement_Panel = new PanelBuilder(requirement_Form);
				requirement_Panel.makeForm();

				JFrame Add_RequirementPage = new JFrame("Add Requirement Module Form");
				Add_RequirementPage.getContentPane().add(requirement_Form.getJPanel(), BorderLayout.NORTH);

				// adding date
				UtilDateModel modelfor = new UtilDateModel();
				UtilDateModel modelto = new UtilDateModel();

				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				final JDatePanelImpl from_datePanel = new JDatePanelImpl(modelfor, p);
				JDatePanelImpl to_datePanel = new JDatePanelImpl(modelto, p);
				JLabel from = new JLabel("From");
				JLabel to = new JLabel("To");
				final JDatePickerImpl from_datePicker = new JDatePickerImpl(from_datePanel, new DateLabelFormatter());
				final JDatePickerImpl to_datePicker = new JDatePickerImpl(to_datePanel, new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());
				date_panel.add(from);

				date_panel.add(from_datePanel);
				date_panel.add(to);
				date_panel.add(to_datePanel);
				Add_RequirementPage.getContentPane().add(date_panel, BorderLayout.CENTER);
				// end date

				JButton submitaddrequeirementBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddrequeirementBtn);
				Add_RequirementPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_RequirementPage.pack();
				Add_RequirementPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_restype = (ComboBoxJPanel) requirement_Form.getJPanel().getComponent(1);
				ComboBoxJPanel comboBoxpane_res = (ComboBoxJPanel) requirement_Form.getJPanel().getComponent(2);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) requirement_Form.getJPanel().getComponent(3);
				ComboBoxJPanel comboBoxpane_projects = (ComboBoxJPanel) requirement_Form.getJPanel().getComponent(4);

				final JComboBox resource_type = comboBoxpanel_restype.getComboBox();
				final JComboBox resourceCombo = comboBoxpane_res.getComboBox();
				final JComboBox sectionCombo = comboBoxpane_sections.getComboBox();
				final JComboBox projectCombo = comboBoxpane_projects.getComboBox();

				resourceCombo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						// System.out.println(resourceCombo.getSelectedItem() +
						// " ino select kardi az resource");
						// System.out.println(sectionCombo.getSelectedItem() + "
						// ino select kardi section");
						// System.out.println(projectCombo.getSelectedItem() + "
						// ino select kardi proje");
						// System.out.println(from_datePicker.getJFormattedTextField().getText()
						// + " from date");
						// System.out.println(to_datePicker.getJFormattedTextField().getText()
						// + " to date");
						//
						System.out.println(from_datePicker.getJFormattedTextField().getText() + " from date");
						System.out.println(to_datePicker.getJFormattedTextField().getText() + " to date");

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
						if (resource_type.getSelectedItem().toString().equals("Module")) {
							resourceCombo.removeAllItems();
							ModuleCatalogue modcat = new ModuleCatalogue();
							ArrayList<HashMap<String, String>> module_resource = modcat.readAllResources();
							for (int i = 0; i < module_resource.size(); i++) {
								resourceCombo.addItem(module_resource.get(i).toString());
							}
						}
					}
				});
				submitaddrequeirementBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < requirement_Form.getJPanel().getComponentCount(); i++) {
							// System.out.println(fpanel.selected_Choice);
						}
						ResourceRequirementCatalogue resreqCat = new ResourceRequirementCatalogue();
						System.out.println("all : ");
						resreqCat.getResourceRequirements();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < requirement_Form.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) requirement_Form.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						// for (int i = 0; i < inputs.size(); i++) {
						// System.out.println(inputs.get(i) + "adasa");
						// }
						String empid = "";
						String rid = "";
						if (resource_type.getSelectedItem().toString().equals("Employee")) {
							Pattern p = Pattern.compile("empid=\\d+");
							Matcher m = p.matcher((CharSequence) resourceCombo.getSelectedItem());
							if (m.find()) {
								empid = m.group();
							}
						} else {
							Pattern p = Pattern.compile("rid=\\d+");
							Matcher m = p.matcher((CharSequence) resourceCombo.getSelectedItem());
							if (m.find()) {
								rid = m.group();
							}
						}

						String sid = "";
						Pattern p1 = Pattern.compile("sid=\\d+");
						Matcher m1 = p1.matcher((CharSequence) sectionCombo.getSelectedItem());
						if (m1.find()) {
							sid = m1.group();
						}

						String projid = "";
						Pattern p2 = Pattern.compile("projid=\\d+");
						Matcher m2 = p2.matcher((CharSequence) projectCombo.getSelectedItem());
						if (m2.find()) {
							projid = m2.group();
						}

						String fromdate = from_datePicker.getJFormattedTextField().getText();
						String todate = to_datePicker.getJFormattedTextField().getText();

						System.out.println("--------------");
						System.out
								.println(empid + " " + rid + " " + sid + " " + projid + " " + fromdate + " " + todate);

						resreqCat.addResourceRequirement(Integer.parseInt(rid.replace("rid=", "")),
								Integer.parseInt(sid.replace("sid=", "")),
								Integer.parseInt(projid.replace("projid=", "")), fromdate, todate);

						ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
						ArrayList<ResourceRequirement> allresourcerequirements;
						allresourcerequirements = resreqCat.getResourceRequirements();
						for (int i = 0; i < allresourcerequirements.size(); i++) {
							data.add((allresourcerequirements.get(i).toHashMap()));
						}
						resrequirement_tabledata.update(data);

						// System.out.println(resreq_tableModel.getRowCount() +
						// "");
						// for (int i = 0; i < allresourcerequirements.size();
						// i++) {
						// Object[] objs = {
						// allresourcerequirements.get(i).toHashMap().get("rid"),
						// allresourcerequirements.get(i).getResource().getName(),
						// allresourcerequirements.get(i).toHashMap().get("sid"),
						// allresourcerequirements.get(i).getSection().getName(),
						// allresourcerequirements.get(i).toHashMap().get("pid"),
						// allresourcerequirements.get(i).getProject().getName(),
						// allresourcerequirements.get(i).toHashMap().get("fromdate"),
						// allresourcerequirements.get(i).toHashMap().get("todate")
						// };
						// resreq_tableModel.addRow(objs);
						// }

					}
				});

			}
		});

		JButton searchreqBtn = new JButton("Search");

		JScrollPane requirement_scrollPane = new JScrollPane();

		JButton requirement_btnEdit = new JButton("Edit");
		requirement_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> requirement_editFields = new ArrayList<Field>();
				ArrayList<String> issatisfied = new ArrayList<String>();
				issatisfied.add("satisfied");

				requirement_editFields.add(new Field("singlecheckbox", "is satisfied", issatisfied, 10, "items"));

				System.out.println("//////////////////");
				final Form editrequirement_Form = new Form(requirement_editFields, "Edit Requirement Form");
				final PanelBuilder editrequirement_Panel = new PanelBuilder(editrequirement_Form);
				editrequirement_Panel.makeForm();

				JFrame Edit_RequirementPage = new JFrame("Edit Requirement Module Form");
				Edit_RequirementPage.getContentPane().add(editrequirement_Form.getJPanel(), BorderLayout.NORTH);

				UtilDateModel modelsatisfydate = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				final JDatePanelImpl satisfy_datePanel = new JDatePanelImpl(modelsatisfydate, p);
				JLabel satisfylbl = new JLabel("Satisfy Date");

				final JDatePickerImpl satisfy_datePicker = new JDatePickerImpl(satisfy_datePanel,
						new DateLabelFormatter());

				// adding date
				UtilDateModel modelfor = new UtilDateModel();
				UtilDateModel modelto = new UtilDateModel();

				final JDatePanelImpl from_datePanel = new JDatePanelImpl(modelfor, p);
				JDatePanelImpl to_datePanel = new JDatePanelImpl(modelto, p);
				JLabel from = new JLabel("From");
				JLabel to = new JLabel("To");
				final JDatePickerImpl from_datePicker = new JDatePickerImpl(from_datePanel, new DateLabelFormatter());
				final JDatePickerImpl to_datePicker = new JDatePickerImpl(to_datePanel, new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());
				date_panel.add(satisfylbl);
				date_panel.add(satisfy_datePanel);
				date_panel.add(from);
				date_panel.add(from_datePanel);
				date_panel.add(to);
				date_panel.add(to_datePanel);
				Edit_RequirementPage.getContentPane().add(date_panel, BorderLayout.CENTER);
				// end date

				JButton submiteditrequeirementBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submiteditrequeirementBtn);
				Edit_RequirementPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Edit_RequirementPage.pack();
				Edit_RequirementPage.setVisible(true);
				final SingleCheckBoxJPanel statisfy_checkBoxpane = (SingleCheckBoxJPanel) editrequirement_Form
						.getJPanel().getComponent(0);

				// CheckBoxJPanel checkBoxpane = (CheckBoxJPanel)
				// editrequirement_Form.getJPanel().getComponent(1);
				// final ArrayList<String>vales =
				// checkBoxpane.getCheckedValues();

				submiteditrequeirementBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						int rowIndex = resrequirement_tabledata.getJdataTable().getSelectedRow();
						int colIndex = resrequirement_tabledata.getJdataTable().getSelectedColumn();
						if (rowIndex == -1) {
							NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
									"Please Select a Resource!");
						} else {

							String Table_click = (resrequirement_tabledata.getJdataTable().getModel()
									.getValueAt(rowIndex, 0).toString()); // the
							System.out.println(Table_click + " what have you clicked");

							String satisfydate = satisfy_datePicker.getJFormattedTextField().getText();

							String fromdate = from_datePicker.getJFormattedTextField().getText();
							String todate = to_datePicker.getJFormattedTextField().getText();
							System.out.println(satisfydate + " " + fromdate + " " + todate);

							ResourceRequirementCatalogue resreqCat = new ResourceRequirementCatalogue();

							boolean satisfied = false;
							if (statisfy_checkBoxpane.getCheckedValues().size() == 1)
								satisfied = true;

							if (satisfied == false)
								satisfydate = "1111-1-1";

							ResourceRequirement resreq = resreqCat
									.getResourceRequirement(Integer.parseInt(Table_click));
							resreq.edit(fromdate, todate, satisfied, satisfydate);
							ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
							ArrayList<ResourceRequirement> allresourcerequirements;
							allresourcerequirements = resreqCat.getResourceRequirements();
							for (int i = 0; i < allresourcerequirements.size(); i++) {
								data.add((allresourcerequirements.get(i).toHashMap()));
							}
							resrequirement_tabledata.update(data);
						}
					}
				});

			}
		});
		requirement_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JButton requirement_btnSatisfy = new JButton("Satisfy");
		requirement_btnSatisfy.setIcon(new ImageIcon(
				new ImageIcon("images/check.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		requirement_btnSatisfy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ArrayList<Field> satisfy = new ArrayList<Field>();
				Field reqname = new Field("text", "req name       ", "", 10, "name");
				final Form satisfy_requirement_Form = new Form(satisfy, "Satisfy Requirement");
				final PanelBuilder satisfy_requirement_Panel = new PanelBuilder(satisfy_requirement_Form);
				satisfy_requirement_Panel.makeForm();

				JFrame Satisfy_RequirementPage = new JFrame("Satisfy Requirement Form");
				Satisfy_RequirementPage.getContentPane().add(satisfy_requirement_Form.getJPanel(), BorderLayout.NORTH);

				// adding date
				UtilDateModel modelfor = new UtilDateModel();

				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				final JDatePanelImpl satisfyfrom_datePanel = new JDatePanelImpl(modelfor, p);
				final JDatePickerImpl satisfyfrom_datePicker = new JDatePickerImpl(satisfyfrom_datePanel,
						new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());

				date_panel.add(satisfyfrom_datePanel);
				Satisfy_RequirementPage.getContentPane().add(date_panel, BorderLayout.CENTER);
				// end date

				JButton submitsatisfyBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitsatisfyBtn);
				Satisfy_RequirementPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Satisfy_RequirementPage.pack();
				Satisfy_RequirementPage.setVisible(true);
				submitsatisfyBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						int rowIndex = resrequirement_tabledata.getJdataTable().getSelectedRow();
						int colIndex = resrequirement_tabledata.getJdataTable().getSelectedColumn();
						if (rowIndex == -1) {
							NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
									"Please Select a Resource!");
						} else {

							String Table_click = (resrequirement_tabledata.getJdataTable().getModel()
									.getValueAt(rowIndex, 0).toString()); // the
							System.out.println(Table_click + " what have you clicked");
							System.out.println(satisfyfrom_datePicker.getJFormattedTextField().getText());
							ResourceRequirementCatalogue resreqCat = new ResourceRequirementCatalogue();
							String satisfydate = satisfyfrom_datePicker.getJFormattedTextField().getText();

							resreqCat.getResourceRequirement(Integer.parseInt(Table_click)).satisfy(satisfydate);

							ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
							ArrayList<ResourceRequirement> allresourcerequirements;
							allresourcerequirements = resreqCat.getResourceRequirements();
							for (int i = 0; i < allresourcerequirements.size(); i++) {
								data.add((allresourcerequirements.get(i).toHashMap()));
							}

							resrequirement_tabledata.update(data);
						}
					}
				});

			}
		});

		JLabel lblResourceName_1 = DefaultComponentFactory.getInstance().createLabel("Resource Name");

		search_reqresourcename = new JTextField();
		search_reqresourcename.setColumns(10);

		JButton requirement_btnDelete = new JButton("Delete");
		requirement_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		requirement_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = resrequirement_tabledata.getJdataTable().getSelectedRow();
				int colIndex = resrequirement_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Resource!");
				} else {

					String Table_click = (resrequirement_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");
					ResourceRequirementCatalogue resreq = new ResourceRequirementCatalogue();
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						resreq.deleteResourceRequirement(Integer.parseInt(Table_click));
						ResourceRequirementCatalogue resreqCat = new ResourceRequirementCatalogue();
						ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
						ArrayList<ResourceRequirement> allresourcerequirements;
						allresourcerequirements = resreqCat.getResourceRequirements();
						for (int i = 0; i < allresourcerequirements.size(); i++) {
							data.add((allresourcerequirements.get(i).toHashMap()));
						}
						resrequirement_tabledata.update(data);
					}
				}
			}
		});
		GroupLayout gl_requirementPanel = new GroupLayout(requirementPanel);
		gl_requirementPanel.setHorizontalGroup(gl_requirementPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_requirementPanel.createSequentialGroup().addContainerGap(381, Short.MAX_VALUE)
						.addComponent(searchreqBtn).addGap(128)
						.addComponent(search_reqresourcename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblResourceName_1).addContainerGap())
				.addGroup(gl_requirementPanel.createSequentialGroup().addGap(40)
						.addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
						.addGap(40))
				.addGroup(gl_requirementPanel.createSequentialGroup().addComponent(requirement_btnEdit)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(requirement_btnDelete)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(requirement_btnSatisfy)
						.addPreferredGap(ComponentPlacement.RELATED, 635, Short.MAX_VALUE).addComponent(addreqBtn)));
		gl_requirementPanel.setVerticalGroup(gl_requirementPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_requirementPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_requirementPanel.createParallelGroup(Alignment.BASELINE).addComponent(searchreqBtn)
								.addComponent(lblResourceName_1).addComponent(search_reqresourcename,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(40).addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
						.addGap(40)
						.addGroup(gl_requirementPanel.createParallelGroup(Alignment.BASELINE).addComponent(addreqBtn)
								.addComponent(requirement_btnEdit).addComponent(requirement_btnSatisfy)
								.addComponent(requirement_btnDelete))));

		resrequirement_tabledata = new TableData(new ResourceRequirementCatalogue());

		requirement_scrollPane.setViewportView(resrequirement_tabledata.getJdataTable());
		requirementPanel.setLayout(gl_requirementPanel);
		searchreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ResourceRequirementCatalogue resreqcat = new
				// ResourceRequirementCatalogue();
				// HashMap<String, String> searchVars = new HashMap<String,
				// String>();
				// searchVars.put("irname", "\'" +
				// search_reqresourcename.getText() + "\'");
				// information_tabledata.update(resreqcat.SearchResource(searchVars));

			}
		});

		JPanel resourcePanel = new JPanel();
		resourcePanel.setBackground(tab_color);
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
		// get human list

		EmployeeCatalogue employeecat = new EmployeeCatalogue();
		System.out.println("all : ");
		allemployees = employeecat.readAllEmployees();

		human_tabledata = new TableData(new EmployeeCatalogue(), "human");

		JPanel informationPanel = new JPanel();
		resourcesTab.addTab("Information", null, informationPanel, null);

		JButton btnAddInformation = new JButton("Add Information Resource");
		btnAddInformation.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JScrollPane information_scrollPane = new JScrollPane();

		information_tabledata = new TableData(new InformationResourceCatalogue(), "information");

		information_scrollPane.setViewportView(information_tabledata.getJdataTable());

		JButton information_btnEdit = new JButton("Edit");
		information_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		information_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				// System.out.println(information_tableModel.getDataVector().elementAt(information_table.getSelectedRow()).toString().contains("1"));
				ArrayList<Field> information_moduleFields = new ArrayList<Field>();
				information_moduleFields.add(new Field("text", "information name", "", 20, "name"));
				information_moduleFields.add(new Field("text", "description", "", 20, "desc"));

				information_moduleFields.add(sections);
				final Form information_moduleForm = new Form(information_moduleFields, "Information Module Form");
				final PanelBuilder information_modulePanel = new PanelBuilder(information_moduleForm);
				information_modulePanel.makeForm();
				JFrame Edit_InformationModulePage = new JFrame("Edit Information Module Form");
				Edit_InformationModulePage.getContentPane().add(information_moduleForm.getJPanel(), BorderLayout.NORTH);

				// adding date
				UtilDateModel modelfor = new UtilDateModel();

				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				final JDatePanelImpl info_datePanel = new JDatePanelImpl(modelfor, p);
				final JDatePickerImpl info_datePicker = new JDatePickerImpl(info_datePanel, new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());

				date_panel.add(info_datePanel);
				Edit_InformationModulePage.getContentPane().add(date_panel, BorderLayout.CENTER);
				// end date

				JButton submiteditinformationmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submiteditinformationmoduleBtn);
				Edit_InformationModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Edit_InformationModulePage.pack();
				Edit_InformationModulePage.setVisible(true);

				submiteditinformationmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						InformationResourceCatalogue infocat = new InformationResourceCatalogue();
						System.out.println("all : ");
						infocat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < information_moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) information_moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " information");
						}
						System.out.println(info_datePicker.getJFormattedTextField().getText());

						int rowIndex = information_tabledata.getJdataTable().getSelectedRow();
						int colIndex = information_tabledata.getJdataTable().getSelectedColumn();
						if (rowIndex == -1) {
							NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
									"Please Select a Resource!");
						} else {

							String Table_click = (information_tabledata.getJdataTable().getModel()
									.getValueAt(rowIndex, 0).toString()); // the
							System.out.println(Table_click + " what have you clicked");
						}

						// infocat.addResource((inputs.get(0)));
						// // tu resource ham bayad insert she
						// allinformation.clear();
						// allinformation = infocat.readAllResources();
						// System.out.println(information_tableModel.getRowCount()
						// + " ---");
						// int rowcount = information_tableModel.getRowCount();
						// for (int j = rowcount - 1; j >= 0; j--) {
						// information_tableModel.removeRow(j);
						// }
						// System.out.println(information_tableModel.getRowCount()
						// + " ---");
						// for (int i = 0; i < allinformation.size(); i++) {
						// Object[] objs = { allinformation.get(i).get("rid"),
						// allinformation.get(i).get("irname") };
						// information_tableModel.addRow(objs);
						// }
					}
				});
			}
		});

		JButton information_btnDelete = new JButton("Delete");
		information_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		information_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = information_tabledata.getJdataTable().getSelectedRow();
				int colIndex = information_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Resource!");
				} else {

					String Table_click = (information_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");
					InformationResourceCatalogue infocat = new InformationResourceCatalogue();
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						infocat.deleteResource(Integer.parseInt(Table_click));
						information_tabledata.update(infocat.readAllResources());
					}
				}
			}
		});

		search_informationname = new JTextField();
		search_informationname.setColumns(10);

		JButton information_btnSearch = new JButton("Search");
		information_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();
				searchVars.put("irname", "\'" + search_informationname.getText() + "\'");
				if (infocat.SearchResource(searchVars).isEmpty()) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification", "No Results Found");
				} else {
					information_tabledata.update(infocat.SearchResource(searchVars));
				}

			}
		});

		JLabel lblInformationName = DefaultComponentFactory.getInstance().createLabel("Information Name");

		JButton search_informationbtnRefresh = new JButton("Refresh");
		search_informationbtnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		search_informationbtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				information_tabledata.update(infocat.readAllResources());
			}

		});
		GroupLayout gl_informationPanel = new GroupLayout(informationPanel);
		gl_informationPanel
				.setHorizontalGroup(
						gl_informationPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_informationPanel.createSequentialGroup().addGap(30)
										.addComponent(information_scrollPane, GroupLayout.DEFAULT_SIZE, 736,
												Short.MAX_VALUE)
										.addGap(30))
								.addGroup(gl_informationPanel.createSequentialGroup()
										.addComponent(information_btnEdit, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(information_btnDelete, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
										.addComponent(btnAddInformation))
								.addGroup(gl_informationPanel.createSequentialGroup()
										.addComponent(search_informationbtnRefresh)
										.addPreferredGap(ComponentPlacement.RELATED, 388, Short.MAX_VALUE)
										.addComponent(information_btnSearch).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(search_informationname, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblInformationName)
										.addContainerGap()));
		gl_informationPanel.setVerticalGroup(gl_informationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_informationPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_informationPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(search_informationname, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(information_btnSearch).addComponent(lblInformationName)
								.addComponent(search_informationbtnRefresh))
						.addGap(31).addComponent(information_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_informationPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(information_btnEdit).addComponent(information_btnDelete)
								.addComponent(btnAddInformation))
						.addContainerGap()));

		informationPanel.setLayout(gl_informationPanel);
		btnAddInformation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				ArrayList<Field> information_moduleFields = new ArrayList<Field>();
				information_moduleFields.add(new Field("text", "information name", "", 20, "name"));
				information_moduleFields.add(new Field("text", "description", "", 20, "desc"));

				information_moduleFields.add(sections);
				final Form information_moduleForm = new Form(information_moduleFields, "Information Module Form");
				final PanelBuilder information_modulePanel = new PanelBuilder(information_moduleForm);
				information_modulePanel.makeForm();
				JFrame Add_InformationModulePage = new JFrame("Add Information Module Form");
				Add_InformationModulePage.getContentPane().add(information_moduleForm.getJPanel(), BorderLayout.NORTH);

				// adding date
				UtilDateModel modelfor = new UtilDateModel();

				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				final JDatePanelImpl info_datePanel = new JDatePanelImpl(modelfor, p);
				final JDatePickerImpl info_datePicker = new JDatePickerImpl(info_datePanel, new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());

				date_panel.add(info_datePanel);
				Add_InformationModulePage.getContentPane().add(date_panel, BorderLayout.CENTER);
				// end date
				JButton submitaddinformationmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddinformationmoduleBtn);
				Add_InformationModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_InformationModulePage.pack();
				Add_InformationModulePage.setVisible(true);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) information_moduleForm.getJPanel()
						.getComponent(2);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				submitaddinformationmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						InformationResourceCatalogue infocat = new InformationResourceCatalogue();
						System.out.println("all : ");
						infocat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < information_moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) information_moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " information");
						}

						System.out.println(sections_combo.getSelectedItem() + " //////");
						Pattern p = Pattern.compile("sid=\\d+");
						String section = null;
						Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
						if (m.find()) {
							section = m.group();
						}
						System.out.println("sid: " + section);

						String infodate = info_datePicker.getJFormattedTextField().getText();

						infocat.addResource(inputs.get(0), Integer.parseInt(section.replace("sid=", "")), infodate,
								inputs.get(1));
						// tu resource ham bayad insert she
						information_tabledata.update(infocat.readAllResources());

					}
				});
			}
		});

		// get financial list

		FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
		System.out.println("all : ");
		allfinance = financat.readAllResources();

		// end get financial list

		JPanel financialPanel = new JPanel();
		resourcesTab.addTab("Financial", null, financialPanel, null);
		JButton btnAddFinancial = new JButton("Add Financial Resource");
		btnAddFinancial.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JButton financial_btnEdit = new JButton("Edit");
		financial_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		financial_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}
				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				ArrayList<Field> financial_moduleFields = new ArrayList<Field>();
				financial_moduleFields.add(new Field("text", "financial name", "", 20, "name"));
				financial_moduleFields.add(sections);
				financial_moduleFields.add(new Field("text", "model description", "", 20, "model desc"));
				financial_moduleFields.add(new Field("text", "net value", "", 20, "value"));
				financial_moduleFields.add(new Field("text", "description", "", 20, "desc"));

				final Form financial_moduleForm = new Form(financial_moduleFields, "Financial Edit Module Form");
				final PanelBuilder financial_modulePanel = new PanelBuilder(financial_moduleForm);
				financial_modulePanel.makeForm();
				JFrame Add_InformationModulePage = new JFrame("Edit Information Module Form");
				Add_InformationModulePage.getContentPane().add(financial_moduleForm.getJPanel(), BorderLayout.NORTH);

				JButton submitaddfinancialBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddfinancialBtn);
				Add_InformationModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_InformationModulePage.pack();
				Add_InformationModulePage.setVisible(true);

				submitaddfinancialBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						int rowIndex = financial_tabledata.getJdataTable().getSelectedRow();
						int colIndex = financial_tabledata.getJdataTable().getSelectedColumn();
						if (rowIndex == -1) {
							NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
									"Please Select a Resource!");
						} else {

							String Table_click = (financial_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
									.toString()); // the
							System.out.println(Table_click + " this was clicked");
						}
						System.out.println();
						FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
						System.out.println("all : ");
						financat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < financial_moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) financial_moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}

						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " financial edit");
						}

						// financat.getFinancialResource().editResource("changed
						// name", 1, 10, "changed model", "changed");

						financial_tabledata.update(financat.readAllResources());

					}
				});

			}
		});

		JScrollPane financial_table_scrollPane = new JScrollPane();
		financial_tabledata = new TableData(new FinancialResourceCatalogue(), "financial");

		financial_table_scrollPane.setViewportView(financial_tabledata.getJdataTable());

		JButton financial_btnDelete = new JButton("Delete");
		financial_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		financial_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowIndex = financial_tabledata.getJdataTable().getSelectedRow();
				int colIndex = financial_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Resource!");
				} else {

					String Table_click = (financial_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");
					FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						financat.deleteResource(Integer.parseInt(Table_click));
						financial_tabledata.update(financat.readAllResources());
					}
				}
			}
		});

		search_financialname = new JTextField();
		search_financialname.setColumns(10);

		JButton financial_btnSearch = new JButton("Search");
		financial_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();
				if (search_financialname.getText() != null && !search_financialname.getText().trim().equals(""))
					searchVars.put("finanname", "\'" + search_financialname.getText() + "\'");
				if (search_financialvalue.getText() != null && !search_financialvalue.getText().trim().equals(""))
					searchVars.put("netvalue", "\'" + search_financialvalue.getText() + "\'");
				if (search_financialmodel.getText() != null && !search_financialmodel.getText().trim().equals(""))
					searchVars.put("modeldesc", "\'" + search_financialmodel.getText() + "\'");

				if (financat.SearchResource(searchVars).isEmpty()) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification", "No Results Found");
				} else {
					financial_tabledata.update(financat.SearchResource(searchVars));
				}

			}
		});

		JLabel lblFinancialName = DefaultComponentFactory.getInstance().createLabel("Financial Name");

		JButton search_financialbtnRefresh = new JButton("Refresh");
		search_financialbtnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		search_financialbtnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allfinance.clear();
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				allfinance = financat.readAllResources();
				financial_tabledata.update(financat.readAllResources());
			}
		});

		JLabel lblModel = DefaultComponentFactory.getInstance().createLabel("Model");

		search_financialmodel = new JTextField();
		search_financialmodel.setColumns(10);

		search_financialvalue = new JTextField();
		search_financialvalue.setColumns(10);

		JLabel lblValue = DefaultComponentFactory.getInstance().createLabel("Value");

		GroupLayout gl_financialPanel = new GroupLayout(financialPanel);
		gl_financialPanel
				.setHorizontalGroup(gl_financialPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_financialPanel.createSequentialGroup().addGap(30)
								.addComponent(financial_table_scrollPane, GroupLayout.DEFAULT_SIZE, 736,
										Short.MAX_VALUE)
								.addGap(30))
						.addGroup(gl_financialPanel.createSequentialGroup()
								.addComponent(financial_btnEdit, GroupLayout.PREFERRED_SIZE, 75,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(financial_btnDelete, GroupLayout.PREFERRED_SIZE, 84,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
								.addComponent(btnAddFinancial))
						.addGroup(gl_financialPanel.createSequentialGroup().addComponent(search_financialbtnRefresh)
								.addPreferredGap(ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
								.addComponent(financial_btnSearch).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(search_financialvalue, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblValue)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_financialPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(Alignment.TRAILING,
												gl_financialPanel.createSequentialGroup()
														.addComponent(search_financialmodel, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblModel))
										.addGroup(Alignment.TRAILING,
												gl_financialPanel.createSequentialGroup()
														.addComponent(search_financialname, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblFinancialName)))
								.addContainerGap()));
		gl_financialPanel.setVerticalGroup(gl_financialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_financialPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_financialPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(search_financialname, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(financial_btnSearch).addComponent(lblFinancialName)
								.addComponent(search_financialbtnRefresh)
								.addComponent(search_financialvalue, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValue))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_financialPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblModel)
								.addComponent(search_financialmodel, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(9)
						.addComponent(financial_table_scrollPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_financialPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(financial_btnEdit).addComponent(financial_btnDelete)
								.addComponent(btnAddFinancial))
						.addContainerGap()));

		financialPanel.setLayout(gl_financialPanel);
		btnAddFinancial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				ArrayList<Field> financial_moduleFields = new ArrayList<Field>();
				financial_moduleFields.add(new Field("text", "financename", "", 20, "name"));
				financial_moduleFields.add(new Field("text", "model description", "", 20, "model desc"));
				financial_moduleFields.add(new Field("text", "net value", "", 20, "value"));
				financial_moduleFields.add(new Field("text", "description", "", 20, "desc"));
				financial_moduleFields.add(sections);

				final Form financial_moduleForm = new Form(financial_moduleFields, "Information Module Form");
				final PanelBuilder financial_modulePanel = new PanelBuilder(financial_moduleForm);
				financial_modulePanel.makeForm();
				JFrame Add_FinancialModulePage = new JFrame("Add Financial Module Form");
				Add_FinancialModulePage.getContentPane().add(financial_moduleForm.getJPanel(), BorderLayout.NORTH);

				JButton submitaddfinancialmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddfinancialmoduleBtn);
				Add_FinancialModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_FinancialModulePage.pack();
				Add_FinancialModulePage.setVisible(true);

				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) financial_moduleForm.getJPanel()
						.getComponent(4);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				submitaddfinancialmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
						System.out.println("all : ");
						financat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < financial_moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) financial_moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " financial");
						}
						System.out.println(sections_combo.getSelectedItem() + " //////");
						Pattern p = Pattern.compile("sid=\\d+");
						String section = null;
						Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
						if (m.find()) {
							section = m.group();
						}
						System.out.println("sid: " + section);
						financat.addResource((inputs.get(0)), Integer.parseInt(section.replace("sid=", "")),
								Integer.parseInt(inputs.get(2)), inputs.get(1), inputs.get(3));
						// tu resource ham bayad insert she
						allfinance.clear();
						allfinance = financat.readAllResources();
						financial_tabledata.update(financat.readAllResources());
					}
				});
			}
		});

		final JPanel modulePanel = new JPanel();
		resourcesTab.addTab("Module", null, modulePanel, null);

		// get module list
		ModuleCatalogue mcat = new ModuleCatalogue();
		System.out.println("all : ");
		allmodules = mcat.readAllResources();

		final JPanel moduledetailpanel = new JPanel();
		resourcesTab.addTab("Module Detail", null, moduledetailpanel, null);
		resourcesTab.remove(resourcesTab.getTabCount() - 1); // remove

		// moduledetail_tabledata = new TableData(new Modu);

		JScrollPane module_detail_scrollPane = new JScrollPane();

		JButton moduledetail_btnEdit = new JButton("Edit");
		moduledetail_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JButton moduledetail_btnDelete = new JButton("Delete");
		moduledetail_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JButton moduledetail_btnBack = new JButton("Back");
		moduledetail_btnBack.setIcon(new ImageIcon(
				new ImageIcon("images/back.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		moduledetail_btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("change tab to module");
				System.out.println("Change JPanel");
				int selected_index = resourcesTab.getSelectedIndex();
				resourcesTab.remove(selected_index);
				resourcesTab.insertTab("Module", null, modulePanel, null, selected_index);
				resourcesTab.setSelectedComponent(modulePanel);

			}
		});
		GroupLayout gl_moduledetailpanel = new GroupLayout(moduledetailpanel);
		gl_moduledetailpanel
				.setHorizontalGroup(
						gl_moduledetailpanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_moduledetailpanel.createSequentialGroup().addGap(30)
										.addComponent(module_detail_scrollPane, GroupLayout.DEFAULT_SIZE, 736,
												Short.MAX_VALUE)
										.addGap(30))
								.addGroup(gl_moduledetailpanel.createSequentialGroup()
										.addComponent(moduledetail_btnEdit).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(moduledetail_btnDelete)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(moduledetail_btnBack)
										.addContainerGap(550, Short.MAX_VALUE)));
		gl_moduledetailpanel
				.setVerticalGroup(
						gl_moduledetailpanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_moduledetailpanel.createSequentialGroup().addGap(30)
										.addComponent(module_detail_scrollPane, GroupLayout.DEFAULT_SIZE, 398,
												Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_moduledetailpanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(moduledetail_btnEdit).addComponent(moduledetail_btnDelete)
												.addComponent(moduledetail_btnBack))
										.addContainerGap()));
		moduledetailpanel.setLayout(gl_moduledetailpanel);

		// end module list
		JButton btnAddModule = new JButton("Add Module");
		btnAddModule.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnAddModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final ArrayList<String> employees = new ArrayList<String>();
				final ArrayList<String> financials = new ArrayList<String>();
				final ArrayList<String> physicals = new ArrayList<String>();
				final ArrayList<String> information = new ArrayList<String>();

				EmployeeCatalogue empcat = new EmployeeCatalogue();
				ArrayList<HashMap<String, String>> employe_readall = empcat.readAllEmployees();
				for (int i = 0; i < employe_readall.size(); i++) {
					employees.add(employe_readall.get(i).toString());
				}
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				ArrayList<HashMap<String, String>> financial_readall = financat.readAllResources();
				for (int i = 0; i < financial_readall.size(); i++) {
					financials.add(financial_readall.get(i).toString());
				}

				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				ArrayList<HashMap<String, String>> physical_readall = physcat.readAllResources();
				for (int i = 0; i < physical_readall.size(); i++) {
					physicals.add(physical_readall.get(i).toString());
				}

				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				ArrayList<HashMap<String, String>> information_readall = infocat.readAllResources();
				for (int i = 0; i < information_readall.size(); i++) {
					information.add(information_readall.get(i).toString());
				}

				ArrayList<Field> moduleFields = new ArrayList<Field>();
				moduleFields.add(new Field("text", "name", "", 10, "name"));
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				Field maintainers = new Field("checkBox", "employees", employees, 20, "res");
				Field financial_check = new Field("checkBox", "fianance", financials, 20, "fianance");
				Field physical_check = new Field("checkBox", "physical", physicals, 20, "physical");
				Field information_check = new Field("checkBox", "information", information, 20, "information");

				moduleFields.add(new Field("text", "duration", "", 20, "duration"));
				moduleFields.add(new Field("text", "description", "", 20, "desc"));
				moduleFields.add(sections);
				moduleFields.add(financial_check);
				moduleFields.add(physical_check);
				moduleFields.add(information_check);
				moduleFields.add(maintainers);

				final Form moduleForm = new Form(moduleFields, "Module Form");
				final PanelBuilder modulePanel = new PanelBuilder(moduleForm);
				modulePanel.makeForm();

				JFrame AddModulePage = new JFrame("Add Module Form");
				AddModulePage.getContentPane().add(moduleForm.getJPanel(), BorderLayout.NORTH);
				JScrollPane scroll = new JScrollPane(moduleForm.getJPanel());
				AddModulePage.getContentPane().add(scroll);

				JButton submitaddmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddmoduleBtn);
				AddModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				AddModulePage.pack();
				AddModulePage.setVisible(true);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) moduleForm.getJPanel().getComponent(3);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				final CheckBoxJPanel checkBoxpane_finance = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(4);
				final CheckBoxJPanel checkBoxpane_physical = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(5);
				final CheckBoxJPanel checkBoxpane_information = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(6);
				final CheckBoxJPanel checkBoxpane_employee = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(7);

				submitaddmoduleBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ModuleCatalogue mcat = new ModuleCatalogue();
						System.out.println("all : ");
						mcat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}
						System.out.println(sections_combo.getSelectedItem() + " //////");
						Pattern p = Pattern.compile("sid=\\d+");
						String section = null;
						Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
						if (m.find()) {
							section = m.group();
						}
						System.out.println("sid: " + section);

						System.out.println("----------");

						//
						long modrid = mcat.addResource(inputs.get(0), Integer.parseInt(section.replace("sid=", "")),
								Integer.parseInt(inputs.get(1)), inputs.get(2));
						// tu resource ham bayad insert she
						module_tabledata.update(mcat.readAllResources());

						//
						System.out.println("----------");
						MakeModuleCatalogue makemoduleCat = new MakeModuleCatalogue();

						final ArrayList<String> finanvales = checkBoxpane_finance.getCheckedValues();
						System.out.println(finanvales);
						final ArrayList<String> physicalvales = checkBoxpane_physical.getCheckedValues();
						System.out.println(physicalvales);
						final ArrayList<String> informationvales = checkBoxpane_information.getCheckedValues();
						System.out.println(informationvales);
						final ArrayList<String> employeevales = checkBoxpane_employee.getCheckedValues();
						System.out.println(employeevales);
						Pattern emp = Pattern.compile("empid=\\d+");
						for (int i = 0; i < employeevales.size(); i++) {
							String empids = null;
							Matcher m_emp = emp.matcher(employeevales.get(i).toString());
							if (m_emp.find()) {
								empids = m_emp.group();
							}
							System.out.println("empids: " + empids);
							makemoduleCat.addEmployee(Integer.parseInt(empids.replace("empid=", "")), (int) modrid);

						}

						Pattern res = Pattern.compile("rid=\\d+");
						for (int i = 0; i < finanvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(finanvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("finan rid: " + respids);
							makemoduleCat.addResource(Integer.parseInt(respids.replace("rid=", "")), (int) modrid);

						}

						for (int i = 0; i < physicalvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(physicalvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("phys rid: " + respids);
							makemoduleCat.addResource(Integer.parseInt(respids.replace("rid=", "")), (int) modrid);

						}

						for (int i = 0; i < informationvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(informationvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("info rid: " + respids);
							makemoduleCat.addResource(Integer.parseInt(respids.replace("rid=", "")), (int) modrid);

						}

					}
				});

			}
		});

		JScrollPane module_scrollPane = new JScrollPane();

		JButton module_btnEdit = new JButton("Edit");
		module_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		module_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final ArrayList<String> employees = new ArrayList<String>();
				final ArrayList<String> financials = new ArrayList<String>();
				final ArrayList<String> physicals = new ArrayList<String>();
				final ArrayList<String> information = new ArrayList<String>();

				EmployeeCatalogue empcat = new EmployeeCatalogue();
				ArrayList<HashMap<String, String>> employe_readall = empcat.readAllEmployees();
				for (int i = 0; i < employe_readall.size(); i++) {
					employees.add(employe_readall.get(i).toString());
				}
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				ArrayList<HashMap<String, String>> financial_readall = financat.readAllResources();
				for (int i = 0; i < financial_readall.size(); i++) {
					financials.add(financial_readall.get(i).toString());
				}

				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				ArrayList<HashMap<String, String>> physical_readall = physcat.readAllResources();
				for (int i = 0; i < physical_readall.size(); i++) {
					physicals.add(physical_readall.get(i).toString());
				}

				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				ArrayList<HashMap<String, String>> information_readall = infocat.readAllResources();
				for (int i = 0; i < information_readall.size(); i++) {
					information.add(information_readall.get(i).toString());
				}

				ArrayList<Field> moduleFields = new ArrayList<Field>();
				moduleFields.add(new Field("text", "name", "", 10, "name"));
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				Field maintainers = new Field("checkBox", "employees", employees, 20, "res");
				Field financial_check = new Field("checkBox", "fianance", financials, 20, "fianance");
				Field physical_check = new Field("checkBox", "physical", physicals, 20, "physical");
				Field information_check = new Field("checkBox", "information", information, 20, "information");

				moduleFields.add(new Field("text", "duration", "", 20, "duration"));
				moduleFields.add(new Field("text", "description", "", 20, "desc"));
				moduleFields.add(sections);
				moduleFields.add(financial_check);
				moduleFields.add(physical_check);
				moduleFields.add(information_check);
				moduleFields.add(maintainers);

				final Form moduleForm = new Form(moduleFields, "Module Form");
				final PanelBuilder modulePanel = new PanelBuilder(moduleForm);
				modulePanel.makeForm();

				JFrame EditModulePage = new JFrame("Edit Module Form");
				EditModulePage.getContentPane().add(moduleForm.getJPanel(), BorderLayout.NORTH);
				JScrollPane scroll = new JScrollPane(moduleForm.getJPanel());
				EditModulePage.getContentPane().add(scroll);

				JButton submitaddmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddmoduleBtn);
				EditModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				EditModulePage.pack();
				EditModulePage.setVisible(true);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) moduleForm.getJPanel().getComponent(3);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				final CheckBoxJPanel checkBoxpane_finance = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(4);
				final CheckBoxJPanel checkBoxpane_physical = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(5);
				final CheckBoxJPanel checkBoxpane_information = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(6);
				final CheckBoxJPanel checkBoxpane_employee = (CheckBoxJPanel) moduleForm.getJPanel().getComponent(7);

				submitaddmoduleBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ModuleCatalogue mcat = new ModuleCatalogue();
						System.out.println("all : ");
						mcat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}
						System.out.println(sections_combo.getSelectedItem() + " //////");
						Pattern p = Pattern.compile("sid=\\d+");
						String section = null;
						Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
						if (m.find()) {
							section = m.group();
						}
						System.out.println("sid: " + section);

						System.out.println("----------");

						//

						mcat.getModule(selected_module).editResource(inputs.get(0),
								Integer.parseInt(section.replace("sid=", "")));
						// addResource(inputs.get(0),
						// Integer.parseInt(section.replace("sid=", "")),
						// Integer.parseInt(inputs.get(1)), inputs.get(2));
						// tu resource ham bayad insert she
						module_tabledata.update(mcat.readAllResources());

						//
						System.out.println("----------");
						MakeModuleCatalogue makemoduleCat = new MakeModuleCatalogue();

						final ArrayList<String> finanvales = checkBoxpane_finance.getCheckedValues();
						System.out.println(finanvales);
						final ArrayList<String> physicalvales = checkBoxpane_physical.getCheckedValues();
						System.out.println(physicalvales);
						final ArrayList<String> informationvales = checkBoxpane_information.getCheckedValues();
						System.out.println(informationvales);
						final ArrayList<String> employeevales = checkBoxpane_employee.getCheckedValues();
						System.out.println(employeevales);
						Pattern emp = Pattern.compile("empid=\\d+");
						for (int i = 0; i < employeevales.size(); i++) {
							String empids = null;
							Matcher m_emp = emp.matcher(employeevales.get(i).toString());
							if (m_emp.find()) {
								empids = m_emp.group();
							}
							System.out.println("empids: " + empids);
							makemoduleCat.addEmployee(Integer.parseInt(empids.replace("empid=", "")), selected_module);

						}

						Pattern res = Pattern.compile("rid=\\d+");
						for (int i = 0; i < finanvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(finanvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("finan rid: " + respids);
							makemoduleCat.addResource(Integer.parseInt(respids.replace("rid=", "")), selected_module);

						}

						for (int i = 0; i < physicalvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(physicalvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("phys rid: " + respids);
							makemoduleCat.addResource(Integer.parseInt(respids.replace("rid=", "")), selected_module);

						}

						for (int i = 0; i < informationvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(informationvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("info rid: " + respids);
							makemoduleCat.addResource(Integer.parseInt(respids.replace("rid=", "")), selected_module);

						}

					}
				});

			}

		});

		JButton module_btnDelete = new JButton("Delete");
		module_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		module_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowIndex = module_tabledata.getJdataTable().getSelectedRow();
				int colIndex = module_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Module!");
				} else {

					String Table_click = (module_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");
					ModuleCatalogue modcat = new ModuleCatalogue();
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						modcat.deleteResource(Integer.parseInt(Table_click));
						module_tabledata.update(modcat.readAllResources());
					}
				}
			}
		});
		final JPanel maintaining_panel = new JPanel();

		JButton btnViewMaintaning = new JButton("View Maintaning");
		btnViewMaintaning.setIcon(new ImageIcon(
				new ImageIcon("images/view.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnViewMaintaning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = module_tabledata.getJdataTable().getSelectedRow();
				int colIndex = module_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a module!");
				} else {

					String Table_click = (module_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // return
					selected_module = Integer.parseInt(Table_click.trim());

					System.out.println(Table_click);
					System.out.println("---module id-- " + selected_module);
					System.out.println("Change JPanel");
					MaintainingModuleCatalogue maintainmodulecat = new MaintainingModuleCatalogue();
					ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
					ArrayList<MaintainingModule> allmaintainmodule;
					allmaintainmodule = maintainmodulecat.getMaintainingModules(selected_module);
					for (int i = 0; i < allmaintainmodule.size(); i++) {
						data.add((allmaintainmodule.get(i).toHashMap()));
					}
					System.out.println("DATA");
					System.out.println(data);

					maintaining_tabledata.update(data);

					int selected_index = resourcesTab.getSelectedIndex();
					resourcesTab.remove(selected_index);
					resourcesTab.insertTab("Maintaining", null, maintaining_panel, null, selected_index);
					resourcesTab.setSelectedComponent(maintaining_panel);
				}
			}
		});

		search_modulename = new JTextField();
		search_modulename.setColumns(10);

		JButton module_btnSearch = new JButton("Search");
		module_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ModuleCatalogue mcat = new ModuleCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();

				if (search_modulename.getText() != null && !search_modulename.getText().trim().equals(""))
					searchVars.put("modname", "\'" + search_modulename.getText() + "\'");
				if (search_moduleduration.getText() != null && !search_moduleduration.getText().trim().equals(""))
					searchVars.put("duration", "\'" + search_moduleduration.getText() + "\'");

				if (mcat.SearchResource(searchVars).isEmpty()) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification", "No Results Found");
				} else {
					module_tabledata.update(mcat.SearchResource(searchVars));
				}
			}

		});

		JLabel lblModuleName = DefaultComponentFactory.getInstance().createLabel("Module Name");

		JButton search_modulebtnRefresh = new JButton("Refresh");
		search_modulebtnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		search_modulebtnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allmodules.clear();
				ModuleCatalogue modcat = new ModuleCatalogue();
				allmodules = modcat.readAllResources();

				module_tabledata.update(modcat.readAllResources());

			}
		});

		JLabel lblDuration = DefaultComponentFactory.getInstance().createLabel("Duration");

		search_moduleduration = new JTextField();
		search_moduleduration.setColumns(10);

		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setIcon(new ImageIcon(
				new ImageIcon("images/view.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = module_tabledata.getJdataTable().getSelectedRow();
				int colIndex = module_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a module!");
				} else {

					String Table_click = (module_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // return
					selected_module = Integer.parseInt(Table_click.trim());

					System.out.println(Table_click);
					System.out.println("---module id-- " + selected_module);
					System.out.println("Change JPanel");
					// MaintainingModuleCatalogue maintainmodulecat = new
					// MaintainingModuleCatalogue();
					// ArrayList<HashMap<String, String>> data = new
					// ArrayList<HashMap<String, String>>();
					// ArrayList<MaintainingModule> allmaintainmodule;
					// allmaintainmodule =
					// maintainmodulecat.getMaintainingModules(selected_module);
					// for (int i = 0; i < allmaintainmodule.size(); i++) {
					// data.add((allmaintainmodule.get(i).toHashMap()));
					// }
					// System.out.println("DATA");
					// System.out.println(data);
					//
					// maintaining_tabledata.update(data);
					//
					int selected_index = resourcesTab.getSelectedIndex();
					resourcesTab.remove(selected_index);
					resourcesTab.insertTab("Module Detail", null, moduledetailpanel, null, selected_index);
					resourcesTab.setSelectedComponent(moduledetailpanel);
				}
			}
		});
		GroupLayout gl_modulePanel_1 = new GroupLayout(modulePanel);
		gl_modulePanel_1.setHorizontalGroup(gl_modulePanel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modulePanel_1.createSequentialGroup().addGap(30)
						.addComponent(module_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(30))
				.addGroup(
						gl_modulePanel_1.createSequentialGroup()
								.addComponent(module_btnEdit, GroupLayout.PREFERRED_SIZE, 75,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(module_btnDelete, GroupLayout.PREFERRED_SIZE, 83,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
								.addComponent(btnViewDetails).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnViewMaintaning).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAddModule))
				.addGroup(gl_modulePanel_1.createSequentialGroup()
						.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(search_moduleduration, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING,
										gl_modulePanel_1.createSequentialGroup().addComponent(search_modulebtnRefresh)
												.addPreferredGap(ComponentPlacement.RELATED, 416, Short.MAX_VALUE)
												.addComponent(module_btnSearch)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(search_modulename, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblModuleName, Alignment.TRAILING)
								.addComponent(lblDuration, Alignment.TRAILING))
						.addContainerGap()));
		gl_modulePanel_1.setVerticalGroup(gl_modulePanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modulePanel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(search_modulename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblModuleName).addComponent(search_modulebtnRefresh)
								.addComponent(module_btnSearch))
						.addGap(3)
						.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblDuration)
								.addComponent(search_moduleduration, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(module_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.BASELINE).addComponent(module_btnEdit)
								.addComponent(module_btnDelete).addComponent(btnAddModule)
								.addComponent(btnViewMaintaning).addComponent(btnViewDetails))
						.addContainerGap()));

		module_tabledata = new TableData(new ModuleCatalogue(), "module");
		module_scrollPane.setViewportView(module_tabledata.getJdataTable());
		modulePanel.setLayout(gl_modulePanel_1);

		// get phys res list

		PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
		System.out.println("all : ");
		allphysicals = physcat.readAllResources();

		resourcesTab.addTab("Maintaining Module", null, maintaining_panel, null);
		resourcesTab.remove(resourcesTab.getTabCount() - 1); // remove
																// maintaining
																// tab

		JScrollPane maintaining_scrollPane = new JScrollPane();

		JButton maintaining_btnEdit = new JButton("Edit");
		maintaining_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final ArrayList<String> employees = new ArrayList<String>();
				final ArrayList<String> financials = new ArrayList<String>();
				final ArrayList<String> physicals = new ArrayList<String>();
				final ArrayList<String> information = new ArrayList<String>();

				EmployeeCatalogue empcat = new EmployeeCatalogue();
				ArrayList<HashMap<String, String>> employe_readall = empcat.readAllEmployees();
				for (int i = 0; i < employe_readall.size(); i++) {
					employees.add(employe_readall.get(i).toString());
				}
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				ArrayList<HashMap<String, String>> financial_readall = financat.readAllResources();
				for (int i = 0; i < financial_readall.size(); i++) {
					financials.add(financial_readall.get(i).toString());
				}

				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				ArrayList<HashMap<String, String>> physical_readall = physcat.readAllResources();
				for (int i = 0; i < physical_readall.size(); i++) {
					physicals.add(physical_readall.get(i).toString());
				}

				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				ArrayList<HashMap<String, String>> information_readall = infocat.readAllResources();
				for (int i = 0; i < information_readall.size(); i++) {
					information.add(information_readall.get(i).toString());
				}

				ArrayList<Field> maintain_moduleFields = new ArrayList<Field>();
				Field change_type = new Field("text", "change type", "", 20, "change type");
				Field duration = new Field("text", "duration", "", 20, "duration");
				Field maintainers = new Field("checkBox", "employees", employees, 20, "res");
				Field financial_check = new Field("checkBox", "fianance", financials, 20, "fianance");
				Field physical_check = new Field("checkBox", "physical", physicals, 20, "physical");
				Field information_check = new Field("checkBox", "information", information, 20, "information");

				maintain_moduleFields.add(change_type);
				maintain_moduleFields.add(duration);
				maintain_moduleFields.add(financial_check);
				maintain_moduleFields.add(physical_check);
				maintain_moduleFields.add(information_check);
				maintain_moduleFields.add(maintainers);

				final Form maintain_Form = new Form(maintain_moduleFields, "Maintain Module Form");
				final PanelBuilder maintain_Panel = new PanelBuilder(maintain_Form);
				maintain_Panel.makeForm();

				JFrame Edit_MaintainPage = new JFrame("Edit Maintain Module Form");

				JScrollPane scroll = new JScrollPane(maintain_Form.getJPanel());
				Edit_MaintainPage.getContentPane().add(scroll);

				// Add_MaintainPage.getContentPane().add(scroll,
				// BorderLayout.NORTH);

				JButton submiteditmaintainmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submiteditmaintainmoduleBtn);
				Edit_MaintainPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Edit_MaintainPage.pack();
				Edit_MaintainPage.setVisible(true);

				final CheckBoxJPanel checkBoxpane_emp = (CheckBoxJPanel) maintain_Form.getJPanel().getComponent(5);
				final CheckBoxJPanel checkBoxpane_phys = (CheckBoxJPanel) maintain_Form.getJPanel().getComponent(3);
				final CheckBoxJPanel checkBoxpane_financial = (CheckBoxJPanel) maintain_Form.getJPanel()
						.getComponent(2);
				final CheckBoxJPanel checkBoxpane_information = (CheckBoxJPanel) maintain_Form.getJPanel()
						.getComponent(4);

				submiteditmaintainmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// INJA TODO Auto-generated method stub
						int rowIndex = maintaining_tabledata.getJdataTable().getSelectedRow();

						String Table_click = (maintaining_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
								.toString()); // return
						selected_maintaining_module = Integer.parseInt(Table_click.trim());

						System.out.println(Table_click);
						System.out.println("---maintian module id-- " + selected_module);

						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < maintain_Form.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) maintain_Form.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}

						MaintainingModuleCatalogue maintainmodulecat = new MaintainingModuleCatalogue();
						// MaintainingModule maintainmod =
						// maintainmodulecat.ge(selected_maintaining_module);

						// long maintainmodpk =
						// maintainmodulecat.addMaintainingModule(selected_module,
						// inputs.get(0),
						// Integer.parseInt(inputs.get(1)));

						ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
						ArrayList<MaintainingModule> allmaintainmodule;
						allmaintainmodule = maintainmodulecat.getMaintainingModules(selected_module);
						for (int i = 0; i < allmaintainmodule.size(); i++) {
							data.add((allmaintainmodule.get(i).toHashMap()));
						}
						System.out.println("DATA");

						maintaining_tabledata.update(data);

						System.out.println("----------");
						MaintainModEmpResCatalogue maintainmodempresCat = new MaintainModEmpResCatalogue();

						final ArrayList<String> finanvales = checkBoxpane_financial.getCheckedValues();
						System.out.println(finanvales);
						final ArrayList<String> physicalvales = checkBoxpane_phys.getCheckedValues();
						System.out.println(physicalvales);
						final ArrayList<String> informationvales = checkBoxpane_information.getCheckedValues();
						System.out.println(informationvales);
						final ArrayList<String> employeevales = checkBoxpane_emp.getCheckedValues();
						System.out.println(employeevales);
						Pattern emp = Pattern.compile("empid=\\d+");
						for (int i = 0; i < employeevales.size(); i++) {
							String empids = null;
							Matcher m_emp = emp.matcher(employeevales.get(i).toString());
							if (m_emp.find()) {
								empids = m_emp.group();
							}
							System.out.println("empids: " + empids);
							maintainmodempresCat.addEmployee(Integer.parseInt(empids.replace("empid=", "")),
									selected_maintaining_module);
						}

						Pattern res = Pattern.compile("rid=\\d+");
						for (int i = 0; i < finanvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(finanvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("finan rid: " + respids);
							maintainmodempresCat.addResource(Integer.parseInt(respids.replace("rid=", "")),
									selected_maintaining_module);

						}

						for (int i = 0; i < physicalvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(physicalvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("phys rid: " + respids);
							maintainmodempresCat.addResource(Integer.parseInt(respids.replace("rid=", "")),
									selected_maintaining_module);

						}

						for (int i = 0; i < informationvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(informationvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("info rid: " + respids);
							maintainmodempresCat.addResource(Integer.parseInt(respids.replace("rid=", "")),
									selected_maintaining_module);

						}

					}
				});

			}
		});
		maintaining_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JButton maintaining_btnDelete = new JButton("Delete");
		maintaining_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		maintaining_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
						"Are you sure you want to Delete this item?");
				if (myDialog.getAnswer()) {
					int rowIndex = maintaining_tabledata.getJdataTable().getSelectedRow();

					String Table_click = (maintaining_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // return
					selected_maintaining_module = Integer.parseInt(Table_click.trim());

					System.out.println(Table_click);
					System.out.println("---maintian module id-- " + selected_module);

					MaintainingModuleCatalogue maintainmodcat = new MaintainingModuleCatalogue();
					maintainmodcat.deleteMaintainingModule(selected_maintaining_module);
					ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
					ArrayList<MaintainingModule> allmaintainmodule;
					allmaintainmodule = maintainmodcat.getMaintainingModules(selected_module);
					for (int i = 0; i < allmaintainmodule.size(); i++) {
						data.add((allmaintainmodule.get(i).toHashMap()));
					}
					System.out.println("DATA " + data);
					maintaining_tabledata.update(data);

				}
			}
		});

		JButton btnAddMaintaining = new JButton("Add Maintaining");
		btnAddMaintaining.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnAddMaintaining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final ArrayList<String> employees = new ArrayList<String>();
				final ArrayList<String> financials = new ArrayList<String>();
				final ArrayList<String> physicals = new ArrayList<String>();
				final ArrayList<String> information = new ArrayList<String>();

				EmployeeCatalogue empcat = new EmployeeCatalogue();
				ArrayList<HashMap<String, String>> employe_readall = empcat.readAllEmployees();
				for (int i = 0; i < employe_readall.size(); i++) {
					employees.add(employe_readall.get(i).toString());
				}
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				ArrayList<HashMap<String, String>> financial_readall = financat.readAllResources();
				for (int i = 0; i < financial_readall.size(); i++) {
					financials.add(financial_readall.get(i).toString());
				}

				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				ArrayList<HashMap<String, String>> physical_readall = physcat.readAllResources();
				for (int i = 0; i < physical_readall.size(); i++) {
					physicals.add(physical_readall.get(i).toString());
				}

				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				ArrayList<HashMap<String, String>> information_readall = infocat.readAllResources();
				for (int i = 0; i < information_readall.size(); i++) {
					information.add(information_readall.get(i).toString());
				}

				ArrayList<Field> maintain_moduleFields = new ArrayList<Field>();
				Field change_type = new Field("text", "change type", "", 20, "change type");
				Field duration = new Field("text", "duration", "", 20, "duration");
				Field maintainers = new Field("checkBox", "employees", employees, 20, "res");
				Field financial_check = new Field("checkBox", "fianance", financials, 20, "fianance");
				Field physical_check = new Field("checkBox", "physical", physicals, 20, "physical");
				Field information_check = new Field("checkBox", "information", information, 20, "information");

				maintain_moduleFields.add(change_type);
				maintain_moduleFields.add(duration);
				maintain_moduleFields.add(financial_check);
				maintain_moduleFields.add(physical_check);
				maintain_moduleFields.add(information_check);
				maintain_moduleFields.add(maintainers);

				final Form maintain_Form = new Form(maintain_moduleFields, "Maintain Module Form");
				final PanelBuilder maintain_Panel = new PanelBuilder(maintain_Form);
				maintain_Panel.makeForm();

				JFrame Add_MaintainPage = new JFrame("Add Maintain Module Form");

				JScrollPane scroll = new JScrollPane(maintain_Form.getJPanel());
				Add_MaintainPage.getContentPane().add(scroll);

				// Add_MaintainPage.getContentPane().add(scroll,
				// BorderLayout.NORTH);

				JButton submitmaintainmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitmaintainmoduleBtn);
				Add_MaintainPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_MaintainPage.pack();
				Add_MaintainPage.setVisible(true);

				final CheckBoxJPanel checkBoxpane_emp = (CheckBoxJPanel) maintain_Form.getJPanel().getComponent(5);
				final CheckBoxJPanel checkBoxpane_phys = (CheckBoxJPanel) maintain_Form.getJPanel().getComponent(3);
				final CheckBoxJPanel checkBoxpane_financial = (CheckBoxJPanel) maintain_Form.getJPanel()
						.getComponent(2);
				final CheckBoxJPanel checkBoxpane_information = (CheckBoxJPanel) maintain_Form.getJPanel()
						.getComponent(4);

				submitmaintainmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// INJA TODO Auto-generated method stub
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < maintain_Form.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) maintain_Form.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}

						MaintainingModuleCatalogue maintainmodulecat = new MaintainingModuleCatalogue();
						long maintainmodpk = maintainmodulecat.addMaintainingModule(selected_module, inputs.get(0),
								Integer.parseInt(inputs.get(1)));

						ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
						ArrayList<MaintainingModule> allmaintainmodule;
						allmaintainmodule = maintainmodulecat.getMaintainingModules(selected_module);
						for (int i = 0; i < allmaintainmodule.size(); i++) {
							data.add((allmaintainmodule.get(i).toHashMap()));
						}
						System.out.println("DATA");

						maintaining_tabledata.update(data);

						System.out.println("----------");
						MaintainModEmpResCatalogue maintainmodempresCat = new MaintainModEmpResCatalogue();

						final ArrayList<String> finanvales = checkBoxpane_financial.getCheckedValues();
						System.out.println(finanvales);
						final ArrayList<String> physicalvales = checkBoxpane_phys.getCheckedValues();
						System.out.println(physicalvales);
						final ArrayList<String> informationvales = checkBoxpane_information.getCheckedValues();
						System.out.println(informationvales);
						final ArrayList<String> employeevales = checkBoxpane_emp.getCheckedValues();
						System.out.println(employeevales);
						Pattern emp = Pattern.compile("empid=\\d+");
						for (int i = 0; i < employeevales.size(); i++) {
							String empids = null;
							Matcher m_emp = emp.matcher(employeevales.get(i).toString());
							if (m_emp.find()) {
								empids = m_emp.group();
							}
							System.out.println("empids: " + empids);
							maintainmodempresCat.addEmployee(Integer.parseInt(empids.replace("empid=", "")),
									(int) maintainmodpk);
						}

						Pattern res = Pattern.compile("rid=\\d+");
						for (int i = 0; i < finanvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(finanvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("finan rid: " + respids);
							maintainmodempresCat.addResource(Integer.parseInt(respids.replace("rid=", "")),
									(int) maintainmodpk);

						}

						for (int i = 0; i < physicalvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(physicalvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("phys rid: " + respids);
							maintainmodempresCat.addResource(Integer.parseInt(respids.replace("rid=", "")),
									(int) maintainmodpk);

						}

						for (int i = 0; i < informationvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(informationvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("info rid: " + respids);
							maintainmodempresCat.addResource(Integer.parseInt(respids.replace("rid=", "")),
									(int) maintainmodpk);

						}

					}
				});
			}
		});

		JButton btnBacktoModule = new JButton("Back");

		btnBacktoModule.setIcon(new ImageIcon(
				new ImageIcon("images/back.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnBacktoModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("change tab to module");
				System.out.println("Change JPanel");
				int selected_index = resourcesTab.getSelectedIndex();
				resourcesTab.remove(selected_index);
				resourcesTab.insertTab("Module", null, modulePanel, null, selected_index);
				resourcesTab.setSelectedComponent(modulePanel);

			}
		});

		JButton btnSearch = new JButton("Search");

		search_maintainingname = new JTextField();
		search_maintainingname.setColumns(10);

		JButton search_maintainingbtnRefresh = new JButton("Refresh");
		search_maintainingbtnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		GroupLayout gl_maintaining_panel = new GroupLayout(maintaining_panel);
		gl_maintaining_panel
				.setHorizontalGroup(
						gl_maintaining_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_maintaining_panel.createSequentialGroup().addGap(30)
										.addComponent(maintaining_scrollPane, GroupLayout.DEFAULT_SIZE, 736,
												Short.MAX_VALUE)
										.addGap(30))
								.addGroup(Alignment.LEADING, gl_maintaining_panel.createSequentialGroup()
										.addComponent(maintaining_btnEdit, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(maintaining_btnDelete, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBacktoModule)
										.addPreferredGap(ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
										.addComponent(btnAddMaintaining, GroupLayout.PREFERRED_SIZE, 147,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_maintaining_panel.createSequentialGroup()
												.addComponent(search_maintainingbtnRefresh)
												.addPreferredGap(ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
												.addComponent(btnSearch).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(search_maintainingname, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(132)));
		gl_maintaining_panel.setVerticalGroup(gl_maintaining_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_maintaining_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_maintaining_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnSearch)
								.addComponent(search_maintainingname, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(search_maintainingbtnRefresh))
						.addGap(30).addComponent(maintaining_scrollPane, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_maintaining_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddMaintaining).addComponent(maintaining_btnEdit)
								.addComponent(maintaining_btnDelete).addComponent(btnBacktoModule))
						.addContainerGap()));

		maintaining_tabledata = new TableData(new MaintainingModuleCatalogue(), selected_module);
		maintaining_scrollPane.setViewportView(maintaining_tabledata.getJdataTable());
		maintaining_panel.setLayout(gl_maintaining_panel);

		JPanel humanPanel = new JPanel();
		resourcesTab.addTab("Human", null, humanPanel, null);
		JScrollPane human_scrollPane = new JScrollPane();

		JButton human_btnEdit = new JButton("Edit");
		human_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		human_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}
				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				ArrayList<Field> human_moduleFields = new ArrayList<Field>();
				human_moduleFields.add(new Field("text", "employee name", "", 20, "name"));
				human_moduleFields.add(new Field("text", "username", "", 20, "username"));
				human_moduleFields.add(new Field("text", "password", "", 20, "password"));
				human_moduleFields.add(new Field("text", "post", "", 20, "post"));
				human_moduleFields.add(sections);

				final Form human_moduleForm = new Form(human_moduleFields, "Employee Edit Module Form");
				final PanelBuilder human_modulePanel = new PanelBuilder(human_moduleForm);
				human_modulePanel.makeForm();
				JFrame Edit_EmployeePage = new JFrame("Edit Employee Form");
				Edit_EmployeePage.getContentPane().add(human_moduleForm.getJPanel(), BorderLayout.NORTH);

				JButton submiteditemployeeBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submiteditemployeeBtn);
				Edit_EmployeePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Edit_EmployeePage.pack();
				Edit_EmployeePage.setVisible(true);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) human_moduleForm.getJPanel().getComponent(4);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				submiteditemployeeBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int rowIndex = human_tabledata.getJdataTable().getSelectedRow();
						int colIndex = human_tabledata.getJdataTable().getSelectedColumn();
						if (rowIndex == -1) {
							NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
									"Please Select an Employee!");
						} else {

							EmployeeCatalogue empcat = new EmployeeCatalogue();
							System.out.println("all : ");
							empcat.readAllEmployees();
							ArrayList<String> inputs = new ArrayList<String>();
							for (int i = 0; i < human_moduleForm.getJPanel().getComponentCount(); i++) {
								FieldPanel fpanel = (FieldPanel) human_moduleForm.getJPanel().getComponent(i);
								inputs.add(fpanel.getValues().get(0));
							}
							for (int i = 0; i < inputs.size(); i++) {
								System.out.println(inputs.get(i) + " human");
							}

							System.out.println(sections_combo.getSelectedItem() + " //////");
							Pattern p = Pattern.compile("sid=\\d+");
							String section = null;
							Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
							if (m.find()) {
								section = m.group();
							}
							System.out.println("sid: " + section);

							String Table_click = (human_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
									.toString()); // the
							System.out.println(Table_click + " this was clicked");
							Employee employee = empcat.getEmployee(Integer.parseInt(Table_click));
							employee.editHuman(inputs.get(0), Integer.parseInt(section.replace("sid=", "")),
									inputs.get(2), inputs.get(3));

							empcat.readAllEmployees();
							allemployees.clear();
							allemployees = empcat.readAllEmployees();
							human_tabledata.update(empcat.readAllEmployees());
						}
					}
				});

			}
		});

		JButton human_btnDelete = new JButton("Delete");
		human_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		human_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = human_tabledata.getJdataTable().getSelectedRow();
				int colIndex = human_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select an Employee!");
				} else {

					String Table_click = (human_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // return
					System.out.println(Table_click);
					EmployeeCatalogue empcat_delete = new EmployeeCatalogue();
					empcat_delete.deleteEmployee(Integer.parseInt(Table_click));
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						allemployees = empcat_delete.readAllEmployees();
						human_tabledata.update(empcat_delete.readAllEmployees());
					}
				}
			}
		});

		search_humanname = new JTextField();
		search_humanname.setColumns(10);

		JButton human_btnSearch = new JButton("Search");
		human_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();

				if (search_humanname.getText() != null && !search_humanname.getText().trim().equals(""))
					searchVars.put("empname", "\'" + search_humanname.getText() + "\'");
				if (search_humanpost.getText() != null && !search_humanpost.getText().trim().equals(""))
					searchVars.put("post", "\'" + search_humanpost.getText() + "\'");

				if (empcat.SearchEmployee(searchVars).isEmpty()) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification", "No Results Found");
				} else {
					human_tabledata.update(empcat.SearchEmployee(searchVars));
				}

			}
		});

		JLabel lblHumanName = DefaultComponentFactory.getInstance().createLabel("Human name");

		JButton search_humanbtnRefresh = new JButton("Refresh");
		search_humanbtnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		search_humanbtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allemployees.clear();
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				human_tabledata.update(empcat.readAllEmployees());

			}
		});

		JLabel lblPost = DefaultComponentFactory.getInstance().createLabel("Post");

		search_humanpost = new JTextField();
		search_humanpost.setColumns(10);

		JLabel lblSection = DefaultComponentFactory.getInstance().createLabel("Section");

		search_humansection = new JTextField();
		search_humansection.setColumns(10);

		JButton human_btnAdd = new JButton("Add Employee");
		human_btnAdd.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		human_btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}
				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				ArrayList<Field> human_moduleFields = new ArrayList<Field>();
				human_moduleFields.add(new Field("text", "employee name", "", 20, "name"));
				human_moduleFields.add(new Field("text", "username", "", 20, "username"));
				human_moduleFields.add(new Field("text", "password", "", 20, "password"));
				human_moduleFields.add(new Field("text", "post", "", 20, "post"));
				human_moduleFields.add(sections);

				final Form human_moduleForm = new Form(human_moduleFields, "Financial Edit Module Form");
				final PanelBuilder human_modulePanel = new PanelBuilder(human_moduleForm);
				human_modulePanel.makeForm();
				JFrame Add_EmployeePage = new JFrame("Add Employee Form");
				Add_EmployeePage.getContentPane().add(human_moduleForm.getJPanel(), BorderLayout.NORTH);

				JButton submitaddemployeeBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddemployeeBtn);
				Add_EmployeePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_EmployeePage.pack();
				Add_EmployeePage.setVisible(true);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) human_moduleForm.getJPanel().getComponent(4);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				submitaddemployeeBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						EmployeeCatalogue empcat = new EmployeeCatalogue();
						System.out.println("all : ");
						empcat.readAllEmployees();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < human_moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) human_moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " human");
						}

						System.out.println(sections_combo.getSelectedItem() + " //////");
						Pattern p = Pattern.compile("sid=\\d+");
						String section = null;
						Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
						if (m.find()) {
							section = m.group();
						}
						System.out.println("sid: " + section);

						empcat.addEmployee(inputs.get(0), inputs.get(3), Integer.parseInt(section.replace("sid=", "")),
								inputs.get(1), inputs.get(2), false, false);
						human_tabledata.update(empcat.readAllEmployees());

					}
				});

			}
		});
		GroupLayout gl_humanPanel = new GroupLayout(humanPanel);
		gl_humanPanel
				.setHorizontalGroup(
						gl_humanPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_humanPanel.createSequentialGroup().addGap(30)
												.addComponent(human_scrollPane, GroupLayout.DEFAULT_SIZE, 736,
														Short.MAX_VALUE)
												.addGap(30))
								.addGroup(gl_humanPanel.createSequentialGroup()
										.addComponent(human_btnEdit, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(human_btnDelete, GroupLayout.PREFERRED_SIZE, 84,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 514, Short.MAX_VALUE)
										.addComponent(human_btnAdd))
								.addGroup(gl_humanPanel.createSequentialGroup().addComponent(search_humanbtnRefresh)
										.addPreferredGap(ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
										.addComponent(human_btnSearch).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(search_humansection, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblSection)
										.addGap(8)
										.addGroup(gl_humanPanel.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(gl_humanPanel.createSequentialGroup()
														.addComponent(search_humanname, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblHumanName))
												.addGroup(gl_humanPanel.createSequentialGroup()
														.addComponent(search_humanpost, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblPost)))
										.addContainerGap()));
		gl_humanPanel.setVerticalGroup(gl_humanPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_humanPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_humanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(search_humanname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(human_btnSearch).addComponent(lblHumanName).addComponent(search_humanbtnRefresh)
						.addComponent(lblSection).addComponent(search_humansection, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(3)
				.addGroup(gl_humanPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblPost).addComponent(
						search_humanpost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(human_scrollPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_humanPanel.createParallelGroup(Alignment.BASELINE).addComponent(human_btnEdit)
						.addComponent(human_btnDelete).addComponent(human_btnAdd))
				.addContainerGap()));
		human_scrollPane.setViewportView(human_tabledata.getJdataTable());
		humanPanel.setLayout(gl_humanPanel);

		// end phys res

		JPanel physicalPanel = new JPanel();
		resourcesTab.addTab("Physical", null, physicalPanel, null);

		JButton btnAddPhysicalResource = new JButton("Add Physical Resource");
		btnAddPhysicalResource.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnAddPhysicalResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				ArrayList<Field> physical_moduleFields = new ArrayList<Field>();
				physical_moduleFields.add(new Field("text", "physical name", "", 20, "name"));
				physical_moduleFields.add(new Field("text", "model description", "", 20, "model desc"));
				physical_moduleFields.add(new Field("text", "description", "", 20, "description"));

				physical_moduleFields.add(sections);
				final Form physical_moduleForm = new Form(physical_moduleFields, "Physical Module Form");
				final PanelBuilder physical_modulePanel = new PanelBuilder(physical_moduleForm);
				physical_modulePanel.makeForm();
				JFrame Add_PhysicalModulePage = new JFrame("Add Physical Module Form");
				Add_PhysicalModulePage.getContentPane().add(physical_moduleForm.getJPanel(), BorderLayout.NORTH);

				JButton submitaddphysicalmoduleBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddphysicalmoduleBtn);
				Add_PhysicalModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_PhysicalModulePage.pack();
				Add_PhysicalModulePage.setVisible(true);
				ComboBoxJPanel comboBoxpane_sections = (ComboBoxJPanel) physical_moduleForm.getJPanel().getComponent(3);

				final JComboBox sections_combo = comboBoxpane_sections.getComboBox();

				submitaddphysicalmoduleBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
						System.out.println("all : ");
						physcat.readAllResources();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < physical_moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) physical_moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " physical");
						}

						System.out.println(sections_combo.getSelectedItem() + " //////");
						Pattern p = Pattern.compile("sid=\\d+");
						String section = null;
						Matcher m = p.matcher((CharSequence) sections_combo.getSelectedItem());
						if (m.find()) {
							section = m.group();
						}
						System.out.println("sid: " + section);

						physcat.addResource(inputs.get(0), Integer.parseInt(section.replace("sid=", "")), inputs.get(1),
								inputs.get(1));

						physical_tabledata.update(physcat.readAllResources());

					}
				});
			}
		});

		JScrollPane physical_scrollPane = new JScrollPane();

		JButton physical_btnEdit = new JButton("Edit");
		physical_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		physical_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				int rowIndex = physical_tabledata.getJdataTable().getSelectedRow();
				int colIndex = physical_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Resource!");
				} else {

					String Table_click = (physical_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");

					ArrayList<String> section_arraylist = new ArrayList<String>();
					SectionCatalogue seccat = new SectionCatalogue();
					ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
					for (int i = 0; i < section_hashmap.size(); i++) {
						section_arraylist.add(section_hashmap.get(i).toString());
					}

					Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

					ArrayList<Field> physical_moduleFields = new ArrayList<Field>();
					physical_moduleFields.add(new Field("text", "physical name", "", 20, "name"));
					physical_moduleFields.add(new Field("text", "model description", "", 20, "model desc"));

					physical_moduleFields.add(sections);
					final Form physical_moduleForm = new Form(physical_moduleFields, "Physical Module Form");
					final PanelBuilder physical_modulePanel = new PanelBuilder(physical_moduleForm);
					physical_modulePanel.makeForm();
					JFrame Edit_PhysicalModulePage = new JFrame("Edit Physical Module Form");
					Edit_PhysicalModulePage.getContentPane().add(physical_moduleForm.getJPanel(), BorderLayout.NORTH);

					JButton submiteditphysicalmoduleBtn = new JButton("Submit");
					JPanel buttonPanel = new JPanel();
					buttonPanel.add(submiteditphysicalmoduleBtn);
					Edit_PhysicalModulePage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
					Edit_PhysicalModulePage.pack();
					Edit_PhysicalModulePage.setVisible(true);

					submiteditphysicalmoduleBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
							System.out.println("all : ");
							physcat.readAllResources();
							ArrayList<String> inputs = new ArrayList<String>();
							for (int i = 0; i < physical_moduleForm.getJPanel().getComponentCount(); i++) {
								FieldPanel fpanel = (FieldPanel) physical_moduleForm.getJPanel().getComponent(i);
								inputs.add(fpanel.getValues().get(0));
							}
							for (int i = 0; i < inputs.size(); i++) {
								System.out.println(inputs.get(i) + " physical");
							}
							// physcat.addResource((inputs.get(0)));
							// // tu resource ham bayad insert she
							// allphysicals.clear();
							// allphysicals = physcat.readAllResources();
							// System.out.println(phyiscal_tableModel.getRowCount()
							// + " ---");
							// int rowcount = phyiscal_tableModel.getRowCount();
							// for (int j = rowcount - 1; j >= 0; j--) {
							// phyiscal_tableModel.removeRow(j);
							// }
							// System.out.println(phyiscal_tableModel.getRowCount()
							// + " ---");
							// for (int i = 0; i < allphysicals.size(); i++) {
							// Object[] objs = { allphysicals.get(i).get("rid"),
							// allphysicals.get(i).get("physname") };
							// phyiscal_tableModel.addRow(objs);
							// }
						}
					});
				}
			}
		});

		JButton physical_btnDelete = new JButton("Delete");
		physical_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		physical_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = physical_tabledata.getJdataTable().getSelectedRow();
				int colIndex = physical_tabledata.getJdataTable().getSelectedColumn();

				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Resource!");
				} else {

					String Table_click = (physical_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // the
					System.out.println(Table_click + " this was clicked");
					PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						physcat.deleteResource(Integer.parseInt(Table_click));
						physical_tabledata.update(physcat.readAllResources());
					}
				}
			}
		});

		search_physicalname = new JTextField();
		search_physicalname.setColumns(10);

		JButton physical_btnSearch = new JButton("Search");
		physical_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();

				if (search_physicalname.getText() != null && !search_physicalname.getText().trim().equals(""))
					searchVars.put("physname", "\'" + search_physicalname.getText() + "\'");
				if (search_physicalmodel.getText() != null && !search_physicalmodel.getText().trim().equals(""))
					searchVars.put("modeldesc", "\'" + search_physicalmodel.getText() + "\'");
				searchVars.put("physname", "\'" + search_physicalname.getText() + "\'");

				if (physcat.SearchResource(searchVars).isEmpty()) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification", "No Results Found");
				} else {
					physical_tabledata.update(physcat.SearchResource(searchVars));
				}

				


			}
		});

		JLabel lblPhysicalName = DefaultComponentFactory.getInstance().createLabel("Physical Name");

		JButton search_physicalbtnRefresh = new JButton("Refresh");
		search_physicalbtnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		search_physicalbtnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				allphysicals.clear();
				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				allphysicals = physcat.readAllResources();

				physical_tabledata.update(physcat.readAllResources());
			}
		});

		JLabel lblModel_1 = DefaultComponentFactory.getInstance().createLabel("Model");

		search_physicalmodel = new JTextField();
		search_physicalmodel.setColumns(10);

		GroupLayout gl_physicalPanel = new GroupLayout(physicalPanel);
		gl_physicalPanel
				.setHorizontalGroup(
						gl_physicalPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_physicalPanel.createSequentialGroup().addGap(30)
										.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 736,
												Short.MAX_VALUE)
										.addGap(30))
								.addGroup(gl_physicalPanel.createSequentialGroup()
										.addComponent(physical_btnEdit, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(physical_btnDelete, GroupLayout.PREFERRED_SIZE, 84,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
										.addComponent(btnAddPhysicalResource))
								.addGroup(gl_physicalPanel.createSequentialGroup()
										.addComponent(search_physicalbtnRefresh)
										.addPreferredGap(ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
										.addComponent(physical_btnSearch).addGap(146)
										.addGroup(gl_physicalPanel.createParallelGroup(Alignment.LEADING, false)
												.addGroup(Alignment.TRAILING, gl_physicalPanel.createSequentialGroup()
														.addComponent(search_physicalname, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblPhysicalName))
												.addGroup(Alignment.TRAILING, gl_physicalPanel.createSequentialGroup()
														.addComponent(search_physicalmodel, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblModel_1)))
										.addContainerGap()));
		gl_physicalPanel.setVerticalGroup(gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_physicalPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(search_physicalname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(physical_btnSearch).addComponent(lblPhysicalName)
								.addComponent(search_physicalbtnRefresh))
						.addGap(3)
						.addGroup(gl_physicalPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblModel_1)
								.addComponent(search_physicalmodel, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_physicalPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddPhysicalResource).addComponent(physical_btnEdit)
								.addComponent(physical_btnDelete))
						.addContainerGap()));

		physical_tabledata = new TableData(new PhysicalResourceCatalogue(), "physical");
		physical_scrollPane.setViewportView(physical_tabledata.getJdataTable());
		physicalPanel.setLayout(gl_physicalPanel);

		JPanel allPanel = new JPanel();
		resourcesTab.addTab("All", null, allPanel, null);

		// get all res list
		final ResourceCatalogue rcat = new ResourceCatalogue();

		JScrollPane allres_scrollPane = new JScrollPane();

		JButton search_allbtnRefresh = new JButton("Refresh");
		search_allbtnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		search_allbtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allresource_tabledata.update(rcat.readAllResources());
			}
		});
		GroupLayout gl_allPanel = new GroupLayout(allPanel);
		gl_allPanel.setHorizontalGroup(gl_allPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_allPanel.createSequentialGroup().addGap(30)
						.addComponent(allres_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE).addGap(30))
				.addGroup(Alignment.LEADING, gl_allPanel.createSequentialGroup().addComponent(search_allbtnRefresh)
						.addContainerGap(705, Short.MAX_VALUE)));
		gl_allPanel.setVerticalGroup(gl_allPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_allPanel.createSequentialGroup().addContainerGap().addComponent(search_allbtnRefresh)
						.addGap(30).addComponent(allres_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addGap(30)));

		allresource_tabledata = new TableData(new ResourceCatalogue(), "all");
		allres_scrollPane.setViewportView(allresource_tabledata.getJdataTable());
		allPanel.setLayout(gl_allPanel);

		tabbedPane.addTab("Project Management", null, projectPanel, null);

		JButton addprojectBtn = new JButton("Add Project");
		addprojectBtn.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		addprojectBtn.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		addprojectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final ArrayList<String> employees = new ArrayList<String>();
				final ArrayList<String> financials = new ArrayList<String>();
				final ArrayList<String> physicals = new ArrayList<String>();
				final ArrayList<String> information = new ArrayList<String>();

				EmployeeCatalogue empcat = new EmployeeCatalogue();
				ArrayList<HashMap<String, String>> employe_readall = empcat.readAllEmployees();
				for (int i = 0; i < employe_readall.size(); i++) {
					employees.add(employe_readall.get(i).toString());
				}
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				ArrayList<HashMap<String, String>> financial_readall = financat.readAllResources();
				for (int i = 0; i < financial_readall.size(); i++) {
					financials.add(financial_readall.get(i).toString());
				}

				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				ArrayList<HashMap<String, String>> physical_readall = physcat.readAllResources();
				for (int i = 0; i < physical_readall.size(); i++) {
					physicals.add(physical_readall.get(i).toString());
				}

				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				ArrayList<HashMap<String, String>> information_readall = infocat.readAllResources();
				for (int i = 0; i < information_readall.size(); i++) {
					information.add(information_readall.get(i).toString());
				}

				ArrayList<Field> projectFields = new ArrayList<Field>();

				projectFields.add(new Field("text", "project name", "", 20, "name"));
				projectFields.add(new Field("text", "technology", "", 20, "tech"));
				projectFields.add(new Field("text", "size", "", 20, "size"));

				projectFields.add(new Field("comboBox", "project manager", employees, 20, "project manager"));
				ArrayList<String> iscomplete = new ArrayList<String>();
				iscomplete.add("is complete");
				projectFields.add(new Field("singlecheckbox", "is complete", iscomplete, 10, "items"));

				Field maintainers = new Field("checkBox", "employees", employees, 20, "res");
				Field financial_check = new Field("checkBox", "fianance", financials, 20, "fianance");
				Field physical_check = new Field("checkBox", "physical", physicals, 20, "physical");
				Field information_check = new Field("checkBox", "information", information, 20, "information");

				projectFields.add(financial_check);
				projectFields.add(physical_check);
				projectFields.add(information_check);
				projectFields.add(maintainers);

				final Form projectForm = new Form(projectFields, "Project Form");
				final PanelBuilder project_addPanel = new PanelBuilder(projectForm);
				project_addPanel.makeForm();
				JFrame Add_ProjectPage = new JFrame("Add Project Form");
				Add_ProjectPage.getContentPane().add(projectForm.getJPanel(), BorderLayout.NORTH);
				JScrollPane scroll = new JScrollPane(projectForm.getJPanel());
				Add_ProjectPage.getContentPane().add(scroll);

				ComboBoxJPanel comboBoxpane = (ComboBoxJPanel) projectForm.getJPanel().getComponent(3);
				final JComboBox employees_comboBox = comboBoxpane.getComboBox();

				final SingleCheckBoxJPanel checkBoxpane = (SingleCheckBoxJPanel) projectForm.getJPanel()
						.getComponent(4);
				JButton submitaddprojectBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitaddprojectBtn);
				Add_ProjectPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Add_ProjectPage.pack();
				Add_ProjectPage.setVisible(true);

				final CheckBoxJPanel checkBoxpane_finance = (CheckBoxJPanel) projectForm.getJPanel().getComponent(5);
				final CheckBoxJPanel checkBoxpane_physical = (CheckBoxJPanel) projectForm.getJPanel().getComponent(6);
				final CheckBoxJPanel checkBoxpane_information = (CheckBoxJPanel) projectForm.getJPanel()
						.getComponent(7);
				final CheckBoxJPanel checkBoxpane_employee = (CheckBoxJPanel) projectForm.getJPanel().getComponent(8);

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
						for (int i = 0; i < projectForm.getJPanel().getComponentCount() - 1; i++) {
							FieldPanel fpanel = (FieldPanel) projectForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " project");
						}

						employees_comboBox.getSelectedItem();
						String empid = "";
						Pattern emp_p = Pattern.compile("empid=\\d+");
						Matcher emp_m = emp_p.matcher((CharSequence) employees_comboBox.getSelectedItem());
						if (emp_m.find()) {
							empid = emp_m.group();
						}
						System.out.println("empid: " + empid);
						int employeeID = Integer.parseInt(empid.replace("empid=", ""));

						EmployeeCatalogue empcat = new EmployeeCatalogue();
						Employee proj_manager = empcat.getEmployee(employeeID);
						System.out.println(proj_manager.getName());

						final ArrayList<String> vales_phys = checkBoxpane.getCheckedValues();
						boolean confirmed = false;
						if (vales_phys.size() == 1)
							confirmed = true;

						long projid = projcat.addProject(inputs.get(0).toString(), proj_manager, inputs.get(2),
								inputs.get(1), confirmed);

						project_tabledata.update(projcat.getProjects());

						allprojects = projcat.getProjects();
						project_tabledata.update(projcat.getProjects());
						System.out.println(vales_phys.toString());

						System.out.println("----------");
						ProjectEmployeeCatalogue projempcat = new ProjectEmployeeCatalogue();
						ProjectResourceUtilizationCatalogue projrescat = new ProjectResourceUtilizationCatalogue();

						final ArrayList<String> finanvales = checkBoxpane_finance.getCheckedValues();
						System.out.println(finanvales);
						final ArrayList<String> physicalvales = checkBoxpane_physical.getCheckedValues();
						System.out.println(physicalvales);
						final ArrayList<String> informationvales = checkBoxpane_information.getCheckedValues();
						System.out.println(informationvales);
						final ArrayList<String> employeevales = checkBoxpane_employee.getCheckedValues();
						System.out.println(employeevales);
						Pattern emp = Pattern.compile("empid=\\d+");
						for (int i = 0; i < employeevales.size(); i++) {
							String empids = null;
							Matcher m_emp = emp.matcher(employeevales.get(i).toString());
							if (m_emp.find()) {
								empids = m_emp.group();
							}
							System.out.println("empids: " + empids);
							projempcat.addProjectEmployee((int) projid, Integer.parseInt(empids.replace("empid=", "")),
									"1111-11-1", "1111-11-1");

						}

						Pattern res = Pattern.compile("rid=\\d+");
						for (int i = 0; i < finanvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(finanvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("finan rid: " + respids);
							projrescat.addProjectResourceUtilization(Integer.parseInt(respids.replace("rid=", "")), 1,
									(int) projid, "1111-11-1", "1111-11-1");

						}

						for (int i = 0; i < physicalvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(physicalvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("phys rid: " + respids);
							projrescat.addProjectResourceUtilization(Integer.parseInt(respids.replace("rid=", "")), 1,
									(int) projid, "1111-11-1", "1111-11-1");

						}

						for (int i = 0; i < informationvales.size(); i++) {
							String respids = null;
							Matcher m_res = res.matcher(informationvales.get(i).toString());
							if (m_res.find()) {
								respids = m_res.group();
							}
							System.out.println("info rid: " + respids);
							projrescat.addProjectResourceUtilization(Integer.parseInt(respids.replace("rid=", "")), 1,
									(int) projid, "1111-11-1", "1111-11-1");
						}

					}
				});
			}
		});

		JButton project_btnSearch = new JButton("Search");
		project_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectCatalogue projcat = new ProjectCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();

				if (search_projectname.getText() != null && !search_projectname.getText().trim().equals(""))
					searchVars.put("projname", "\'" + search_projectname.getText() + "\'");
				if (search_projectsize.getText() != null && !search_projectsize.getText().trim().equals(""))
					searchVars.put("size", "\'" + search_projectsize.getText() + "\'");
				if (search_projecttech.getText() != null && !search_projecttech.getText().trim().equals(""))
					searchVars.put("tech", "\'" + search_projecttech.getText() + "\'");
				project_tabledata.update(projcat.Search(searchVars));


				if (projcat.Search(searchVars).isEmpty()) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification", "No Results Found");
				} else {
					project_tabledata.update(projcat.Search(searchVars));
				}

			}
		});

		JScrollPane project_scrollPane = new JScrollPane();

		JButton viewsubsys_Btn = new JButton("View SubSystem");
		viewsubsys_Btn.setIcon(new ImageIcon(
				new ImageIcon("images/view.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		viewsubsys_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = project_tabledata.getJdataTable().getSelectedRow();
				int colIndex = project_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Project!");
				} else {

					String Table_click = (project_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // return
					System.out.println(Table_click);
					selected_project_forsubsystem = Integer.parseInt(Table_click.trim());
					System.out.println("-----");
					System.out.println("Change JPanel");
					SubSystemCatalogue subsyscat = new SubSystemCatalogue();
					subsystem_tabledata.update(subsyscat.getSubSystemsByProject(selected_project_forsubsystem));

					int selected_index = tabbedPane.getSelectedIndex();
					tabbedPane.remove(selected_index);
					tabbedPane.insertTab("Subsystem", null, subsystemPanel, null, selected_index);
					tabbedPane.setSelectedComponent(subsystemPanel);
				}
			}
		});

		search_projectname = new JTextField();
		search_projectname.setColumns(10);

		JLabel lblProjectName = DefaultComponentFactory.getInstance().createLabel("Project Name");

		JButton btnViewResources = new JButton("View Resources");
		btnViewResources.setIcon(new ImageIcon(
				new ImageIcon("images/view.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnViewResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = project_tabledata.getJdataTable().getSelectedRow();
				int colIndex = project_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a module!");
				} else {

					String Table_click = (project_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // return

					selected_project_forsubsystem = Integer.parseInt(Table_click.trim());
					System.out.println("-----");
					System.out.println("Change JPanel");
					ProjectResourceUtilizationCatalogue presutilcat = new ProjectResourceUtilizationCatalogue();
					ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
					ArrayList<ProjectResourceUtilization> projresutil = presutilcat
							.getProjectResourceUtilizationbyProject(selected_project_forsubsystem);
					for (int i = 0; i < projresutil.size(); i++) {
						data.add((projresutil.get(i).toHashMap()));
					}
					resourceutil_tabledata.update(data);
					int selected_index = tabbedPane.getSelectedIndex();
					tabbedPane.remove(selected_index);
					tabbedPane.insertTab("Resource Utilization", null, resourceutilpanel, null, selected_index);
					tabbedPane.setSelectedComponent(resourceutilpanel);
				}
			}
		});

		JButton btnEditProject = new JButton("Edit");
		btnEditProject.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnEditProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// EDITPROJECT
				ArrayList<Field> projectFields = new ArrayList<Field>();
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				ArrayList<HashMap<String, String>> employees_fromcatalouge = empcat.readAllEmployees();
				ArrayList<String> employees = new ArrayList<String>();

				projectFields.add(new Field("text", "project name", "", 20, "name"));
				projectFields.add(new Field("text", "technology", "", 20, "tech"));
				projectFields.add(new Field("text", "size", "", 20, "size"));

				for (int i = 0; i < employees_fromcatalouge.size(); i++) {
					employees.add("id:" + employees_fromcatalouge.get(i).get("empid").toString() + " "
							+ employees_fromcatalouge.get(i).get("empname").toString());
				}
				System.out.println(employees + " 00");
				projectFields.add(new Field("comboBox", "project manager", employees, 20, "project manager"));
				ArrayList<String> iscomplete = new ArrayList<String>();
				iscomplete.add("is complete");
				projectFields.add(new Field("singlecheckbox", "is complete", iscomplete, 10, "items"));

				final Form edit_projectForm = new Form(projectFields, "Project Form");
				final PanelBuilder project_addPanel = new PanelBuilder(edit_projectForm);
				project_addPanel.makeForm();
				JFrame Edit_ProjectPage = new JFrame("Edit Project Form");

				Edit_ProjectPage.getContentPane().add(edit_projectForm.getJPanel(), BorderLayout.NORTH);

				ComboBoxJPanel comboBoxpane = (ComboBoxJPanel) edit_projectForm.getJPanel().getComponent(3);
				final JComboBox employees_comboBox = comboBoxpane.getComboBox();

				final SingleCheckBoxJPanel checkBoxpane = (SingleCheckBoxJPanel) edit_projectForm.getJPanel()
						.getComponent(4);
				JButton submiteditprojectBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submiteditprojectBtn);
				Edit_ProjectPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				Edit_ProjectPage.pack();
				Edit_ProjectPage.setVisible(true);

				employees_comboBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(employees_comboBox.getSelectedItem() + " ino select kardi project");
					}
				});

				submiteditprojectBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("-----");
						int rowIndex = project_tabledata.getJdataTable().getSelectedRow();
						int colIndex = project_tabledata.getJdataTable().getSelectedColumn();
						if (rowIndex == -1) {
							NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
									"Please Select a module!");
						} else {

							String Table_click = (project_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
									.toString()); // return

							selected_project_forsubsystem = Integer.parseInt(Table_click.trim());
							System.out.println("-----");

							ProjectCatalogue projcat = new ProjectCatalogue();
							Project project = projcat.getProject(selected_project_forsubsystem);
							System.out.println("all : ");
							projcat.getProjects();
							ArrayList<String> inputs = new ArrayList<String>();
							for (int i = 0; i < edit_projectForm.getJPanel().getComponentCount() - 1; i++) {
								FieldPanel fpanel = (FieldPanel) edit_projectForm.getJPanel().getComponent(i);
								inputs.add(fpanel.getValues().get(0));
							}
							for (int i = 0; i < inputs.size(); i++) {
								System.out.println(inputs.get(i) + " project");
							}
							int employeeID = Integer.parseInt((inputs.get(3).substring(0, 4).replace("id:", "")));
							EmployeeCatalogue empcat = new EmployeeCatalogue();
							Employee proj_manager = empcat.getEmployee(employeeID);
							System.out.println(proj_manager.getName());
							final ArrayList<String> vales_phys = checkBoxpane.getCheckedValues();
							boolean confirmed = false;
							if (vales_phys.size() == 1)
								confirmed = true;

							project.editProject(inputs.get(0).toString(), "", inputs.get(1), proj_manager,
									inputs.get(2), confirmed);

							allprojects = projcat.getProjects();
							project_tabledata.update(projcat.getProjects());
							System.out.println(vales_phys.toString() + " is this it");
						}
					}
				});

			}
		});

		JButton btnDeleteProject = new JButton("Delete");
		btnDeleteProject.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnDeleteProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = project_tabledata.getJdataTable().getSelectedRow();
				int colIndex = project_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a Project!");
				} else {

					String Table_click = (project_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString()); // return
					ProjectCatalogue projcat = new ProjectCatalogue();
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Delete this item?");
					if (myDialog.getAnswer()) {
						projcat.deleteProject(Integer.parseInt(Table_click));
						project_tabledata.update(projcat.getProjects());
					}
				}

			}
		});

		search_projectmanager = new JTextField();
		search_projectmanager.setColumns(10);

		JLabel lblProjectManager = DefaultComponentFactory.getInstance().createLabel("Project Manager");

		JLabel lblTechnology = DefaultComponentFactory.getInstance().createLabel("Technology");

		JLabel lblSize = DefaultComponentFactory.getInstance().createLabel("Size");

		search_projecttech = new JTextField();
		search_projecttech.setColumns(10);

		search_projectsize = new JTextField();
		search_projectsize.setColumns(10);

		JButton project_btnRefresh = new JButton("Refresh");
		project_btnRefresh.setIcon(new ImageIcon(
				new ImageIcon("images/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		project_btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectCatalogue projcat = new ProjectCatalogue();
				project_tabledata.update(projcat.getProjects());

			}
		});
		GroupLayout gl_projectPanel = new GroupLayout(projectPanel);
		gl_projectPanel
				.setHorizontalGroup(gl_projectPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_projectPanel.createSequentialGroup().addGap(40)
								.addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
								.addGap(40))
						.addGroup(
								gl_projectPanel.createSequentialGroup().addContainerGap().addComponent(btnEditProject)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDeleteProject)
										.addPreferredGap(ComponentPlacement.RELATED, 450,
												Short.MAX_VALUE)
										.addComponent(btnViewResources).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(viewsubsys_Btn).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(addprojectBtn))
						.addGroup(gl_projectPanel.createSequentialGroup().addContainerGap()
								.addComponent(project_btnRefresh)
								.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
								.addComponent(project_btnSearch).addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_projectPanel.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_projectPanel.createSequentialGroup()
												.addComponent(search_projecttech, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblTechnology))
										.addGroup(gl_projectPanel.createSequentialGroup()
												.addComponent(search_projectsize,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lblSize)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_projectPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_projectPanel.createSequentialGroup()
												.addComponent(search_projectmanager, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lblProjectManager))
										.addGroup(gl_projectPanel.createSequentialGroup()
												.addComponent(search_projectname, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblProjectName)))
								.addContainerGap()));
		gl_projectPanel.setVerticalGroup(gl_projectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_projectPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_projectPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(search_projectname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProjectName).addComponent(project_btnSearch)
								.addComponent(lblTechnology)
								.addComponent(search_projecttech, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(project_btnRefresh))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_projectPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(search_projectmanager, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProjectManager).addComponent(lblSize).addComponent(search_projectsize,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(40).addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addGap(40)
						.addGroup(gl_projectPanel.createParallelGroup(Alignment.BASELINE).addComponent(addprojectBtn)
								.addComponent(viewsubsys_Btn).addComponent(btnViewResources)
								.addComponent(btnEditProject).addComponent(btnDeleteProject))));
		project_tabledata = new TableData(new ProjectCatalogue());

		project_tabledata.getJdataTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		project_scrollPane.setViewportView(project_tabledata.getJdataTable());
		projectPanel.setLayout(gl_projectPanel);

		JPanel reportPanel = new JPanel();
		reportPanel.setBackground(tab_color);
		// if
		// (AuthenticatedEmployee.getInstance().getEmployee().getAccessRight().getName().equals("super"))
		// {
		tabbedPane.addTab("Report", null, reportPanel, null);
		SpringLayout sl_reportPanel = new SpringLayout();
		reportPanel.setLayout(sl_reportPanel);

		JTabbedPane reportsTab = new JTabbedPane(JTabbedPane.TOP);
		sl_reportPanel.putConstraint(SpringLayout.NORTH, reportsTab, 0, SpringLayout.NORTH, reportPanel);
		sl_reportPanel.putConstraint(SpringLayout.WEST, reportsTab, 10, SpringLayout.WEST, reportPanel);
		sl_reportPanel.putConstraint(SpringLayout.SOUTH, reportsTab, 495, SpringLayout.NORTH, reportPanel);
		sl_reportPanel.putConstraint(SpringLayout.EAST, reportsTab, 827, SpringLayout.WEST, reportPanel);
		reportPanel.add(reportsTab);

		JPanel circulationPanel = new JPanel();
		reportsTab.addTab("Circulation Report", null, circulationPanel, null);

		JScrollPane circulation_scrollPane = new JScrollPane();

		JButton circulation_btnGetReport = new JButton("Get Report");
		circulation_btnGetReport.setIcon(new ImageIcon(
				new ImageIcon("images/report.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		circulation_btnGetReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> resource_types = new ArrayList<String>();
				final ArrayList<String> resources = new ArrayList<String>();
				resource_types.add("Information");
				resource_types.add("Financial");
				resource_types.add("Physical");
				resource_types.add("Employee");
				resource_types.add("Module");
				ArrayList<Field> circulationReport_Fields = new ArrayList<Field>();
				Field res_type = new Field("comboBox", "resource types", resource_types, 30, "items");
				Field res = new Field("comboBox", "resources", resources, 30, "items");

				circulationReport_Fields.add(res_type);
				circulationReport_Fields.add(res);

				final Form circulationreport_Form = new Form(circulationReport_Fields, "Circulation Report Form");
				final PanelBuilder circulation_Panel = new PanelBuilder(circulationreport_Form);
				circulation_Panel.makeForm();

				JFrame getReport_CirculationPage = new JFrame("Get Report Circulation Resource Form");

				getReport_CirculationPage.getContentPane().add(circulationreport_Form.getJPanel(), BorderLayout.NORTH);

				JButton submitgetReportBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitgetReportBtn);
				getReport_CirculationPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				getReport_CirculationPage.pack();
				getReport_CirculationPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_restype = (ComboBoxJPanel) circulationreport_Form.getJPanel()
						.getComponent(0);
				ComboBoxJPanel comboBoxpane_res = (ComboBoxJPanel) circulationreport_Form.getJPanel().getComponent(1);

				final JComboBox resource_type = comboBoxpanel_restype.getComboBox();
				final JComboBox resourceCombo = comboBoxpane_res.getComboBox();
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
						if (resource_type.getSelectedItem().toString().equals("Module")) {
							resourceCombo.removeAllItems();
							ModuleCatalogue modcat = new ModuleCatalogue();
							ArrayList<HashMap<String, String>> module_resource = modcat.readAllResources();
							for (int i = 0; i < module_resource.size(); i++) {
								resourceCombo.addItem(module_resource.get(i).toString());
							}
						}
					}
				});
				submitgetReportBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(resourceCombo.getSelectedItem() + " this is resource combo");
						System.out.println(resource_type.getSelectedItem() + " this is resource type");

						if (resource_type.getSelectedItem() == "Employee") {
							String empid = "";
							Pattern emp_p = Pattern.compile("empid=\\d+");
							Matcher emp_m = emp_p.matcher((CharSequence) resourceCombo.getSelectedItem());
							if (emp_m.find()) {
								empid = emp_m.group();
							}
							System.out.println("empid: " + empid);
							EmployeeCatalogue empcat = new EmployeeCatalogue();
							ProjectEmployeeCatalogue projempcat = new ProjectEmployeeCatalogue();
							projempcat
									.getCirculationReport(
											empcat.getEmployee(Integer.parseInt(empid.replace("empid=", ""))))
									.getResults();
							circulation_tabledata.update(projempcat
									.getCirculationReport(
											empcat.getEmployee(Integer.parseInt(empid.replace("empid=", ""))))
									.getResults());
							System.out.println(".....mmmm");
							System.out.println(projempcat
									.getCirculationReport(
											empcat.getEmployee(Integer.parseInt(empid.replace("empid=", ""))))
									.getReport());
						} else {
							String rid = "";
							Pattern p = Pattern.compile("rid=\\d+");
							Matcher m = p.matcher((CharSequence) resourceCombo.getSelectedItem());
							if (m.find()) {
								rid = m.group();
							}
							System.out.println("rid: " + rid);

							ProjectResourceUtilizationCatalogue prucat = new ProjectResourceUtilizationCatalogue();
							PhysicalResourceCatalogue physResCat = new PhysicalResourceCatalogue();
							System.out.println("This is the Project Resource Utilization Report:");
							prucat.getCirculationReport(
									physResCat.getResource(Integer.parseInt(rid.replace("rid=", "")))).printRep();

							circulation_tabledata.update(prucat
									.getCirculationReport(
											physResCat.getResource(Integer.parseInt(rid.replace("rid=", ""))))
									.getResults());
						}
						// %%%

					}
				});
			}
		});
		GroupLayout gl_circulationPanel = new GroupLayout(circulationPanel);
		gl_circulationPanel
				.setHorizontalGroup(
						gl_circulationPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_circulationPanel.createSequentialGroup().addGap(20)
										.addComponent(circulation_scrollPane, GroupLayout.DEFAULT_SIZE, 756,
												Short.MAX_VALUE)
										.addGap(20))
								.addGroup(Alignment.TRAILING, gl_circulationPanel.createSequentialGroup()
										.addContainerGap(679, Short.MAX_VALUE).addComponent(circulation_btnGetReport)));
		gl_circulationPanel.setVerticalGroup(gl_circulationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_circulationPanel.createSequentialGroup().addComponent(circulation_btnGetReport).addGap(20)
						.addComponent(circulation_scrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
						.addGap(20)));

		circulation_tabledata = new TableData(new ArrayList<HashMap<String, String>>(), "cycle report");
		circulation_scrollPane.setViewportView(circulation_tabledata.getJdataTable());
		circulationPanel.setLayout(gl_circulationPanel);

		JPanel resourcereqPanel = new JPanel();
		reportsTab.addTab("Resource Requirement Report", null, resourcereqPanel, null);

		JScrollPane resreq_scrollPane = new JScrollPane();

		JButton resreq_btnGetReport = new JButton("Get Report");
		resreq_btnGetReport.setIcon(new ImageIcon(
				new ImageIcon("images/report.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		resreq_btnGetReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<String> project_arraylist = new ArrayList<String>();

				ProjectCatalogue projcat = new ProjectCatalogue();
				ArrayList<HashMap<String, String>> project_hashmap = projcat.getProjects();
				for (int i = 0; i < project_hashmap.size(); i++) {
					project_arraylist.add(project_hashmap.get(i).toString());
				}

				ArrayList<Field> getreport_resreqFields = new ArrayList<Field>();
				Field projects = new Field("comboBox", "projects", project_arraylist, 20, "items");

				getreport_resreqFields.add(projects);

				final Form getreport_resreqForm = new Form(getreport_resreqFields, "Report Resource Requirement Form");
				final PanelBuilder report_resreq_panel = new PanelBuilder(getreport_resreqForm);
				report_resreq_panel.makeForm();

				JFrame getReport_ResReqPage = new JFrame("Get Report Resource Requirement Form");
				getReport_ResReqPage.getContentPane().add(getreport_resreqForm.getJPanel(), BorderLayout.NORTH);

				JButton submitresreqreportBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitresreqreportBtn);
				getReport_ResReqPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				getReport_ResReqPage.pack();
				getReport_ResReqPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_project = (ComboBoxJPanel) getreport_resreqForm.getJPanel()
						.getComponent(0);
				final JComboBox projectCombo = comboBoxpanel_project.getComboBox();

				submitresreqreportBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < getreport_resreqForm.getJPanel().getComponentCount(); i++) {
							// System.out.println(fpanel.selected_Choice);
						}
						System.out.println(projectCombo.getSelectedItem() + " ino select");

						ResourceRequirementCatalogue resReqCat = new ResourceRequirementCatalogue();
						ProjectCatalogue projCat = new ProjectCatalogue();
						Pattern p = Pattern.compile("projid=\\d+");
						Matcher m = p.matcher(projectCombo.getSelectedItem().toString());
						if (m.find()) {
							System.out.println(m.group().replace("projid=", ""));
							int projid = Integer.parseInt(m.group().replace("projid=", ""));
							Project proj = projCat.getProject(projid);
							resReqCat.getReport(proj).getResults();
							resreq_tabledata.update(resReqCat.getReport(proj).getResults());

						}

					}
				});
			}
		});
		GroupLayout gl_resourcereqPanel = new GroupLayout(resourcereqPanel);
		gl_resourcereqPanel
				.setHorizontalGroup(gl_resourcereqPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_resourcereqPanel.createSequentialGroup().addGap(20)
								.addGroup(gl_resourcereqPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_resourcereqPanel.createSequentialGroup()
												.addComponent(resreq_scrollPane, GroupLayout.DEFAULT_SIZE, 644,
														Short.MAX_VALUE)
												.addGap(20))
										.addComponent(resreq_btnGetReport, Alignment.TRAILING))));
		gl_resourcereqPanel.setVerticalGroup(gl_resourcereqPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resourcereqPanel.createSequentialGroup().addComponent(resreq_btnGetReport).addGap(20)
						.addComponent(resreq_scrollPane, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE).addGap(20)));

		resreq_tabledata = new TableData(new ArrayList<HashMap<String, String>>(), "req report");
		resreq_scrollPane.setViewportView(resreq_tabledata.getJdataTable());
		resourcereqPanel.setLayout(gl_resourcereqPanel);

		JPanel resourceavailPanel = new JPanel();
		reportsTab.addTab("Resource Available Report", null, resourceavailPanel, null);

		JScrollPane resavail_scrollpane = new JScrollPane();

		JButton resavail_btnGetReport = new JButton("Get Report");
		resavail_btnGetReport.setIcon(new ImageIcon(
				new ImageIcon("images/report.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		resavail_btnGetReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> resource_types = new ArrayList<String>();
				resource_types.add("Information");
				resource_types.add("Financial");
				resource_types.add("Physical");
				resource_types.add("Employee");
				resource_types.add("Module");

				ArrayList<Field> getreport_resavailFields = new ArrayList<Field>();
				Field req_res_type = new Field("comboBox", "resource types", resource_types, 20, "items");

				getreport_resavailFields.add(req_res_type);

				final Form getreport_resavailForm = new Form(getreport_resavailFields,
						"Report Resource Available Form");
				final PanelBuilder report_resavail_panel = new PanelBuilder(getreport_resavailForm);
				report_resavail_panel.makeForm();

				JFrame getReport_ResAvailPage = new JFrame("Get Report Resource Available Form");
				getReport_ResAvailPage.getContentPane().add(getreport_resavailForm.getJPanel(), BorderLayout.NORTH);

				JButton submitresavailreportBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitresavailreportBtn);
				getReport_ResAvailPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				getReport_ResAvailPage.pack();
				getReport_ResAvailPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_restype = (ComboBoxJPanel) getreport_resavailForm.getJPanel()
						.getComponent(0);
				final JComboBox resourceRepCombo = comboBoxpanel_restype.getComboBox();
				submitresavailreportBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(resourceRepCombo.getSelectedItem().toString() + " ine res type");
						// FinancialResourceCatalogue finanResCat= new
						// FinancialResourceCatalogue();
						// finanResCat.getReport().printRep();
						//
						if (resourceRepCombo.getSelectedItem().toString().equals("Financial")) {
							FinancialResourceCatalogue finanResCat = new FinancialResourceCatalogue();
							// System.out.println(finanResCat.getReport().getResults());
							resavail_tabledata.update(finanResCat.getReport().getResults(),
									new String[] { "sid", "count", "finanname" });
						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Physical")) {
							// HERE
							PhysicalResourceCatalogue physResCat = new PhysicalResourceCatalogue();
							// System.out.println(physResCat.getReport().getResults());
							resavail_tabledata.update(physResCat.getReport().getResults(),
									new String[] { "sid", "count", "physname" });

						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Information")) {
							InformationResourceCatalogue infoResCat = new InformationResourceCatalogue();
							// System.out.println(infoResCat.getReport().getResults());
							resavail_tabledata.update(infoResCat.getReport().getResults(),
									new String[] { "sid", "count", "irname" });

						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Employee")) {
							EmployeeCatalogue empResCat = new EmployeeCatalogue();
							System.out.println(empResCat.getReport().getResults());
							resavail_tabledata.update(empResCat.getReport().getResults(),
									new String[] { "sid", "count", "empname" });

						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Module")) {
							ModuleCatalogue modResCat = new ModuleCatalogue();
							// System.out.println(modResCat.getReport().getResults());
							resavail_tabledata.update(modResCat.getReport().getResults(),
									new String[] { "sid", "count", "modname" });

						}

					}
				});
			}
		});
		GroupLayout gl_resourceavailPanel = new GroupLayout(resourceavailPanel);
		gl_resourceavailPanel
				.setHorizontalGroup(
						gl_resourceavailPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_resourceavailPanel.createSequentialGroup().addGap(20)
										.addComponent(resavail_scrollpane, GroupLayout.DEFAULT_SIZE, 644,
												Short.MAX_VALUE)
										.addGap(20))
								.addGroup(Alignment.TRAILING, gl_resourceavailPanel.createSequentialGroup()
										.addContainerGap(679, Short.MAX_VALUE).addComponent(resavail_btnGetReport)));
		gl_resourceavailPanel.setVerticalGroup(gl_resourceavailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resourceavailPanel.createSequentialGroup().addComponent(resavail_btnGetReport).addGap(20)
						.addComponent(resavail_scrollpane, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE).addGap(20)));

		resavail_tabledata = new TableData(new ArrayList<HashMap<String, String>>(), "resavail report");
		resavail_scrollpane.setViewportView(resavail_tabledata.getJdataTable());
		resourceavailPanel.setLayout(gl_resourceavailPanel);
		// }

		JPanel RegisteredUserspanel = new JPanel();
		RegisteredUserspanel.setBackground(tab_color);
		// if(AuthenticatedEmployee.getInstance().getEmployee().getAccessRight().getName().equals("super")
		// ){
		tabbedPane.addTab("Registered Users", null, RegisteredUserspanel, null);
		// }
		JScrollPane registered_scrollPane = new JScrollPane();

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setIcon(new ImageIcon(
				new ImageIcon("images/check.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = registered_tabledata.getJdataTable().getSelectedRow();
				int colIndex = registered_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a User!");
				} else {

					String Table_click = (registered_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString());
					System.out.println(Table_click);
					EmployeeCatalogue regempcat = new EmployeeCatalogue();
					regempcat.makeDecision(Integer.parseInt(Table_click), true);
					registered_tabledata.update(regempcat.getRegistrations());
				}
			}
		});

		JButton btnDeny = new JButton("Deny");
		btnDeny.setIcon(new ImageIcon(
				new ImageIcon("images/remove.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnDeny.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("-----");
				int rowIndex = registered_tabledata.getJdataTable().getSelectedRow();
				int colIndex = registered_tabledata.getJdataTable().getSelectedColumn();
				if (rowIndex == -1) {
					NotificationPage notif = new NotificationPage(new JFrame(), "Notification",
							"Please Select a User!");
				} else {

					String Table_click = (registered_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
							.toString());
					System.out.println(Table_click);
					DeleteDialog myDialog = new DeleteDialog(new JFrame(), true,
							"Are you sure you want to Deny this user?");
					if (myDialog.getAnswer()) {
						EmployeeCatalogue regempcat = new EmployeeCatalogue();
						regempcat.makeDecision(Integer.parseInt(Table_click), false);
						registered_tabledata.update(regempcat.getRegistrations());
					}
				}
			}
		});

		JLabel lblEmployeeName = DefaultComponentFactory.getInstance().createLabel("Employee Name");

		search_regemployeename = new JTextField();
		search_regemployeename.setColumns(10);

		JButton employee_btnSearch = new JButton("Search");
		employee_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_RegisteredUserspanel = new GroupLayout(RegisteredUserspanel);
		gl_RegisteredUserspanel
				.setHorizontalGroup(
						gl_RegisteredUserspanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_RegisteredUserspanel.createSequentialGroup().addGap(40)
										.addComponent(registered_scrollPane, GroupLayout.DEFAULT_SIZE, 757,
												Short.MAX_VALUE)
										.addGap(40))
								.addGroup(gl_RegisteredUserspanel.createSequentialGroup()
										.addContainerGap(722, Short.MAX_VALUE).addComponent(btnDeny)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnConfirm)
										.addContainerGap())
								.addGroup(gl_RegisteredUserspanel.createSequentialGroup()
										.addContainerGap(500, Short.MAX_VALUE).addComponent(employee_btnSearch)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(search_regemployeename, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblEmployeeName)
										.addContainerGap()));
		gl_RegisteredUserspanel.setVerticalGroup(gl_RegisteredUserspanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RegisteredUserspanel.createSequentialGroup().addGap(14)
						.addGroup(gl_RegisteredUserspanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmployeeName)
								.addComponent(search_regemployeename, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(employee_btnSearch))
						.addGap(40).addComponent(registered_scrollPane, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
						.addGap(40).addGroup(gl_RegisteredUserspanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnConfirm).addComponent(btnDeny))
						.addContainerGap()));

		registered_tabledata = new TableData(new EmployeeCatalogue(), "registered");

		registered_scrollPane.setViewportView(registered_tabledata.getJdataTable());
		RegisteredUserspanel.setLayout(gl_RegisteredUserspanel);
		
		JMenuBar menuBar = new JMenuBar();
		ImageIcon logout_icon = new ImageIcon(new ImageIcon("images/exit.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon about_icon = new ImageIcon(new ImageIcon("images/info.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		JMenuItem logout_eMenuItem = new JMenuItem("Logout", logout_icon);
		JMenuItem about_eMenuItem = new JMenuItem("About", about_icon);

		logout_eMenuItem.setMnemonic(KeyEvent.VK_E);
		logout_eMenuItem.setToolTipText("Exit application");
		about_eMenuItem.setToolTipText("About");
		logout_eMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	System.out.println("logged out");
				userpageFrame.dispose();
				AuthenticatedEmployee.getInstance().logoutEmployee();
				NLoginPage loginWindow = new NLoginPage();
				loginWindow.getloginpageFrame().setVisible(true);
		    }
		});
		about_eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AboutPage about = new AboutPage();
				JFrame About = about.getaboutpageFrame();
				About.setVisible(true);
			}
		});
		file.add(logout_eMenuItem);
		file.add(about_eMenuItem);
		menuBar.add(file);


		userpageFrame.setJMenuBar(menuBar);
		if (AuthenticatedEmployee.getInstance().getEmployee().getAccessRight().getName().equals("default")) {
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
