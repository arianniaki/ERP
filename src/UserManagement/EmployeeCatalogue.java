package UserManagement;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import Resource.ResourceCatalogue;

public class EmployeeCatalogue{
	DataBase DB;
	
	public EmployeeCatalogue() {
		DB = new DataBase();
	}

	public ArrayList<HashMap<String, String>> readAllEmployees() {
		Table table = new Table("employee");
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public void deleteEmployee(int id) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(id));
		DB.delete(vars, "employee");
	}

	public void addEmployee(int empid, boolean ismodir, String empname, String post, int sectionId) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(empid));
		vars.put("empname", "\'" + empname + "\'");
		vars.put("sectionid", Integer.toString(sectionId));
		vars.put("post", "\'" + post +"\'");
		vars.put("ismodir", Boolean.toString(ismodir));
		
		DB.insert(vars, "employee");
	}


}