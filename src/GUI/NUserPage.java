package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
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
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.SubSystem.SubSystemCatalogue;
import RequirementUtilization.ResourceRequirement;
import RequirementUtilization.ResourceRequirementCatalogue;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.FinancialResourceCatalogue;
import ResourceManagement.Section.Resource.InformationResourceCatalogue;
import ResourceManagement.Section.Resource.MaintainingModuleCatalogue;
import ResourceManagement.Section.Resource.ModuleCatalogue;
import ResourceManagement.Section.Resource.PhysicalResourceCatalogue;
import ResourceManagement.Section.Resource.ResourceCatalogue;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
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

public class NUserPage {

	private Color userpage_color = new Color(0, 150, 130);
	private Color tab_color = new Color(128, 203, 196);

	private JFrame userpageFrame;
	private JTextField editname_textField;
	private JTextField editpassword_textField;
	private ArrayList<HashMap<String, String>> allmodules;
	private ArrayList<HashMap<String, String>> allphysicals;
	private ArrayList<HashMap<String, String>> allfinance;
	private ArrayList<HashMap<String, String>> allinformation;
	private ArrayList<HashMap<String, String>> allres;
	private ArrayList<HashMap<String, String>> allprojects;
	private ArrayList<HashMap<String, String>> allemployees;
	private ArrayList<HashMap<String, String>> allsubsystems;
	private ArrayList<ResourceRequirement> allresourcerequirements;
	private ArrayList<HashMap<String, String>> allregisteredusers;

	// private JTable finan_table;
	// private JTable information_table;
	// private JTable module_table;
	// private JTable physical_table;
	// private JTable allresource_table;
	// private JTable project_table;
	private JTable subsystem_table;
	// private JTable accessright_table;
	// private JTable registered_table;
	// private JTable human_table;
	// private JTable maintaining_table;
	private JTable requirement_table;

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

	private int selected_project_forsubsystem;
	private int selected_accessright_forassignment;

