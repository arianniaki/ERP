package ProjectEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import AccessRight.AccessRightCatalogue;
import DataBase.DataBase;
import DataBase.Table;

public class ProjectCatalogue {
	private static ProjectCatalogue projectCat = new ProjectCatalogue();
	private DataBase DB;
	private String tableName;

	private ProjectCatalogue() {
		DB = DB.getInstance();
		tableName = "project";
	}
	
	public static ProjectCatalogue getInstance(){
		return projectCat;
	}
	
	public ArrayList<HashMap<String, String>> getProjects() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString()+" SSsssss");
			EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
			result.get(i).put("managername", empcat.getEmployee(Integer.parseInt(result.get(i).get("projectmanager"))).getName());
		}
		return result;
	}
	
	public Project getProject(int pid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projid", Integer.toString(pid));
		ResultSet res = DB.select("project", vars, null);

		EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
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
	
	public long addProject(String name, Employee manager, String size, String tech,boolean isComp) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projname", "\'"+name+"\'");
		vars.put("projectmanager", "\'"+manager.getId()+"\'");
		vars.put("size", "\'"+size+"\'");
		vars.put("tech", "\'"+tech+"\'");
		vars.put("is_complete", "\'"+Boolean.toString(isComp)+"\'");


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
			EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
			result.get(i).put("managername", empcat.getEmployee(Integer.parseInt(result.get(i).get("projectmanager"))).getName());
		}
		return result;

	}

	public ArrayList<HashMap<String, String>> Search(HashMap<String, String> searchvars){
		
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.search(searchvars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
			EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
			result.get(i).put("managername", empcat.getEmployee(Integer.parseInt(result.get(i).get("projectmanager"))).getName());
		}
		return result;		
	}

}
