package Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import DataBase.DataBase;

public class Resource{
	private int id;
	private String name;
	DataBase DB;
	
	public Resource(){
		DB = new DataBase();
	}
	
	public void setId(int inputId) {
		this.id = inputId;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String inputName) {
		this.name = inputName;
	}

	public String getName() {
		return this.name;
	}
	
	public boolean getFromDB(int rid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		ResultSet rs = DB.select(vars, "Employee");
		try {
			if (rs.next()) {
				this.id = rs.getInt("rid");
				this.name = rs.getString("rname");
			}
			rs.close();
			DB.connectionClose();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return false;
		}
	}
 

}