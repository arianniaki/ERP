package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import RequirementUtilization.ResourceRequirement;
import ResourceManagement.Section.Section;

public class ModuleCatalogue extends ResourceCatalogue{
	
	public ModuleCatalogue(){
		super();
		tableName = "module";
	}
	
	public long addResource(String name) {
		long resid = super.addResource(name);
		System.out.println("resid in module cata: "+resid);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("modname", "\'" + name + "\'");
		return DB.insert(vars, tableName);
	}
	
	public Module getModule(int modid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modid", modid+"");
		ResultSet res = DB.select(tableName, vars, null);
		Module mod = new Module();
		try {
			if(res.next()){
				mod.setId(modid);
				mod.setName(res.getString("modname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mod;
	}


}
