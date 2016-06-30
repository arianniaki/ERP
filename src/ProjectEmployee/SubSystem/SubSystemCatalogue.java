package ProjectEmployee.SubSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;

public class SubSystemCatalogue {

	DataBase DB;
	String tableName;
	
	public SubSystemCatalogue() {
		DB = new DataBase();
		tableName = "subsystem";
	}
	
	public ArrayList<HashMap<String, String>> getSubSystems() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getSubSystemsByProject(int pid) {
		Table table = new Table(tableName);
		HashMap<String,String> vars = new HashMap<String,String>();
		vars.put("pid", Integer.toString(pid));
		
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public SubSystem getSubSystem(int sid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sid", Integer.toString(sid));
		ResultSet res = DB.select("subsystem",vars,null);
		ProjectCatalogue projCat = new ProjectCatalogue();
		SectionCatalogue secCat = new SectionCatalogue();
		SubSystem subSystem = null;
		try {
			if (res.next()) {
				Project proj = projCat.getProject(res.getInt("pid"));
				Section sec = secCat.getSection(res.getInt("sectionid"));
				subSystem = new SubSystem(res.getString("sname"), proj, sec);
				subSystem.setId(sid);
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
		vars.put("sectionid", Integer.toString(secid));

		long pk=DB.insert(vars, "subsystem");
		System.out.println("inserted into subsystem table: " + pk);
		return pk;
	}
	
	public void deleteSubSystem(int id) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sid", Integer.toString(id));
		DB.delete(vars, "subsystem");
	}

}
