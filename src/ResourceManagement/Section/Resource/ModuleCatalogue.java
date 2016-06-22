package ResourceManagement.Section.Resource;

import java.util.HashMap;

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


}
