package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.table.DefaultTableModel;

import GUI.Form.Field;
import GUI.Form.FieldPanel;
import GUI.Form.PanelBuilder;
import GUI.Form.TextForm;
import GUI.Form.Form;
import ResourceManagement.Section.Resource.FinancialResourceCatalogue;
import ResourceManagement.Section.Resource.InformationResourceCatalogue;
import ResourceManagement.Section.Resource.ModuleCatalogue;
import ResourceManagement.Section.Resource.PhysicalResourceCatalogue;
import ResourceManagement.Section.Resource.ResourceCatalogue;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
public class UserPage extends JFrame {
    protected JButton addmodulebtn, addresourcebtn,logout,displaymodulebtn,displayemployees,displayresources;
    // Constructor sets features of the frame, creates buttons, adds them to the frame, and
    // assigns an object to listen to them
    
    public UserPage(final Employee emp) {
        super("Users Page"); // set title bar
        final JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.setBorder( new TitledBorder("BorderLayout(5,5)") );

        JPanel dynamicLabels = new JPanel(new BorderLayout(4,5));
        dynamicLabels.setBorder(
            new TitledBorder("BorderLayout(4,4)") );
        gui.add(dynamicLabels, BorderLayout.WEST);

        super.setContentPane(gui);

        super.pack();
        
        super.validate();

        
        
        
        final JPanel buttons = new JPanel(new GridLayout(0,1,0,0));
        buttons.setBorder(
            new TitledBorder("GridLayout(0,2,3,4)") );
        dynamicLabels.add(buttons, BorderLayout.NORTH);

        
        
        setSize(500,500); // sets the size of the window
        addmodulebtn = new JButton("Add Module"); // create two new buttons w/labels start and stop
        addresourcebtn = new JButton("Add Resource");
        displaymodulebtn = new JButton("Display Modules");
        displayemployees = new JButton("Display Employees");
        displayresources = new JButton("Display Resources");
        
        logout = new JButton("Logout");
        buttons.add( addmodulebtn);
        buttons.add( addresourcebtn);
        buttons.add( logout);
        buttons.add( displaymodulebtn);
        buttons.add( displayemployees);
        buttons.add( displayresources);


        super.validate();
      
        final JFrame ine = this;
        ine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    
//        Container contentPane = getContentPane();
//        contentPane.setLayout(new FlowLayout()); // layout objects from left to right
                                                 // until fill up row and then go to next row
//        contentPane.add(addmodulebtn);
//        contentPane.add(addresourcebtn);
//        contentPane.add(logout);
        
        

        String[] header = {"Name", "Value"};
//        String[] a = new String[0];
//        String[] names = System.getProperties().
//            stringPropertyNames().toArray(a);
        String[][] data = new String[2][2];
        
//        for (int ii=0; ii<names.length; ii++) {
//            data[ii][0] = names[ii];
//            data[ii][1] = System.getProperty(names[ii]);
//        }
        final DefaultTableModel model = new DefaultTableModel(data, header);
        JTable table = new JTable(model);
        try {
            // 1.6+
            table.setAutoCreateRowSorter(true);
        } catch(Exception continuewithNoSort) {
        }
        JScrollPane tableScroll = new JScrollPane(table);
        Dimension tablePreferred = tableScroll.getPreferredSize();
        tableScroll.setPreferredSize(
            new Dimension(tablePreferred.width, tablePreferred.height/3) );

//        JPanel imagePanel = new JPanel(new GridBagLayout());
//        imagePanel.setBorder(
//            new TitledBorder("GridBagLayout()") );

//        BufferedImage bi = new BufferedImage(
//            200,200,BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g = bi.createGraphics();
//        GradientPaint gp = new GradientPaint(
//            20f,20f,Color.red, 180f,180f,Color.yellow);
//        g.setPaint(gp);
//        g.fillRect(0,0,200,200);
//        ImageIcon ii = new ImageIcon(bi);
//        JLabel imageLabel = new JLabel(ii);
//        imagePanel.add( imageLabel, null );

        JSplitPane splitPane = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            tableScroll,
            new JScrollPane());
        gui.add( splitPane, BorderLayout.CENTER );

