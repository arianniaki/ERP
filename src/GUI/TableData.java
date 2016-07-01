package GUI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.SubSystem.SubSystemCatalogue;
import RequirementUtilization.ProjectResourceUtilization;
import RequirementUtilization.ProjectResourceUtilizationCatalogue;
import RequirementUtilization.ResourceRequirement;
import RequirementUtilization.ResourceRequirementCatalogue;
import ResourceManagement.Section.Resource.MaintainingModule;
import ResourceManagement.Section.Resource.MaintainingModuleCatalogue;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class TableData {
	ArrayList<HashMap<String, String>> data;
	String[] columns;
	String[] dbnames;
	DefaultTableModel dataTableModel;
	JTable jdataTable;

	public JTable getJdataTable() {
		return jdataTable;
	}

	public void setJdataTable(JTable jdataTable) {
		this.jdataTable = jdataTable;
	}

	public DefaultTableModel getDataTableModel() {
		return dataTableModel;
	}

	public void setDataTableModel(DefaultTableModel dataTableModel) {
		this.dataTableModel = dataTableModel;
	}

	public ArrayList<HashMap<String, String>> getData() {
		return data;
	}

	public void setData(ArrayList<HashMap<String, String>> data) {
		this.data = data;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public TableData(ResourceCatalogue rescat, String type) {
		data = rescat.readAllResources();
		if (type == "physical") {
			columns = new String[] { "Id", "Name", "Model", "Description"};
			dbnames = new String[] { "rid", "physname","modeldesc","description" };
		}
		if (type == "information") {
			columns = new String[] { "Id", "Name","Create Date","Description" };
			dbnames = new String[] { "rid", "irname","createdate","description" };
		}
		if (type == "financial") {
			columns = new String[] { "Id", "Name","Value","Model","Description" };
			dbnames = new String[] { "rid", "finanname","netvalue","modeldesc","description" };
		}
		if (type == "module") {
			columns = new String[] { "Id", "Name","Duration","Description" };
			dbnames = new String[] { "modrid", "modname","duration","description" };
		}
		if(type=="all")
		{
			columns = new String[] { "Id", "Name" };
			dbnames = new String[] { "rid", "rname" };
		}

		this.buildFilledJTable();

	}

	public TableData(EmployeeCatalogue empcat, String type) {
		data = empcat.readAllEmployees();
		if (type == "assign") {
			columns = new String[] { "Id", "Username", "AccessRight", "Access Right" };
			dbnames = new String[] { "empid", "empname", "accessrightid" ,"accessrightname"};
		}

		if (type == "registered") {
			columns = new String[] { "Id", "Name", };
			dbnames = new String[] { "empid", "empname" };
			data = empcat.getRegistrations();
		}
		if(type=="human")
		{
			columns = new String[] { "Id", "Name", };
			dbnames = new String[] { "empid", "empname" };
		}
		this.buildFilledJTable();
	}

	public TableData(ProjectCatalogue projcat) {
		data = projcat.getProjects();
		columns = new String[] { "Id", "Name", "Project Manager","Manager Name","Size", "Technology","is complete" };
		dbnames = new String[] { "projid", "projname", "projectmanager","managername","size","tech","is_complete" };
		this.buildFilledJTable();

	}
	
	public TableData(SubSystemCatalogue subsyscat) {
		data = subsyscat.getSubSystems();
		columns = new String[] { "Subsystem Id","Project Id", "Name"};
		dbnames = new String[] { "sid","pid", "sname"};
		this.buildFilledJTable();

	}
	public TableData(ProjectResourceUtilizationCatalogue presutilcat) {
		
		data= new ArrayList<HashMap<String, String>>();
		columns = new String[] { "presutilid","rid", "sid", "pid","fromdate","todate" };
		dbnames = new String[] { "presutilid","rid", "sid", "pid","fromdate","todate"};
		ArrayList<ProjectResourceUtilization> allpresutil;
		allpresutil = presutilcat.getProjectResourceUtilizations();
		for (int i = 0; i < allpresutil.size(); i++) {
			data.add((allpresutil.get(i).toHashMap()));
		}
		this.buildFilledJTable();
		

	}


	public TableData(MaintainingModuleCatalogue mainmodcat,int moduleid) {
		data = new ArrayList<HashMap<String, String>>();
		columns = new String[] { "maintainid", "modrid", "duration","changetype" };
		dbnames = new String[] { "maintainid", "modrid", "duration","changetype" };
		ArrayList<MaintainingModule> allmaintainmod;
		allmaintainmod = mainmodcat.getMaintainingModules(moduleid);
		for (int i = 0; i < allmaintainmod.size(); i++) {
			data.add((allmaintainmod.get(i).toHashMap()));
		}
		this.buildFilledJTable();

		
		this.buildFilledJTable();

	}
	public TableData(ResourceRequirementCatalogue resreqcat) {
		data= new ArrayList<HashMap<String, String>>();
		columns = new String[] { "resReqid","rid", "sid", "pid","fromdate","todate","projectName","sectionName","resourceName","is_satisfied" };
		dbnames = new String[] { "resReqid","rid", "sid", "pid","fromdate","todate","projectName","sectionName","resourceName","is_satisfied"};
		ArrayList<ResourceRequirement> allresourcerequirements;
		allresourcerequirements = resreqcat.getResourceRequirements();
		for (int i = 0; i < allresourcerequirements.size(); i++) {
			data.add((allresourcerequirements.get(i).toHashMap()));
		}
		this.buildFilledJTable();

	}
	
	public TableData(ArrayList<HashMap<String, String>> report, String type) {
		data= report;
		if(type=="req report")
		{
			columns = new String[] { "rid","rname" ,"sid", "sname","pid","pname","fromdate","todate", "satisfydate" };
			dbnames = new String[] { "rid","rname" ,"sid", "sname","pid","pname","fromdate","todate", "satisfydate" };
		}
		if(type=="cycle report")
		{
			columns = new String[] {"sid", "presutilid", "rid", "pname", "fromdate", "rname", "todate", "pid", "sname"};
			dbnames = new String[] { "sid", "presutilid", "rid", "pname", "fromdate", "rname", "todate", "pid", "sname"};
		}
		if(type=="resavail report")
		{
			columns = new String[] {"sid", "count", "resource name"};
			dbnames = new String[] { "sid", "count", "physname"};

		}

		
		this.buildFilledJTable();

	}
	public JTable buildFilledJTable() {

		dataTableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		jdataTable = new JTable(dataTableModel);
		for (int i = 0; i < data.size(); i++) {
			Object[] objs = new Object[dbnames.length];
			for (int j = 0; j < dbnames.length; j++) {
				objs[j] = data.get(i).get(dbnames[j]);
			}
			dataTableModel.addRow(objs);

		}
		return jdataTable;
	}
	public void update(ArrayList<HashMap<String, String>> inputData) {
		// TODO Auto-generated method stub
		data = inputData;

		int rowcount = dataTableModel.getRowCount();
		for (int j = rowcount - 1; j >= 0; j--) {
			dataTableModel.removeRow(j);
		}
		for (int i = 0; i < data.size(); i++) {
			Object[] objs = new Object[dbnames.length];
			for (int j = 0; j < dbnames.length; j++) {
				objs[j] = data.get(i).get(dbnames[j]);
			}
			dataTableModel.addRow(objs);
		}

	}
	
	
	public void update(ArrayList<HashMap<String, String>> inputData	,String[] dbnames) {
		// TODO Auto-generated method stub
		data = inputData;

		int rowcount = dataTableModel.getRowCount();
		for (int j = rowcount - 1; j >= 0; j--) {
			dataTableModel.removeRow(j);
		}
		for (int i = 0; i < data.size(); i++) {
			Object[] objs = new Object[dbnames.length];
			for (int j = 0; j < dbnames.length; j++) {
				objs[j] = data.get(i).get(dbnames[j]);
			}
			dataTableModel.addRow(objs);
		}

	}

}
