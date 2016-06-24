package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.Column;
import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;

public class ResourceCatalogue {
	DataBase DB;
	String tableName;

	public ResourceCatalogue() {
		DB = new DataBase();
		tableName = "resource";
	}

	public ArrayList<HashMap<String, String>> readAllResources() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public Resource getResource (int rid){
		Resource res = new Resource();		
		res.getFromDB(rid);
		return res;
	}
	public long addResource(String name) {
		HashMap<String, String> vars = new HashMap<String, String>();
//		vars.put("rid", Integer.toString(rid));
		vars.put("rname", "\'"+name+"\'");
		long pk=DB.insert(vars, "resource");
		System.out.println(pk+" return //////");
		return pk;
	}

	public void deleteResource(int id) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(id));
		DB.delete(vars, "resource");
	}

	public ArrayList<HashMap<String, String>> SearchResource(HashMap<String, String> searchvars){
		
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.search(searchvars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;		
	}
}