package RequirementUtilization;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;

public class ProjectResourceUtilizationCatalogue {

	DataBase DB;
	String tableName;
	
	public ProjectResourceUtilizationCatalogue() {
		// TODO Auto-generated constructor stub
		DB = new DataBase();
		tableName = "projectresourceutilization";
	}
	
	public ArrayList<HashMap<String, String>> getProjectResourceUtilizations() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}
	
	public long addProjectResourceUtilization(int rid, int sid, int pid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+rid+"\'");
		vars.put("sid", "\'"+sid+"\'");
		vars.put("pid", "\'"+pid+"\'");
		
		long pk=DB.insert(vars, "projectresourceutilization");
		System.out.println("inserted into projectresourceutilization table: " + pk);
		return pk;
	}
	
	public void deleteProjectResourceUtilization(int rid, int sid, int pid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sid", Integer.toString(sid));
		vars.put("rid", Integer.toString(rid));
		vars.put("pid", Integer.toString(pid));

		DB.delete(vars, "projectresourceutilization");
	}

}
