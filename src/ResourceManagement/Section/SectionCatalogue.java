package ResourceManagement.Section;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;

public class SectionCatalogue {

	DataBase DB;
	String tableName;
	
	public SectionCatalogue() {
		DB = new DataBase();
		tableName = "section";
	}
	
	public ArrayList<HashMap<String, String>> getSections() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}
	
	public Section getSection(int sid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sectionid", Integer.toString(sid));
		ResultSet res = DB.select(vars, "section");
		Section sec = null;
		try {
			if (res.next()) {
				sec = new Section(res.getString("sectionname"));
				sec.setId(sid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sec;

	}
	
	public long addSection(String name) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sectionname", "\'"+name+"\'");

		long pk=DB.insert(vars, "section");
		System.out.println("inserted into section table: " + pk);
		return pk;
	}
	
	public void deleteSection(int id) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sectionid", Integer.toString(id));
		DB.delete(vars, "section");
	}

}
