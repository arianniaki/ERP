package Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.Column;
import DataBase.DataBase;
import DataBase.Table;

public class ResourceCatalogue {
	DataBase DB;
	String tableName;

	public ResourceCatalogue() {
		DB = new DataBase();
	}

	public ArrayList<HashMap<String, String>> readAllResources() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public void addResource(int rid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		DB.insert(vars, "resource");
	}

	public void deleteResource(int id) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(id));
		DB.delete(vars, "resource");
	}

}