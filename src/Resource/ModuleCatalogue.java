package Resource;

import java.util.HashMap;

public class ModuleCatalogue extends ResourceCatalogue{
	
	public ModuleCatalogue(){
		super();
		tableName = "module";
		nameInDb = "modname";
	}
	
	public void addResource(int rid, int modid, String name) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put(nameInDb, "\'" + name + "\'");
		vars.put("modid", Integer.toString(modid));
		DB.insert(vars, tableName);
	}


}