	private JTextField search_modulename;
	private JTextField search_financialname;
	private JTextField search_informationname;
	private JTextField search_humanname;
	private JTextField search_physicalname;
	private JTextField search_projectname;
	private JTable resavail_table;
	private JTable resreq_table;
	private JTable cycle_table;
	private JTable resourceutil_table;
	private JTextField repassword_textField;
	private JTextField search_maintainingname;

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
		userpageFrame.getContentPane().setBackground(userpage_color);
		userpageFrame.setResizable(false);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 858, 551);
		userpageFrame.getContentPane().add(tabbedPane);

		JPanel editPanel = new JPanel();
		editPanel.setBackground(tab_color);
		tabbedPane.addTab("Edit Info", null, editPanel, null);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AuthenticatedEmployee.getInstance().getEmployee().logout()) {
					System.out.println("logged out");
					userpageFrame.dispose();
					NLoginPage loginWindow = new NLoginPage();
					loginWindow.getloginpageFrame().setVisible(true);

				}
			}
		});

		JLabel lblName = new JLabel("First and Last Name");

		editname_textField = new JTextField();
		editname_textField.setColumns(10);
		editname_textField.setText(AuthenticatedEmployee.getInstance().getEmployee().getName());
		editpassword_textField = new JTextField();
		editpassword_textField.setColumns(10);

		JLabel password = new JLabel("Password");

		repassword_textField = new JTextField();
		repassword_textField.setColumns(10);

		JLabel lblRepassword = DefaultComponentFactory.getInstance().createLabel("Re-Password");

		JButton btnEditInformation = new JButton("Edit Information");
		btnEditInformation.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnEditInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(editpassword_textField.getText() + " " + repassword_textField.getText() + " "
						+ editname_textField.getText());
				NotificationPage confirmation = new NotificationPage(new JFrame(), "Notification",
						"You have been successfully edited your information!");

			}
		});
		GroupLayout gl_editPanel = new GroupLayout(editPanel);
		gl_editPanel.setHorizontalGroup(gl_editPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_editPanel
				.createSequentialGroup().addContainerGap(515, Short.MAX_VALUE)
				.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(editname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(editpassword_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(repassword_textField, GroupLayout.PREFERRED_SIZE, 134,
								GroupLayout.PREFERRED_SIZE))
				.addGap(40)
				.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING).addComponent(lblRepassword)
						.addComponent(password).addComponent(lblName).addComponent(btnLogout)))
				.addGroup(Alignment.LEADING, gl_editPanel.createSequentialGroup().addComponent(btnEditInformation)
						.addContainerGap(720, Short.MAX_VALUE)));
		gl_editPanel.setVerticalGroup(gl_editPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_editPanel
				.createSequentialGroup().addContainerGap().addComponent(btnLogout).addGap(59)
				.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(editname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
				.addGap(26)
				.addGroup(gl_editPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(editpassword_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(password))
				.addGap(31)
				.addGroup(gl_editPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(repassword_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRepassword))
				.addPreferredGap(ComponentPlacement.RELATED, 241, Short.MAX_VALUE).addComponent(btnEditInformation)));
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

						String Table_click = (accessright_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
								.toString());
						System.out.println(Table_click);
						EmployeeCatalogue empcat = new EmployeeCatalogue();
						Employee emp_access = empcat.getEmployee(Integer.parseInt(Table_click));
						emp_access.setAccessRight(selected_accessright_forassignment);
						System.out.println("ACCESS RIGHT O DADAM");
						accessright_tabledata.update(empcat.readAllEmployees());

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

		GroupLayout gl_accessrightPanel = new GroupLayout(accessrightPanel);
		gl_accessrightPanel.setHorizontalGroup(gl_accessrightPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_accessrightPanel.createSequentialGroup().addGap(40).addComponent(btnAssignAccessright))
				.addGroup(gl_accessrightPanel.createSequentialGroup().addGap(41)
						.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
						.addGap(39)));
		gl_accessrightPanel.setVerticalGroup(gl_accessrightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_accessrightPanel.createSequentialGroup().addContainerGap()
						.addComponent(btnAssignAccessright).addGap(48)
						.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
						.addGap(32)));

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

		String[] subsystem_columns = new String[] { "Name", "ProjectId" };

		final DefaultTableModel subsystem_tableModel = new DefaultTableModel(subsystem_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		// THIS SHOULD BE CHANGED +++
		SubSystemCatalogue subsyscat = new SubSystemCatalogue();
		allsubsystems = subsyscat.getSubSystems();
		for (int i = 0; i < allsubsystems.size(); i++) {
			Object[] objs = { allsubsystems.get(i).get("sname"), allsubsystems.get(i).get("pid") };
			subsystem_tableModel.addRow(objs);
		}

		JButton addsubsystemBtn = new JButton("Add Subsystem");
		addsubsystemBtn.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		addsubsystemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> subsystem_addFields = new ArrayList<Field>();

				Field subsystem_name = new Field("text", "Subsystem Name", "", 10, "name");
				Field subsystem_desc = new Field("text", "Subsystem Description", "", 30, "desc");

				subsystem_addFields.add(subsystem_name);
				subsystem_addFields.add(subsystem_desc);

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
						SubSystemCatalogue subsyscat = new SubSystemCatalogue();
						System.out.println("all : ");
						subsyscat.getSubSystems();
						ArrayList<String> inputs = new ArrayList<String>();
						for (int i = 0; i < subsystem_Form.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) subsystem_Form.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " subsystesm");
						}

						subsyscat.addSubSystem(inputs.get(0).toString(), selected_project_forsubsystem);

						allsubsystems = subsyscat.getSubSystems();
						int rowcount = subsystem_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							subsystem_tableModel.removeRow(j);
						}
						System.out.println(subsystem_tableModel.getRowCount() + " ---");
						for (int i = 0; i < allsubsystems.size(); i++) {
							Object[] objs = { allsubsystems.get(i).get("sname"), allsubsystems.get(i).get("pid") };
							subsystem_tableModel.addRow(objs);
						}

					}
				});

			}
		});
		GroupLayout gl_subsystemPanel = new GroupLayout(subsystemPanel);
		gl_subsystemPanel.setHorizontalGroup(gl_subsystemPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_subsystemPanel.createSequentialGroup().addComponent(btnBacktoProject)
						.addPreferredGap(ComponentPlacement.RELATED, 640, Short.MAX_VALUE)
						.addComponent(addsubsystemBtn, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_subsystemPanel.createSequentialGroup().addGap(40)
						.addComponent(subsystem_scrollPane, GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
						.addGap(40)));
		gl_subsystemPanel
				.setVerticalGroup(
						gl_subsystemPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_subsystemPanel.createSequentialGroup().addGap(40)
										.addComponent(subsystem_scrollPane, GroupLayout.DEFAULT_SIZE, 425,
												Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_subsystemPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnBacktoProject).addComponent(addsubsystemBtn))));

		subsystem_table = new JTable(subsystem_tableModel);
		subsystem_scrollPane.setViewportView(subsystem_table);
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
		GroupLayout gl_resourceutilpanel = new GroupLayout(resourceutilpanel);
		gl_resourceutilpanel
				.setHorizontalGroup(
						gl_resourceutilpanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_resourceutilpanel.createSequentialGroup().addGap(40)
										.addComponent(resourceutil_scrollPane, GroupLayout.DEFAULT_SIZE, 364,
												Short.MAX_VALUE)
										.addGap(40))
								.addGroup(gl_resourceutilpanel.createSequentialGroup()
										.addComponent(utilbtnBacktoProject).addContainerGap(720, Short.MAX_VALUE)));
		gl_resourceutilpanel
				.setVerticalGroup(
						gl_resourceutilpanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_resourceutilpanel
										.createSequentialGroup().addGap(40).addComponent(resourceutil_scrollPane,
												GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
										.addGap(11).addComponent(utilbtnBacktoProject)));

		resourceutil_table = new JTable();
		resourceutil_scrollPane.setViewportView(resourceutil_table);
		resourceutilpanel.setLayout(gl_resourceutilpanel);
		//
		JPanel requirementPanel = new JPanel();
		requirementPanel.setBackground(tab_color);
		tabbedPane.addTab("Requirment Management", null, requirementPanel, null);

		JButton addreqBtn = new JButton("Add Requirement");
		addreqBtn.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		ResourceRequirementCatalogue resreqcat = new ResourceRequirementCatalogue();
		System.out.println("all : ");
		allresourcerequirements = resreqcat.getResourceRequirements();

		String[] resreq_columns = new String[] { "rid", "resource", "sid", "section", "pid", "project", "from", "to" };

		final DefaultTableModel resreq_tableModel = new DefaultTableModel(resreq_columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

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

						String rid = "";
						Pattern p = Pattern.compile("rid=\\d+");
						Matcher m = p.matcher((CharSequence) resourceCombo.getSelectedItem());
						if (m.find()) {
							rid = m.group();
						}

						String sectionid = "";
						Pattern p1 = Pattern.compile("sectionid=\\d+");
						Matcher m1 = p1.matcher((CharSequence) sectionCombo.getSelectedItem());
						if (m1.find()) {
							sectionid = m1.group();
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
						System.out.println(rid + " " + sectionid + " " + projid + " " + fromdate + " " + todate);

						resreqCat.addResourceRequirement(Integer.parseInt(rid.replace("rid=", "")),
								Integer.parseInt(sectionid.replace("sectionid=", "")),
								Integer.parseInt(projid.replace("projid=", "")), fromdate, todate);
						// // tu resource ham bayad insert she
						// allmodules.clear();
						allresourcerequirements = resreqCat.getResourceRequirements();
						System.out.println(resreq_tableModel.getRowCount() + "");
						int rowcount = resreq_tableModel.getRowCount();
						for (int j = rowcount - 1; j >= 0; j--) {
							System.out.println(j);
							resreq_tableModel.removeRow(j);
						}
						System.out.println(resreq_tableModel.getRowCount() + "");
						for (int i = 0; i < allresourcerequirements.size(); i++) {
							Object[] objs = { allresourcerequirements.get(i).toHashMap().get("rid"),
									allresourcerequirements.get(i).getResource().getName(),
									allresourcerequirements.get(i).toHashMap().get("sid"),
									allresourcerequirements.get(i).getSection().getName(),
									allresourcerequirements.get(i).toHashMap().get("pid"),
									allresourcerequirements.get(i).getProject().getName(),
									allresourcerequirements.get(i).toHashMap().get("fromdate"),
									allresourcerequirements.get(i).toHashMap().get("todate") };
							resreq_tableModel.addRow(objs);
						}

					}
				});

			}
		});

		JButton searchreqBtn = new JButton("Search");

		JScrollPane requirement_scrollPane = new JScrollPane();

		JButton requirement_btnEdit = new JButton("Edit");
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
				final JDatePanelImpl from_datePanel = new JDatePanelImpl(modelfor, p);
				final JDatePickerImpl from_datePicker = new JDatePickerImpl(from_datePanel, new DateLabelFormatter());

				JPanel date_panel = new JPanel(new FlowLayout());

				date_panel.add(from_datePanel);
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
						System.out.println(from_datePicker.getJFormattedTextField().getText());

					}
				});

			}
		});
		GroupLayout gl_requirementPanel = new GroupLayout(requirementPanel);
		gl_requirementPanel
				.setHorizontalGroup(
						gl_requirementPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_requirementPanel.createSequentialGroup()
										.addContainerGap(358, Short.MAX_VALUE).addComponent(searchreqBtn).addGap(394))
								.addGroup(gl_requirementPanel.createSequentialGroup().addGap(40)
										.addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 757,
												Short.MAX_VALUE)
										.addGap(40))
								.addGroup(Alignment.TRAILING,
										gl_requirementPanel.createSequentialGroup().addComponent(requirement_btnEdit)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(requirement_btnSatisfy)
												.addPreferredGap(ComponentPlacement.RELATED, 529, Short.MAX_VALUE)
												.addComponent(addreqBtn)));
		gl_requirementPanel.setVerticalGroup(gl_requirementPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_requirementPanel.createSequentialGroup().addContainerGap().addComponent(searchreqBtn)
						.addGap(40).addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
						.addGap(11)
						.addGroup(gl_requirementPanel.createParallelGroup(Alignment.BASELINE).addComponent(addreqBtn)
								.addComponent(requirement_btnEdit).addComponent(requirement_btnSatisfy))));

		requirement_table = new JTable(resreq_tableModel);

		for (int i = 0; i < allresourcerequirements.size(); i++) {
			Object[] objs = { allresourcerequirements.get(i).toHashMap().get("rid"),
					allresourcerequirements.get(i).getResource().getName(),
					allresourcerequirements.get(i).toHashMap().get("sid"),
					allresourcerequirements.get(i).getSection().getName(),
					allresourcerequirements.get(i).toHashMap().get("pid"),
					allresourcerequirements.get(i).getProject().getName(),
					allresourcerequirements.get(i).toHashMap().get("fromdate"),
					allresourcerequirements.get(i).toHashMap().get("todate") };
			resreq_tableModel.addRow(objs);
		}

		requirement_scrollPane.setViewportView(requirement_table);
		requirementPanel.setLayout(gl_requirementPanel);
		searchreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

				String Table_click = (information_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
						.toString()); // the
				System.out.println(Table_click + " this was clicked");
				InformationResourceCatalogue infocat = new InformationResourceCatalogue();
				infocat.deleteResource(Integer.parseInt(Table_click));

				information_tabledata.update(infocat.readAllResources());

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

				information_tabledata.update(infocat.SearchResource(searchVars));

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
		gl_informationPanel.setHorizontalGroup(
			gl_informationPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(information_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addComponent(information_btnEdit, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(information_btnDelete, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
					.addComponent(btnAddInformation))
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addComponent(search_informationbtnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED, 388, Short.MAX_VALUE)
					.addComponent(information_btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(search_informationname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInformationName)
					.addContainerGap())
		);
		gl_informationPanel.setVerticalGroup(
			gl_informationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_informationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(search_informationname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(information_btnSearch)
						.addComponent(lblInformationName)
						.addComponent(search_informationbtnRefresh))
					.addGap(31)
					.addComponent(information_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_informationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(information_btnEdit)
						.addComponent(information_btnDelete)
						.addComponent(btnAddInformation))
					.addContainerGap())
		);

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
						for (int i = 0; i < information_moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) information_moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " information");
						}
						infocat.addResource((inputs.get(0)));
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

				String Table_click = (financial_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
						.toString()); // the
				System.out.println(Table_click + " this was clicked");
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				financat.deleteResource(Integer.parseInt(Table_click));
				financial_tabledata.update(financat.readAllResources());

			}
		});

		search_financialname = new JTextField();
		search_financialname.setColumns(10);

		JButton financial_btnSearch = new JButton("Search");
		financial_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();
				searchVars.put("finanname", "\'" + search_financialname.getText() + "\'");
				financat.SearchResource(searchVars);

				allfinance.clear();
				financial_tabledata.update(financat.SearchResource(searchVars));

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

		GroupLayout gl_financialPanel = new GroupLayout(financialPanel);
		gl_financialPanel.setHorizontalGroup(
			gl_financialPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(financial_table_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addComponent(financial_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(financial_btnDelete, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
					.addComponent(btnAddFinancial))
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addComponent(search_financialbtnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
					.addComponent(financial_btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(search_financialname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFinancialName)
					.addContainerGap())
		);
		gl_financialPanel.setVerticalGroup(
			gl_financialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_financialPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(search_financialname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(financial_btnSearch)
						.addComponent(lblFinancialName)
						.addComponent(search_financialbtnRefresh))
					.addGap(31)
					.addComponent(financial_table_scrollPane, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_financialPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(financial_btnEdit)
						.addComponent(financial_btnDelete)
						.addComponent(btnAddFinancial))
					.addContainerGap())
		);

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
				financial_moduleFields.add(sections);
				financial_moduleFields.add(new Field("text", "model description", "", 20, "model desc"));
				financial_moduleFields.add(new Field("text", "net value", "", 20, "value"));
				financial_moduleFields.add(new Field("text", "description", "", 20, "desc"));

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
						financat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
						allfinance.clear();
						allfinance = financat.readAllResources();
						financial_tabledata.update(financat.readAllResources());
					}
				});
			}
		});

		// get module list
		ModuleCatalogue mcat = new ModuleCatalogue();
		System.out.println("all : ");
		allmodules = mcat.readAllResources();

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
		btnAddModule.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnAddModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Field> moduleFields = new ArrayList<Field>();
				moduleFields.add(new Field("text", "name", "", 10, "name"));
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");
				moduleFields.add(sections);
				moduleFields.add(new Field("text", "duration", "", 20, "duration"));

				final Form moduleForm = new Form(moduleFields, "Module Form");
				final PanelBuilder modulePanel = new PanelBuilder(moduleForm);
				modulePanel.makeForm();
				JFrame AddModulePage = new JFrame("Add Module Form");
				AddModulePage.getContentPane().add(moduleForm.getJPanel(), BorderLayout.NORTH);

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
						for (int i = 0; i < moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}
						mcat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
						module_tabledata.update(mcat.readAllResources());
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
				ArrayList<String> section_arraylist = new ArrayList<String>();
				SectionCatalogue seccat = new SectionCatalogue();
				ArrayList<HashMap<String, String>> section_hashmap = seccat.getSections();
				for (int i = 0; i < section_hashmap.size(); i++) {
					section_arraylist.add(section_hashmap.get(i).toString());
				}

				Field sections = new Field("comboBox", "sections", section_arraylist, 20, "items");

				ArrayList<Field> moduleFields = new ArrayList<Field>();
				moduleFields.add(new Field("text", "name", "", 10, "name"));
				moduleFields.add(sections);
				moduleFields.add(new Field("text", "duration", "", 20, "duration"));

				final Form moduleForm = new Form(moduleFields, "Module Form");
				final PanelBuilder modulePanel = new PanelBuilder(moduleForm);
				modulePanel.makeForm();
				JFrame AddModulePage = new JFrame("Edit Module Form");
				AddModulePage.getContentPane().add(moduleForm.getJPanel(), BorderLayout.NORTH);

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
						for (int i = 0; i < moduleForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) moduleForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + "adasa");
						}
						// mcat.addResource((inputs.get(0)));
						// // tu resource ham bayad insert she
						// allmodules.clear();
						// allmodules = mcat.readAllResources();
						// System.out.println(module_tableModel.getRowCount() +
						// " ---");
						// int rowcount = module_tableModel.getRowCount();
						// for (int j = rowcount - 1; j >= 0; j--) {
						// System.out.println(j);
						// module_tableModel.removeRow(j);
						// }
						// System.out.println(module_tableModel.getRowCount() +
						// " ---");
						// for (int i = 0; i < allmodules.size(); i++) {
						// Object[] objs = { allmodules.get(i).get("rid"),
						// allmodules.get(i).get("modname") };
						// module_tableModel.addRow(objs);
						// }
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

				String Table_click = (module_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // the
				System.out.println(Table_click + " this was clicked");
				ModuleCatalogue modcat = new ModuleCatalogue();
				modcat.deleteResource(Integer.parseInt(Table_click));

				module_tabledata.update(modcat.readAllResources());

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

				String Table_click = (module_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // return
				// the
				// thing
				// in
				// the
				// 0st
				// column
				System.out.println(Table_click);
				System.out.println("-----");
				System.out.println("Change JPanel");
				int selected_index = resourcesTab.getSelectedIndex();
				resourcesTab.remove(selected_index);
				resourcesTab.insertTab("Maintaining", null, maintaining_panel, null, selected_index);
				resourcesTab.setSelectedComponent(maintaining_panel);
			}
		});

		search_modulename = new JTextField();
		search_modulename.setColumns(10);

		JButton module_btnSearch = new JButton("Search");
		module_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModuleCatalogue mcat = new ModuleCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();
				searchVars.put("modname", "\'" + search_modulename.getText() + "\'");

				allmodules.clear();
				allmodules = mcat.SearchResource(searchVars);
				module_tabledata.update(mcat.SearchResource(searchVars));
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
		GroupLayout gl_modulePanel_1 = new GroupLayout(modulePanel);
		gl_modulePanel_1.setHorizontalGroup(
			gl_modulePanel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modulePanel_1.createSequentialGroup()
					.addGap(30)
					.addComponent(module_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(30))
				.addGroup(gl_modulePanel_1.createSequentialGroup()
					.addComponent(module_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(module_btnDelete, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
					.addComponent(btnViewMaintaning)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddModule))
				.addGroup(gl_modulePanel_1.createSequentialGroup()
					.addComponent(search_modulebtnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED, 416, Short.MAX_VALUE)
					.addComponent(module_btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(search_modulename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblModuleName)
					.addContainerGap())
		);
		gl_modulePanel_1.setVerticalGroup(
			gl_modulePanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modulePanel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(module_btnSearch)
						.addComponent(search_modulename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModuleName)
						.addComponent(search_modulebtnRefresh))
					.addGap(31)
					.addComponent(module_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_modulePanel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(module_btnEdit)
						.addComponent(module_btnDelete)
						.addComponent(btnAddModule)
						.addComponent(btnViewMaintaning))
					.addContainerGap())
		);

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
		maintaining_btnEdit.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		JButton maintaining_btnDelete = new JButton("Delete");
		maintaining_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		maintaining_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnAddMaintaining = new JButton("Add Maintaining");
		btnAddMaintaining.setIcon(new ImageIcon(
				new ImageIcon("images/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnAddMaintaining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
								.addGroup(gl_maintaining_panel.createSequentialGroup()
										.addComponent(maintaining_btnEdit, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(maintaining_btnDelete, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBacktoModule)
										.addPreferredGap(ComponentPlacement.RELATED, 428, Short.MAX_VALUE)
										.addComponent(btnAddMaintaining, GroupLayout.PREFERRED_SIZE, 131,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_maintaining_panel.createSequentialGroup()
												.addComponent(search_maintainingbtnRefresh)
												.addPreferredGap(ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
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
						.addGap(30).addComponent(maintaining_scrollPane, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_maintaining_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddMaintaining).addComponent(maintaining_btnEdit)
								.addComponent(maintaining_btnDelete).addComponent(btnBacktoModule))
						.addContainerGap()));

		maintaining_tabledata = new TableData(new MaintainingModuleCatalogue());
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

				int rowIndex = human_tabledata.getJdataTable().getSelectedRow();
				int colIndex = human_tabledata.getJdataTable().getSelectedColumn();

				String Table_click = (human_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // the
				System.out.println(Table_click + " this was clicked");
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				// empcat.deleteEmployee(Integer.parseInt(Table_click));

				empcat.readAllEmployees();
				allemployees.clear();
				allemployees = empcat.readAllEmployees();
				human_tabledata.update(empcat.readAllEmployees());
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

				String Table_click = (human_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // return
				// the
				// thing
				// in
				// the
				// 0st
				// column
				System.out.println(Table_click);
				EmployeeCatalogue empcat_delete = new EmployeeCatalogue();
				empcat_delete.deleteEmployee(Integer.parseInt(Table_click));
				allemployees = empcat_delete.readAllEmployees();
				human_tabledata.update(empcat_delete.readAllEmployees());

			}
		});

		search_humanname = new JTextField();
		search_humanname.setColumns(10);

		JButton human_btnSearch = new JButton("Search");
		human_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCatalogue empcat = new EmployeeCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();
				searchVars.put("empname", "\'" + search_humanname.getText() + "\'");

				allemployees.clear();
				allemployees = empcat.SearchEmployee(searchVars);
				human_tabledata.update(empcat.SearchEmployee(searchVars));
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
					.addComponent(human_btnDelete, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(631, Short.MAX_VALUE))
				.addGroup(gl_humanPanel.createSequentialGroup()
					.addComponent(search_humanbtnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED, 419, Short.MAX_VALUE)
					.addComponent(human_btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(search_humanname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHumanName)
					.addContainerGap())
		);
		gl_humanPanel.setVerticalGroup(
			gl_humanPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_humanPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_humanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(search_humanname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(human_btnSearch)
						.addComponent(lblHumanName)
						.addComponent(search_humanbtnRefresh))
					.addGap(31)
					.addComponent(human_scrollPane, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_humanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(human_btnEdit)
						.addComponent(human_btnDelete))
					.addContainerGap())
		);
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
						physcat.addResource((inputs.get(0)));

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

				String Table_click = (physical_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // the
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
		});

		JButton physical_btnDelete = new JButton("Delete");
		physical_btnDelete.setIcon(new ImageIcon(
				new ImageIcon("images/delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		physical_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = physical_tabledata.getJdataTable().getSelectedRow();
				int colIndex = physical_tabledata.getJdataTable().getSelectedColumn();

				String Table_click = (physical_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // the
				System.out.println(Table_click + " this was clicked");
				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				physcat.deleteResource(Integer.parseInt(Table_click));

				physical_tabledata.update(physcat.readAllResources());

			}
		});

		search_physicalname = new JTextField();
		search_physicalname.setColumns(10);

		JButton physical_btnSearch = new JButton("Search");
		physical_btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
				HashMap<String, String> searchVars = new HashMap<String, String>();
				searchVars.put("physname", "\'" + search_physicalname.getText() + "\'");

				physical_tabledata.update(physcat.SearchResource(searchVars));

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

		GroupLayout gl_physicalPanel = new GroupLayout(physicalPanel);
		gl_physicalPanel.setHorizontalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addGap(30))
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addComponent(physical_btnEdit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(physical_btnDelete, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
					.addComponent(btnAddPhysicalResource))
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addComponent(search_physicalbtnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED, 411, Short.MAX_VALUE)
					.addComponent(physical_btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(search_physicalname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPhysicalName)
					.addContainerGap())
		);
		gl_physicalPanel.setVerticalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_physicalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(search_physicalname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(physical_btnSearch)
						.addComponent(lblPhysicalName)
						.addComponent(search_physicalbtnRefresh))
					.addGap(31)
					.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_physicalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddPhysicalResource)
						.addComponent(physical_btnEdit)
						.addComponent(physical_btnDelete))
					.addContainerGap())
		);

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

				final Form projectForm = new Form(projectFields, "Project Form");
				final PanelBuilder project_addPanel = new PanelBuilder(projectForm);
				project_addPanel.makeForm();
				JFrame Add_ProjectPage = new JFrame("Add Project Form");
				Add_ProjectPage.getContentPane().add(projectForm.getJPanel(), BorderLayout.NORTH);

				ComboBoxJPanel comboBoxpane = (ComboBoxJPanel) projectForm.getJPanel().getComponent(3);
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
						for (int i = 0; i < projectForm.getJPanel().getComponentCount(); i++) {
							FieldPanel fpanel = (FieldPanel) projectForm.getJPanel().getComponent(i);
							inputs.add(fpanel.getValues().get(0));
						}
						for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i) + " project");
						}
						int employeeID = Integer.parseInt((inputs.get(1).substring(0, 4).replace("id:", "")));
						EmployeeCatalogue empcat = new EmployeeCatalogue();
						Employee proj_manager = empcat.getEmployee(employeeID);
						System.out.println(proj_manager.getName());
						projcat.addProject(inputs.get(0).toString(), proj_manager);

						allprojects = projcat.getProjects();
						project_tabledata.update(projcat.getProjects());

					}
				});
			}
		});

		JButton project_btnSearch = new JButton("Search");

		JScrollPane project_scrollPane = new JScrollPane();

		JButton viewsubsys_Btn = new JButton("View SubSystem");
		viewsubsys_Btn.setIcon(new ImageIcon(
				new ImageIcon("images/view.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		viewsubsys_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("-----");
				int rowIndex = project_tabledata.getJdataTable().getSelectedRow();
				int colIndex = project_tabledata.getJdataTable().getSelectedColumn();

				String Table_click = (project_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // return
				// the
				// thing
				// in
				// the
				// 0st
				// column
				System.out.println(Table_click);
				selected_project_forsubsystem = Integer.parseInt(Table_click.trim());
				System.out.println("-----");
				System.out.println("Change JPanel");
				int selected_index = tabbedPane.getSelectedIndex();
				tabbedPane.remove(selected_index);
				tabbedPane.insertTab("Subsystem", null, subsystemPanel, null, selected_index);
				tabbedPane.setSelectedComponent(subsystemPanel);

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

				String Table_click = (project_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // return

				selected_project_forsubsystem = Integer.parseInt(Table_click.trim());
				System.out.println("-----");
				System.out.println("Change JPanel");
				int selected_index = tabbedPane.getSelectedIndex();
				tabbedPane.remove(selected_index);
				tabbedPane.insertTab("Resource Utilization", null, resourceutilpanel, null, selected_index);
				tabbedPane.setSelectedComponent(resourceutilpanel);

			}
		});

		JButton btnEditProject = new JButton("Edit");
		btnEditProject.setIcon(new ImageIcon(
				new ImageIcon("images/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		btnEditProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

				String Table_click = (project_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0).toString()); // return
				ProjectCatalogue projcat = new ProjectCatalogue();
				projcat.deleteProject(Integer.parseInt(Table_click));
				project_tabledata.update(projcat.getProjects());

			}
		});
		GroupLayout gl_projectPanel = new GroupLayout(projectPanel);
		gl_projectPanel
				.setHorizontalGroup(
						gl_projectPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_projectPanel.createSequentialGroup().addGap(40)
										.addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 757,
												Short.MAX_VALUE)
										.addGap(40))
								.addGroup(gl_projectPanel.createSequentialGroup().addContainerGap(517, Short.MAX_VALUE)
										.addComponent(project_btnSearch).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(search_projectname, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblProjectName)
										.addContainerGap())
								.addGroup(gl_projectPanel.createSequentialGroup().addContainerGap()
										.addComponent(btnEditProject).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnDeleteProject)
										.addPreferredGap(ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
										.addComponent(btnViewResources).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(viewsubsys_Btn).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(addprojectBtn)));
		gl_projectPanel.setVerticalGroup(gl_projectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_projectPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_projectPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(search_projectname, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProjectName).addComponent(project_btnSearch))
						.addGap(40).addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
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

		JPanel cyclePanel = new JPanel();
		reportsTab.addTab("Cycle Report", null, cyclePanel, null);

		JScrollPane cycle_scrollPane = new JScrollPane();

		JButton cycle_btnGetReport = new JButton("Get Report");
		cycle_btnGetReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> resource_types = new ArrayList<String>();
				final ArrayList<String> resources = new ArrayList<String>();
				resource_types.add("Information");
				resource_types.add("Financial");
				resource_types.add("Physical");
				resource_types.add("Employee");
				resource_types.add("Module");
				ArrayList<Field> cyclicReport_Fields = new ArrayList<Field>();
				Field res_type = new Field("comboBox", "resource types", resource_types, 30, "items");
				Field res = new Field("comboBox", "resources", resources, 30, "items");

				cyclicReport_Fields.add(res_type);
				cyclicReport_Fields.add(res);

				final Form cycliclreport_Form = new Form(cyclicReport_Fields, "Cyclic Report Form");
				final PanelBuilder cylic_Panel = new PanelBuilder(cycliclreport_Form);
				cylic_Panel.makeForm();

				JFrame getReport_CycliclPage = new JFrame("Get Report Cyclic Resource Form");

				getReport_CycliclPage.getContentPane().add(cycliclreport_Form.getJPanel(), BorderLayout.NORTH);

				JButton submitgetReportBtn = new JButton("Submit");
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(submitgetReportBtn);
				getReport_CycliclPage.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				getReport_CycliclPage.pack();
				getReport_CycliclPage.setVisible(true);
				ComboBoxJPanel comboBoxpanel_restype = (ComboBoxJPanel) cycliclreport_Form.getJPanel().getComponent(0);
				ComboBoxJPanel comboBoxpane_res = (ComboBoxJPanel) cycliclreport_Form.getJPanel().getComponent(1);

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
						System.out.println(resource_type.getSelectedItem() + " this is resource combo");
						String rid = "";
						Pattern p = Pattern.compile("rid=\\d+");
						Matcher m = p.matcher((CharSequence) resourceCombo.getSelectedItem());
						if (m.find()) {
							rid = m.group();
						}
						System.out.println("rid: " + rid);

					}
				});
			}
		});
		GroupLayout gl_cyclePanel = new GroupLayout(cyclePanel);
		gl_cyclePanel.setHorizontalGroup(gl_cyclePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cyclePanel.createSequentialGroup().addGap(20)
						.addComponent(cycle_scrollPane, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE).addGap(20))
				.addGroup(Alignment.TRAILING, gl_cyclePanel.createSequentialGroup()
						.addContainerGap(679, Short.MAX_VALUE).addComponent(cycle_btnGetReport)));
		gl_cyclePanel
				.setVerticalGroup(gl_cyclePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cyclePanel.createSequentialGroup().addComponent(cycle_btnGetReport).addGap(20)
								.addComponent(cycle_scrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
								.addGap(20)));

		cycle_table = new JTable();
		cycle_scrollPane.setViewportView(cycle_table);
		cyclePanel.setLayout(gl_cyclePanel);

		JPanel resourcereqPanel = new JPanel();
		reportsTab.addTab("Resource Requirement Report", null, resourcereqPanel, null);

		JScrollPane resreq_scrollPane = new JScrollPane();

		JButton resreq_btnGetReport = new JButton("Get Report");
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
							resReqCat.getReport(proj).printRep();
							// print doesnt work with table gimme sth else
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

		resreq_table = new JTable();
		resreq_scrollPane.setViewportView(resreq_table);
		resourcereqPanel.setLayout(gl_resourcereqPanel);

		JPanel resourceavailPanel = new JPanel();
		reportsTab.addTab("Resource Available Report", null, resourceavailPanel, null);

		JScrollPane resavail_scrollpane = new JScrollPane();

		JButton resavail_btnGetReport = new JButton("Get Report");
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
						// PhysicalResourceCatalogue physResCat = new
						// PhysicalResourceCatalogue();
						// physResCat.getReport().printRep();
						// FinancialResourceCatalogue finanResCat= new
						// FinancialResourceCatalogue();
						// finanResCat.getReport().printRep();
						//
						if (resourceRepCombo.getSelectedItem().toString().equals("Financial")) {
						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Physical")) {
						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Information")) {
						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Employee")) {
						}
						if (resourceRepCombo.getSelectedItem().toString().equals("Module")) {
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

		resavail_table = new JTable();
		resavail_scrollpane.setViewportView(resavail_table);
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

				String Table_click = (registered_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
						.toString());
				System.out.println(Table_click);
				EmployeeCatalogue regempcat = new EmployeeCatalogue();
				regempcat.makeDecision(Integer.parseInt(Table_click), true);
				registered_tabledata.update(regempcat.getRegistrations());

			}
		});

		JButton btnDeny = new JButton("Deny");
		btnDeny.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("-----");
				int rowIndex = registered_tabledata.getJdataTable().getSelectedRow();
				int colIndex = registered_tabledata.getJdataTable().getSelectedColumn();

				String Table_click = (registered_tabledata.getJdataTable().getModel().getValueAt(rowIndex, 0)
						.toString());
				System.out.println(Table_click);
				EmployeeCatalogue regempcat = new EmployeeCatalogue();
				regempcat.makeDecision(Integer.parseInt(Table_click), false);
				registered_tabledata.update(regempcat.getRegistrations());

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
										.addContainerGap(660, Short.MAX_VALUE).addComponent(btnDeny)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnConfirm)
										.addContainerGap()));
		gl_RegisteredUserspanel.setVerticalGroup(gl_RegisteredUserspanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RegisteredUserspanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_RegisteredUserspanel.createParallelGroup(Alignment.BASELINE).addComponent(btnDeny)
								.addComponent(btnConfirm))
						.addGap(40).addComponent(registered_scrollPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
						.addGap(40)));

		registered_tabledata = new TableData(new EmployeeCatalogue(), "registered");

		registered_scrollPane.setViewportView(registered_tabledata.getJdataTable());
		RegisteredUserspanel.setLayout(gl_RegisteredUserspanel);
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
