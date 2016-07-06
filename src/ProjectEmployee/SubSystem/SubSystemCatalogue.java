package ProjectEmployee.SubSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;

public class SubSystemCatalogue {

	DataBase DB;
	String tableName;
	
	public SubSystemCatalogue() {
		DB = DB.getInstance();
		tableName = "subsystem";
	}
	
	public ArrayList<HashMap<String, String>> getSubSystems() {
		Table table = new Table(tableName+"section");
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println("getSubsystems: "+result.get(i).toString());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getSubSystemsByProject(int pid) {
		Table table = new Table(tableName+"section");
		HashMap<String,String> vars = new HashMap<String,String>();
		vars.put("pid", Integer.toString(pid));
		
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public SubSystem getSubSystem(int subid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("subid", Integer.toString(subid));
		ResultSet res = DB.select("subsystemsection",vars,null);
		ProjectCatalogue projCat = ProjectCatalogue.getInstance();
		SectionCatalogue secCat = SectionCatalogue.getInstance();
		SubSystem subSystem = null;
		try {
			if (res.next()) {
				Project proj = projCat.getProject(res.getInt("pid"));
				Section sec = secCat.getSection(res.getInt("sid"));
				subSystem = new SubSystem(res.getString("sname"), proj, sec);
				subSystem.setId(subid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subSystem;


	}
	
	public long addSubSystem(String name, int pid, int secid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sname", "\'"+name+"\'");
		vars.put("pid", Integer.toString(pid));
		vars.put("sid", Integer.toString(secid));

		long pk=DB.insert(vars, "subsystem");
		System.out.println("inserted into subsystem table: " + pk);
		return pk;
	}
	
	public void deleteSubSystem(int id) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("subid", Integer.toString(id));
		DB.delete(vars, "subsystem");
	}
	
	public ArrayList<HashMap<String, String>> searchSubsystem(HashMap<String, String> searchvars){
		
		Table table = new Table(tableName+"section");
		ArrayList<HashMap<String, String>> result = table.search(searchvars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;		
	}
}
