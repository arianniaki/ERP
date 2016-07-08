package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class PhysicalResource extends Resource{

	String modeldesc;
	
	public PhysicalResource(){
		super();
		tableName = "physres";
	}
	
	public void editResource(String name,int SectionId, String modeldesc, String desc){
		this.modeldesc = modeldesc;
		this.description = desc;
		super.editResource(name,SectionId);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("physname", "\'" + name + "\'");
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