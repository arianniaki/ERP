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
	
	public Project getProject(int pid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projid", Integer.toString(pid));
		ResultSet res = DB.select("project",vars, null);
		EmployeeCatalogue empcat = new EmployeeCatalogue();
		Project proj = null;
		try {
			if (res.next()) {
				Employee manager = empcat.getEmployee(res.getInt("projectmanager"));
				proj = new Project(res.getString("projname"), null, res.getString("size"), manager, res.getString("tech"));
				proj.setId(pid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proj;

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
	
	public ArrayList<HashMap<String, String>>  searchProjects(String size, String tech){
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("size", "\'"+size+"\'");
		vars.put("tech", "\'"+tech+"\'");
		System.out.println(vars.toString());
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;

	}

	
	public ArrayList<Project> searchProjects(String resourceType){
		ArrayList<Project> result = new ArrayList<Project>();
		return result;
	}

}
