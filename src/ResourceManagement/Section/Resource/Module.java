package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import AccessRight.AccessRight;

public class Module extends Resource{
	
	int duration;

	public Module() {
		super();
		tableName = "module";

	}
	
	
	
	public void editResource(String name, int duration, String desc){
		this.duration = duration;
		this.description = desc;
		super.editResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modname", "\'" + name + "\'");
		vars.put("duration",Integer.toString(duration));
		vars.put("description", "\'" + desc + "\'");

		submitToDB(vars);
	}

	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("rid", Integer.toString(super.getId()));
		DB.update(condVars, setVars, tableName);
	}
	
	public boolean getFromDB(int modid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modid", Integer.toString(modid));
		ResultSet rs = DB.select("module", vars, null);
		try {
			if (rs.next()) {
				setId(rs.getInt("modid"));
				setName(rs.getString("modname"));
				this.description = rs.getString("description");
				this.duration = rs.getInt("duration");
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
