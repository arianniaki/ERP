package ProjectEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;

public class ProjectCatalogue {

	DataBase DB;
	String tableName;

	public ProjectCatalogue() {
		DB = new DataBase();
		tableName = "project";
	}

	public ArrayList<HashMap<String, String>> getProjects() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}
	
	public long addProject(String name, Employee manager) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projname", "\'"+name+"\'");
		vars.put("projectmanager", "\'"+manager.getId()+"\'");

		long pk=DB.insert(vars, "project");
		System.out.println("inserted into project table: " + pk);
		return pk;
	}
	
	public void deleteProject(int id) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projid", Integer.toString(id));
		DB.delete(vars, "project");
	}
	
	public ArrayList<Project> searchProjects(String size, String tech){
		ArrayList<Project> result = new ArrayList<Project>();
//		
//		HashMap<String, String> vars = new HashMap<String, String>();
//		vars.put("size", "\'"+size+"\'");
//		vars.put("tech", "\'"+tech+"\'");
//		System.out.println(vars.toString());
//		ResultSet res = DB.select(vars, "Employee");
//
//		try {
//			if(!res.wasNull()){
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return result;
	}

	

	public ArrayList<Project> searchProjects(String resourceType){
		ArrayList<Project> result = new ArrayList<Project>();
		return result;
	}

}
