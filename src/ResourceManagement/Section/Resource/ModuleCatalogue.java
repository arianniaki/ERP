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
	
	public long addResource(String name,int SectionId, int duration, String desc) {
		long resid = super.addResource(name,SectionId);
		System.out.println("resid in module cata: "+resid);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modrid", resid+"");
		vars.put("modname", "\'" + name + "\'");
		vars.put("duration",Integer.toString(duration));
		vars.put("description", "\'" + desc + "\'");

		return DB.insert(vars, tableName);
	}
	
	public Module getModule(int modrid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modrid", modrid+"");
		ResultSet res = DB.select(tableName, vars, null);
		Module mod = new Module();
		try {
			if(res.next()){
				mod.getFromDB(modrid);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mod;
	}


}
