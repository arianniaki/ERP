package GUI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.ProjectEmployee;
import ProjectEmployee.ProjectEmployeeCatalogue;
import ProjectEmployee.SubSystem.SubSystemCatalogue;
import RequirementUtilization.ProjectResourceUtilization;
import RequirementUtilization.ProjectResourceUtilizationCatalogue;
import RequirementUtilization.ResourceRequirement;
import RequirementUtilization.ResourceRequirementCatalogue;
import ResourceManagement.Section.Resource.MaintainModEmpResCatalogue;
import ResourceManagement.Section.Resource.MaintainingModule;
import ResourceManagement.Section.Resource.MaintainingModuleCatalogue;
import ResourceManagement.Section.Resource.MakeModuleCatalogue;
import ResourceManagement.Section.Resource.Resource;
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
			columns = new String[] { "Physical Id", "Name", "Model", "Description"};
			dbnames = new String[] { "rid", "physname","modeldesc","description" };
		}
		if (type == "information") {
			columns = new String[] { "Information Id", "Name","Create Date","Description" };
			dbnames = new String[] { "rid", "irname","createdate","description" };
		}
		if (type == "financial") {
			columns = new String[] { "Financial Id", "Name","Value","Model","Description" };
			dbnames = new String[] { "rid", "finanname","netvalue","modeldesc","description" };
		}
		if (type == "module") {
			columns = new String[] { "Module Id", "Name","Duration","Description" };
			dbnames = new String[] { "modrid", "modname","duration","description" };
		}
		if(type=="all")
		{
			columns = new String[] { "Resource Id", "Name" };
			dbnames = new String[] { "rid", "rname" };
		}

		this.buildFilledJTable();

	}

	public TableData(EmployeeCatalogue empcat, String type) {
		data = empcat.getConfirmedEmployees();
		if (type == "assign") {
			columns = new String[] { "Id", "Name","Username", "AccessRight", "Access Right" };
			dbnames = new String[] { "empid", "empname","username" ,"accessrightid" ,"accessrightname"};
		}

		if (type == "registered") {
			columns = new String[] { "Id", "Name", };
			dbnames = new String[] { "empid", "empname" };
			data = empcat.getRegistrations();
		}
		if(type=="human")
		{
			columns = new String[] { "Id", "Name","Post","Access Right" ,"Section Id","Section"};
			dbnames = new String[] { "empid", "empname","post" ,"accessrightname","sid","sectionname"};
		}
		this.buildFilledJTable();
	}

	public TableData(ProjectCatalogue projcat) {
		data = projcat.getProjects();
		columns = new String[] { "Id", "Project", "Project Manager","Manager Name","Size", "Technology","Complete" };
		dbnames = new String[] { "projid", "projname", "projectmanager","managername","size","tech","is_complete" };
		this.buildFilledJTable();

	}
	
	public TableData(SubSystemCatalogue subsyscat) {
		data = subsyscat.getSubSystems();
		columns = new String[] { "Subsystem Id","Project Id", "Subsystem","Section Id","Section"};
		dbnames = new String[] { "subid","pid", "sname","sid","sectionname"};
		this.buildFilledJTable();

	}
	public TableData(ProjectResourceUtilizationCatalogue presutilcat, int projectid) {
		
		data= new ArrayList<HashMap<String, String>>();
		columns = new String[] { "presutilid","Resource Id","Resource", "Section Id", "Section","Project Id","Project","From","To" };
		dbnames = new String[] { "presutilid","rid","resourceName", "sid", "sectionName","pid","projectName","fromdate","todate" };
		ArrayList<ProjectResourceUtilization> allpresutil;
		allpresutil = presutilcat.getProjectResourceUtilizationbyProject(projectid);
		for (int i = 0; i < allpresutil.size(); i++) {
			data.add((allpresutil.get(i).toHashMap()));
		}
		this.buildFilledJTable();
		

	}


	public TableData(MaintainingModuleCatalogue mainmodcat,int moduleid) {
		data = new ArrayList<HashMap<String, String>>();
		columns = new String[] { "Maintain Id", "Module Id", "Duration","Change Type" };
		dbnames = new String[] { "maintainid", "modrid", "duration","changetype" };
		ArrayList<MaintainingModule> allmaintainmod;
		allmaintainmod = mainmodcat.getMaintainingModules(moduleid);
		for (int i = 0; i < allmaintainmod.size(); i++) {
			data.add((allmaintainmod.get(i).toHashMap()));
		}
		this.buildFilledJTable();
	}
	
	public TableData(ResourceRequirementCatalogue resreqcat) {
		data= new ArrayList<HashMap<String, String>>();
		columns = new String[] { "resReqid","Resource ID", "Resource", "Section Id","Section", "Project Id","Project","From","To","Satisfied","Satisfy Date" };
		dbnames = new String[] {  "resReqid","rid", "resourceName", "sid","sectionName", "pid","projectName","fromdate","todate","is_satisfied","satisfydate" };
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
			columns = new String[] { "Resource Id","Resource" ,"Section Id", "Section","Project Id","Project","From","To", "Satisfy Date" };
			dbnames = new String[] { "rid","rname" ,"sid", "sname","pid","pname","fromdate","todate", "satisfydate" };
		}
		if(type=="cycle report")
		{
			columns = new String[] {"presutilid", "Resource Id","Resource", "Section Id","Section","Project Id","Project", "From", "To"};
			dbnames = new String[] {"presutilid", "rid","rname", "sname","sid","pid","pname", "fromdate", "todate"};
		}
		if(type=="employee cycle report")
		{
			columns = new String[] {"Employee Id", "Project Id","Project","Employee", "From", "To"};
			dbnames = new String[] {"empid", "projid","pname", "empname","fromdate", "todate"};
		}
		if(type=="resavail report")
		{
			columns = new String[] {"Section Id","Section", "Count", "Resource"};
			dbnames = new String[] { "sid","sname", "count", "physname"};

		}

		
		this.buildFilledJTable();

	}
	public TableData(MakeModuleCatalogue makemodulecat, String type) {
		data = new ArrayList<HashMap<String, String>>();
		if(type.equals("Employee")){
			columns = new String[] { "Employee Id","Employee"};
			dbnames = new String[] { "empid","empname"};	
		}
		if(type.equals("Resource"))
		{
			columns = new String[] { "Resource Id","Resource"};
			dbnames = new String[] { "rid", "rname"};
		}
		this.buildFilledJTable();
	}

	public TableData(MaintainModEmpResCatalogue maintainModEmpResCatalogue, String type) {
		// TODO Auto-generated constructor stub
		data = new ArrayList<HashMap<String, String>>();
		if(type.equals("Employee")){
			columns = new String[] { "Employee Id","Employee"};
			dbnames = new String[] { "empid","empname"};	
		}
		if(type.equals("Resource"))
		{
			columns = new String[] { "Resource Id","Resource"};
			dbnames = new String[] { "rid", "rname"};
		}
		this.buildFilledJTable();
	}

	public TableData(ProjectEmployeeCatalogue projempcat,int projectid) {
		// TODO Auto-generated constructor stub
		data= new ArrayList<HashMap<String, String>>();
		columns = new String[] { "projempid","Employee Id","Employee Name","Project Id","Project","From","To" };
		dbnames = new String[] { "projempid","empid","empname", "pid","projectname","fromdate","todate" };
		ArrayList<ProjectEmployee> allpresutil;
		allpresutil = projempcat.getProjectEmployeesByProject(projectid);
		for (int i = 0; i < allpresutil.size(); i++) {
			data.add((allpresutil.get(i).toHashMap()));
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
