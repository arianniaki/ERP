package RequirementUtilization;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;

public class ResourceRequirementCatalogue{
	
	DataBase DB;
	String tableName;
	
	public ResourceRequirementCatalogue() {
		DB = new DataBase();
		tableName = "resourcerequirment";
	}
	
	public ArrayList<HashMap<String, String>> getResourceRequirements() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}
	
	public long addResourceRequirement(int rid, int sid, int pid, String from, String to) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+rid+"\'");
		vars.put("sid", "\'"+sid+"\'");
		vars.put("pid", "\'"+pid+"\'");
		vars.put("fromdate", "\'"+from+"\'");
		vars.put("todate", "\'"+to+"\'");
		
		long pk=DB.insert(vars, "resourcerequirment");
		System.out.println("inserted into resourcerequirment table: " + pk);
		return pk;
	}
	
	public void deleteResourceRequirement(int rid, int sid, int pid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sid", Integer.toString(sid));
		vars.put("rid", Integer.toString(rid));
		vars.put("pid", Integer.toString(pid));

		DB.delete(vars, "resourcerequirment");
	}

}