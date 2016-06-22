package AccessRight;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;

public class AccessRightCatalogue {

	DataBase DB;
	String tableName;
	
	public AccessRightCatalogue(){
		DB = new DataBase();
		tableName = "accessright";
	}
	
	public ArrayList<HashMap<String, String>> getAccessRights() {
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}
	

}