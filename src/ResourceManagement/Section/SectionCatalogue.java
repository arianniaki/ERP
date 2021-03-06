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
import ProjectEmployee.ProjectEmployeeCatalogue;

public class SectionCatalogue {
	private static SectionCatalogue secCat = new SectionCatalogue();
	private DataBase DB;
	private String tableName;
	
	private SectionCatalogue() {
		DB = DB.getInstance();
		tableName = "section";
	}
	
	public static SectionCatalogue getInstance(){
		return secCat;
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
		vars.put("sid", Integer.toString(sid));
		ResultSet res = DB.select("section",vars,null);
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
}
