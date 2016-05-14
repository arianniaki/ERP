package Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import DataBase.DataBase;

public class ResourceCatalogue{
	DataBase DB;
	
	public ResourceCatalogue(){
		DB = new DataBase();
	}
	
	public void readAllResources(){
		try {
			ResultSet rs = DB.selectAll("resource");
			while ( rs.next() ) {
			    int id = rs.getInt("rid");
			    String  name = rs.getString("rname");
			    System.out.print( "ID = " + id +" ; ");
			    System.out.println( "NAME = " + name );
			 }
	        rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addResource(int id, String name){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(id));
		vars.put("rname", "\'"+name+"\'");
		DB.insert(vars, "resource");
	}

	public void deleteResource(int id){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(id));
		DB.delete(vars, "resource");
	}

}