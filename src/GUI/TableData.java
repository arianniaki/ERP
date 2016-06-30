package GUI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.ProjectCatalogue;
import RequirementUtilization.ResourceRequirement;
import RequirementUtilization.ResourceRequirementCatalogue;
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
			columns = new String[] { "Id", "Name" };
			dbnames = new String[] { "rid", "physname" };
		}
		if (type == "information") {
			columns = new String[] { "Id", "Name" };
			dbnames = new String[] { "rid", "irname" };
		}
		if (type == "financial") {
			columns = new String[] { "Id", "Name" };
			dbnames = new String[] { "rid", "finanname" };
		}
		if (type == "module") {
			columns = new String[] { "Id", "Name" };
			dbnames = new String[] { "rid", "modname" };
		}
		if(type=="all")
		{
			columns = new String[] { "Id", "Name", };
			dbnames = new String[] { "rid", "rname" };
		}

		this.buildFilledJTable();

	}

	public TableData(EmployeeCatalogue empcat, String type) {
		data = empcat.readAllEmployees();
		if (type == "assign") {
			columns = new String[] { "Id", "Username", "AccessRight" };
			dbnames = new String[] { "empid", "empname", "accessrightid" };
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
		columns = new String[] { "Id", "Name", "Project Manager","managername" };
		dbnames = new String[] { "projid", "projname", "projectmanager","managername" };
		this.buildFilledJTable();

	}

	public TableData(MaintainingModuleCatalogue mainmodcat) {
//		data = mainmodcat.
//		columns = new String[] { "Id", "Name", "Project Manager" };
//		dbnames = new String[] { "projid", "projname", "projectmanager" };
//		this.buildFilledJTable();

	}
	public TableData(ResourceRequirementCatalogue resreqcat) {
		data= new ArrayList<HashMap<String, String>>();
		columns = new String[] { "rid", "sid", "pid","fromdate","todate" };
		dbnames = new String[] { "rid", "sid", "pid","fromdate","todate"};
		ArrayList<ResourceRequirement> allresourcerequirements;
		allresourcerequirements = resreqcat.getResourceRequirements();
		for (int i = 0; i < allresourcerequirements.size(); i++) {
			data.add((allresourcerequirements.get(i).toHashMap()));
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

}
