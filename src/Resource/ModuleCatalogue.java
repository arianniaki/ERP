package Resource;

import java.util.HashMap;

public class ModuleCatalogue extends ResourceCatalogue{
	
	public ModuleCatalogue(){
		super();
		tableName = "module";
	}
	
	public void addResource(int rid, int modid, String name) {
		super.addResource(rid);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put("modname", "\'" + name + "\'");
		vars.put("modid", Integer.toString(modid));
		DB.insert(vars, tableName);
	}


}
