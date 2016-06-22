package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class PhysicalResourceCatalogue extends ResourceCatalogue {

	public PhysicalResourceCatalogue() {
		super();
		tableName = "physres";
	}

	public long addResource(String name) {
		long resid = super.addResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("physname", "\'" + name + "\'");
		return DB.insert(vars, tableName);
	}

}