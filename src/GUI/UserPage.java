package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.table.DefaultTableModel;

import Resource.ResourceCatalogue;
import UserManagement.Employee;
public class UserPage extends JFrame {
    protected JButton addmodulebtn, addresourcebtn,logout,displayresourcebtn;
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
        displayresourcebtn = new JButton("Display Resource");

        logout = new JButton("Logout");
        buttons.add( addmodulebtn);
        buttons.add( addresourcebtn);
        buttons.add( logout);
        buttons.add( displayresourcebtn);

        super.validate();
      
        final JFrame ine = this;
//        Container contentPane = getContentPane();
//        contentPane.setLayout(new FlowLayout()); // layout objects from left to right
                                                 // until fill up row and then go to next row
//        contentPane.add(addmodulebtn);
//        contentPane.add(addresourcebtn);
//        contentPane.add(logout);
        
        

        String[] header = {"Name", "Value"};
        String[] a = new String[0];
        String[] names = System.getProperties().
            stringPropertyNames().toArray(a);
        String[][] data = new String[names.length][2];
        for (int ii=0; ii<names.length; ii++) {
            data[ii][0] = names[ii];
            data[ii][1] = System.getProperty(names[ii]);
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
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

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBorder(
            new TitledBorder("GridBagLayout()") );

        BufferedImage bi = new BufferedImage(
            200,200,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        GradientPaint gp = new GradientPaint(
            20f,20f,Color.red, 180f,180f,Color.yellow);
        g.setPaint(gp);
        g.fillRect(0,0,200,200);
        ImageIcon ii = new ImageIcon(bi);
        JLabel imageLabel = new JLabel(ii);
        imagePanel.add( imageLabel, null );

        JSplitPane splitPane = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            tableScroll,
            new JScrollPane(imagePanel));
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

        
        
        // create an object to listen to both buttons:
        addmodulebtn.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println("clicked add module");
		        final JFrame fmodule = new JFrame("Add Module pop up");
			      String[] labels = { "Module Name" };
			      int[] widths = { 15, 15};

	    		    final TextForm moduleform = new TextForm(labels,widths);
	    		    JButton submitres = new JButton("Submit Module");

//	    		    fresource.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		    fmodule.getContentPane().add(moduleform, BorderLayout.NORTH);
	    		    JPanel p = new JPanel();
	    		    p.add(submitres);
	    		    fmodule.getContentPane().add(p, BorderLayout.SOUTH);
	    		    fmodule.pack();
	    		    fmodule.setVisible(true);
//	    			ine.dispose();
	    		    
	    			submitres.addActionListener(new ActionListener() {
	  			      public void actionPerformed(ActionEvent e) {
	  			        System.out.println(moduleform.getText(0));
	  					ResourceCatalogue rscat = new ResourceCatalogue();
	  					rscat = new ResourceCatalogue();
	  					rscat.addResource(moduleform.getText(0));
	  					rscat.readAllResources();
	  			      }
	  			    });
		        
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