        ine.setContentPane(gui);

        ine.pack();

        ine.setLocationRelativeTo(null);
        try {
            // 1.6+
            ine.setLocationByPlatform(true);
            ine.setMinimumSize(ine.getSize());
        } catch(Throwable ignoreAndContinue) {
        }

displaymodulebtn.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuleCatalogue mcat = new ModuleCatalogue();
		System.out.println("all : ");
		ArrayList<HashMap<String, String>> allmodules = mcat.readAllResources();
		if (model.getRowCount() > 0) {
		    for (int i = model.getRowCount() - 1; i > -1; i--) {
		    	model.removeRow(i);
		    }
		}
		for (int i = 0; i < allmodules.size(); i++) {
			System.out.println(allmodules.get(i));
			model.addRow(new Object[] { allmodules.get(i).toString().substring(0, 7), allmodules.get(i).toString().subSequence(7, allmodules.get(i).toString().length())});
		}
		model.setColumnIdentifiers(new Object[]{"rid","resource"});
	}
});

displayresources.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ResourceCatalogue rcat = new ResourceCatalogue();
		System.out.println("all : ");
		ArrayList<HashMap<String, String>> allres = rcat.readAllResources();
		if (model.getRowCount() > 0) {
		    for (int i = model.getRowCount() - 1; i > -1; i--) {
		    	model.removeRow(i);
		    }
		}
		for (int i = 0; i < allres.size(); i++) {
			System.out.println(allres.get(i));
			model.addRow(new Object[] { allres.get(i).toString().substring(0, 7), allres.get(i).toString().subSequence(7, allres.get(i).toString().length())});
		}
		model.setColumnIdentifiers(new Object[]{"rid","resource"});
	}
	
});

displayemployees.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
		System.out.println("all : ");
		ArrayList<HashMap<String, String>> allemps = empcat.readAllEmployees();
		if (model.getRowCount() > 0) {
		    for (int i = model.getRowCount() - 1; i > -1; i--) {
		    	model.removeRow(i);
		    }
		}
		for (int i = 0; i < allemps.size(); i++) {
			System.out.println(allemps.get(i));
			model.addRow(new Object[] { allemps.get(i).toString().substring(0, 9), allemps.get(i).toString().subSequence(7, allemps.get(i).toString().length())});
		}
		model.setColumnIdentifiers(new Object[]{"empid","employee"});
	}
});
        
        // create an object to listen to both buttons:
        addmodulebtn.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println("clicked add module");
		        final JFrame fmodule = new JFrame("Add Module pop up");
			      String[] labels = { "Module Name","Module id","Resource id" };
			      int[] widths = { 15, 15,15};
			      	ArrayList<String> options = new ArrayList<String>();
			      	options.add("iphone");
			      	options.add("macbook");
			      	options.add("imac");
			      	ArrayList<Field> myfields = new ArrayList<Field>();
			      	Field field1 = new Field("text", "name       ", "Arian", 10, "name");
			      	Field field2 = new Field("text", "family     ", "Niaki", 10, "family");
			      	Field field3 = new Field("text", "affiliation", "sharif", 10, "affiliation");
			      	Field field4 = new Field("comboBox", "items", options , 10, "items");
			      	Field field5 = new Field("text", "work", "sharif", 10, "work");
			      	Field field6 = new Field("text", "phone", "sharif", 10, "phone");
			      	Field field7 = new Field("text", "address", "sharif", 10, "address");
			      	Field field8 = new Field("checkBox", "items", options , 10, "items");
			      	
			      	
			      	myfields.add(field1);
			      	myfields.add(field2);
			      	myfields.add(field3);
			      	myfields.add(field4);
			      	myfields.add(field5);
			      	myfields.add(field6);
			      	myfields.add(field7);
			      	myfields.add(field8);
			      	Form myForm = new Form(myfields, "Arian Info");
			      	final PanelBuilder myPanel = new PanelBuilder(myForm);
			      	myPanel.makeForm();
			      	
	    		    final TextForm moduleform = new TextForm(labels,widths);
	    		    JButton submitmodule = new JButton("Submit Module");

