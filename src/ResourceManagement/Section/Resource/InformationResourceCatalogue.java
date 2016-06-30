package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class InformationResourceCatalogue extends ResourceCatalogue {

	public InformationResourceCatalogue() {
		super();
		tableName = "infores";
	}

	public long addResource(String name, String createDate, String desc) {
		long resid = super.addResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("irname", "\'" + name + "\'");
		vars.put("createdate", "\'" + createDate + "\'");
		vars.put("description", "\'" + desc + "\'");

		return DB.insert(vars, tableName);
	}

}