package ProjectEmployee.SubSystem;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;

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
	
	public long addSubSystem(String name, int pid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sname", "\'"+name+"\'");
		vars.put("pid", "\'"+pid+"\'");

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
