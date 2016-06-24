package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class Module extends Resource{
	public Module() {
		super();
		tableName = "module";

	}
	
	
	public void editResource(String name){
		super.editResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modname", "\'" + name + "\'");
		submitToDB(vars);
	}

	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("rid", Integer.toString(super.getId()));
		DB.update(condVars, setVars, tableName);
	}

}
