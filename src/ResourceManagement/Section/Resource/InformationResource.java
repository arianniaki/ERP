package ResourceManagement.Section.Resource;

import java.util.HashMap;

import DataBase.DataBase;

public class InformationResource extends Resource{

	public InformationResource(){
		super();
		tableName = "infores";
	}
	
	public void editResource(String name){
		super.editResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("irname", "\'" + name + "\'");
		submitToDB(vars);
	}

	
	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("rid", Integer.toString(super.getId()));
		DB.update(condVars, setVars, tableName);
	}

}