//	    		    fresource.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    		    fmodule.getContentPane().add(myPanel.getJPanel(), BorderLayout.NORTH);
	    		    JPanel p = new JPanel();
	    		    p.add(submitmodule);
	    		    fmodule.getContentPane().add(p, BorderLayout.SOUTH);
	    		    fmodule.pack();
	    		    fmodule.setVisible(true);
//	    			ine.dispose();
	    		    
	    			submitmodule.addActionListener(new ActionListener() {
	  			      public void actionPerformed(ActionEvent e) {
//	  			    	for(int i=0; i<myPanel.getJPanel().getComponentCount(); i++){
//	  			    		FieldPanel fpanel = (FieldPanel)myPanel.getJPanel().getComponent(i);
//	  			    	}
	  			        
	  					/*ModuleCatalogue mcat = new ModuleCatalogue();
	  					System.out.println("all : ");
	  					mcat.readAllResources();
	  			        
	  					mcat.addResource(Integer.parseInt(moduleform.getText(2)),Integer.parseInt(moduleform.getText(1)),moduleform.getText(0));
	  					mcat.readAllResources();*/
	  			      }
	  			    });
		        
		      }});
        addresourcebtn.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
			        final JFrame fresource = new JFrame("Add Resource pop up");

		    	   final JPanel guires = new JPanel(new BorderLayout(5,5));
		    	   guires.setBorder( new TitledBorder("BorderLayout(5,5)") );
		           fresource.setContentPane(guires);

		           
		           JPanel resourceLables = new JPanel(new BorderLayout(4,5));
		           resourceLables.setBorder(
		               new TitledBorder("BorderLayout(4,4)") );
		           guires.add(resourceLables, BorderLayout.WEST);


//		           final JPanel resourcePanel = new JPanel(new GridLayout(0,1,0,0));
//		           resourcePanel.setBorder(
//		               new TitledBorder("Res") );
//		           resourceLables.add(resourcePanel, BorderLayout.NORTH);
		           
		           final JPanel InforesourcePanel = new JPanel(new GridLayout(0,1,0,0));
		           InforesourcePanel.setBorder(
		               new TitledBorder("Info") );
		           resourceLables.add(InforesourcePanel, BorderLayout.NORTH);
		           
		           
		           JPanel PhysresourceLables = new JPanel(new BorderLayout(4,5));
		           PhysresourceLables.setBorder(
		               new TitledBorder("BorderLayout(4,4)") );
		           guires.add(PhysresourceLables, BorderLayout.EAST);

		           final JPanel PhysresourcePanel = new JPanel(new GridLayout(0,1,0,0));
		           PhysresourcePanel.setBorder(
		               new TitledBorder("Phys") );
		           PhysresourceLables.add(PhysresourcePanel, BorderLayout.NORTH);
		           
		           

		           final JPanel FinanresourceLables = new JPanel(new GridLayout(0,1,0,0));
		           FinanresourceLables.setBorder(
		               new TitledBorder("Finan") );
		           PhysresourceLables.add(FinanresourceLables, BorderLayout.SOUTH);
		           fresource.pack();
		           
		           fresource.validate();
		           
		           fresource.pack();
		           
		           fresource.validate();
		    	  
		    	  
		        System.out.println("clicked add resource");
		        String[] labels = { "Resource Name","Resource ID" };
		        String[] physlabels = { "PhysResource Name","Resource ID" };
		        String[] infolabels = { "InfoResource Name","Resource ID" };
		        String[] finanlabels = { "FinanResource Name","Resource ID" };


		        int[] widths = { 15, 15};

    		    final TextForm resourceform = new TextForm(labels,widths);
    		    final TextForm physresform = new TextForm(physlabels,widths);
    		    final TextForm infoform = new TextForm(infolabels,widths);
    		    final TextForm finanform = new TextForm(finanlabels,widths);


