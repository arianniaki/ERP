package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class FinancialResource extends Resource{
	
	int netValue;
	String modeldesc;

	public FinancialResource(){
		super();
		tableName = "finanres";
	}
	
	public void editResource(String name, int netValue, String modeldesc, String desc){
		this.netValue = netValue;
		this.modeldesc = modeldesc;
		this.description = desc;
		super.editResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("finanname", "\'" + name + "\'");
		vars.put("netvalue", Integer.toString(netValue));
		vars.put("modeldesc", "\'" + modeldesc + "\'");
		vars.put("description", "\'" + desc + "\'");
		submitToDB(vars);
	}
	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("rid", Integer.toString(super.getId()));
		DB.update(condVars, setVars, tableName);
	}

}