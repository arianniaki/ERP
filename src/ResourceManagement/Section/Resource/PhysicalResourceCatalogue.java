package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class PhysicalResourceCatalogue extends ResourceCatalogue {

	public PhysicalResourceCatalogue() {
		super();
		tableName = "physres";
	}

	public long addResource(String name, String modeldesc, String desc) {
		long resid = super.addResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("physname", "\'" + name + "\'");
		vars.put("modeldesc", "\'" + modeldesc + "\'");
		vars.put("description", "\'" + desc + "\'");

		return DB.insert(vars, tableName);
	}
	
	

}