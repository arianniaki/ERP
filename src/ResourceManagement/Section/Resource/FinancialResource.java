package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class FinancialResource extends Resource{

	public FinancialResource(){
		super();
		tableName = "finanres";
	}
	
	public void editResource(String name){
		super.editResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("finanname", "\'" + name + "\'");
		submitToDB(vars);
	}

	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("rid", Integer.toString(super.getId()));
		DB.update(condVars, setVars, tableName);
	}

}