//    		    resourcePanel.add(resourceform,BorderLayout.NORTH);
    		    InforesourcePanel.add(infoform,BorderLayout.NORTH);

    		    PhysresourcePanel.add(physresform,BorderLayout.NORTH);
    		    FinanresourceLables.add(finanform,BorderLayout.NORTH);

    		    
    		    JButton submitres = new JButton("Submit Resource");
//    		    fresource.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    		    JPanel submitresbtnPanel = new JPanel();
    		    submitresbtnPanel.add(submitres);
//    		    resourcePanel.add(submitresbtnPanel,BorderLayout.SOUTH);
    		    
    		    
    		    JButton submitphysres = new JButton("Submit Physical Resource");
//    		    fresource.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    		    JPanel submitPhysresbtnPanel = new JPanel();
    		    submitPhysresbtnPanel.add(submitphysres);
    		    PhysresourcePanel.add(submitPhysresbtnPanel,BorderLayout.SOUTH);

    		    
    		    JButton submitinfores = new JButton("Submit Info Resource");
//    		    fresource.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    		    JPanel submitInforesbtnPanel = new JPanel();
    		    submitInforesbtnPanel.add(submitinfores);
    		    InforesourcePanel.add(submitInforesbtnPanel,BorderLayout.SOUTH);
    		    
    		    JButton submitfinanres = new JButton("Submit Finan Resource");
//    		    fresource.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    		    JPanel submitFinanresbtnPanel = new JPanel();
    		    submitFinanresbtnPanel.add(submitfinanres);
    		    FinanresourceLables.add(submitFinanresbtnPanel,BorderLayout.SOUTH);
    		    
    		    
//    		    fresource.getContentPane().add(resourceform, BorderLayout.NORTH);
//    		    fresource.getContentPane().add(p, BorderLayout.SOUTH);
    		    fresource.pack();
    		    fresource.setVisible(true);
//    			ine.dispose();
    		    
    		         

    		    
    			submitres.addActionListener(new ActionListener() {
  			      public void actionPerformed(ActionEvent e) {
  			        System.out.println(resourceform.getText(0));
  					ResourceCatalogue rscat = new ResourceCatalogue();
  					rscat = new ResourceCatalogue();
//  					rscat.addResource(resourceform.getText(0));
  					rscat.readAllResources();
  			      }
  			    });
    			
    			submitphysres.addActionListener(new ActionListener() {
    			      public void actionPerformed(ActionEvent e) {
    			        System.out.println(physresform.getText(0));
    					PhysicalResourceCatalogue physrscat = new PhysicalResourceCatalogue();
    					
//    					physrscat.addResource(Integer.parseInt(physresform.getText(1)),2,physresform.getText(0));
    			      }
    			    });
    			
    			submitinfores.addActionListener(new ActionListener() {
  			      public void actionPerformed(ActionEvent e) {
  			        System.out.println(infoform.getText(0));
					InformationResourceCatalogue inforscat = new InformationResourceCatalogue();
					
//					inforscat.addResource(Integer.parseInt(infoform.getText(1)),2,infoform.getText(0));

//  					ResourceCatalogue rscat = new ResourceCatalogue();
//  					rscat = new ResourceCatalogue();
//  					rscat.addResource(resourceform.getText(0));
//  					rscat.readAllResources();
  			      }
  			    });
    			
    			submitfinanres.addActionListener(new ActionListener() {
    			      public void actionPerformed(ActionEvent e) {
    			        System.out.println(finanform.getText(0));
    			        
    			        FinancialResourceCatalogue finanrscat = new FinancialResourceCatalogue();
    					
//    			        finanrscat.addResource(Integer.parseInt(finanform.getText(1)),2,finanform.getText(0));
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