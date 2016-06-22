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
import javax.swing.ListSelectionModel;
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
import GUI.Form.FieldPanel;
import GUI.Form.Form;
import GUI.Form.PanelBuilder;
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
import javax.swing.ListModel;

public class NUserPage {

	private JFrame userpageFrame;
	private JTextField editname_textField;
	private JTextField editfamilyname_textField;
	private String loggedin_user;
	private ArrayList<HashMap<String, String>> allmodules;
	private ArrayList<HashMap<String, String>> allphysicals;
	private ArrayList<HashMap<String, String>> allfinance;
	private ArrayList<HashMap<String, String>> allinformation;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUserPage window = new NUserPage(null);
					window.userpageFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param loggedin_user 
	 */
	public NUserPage(String user) {
		loggedin_user=user;
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 858, 551);
		userpageFrame.getContentPane().add(tabbedPane);
		
		JPanel editPanel = new JPanel();
		tabbedPane.addTab("Edit Info", null, editPanel, null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblName = new JLabel("Name");
		
		editname_textField = new JTextField();
		editname_textField.setColumns(10);
		editname_textField.setText(loggedin_user);
		editfamilyname_textField = new JTextField();
		editfamilyname_textField.setColumns(10);
		
		JLabel lblFamilyName = new JLabel("Family Name");
		GroupLayout gl_editPanel = new GroupLayout(editPanel);
		gl_editPanel.setHorizontalGroup(
			gl_editPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_editPanel.createSequentialGroup()
					.addContainerGap(575, Short.MAX_VALUE)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(editname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editfamilyname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFamilyName)
						.addComponent(lblName)
						.addComponent(btnLogout)))
		);
		gl_editPanel.setVerticalGroup(
			gl_editPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogout)
					.addGap(59)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(editname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(26)
					.addGroup(gl_editPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(editfamilyname_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFamilyName))
					.addContainerGap(329, Short.MAX_VALUE))
		);
		editPanel.setLayout(gl_editPanel);
		
		
		JPanel accessrightPanel = new JPanel();
		tabbedPane.addTab("AccessRight Management", null, accessrightPanel, null);
		
		JButton btnAssignAccessright = new JButton("Assign AccessRight");
		
		JScrollPane accessright_scrollPane = new JScrollPane();
		GroupLayout gl_accessrightPanel = new GroupLayout(accessrightPanel);
		gl_accessrightPanel.setHorizontalGroup(
			gl_accessrightPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(btnAssignAccessright))
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
					.addGap(40))
		);
		gl_accessrightPanel.setVerticalGroup(
			gl_accessrightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_accessrightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAssignAccessright)
					.addGap(40)
					.addComponent(accessright_scrollPane, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
					.addGap(40))
		);
		
		JList<String> accessright_list = new JList<String>();
		accessright_scrollPane.setViewportView(accessright_list);
		accessright_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accessrightPanel.setLayout(gl_accessrightPanel);
		
		JPanel requirementPanel = new JPanel();
		tabbedPane.addTab("Requirment Management", null, requirementPanel, null);
		
		JButton addreqBtn = new JButton("Add Requirement");
		addreqBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		JButton searchreqBtn = new JButton("Search");
		
		JScrollPane requirement_scrollPane = new JScrollPane();
		GroupLayout gl_requirementPanel = new GroupLayout(requirementPanel);
		gl_requirementPanel.setHorizontalGroup(
			gl_requirementPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_requirementPanel.createSequentialGroup()
					.addContainerGap(358, Short.MAX_VALUE)
					.addComponent(searchreqBtn)
					.addGap(242)
					.addComponent(addreqBtn))
				.addGroup(gl_requirementPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
					.addGap(40))
		);
		gl_requirementPanel.setVerticalGroup(
			gl_requirementPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_requirementPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_requirementPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchreqBtn)
						.addComponent(addreqBtn))
					.addGap(40)
					.addComponent(requirement_scrollPane, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
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
		
		JTabbedPane resourcesTab = new JTabbedPane(JTabbedPane.TOP);
		sl_resourcePanel.putConstraint(SpringLayout.NORTH, resourcesTab, 10, SpringLayout.NORTH, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.WEST, resourcesTab, 10, SpringLayout.WEST, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.SOUTH, resourcesTab, 525, SpringLayout.NORTH, resourcePanel);
		sl_resourcePanel.putConstraint(SpringLayout.EAST, resourcesTab, 827, SpringLayout.WEST, resourcePanel);
		resourcePanel.add(resourcesTab);
	
		
		

		//get information list
		final DefaultListModel<String> informationlistModel = new DefaultListModel<String>();
		
		InformationResourceCatalogue infocat = new InformationResourceCatalogue();
		System.out.println("all : ");
		allinformation = infocat.readAllResources();

		for (int i = 0; i < allinformation.size(); i++) {
			System.out.println(allinformation.get(i));
			informationlistModel.addElement(""+allinformation.get(i).toString().substring(0, 7)+" " +allinformation.get(i).toString().subSequence(7, allinformation.get(i).toString().length()));
		}

		//end get information list
		
		JPanel informationPanel = new JPanel();
		resourcesTab.addTab("Information", null, informationPanel, null);
		
		JButton btnAddInformation = new JButton("Add Information Resource");
		
		

		 
		JScrollPane information_scrollPane = new JScrollPane();
		GroupLayout gl_informationPanel = new GroupLayout(informationPanel);
		gl_informationPanel.setHorizontalGroup(
			gl_informationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addContainerGap(588, Short.MAX_VALUE)
					.addComponent(btnAddInformation))
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(information_scrollPane, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_informationPanel.setVerticalGroup(
			gl_informationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_informationPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAddInformation)
					.addGap(30)
					.addComponent(information_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(30))
		);
		
		JList<String> information_list = new JList<String>(informationlistModel);
		information_scrollPane.setViewportView(information_list);
		information_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		informationPanel.setLayout(gl_informationPanel);
		btnAddInformation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> information_moduleFields = new ArrayList<Field>();
				information_moduleFields.add(new Field("text","infosname","",20,"name"));


				Form information_moduleForm = new Form(information_moduleFields,"Information Module Form");
				final PanelBuilder information_modulePanel = new PanelBuilder(information_moduleForm);
				information_modulePanel.makeForm();
				JFrame Add_InformationModulePage= new JFrame("Add Information Module Form");
				Add_InformationModulePage.getContentPane().add(information_modulePanel.getJPanel(),BorderLayout.NORTH);
				
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
	  			    	for(int i=0; i<information_modulePanel.getJPanel().getComponentCount(); i++){
	  			    		FieldPanel fpanel = (FieldPanel)information_modulePanel.getJPanel().getComponent(i);
	  			    		inputs.add(fpanel.getValues().get(0));
	  			    	}
	  			    	for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i)+" information");
						}
	  			    	infocat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
	  			    	infocat.readAllResources();
	  			    	
	  			    	
	  			    	allinformation.clear();
	  			    	informationlistModel.removeAllElements();
	  			    	allinformation=infocat.readAllResources();
						for (int i = 0; i < allinformation.size(); i++) {
							System.out.println(allinformation.get(i));
							informationlistModel.addElement(""+allinformation.get(i).toString().substring(0, 7)+" " +allinformation.get(i).toString().subSequence(7, allinformation.get(i).toString().length()));
						}
	  			    	
						
						
					}
				});
			}
		});
		

	
		
		
		//get financial list
		final DefaultListModel<String> financiallistModel = new DefaultListModel<String>();
		
		FinancialResourceCatalogue financat = new FinancialResourceCatalogue();
		System.out.println("all : ");
		allfinance = financat.readAllResources();

		for (int i = 0; i < allfinance.size(); i++) {
			System.out.println(allfinance.get(i));
			financiallistModel.addElement(""+allfinance.get(i).toString().substring(0, 7)+" " +allfinance.get(i).toString().subSequence(7, allfinance.get(i).toString().length()));
		}

		//end get financial list
		
		
		JPanel financialPanel = new JPanel();
		resourcesTab.addTab("Financial", null, financialPanel, null);
		
		JButton btnAddFinancial = new JButton("Add Financial Resource");
		
		JScrollPane financial_scrollPane = new JScrollPane();
		GroupLayout gl_financialPanel = new GroupLayout(financialPanel);
		gl_financialPanel.setHorizontalGroup(
			gl_financialPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_financialPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(btnAddFinancial))
				.addGroup(Alignment.LEADING, gl_financialPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(financial_scrollPane, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_financialPanel.setVerticalGroup(
			gl_financialPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_financialPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAddFinancial)
					.addGap(30)
					.addComponent(financial_scrollPane, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(30))
		);
		
		JList<String> financial_list = new JList<String>(financiallistModel);
		financial_scrollPane.setViewportView(financial_list);
		financial_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		financialPanel.setLayout(gl_financialPanel);
		btnAddFinancial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Field> financial_moduleFields = new ArrayList<Field>();
				financial_moduleFields.add(new Field("text","financename","",20,"name"));

				Form financial_moduleForm = new Form(financial_moduleFields,"Information Module Form");
				final PanelBuilder financial_modulePanel = new PanelBuilder(financial_moduleForm);
				financial_modulePanel.makeForm();
				JFrame Add_FinancialModulePage= new JFrame("Add Financial Module Form");
				Add_FinancialModulePage.getContentPane().add(financial_modulePanel.getJPanel(),BorderLayout.NORTH);
				
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
	  			    	for(int i=0; i<financial_modulePanel.getJPanel().getComponentCount(); i++){
	  			    		FieldPanel fpanel = (FieldPanel)financial_modulePanel.getJPanel().getComponent(i);
	  			    		inputs.add(fpanel.getValues().get(0));
	  			    	}
	  			    	for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i)+" financial");
						}
	  			    	financat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
	  			    	financat.readAllResources();
	  			    	
	  			    	
	  			    	allfinance.clear();
	  			    	financiallistModel.removeAllElements();
	  			    	allfinance=financat.readAllResources();
						for (int i = 0; i < allfinance.size(); i++) {
							System.out.println(allfinance.get(i));
							financiallistModel.addElement(""+allfinance.get(i).toString().substring(0, 7)+" " +allfinance.get(i).toString().subSequence(7, allfinance.get(i).toString().length()));
						}
	  			    	
						
					}
				});
			}
		});
		
		

		//get module list
		final DefaultListModel<String> modulelistModel = new DefaultListModel<String>();
		
		ModuleCatalogue mcat = new ModuleCatalogue();
		System.out.println("all : ");
		allmodules = mcat.readAllResources();

		for (int i = 0; i < allmodules.size(); i++) {
			System.out.println(allmodules.get(i));
			modulelistModel.addElement(""+allmodules.get(i).toString().substring(0, 7)+" " +allmodules.get(i).toString().subSequence(7, allmodules.get(i).toString().length()));
		}
		// end module list
		JPanel modulePanel = new JPanel();
		resourcesTab.addTab("Module", null, modulePanel, null);
				
						
						JButton btnAddModule = new JButton("Add Module");
						btnAddModule.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								ArrayList<Field> moduleFields = new ArrayList<Field>();
								moduleFields.add(new Field("text","name","",10,"name"));


								Form moduleForm = new Form(moduleFields,"Module Form");
								final PanelBuilder modulePanel = new PanelBuilder(moduleForm);
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
										ArrayList<String> inputs = new ArrayList<String>();
	  			    	for(int i=0; i<modulePanel.getJPanel().getComponentCount(); i++){
	  			    		FieldPanel fpanel = (FieldPanel)modulePanel.getJPanel().getComponent(i);
	  			    		inputs.add(fpanel.getValues().get(0));
	  			    	}
	  			    	for (int i = 0; i < inputs.size(); i++) {
											System.out.println(inputs.get(i)+"adasa");
										}
	  			    	mcat.addResource((inputs.get(0)));
										// tu resource ham bayad insert she
										mcat.readAllResources();
										allmodules.clear();
										modulelistModel.removeAllElements();
										allmodules=mcat.readAllResources();
										for (int i = 0; i < allmodules.size(); i++) {
											System.out.println(allmodules.get(i));
											modulelistModel.addElement(""+allmodules.get(i).toString().substring(0, 7)+" " +allmodules.get(i).toString().subSequence(7, allmodules.get(i).toString().length()));
										}
									}
								});
								
									
							}
						});
						
						
						
						JScrollPane module_scrollPane = new JScrollPane();
						GroupLayout gl_modulePanel_1 = new GroupLayout(modulePanel);
						gl_modulePanel_1.setHorizontalGroup(
							gl_modulePanel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_modulePanel_1.createSequentialGroup()
									.addContainerGap(677, Short.MAX_VALUE)
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
									.addComponent(btnAddModule)
									.addGap(30)
									.addComponent(module_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
									.addGap(30))
						);
						
												//end get module list
						JList<String> module_list = new JList<String>(modulelistModel);
						module_scrollPane.setViewportView(module_list);
						module_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						modulePanel.setLayout(gl_modulePanel_1);
						
						//get phys res list
						final DefaultListModel<String> physicalreslistModel = new DefaultListModel<String>();
						
						PhysicalResourceCatalogue physcat = new PhysicalResourceCatalogue();
						System.out.println("all : ");
						allphysicals = physcat.readAllResources();

						for (int i = 0; i < allphysicals.size(); i++) {
							System.out.println(allphysicals.get(i));
							physicalreslistModel.addElement(""+allphysicals.get(i).toString().substring(0, 7)+" " +allphysicals.get(i).toString().subSequence(7, allphysicals.get(i).toString().length()));
						}
						
						
		
		JPanel physicalPanel = new JPanel();
		resourcesTab.addTab("Physical", null, physicalPanel, null);
		
		JButton btnAddPhysicalResource = new JButton("Add Physical Resource");
		btnAddPhysicalResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Field> physical_moduleFields = new ArrayList<Field>();
				physical_moduleFields.add(new Field("text","physname","",20,"name"));


				Form physical_moduleForm = new Form(physical_moduleFields,"Physical Module Form");
				final PanelBuilder physical_modulePanel = new PanelBuilder(physical_moduleForm);
				physical_modulePanel.makeForm();
				JFrame Add_PhysicalModulePage= new JFrame("Add Physical Module Form");
				Add_PhysicalModulePage.getContentPane().add(physical_modulePanel.getJPanel(),BorderLayout.NORTH);
				
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
	  			    	for(int i=0; i<physical_modulePanel.getJPanel().getComponentCount(); i++){
	  			    		FieldPanel fpanel = (FieldPanel)physical_modulePanel.getJPanel().getComponent(i);
	  			    		inputs.add(fpanel.getValues().get(0));
	  			    	}
	  			    	for (int i = 0; i < inputs.size(); i++) {
							System.out.println(inputs.get(i)+" physical");
						}
	  			    	physcat.addResource((inputs.get(0)));
						// tu resource ham bayad insert she
	  			    	physcat.readAllResources();
	  			    	
	  			    	allphysicals.clear();
	  			    	physicalreslistModel.removeAllElements();
						allphysicals=physcat.readAllResources();
						for (int i = 0; i < allphysicals.size(); i++) {
							System.out.println(allphysicals.get(i));
							physicalreslistModel.addElement(""+allphysicals.get(i).toString().substring(0, 7)+" " +allphysicals.get(i).toString().subSequence(7, allphysicals.get(i).toString().length()));
						}
	  			    	

					}
				});
			}
		});
		

		JScrollPane physical_scrollPane = new JScrollPane();
		GroupLayout gl_physicalPanel = new GroupLayout(physicalPanel);
		gl_physicalPanel.setHorizontalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addContainerGap(611, Short.MAX_VALUE)
					.addComponent(btnAddPhysicalResource))
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_physicalPanel.setVerticalGroup(
			gl_physicalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAddPhysicalResource)
					.addGap(30)
					.addComponent(physical_scrollPane, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addGap(30))
		);
		
				//end get phys res list
		
				
				JList<String> physical_list = new JList<String>(physicalreslistModel);
				physical_scrollPane.setViewportView(physical_list);
				physical_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				physicalPanel.setLayout(gl_physicalPanel);

		
		
		JPanel allPanel = new JPanel();
		resourcesTab.addTab("All", null, allPanel, null);
		
		DefaultListModel<String> allreslistModel = new DefaultListModel<String>();
		//get all res list
				ResourceCatalogue rcat = new ResourceCatalogue();
				System.out.println("all : ");
				ArrayList<HashMap<String, String>> allres = rcat.readAllResources();
				
				for (int i = 0; i < allres.size(); i++) {
					System.out.println(allres.get(i));
					allreslistModel.addElement(""+allres.get(i).toString().substring(0, 7)+" "+allres.get(i).toString().subSequence(7, allres.get(i).toString().length()));
				}
		
		JScrollPane allres_scrollPane = new JScrollPane();
		GroupLayout gl_allPanel = new GroupLayout(allPanel);
		gl_allPanel.setHorizontalGroup(
			gl_allPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(allres_scrollPane, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_allPanel.setVerticalGroup(
			gl_allPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_allPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(allres_scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(30))
		);
		
		//end get res list
		
		JList<String> allres_list = new JList<String>(allreslistModel);
		allres_scrollPane.setViewportView(allres_list);
		allres_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		allPanel.setLayout(gl_allPanel);

		
		
		
		JPanel projectPanel = new JPanel();
		tabbedPane.addTab("Project Management", null, projectPanel, null);
		tabbedPane.setEnabledAt(4, true);
		
		DefaultListModel<String> projectlistModel = new DefaultListModel<String>();
		projectlistModel.addElement("hello");
		
		JButton addprojectBtn = new JButton("Add Project");
		addprojectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton projsearchBtn = new JButton("Search");
		
		JScrollPane project_scrollPane = new JScrollPane();
		GroupLayout gl_projectPanel = new GroupLayout(projectPanel);
		gl_projectPanel.setHorizontalGroup(
			gl_projectPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_projectPanel.createSequentialGroup()
					.addGap(300)
					.addComponent(projsearchBtn)
					.addPreferredGap(ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
					.addComponent(addprojectBtn))
				.addGroup(Alignment.LEADING, gl_projectPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(40))
		);
		gl_projectPanel.setVerticalGroup(
			gl_projectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_projectPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_projectPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(addprojectBtn)
						.addComponent(projsearchBtn))
					.addGap(40)
					.addComponent(project_scrollPane, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(40))
		);
		
				JList<String> projectList = new JList<String>(projectlistModel);
				project_scrollPane.setViewportView(projectList);
				projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectPanel.setLayout(gl_projectPanel);
	}
	public JFrame getUserpageFrame() {
		return userpageFrame;
	}
}
