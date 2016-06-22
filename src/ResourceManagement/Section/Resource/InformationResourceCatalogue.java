package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class InformationResourceCatalogue extends ResourceCatalogue {

	public InformationResourceCatalogue() {
		super();
		tableName = "infores";
	}

	public long addResource(String name) {
		long resid = super.addResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("irname", "\'" + name + "\'");
		return DB.insert(vars, tableName);
	}

}