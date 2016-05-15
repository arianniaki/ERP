package Resource;

import java.util.HashMap;

public class PhysicalResourceCatalogue extends ResourceCatalogue {

	public PhysicalResourceCatalogue() {
		super();
		tableName = "physres";
	}

	public void addResource(int rid, int irid, String name) {
		super.addResource(rid);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put("physname", "\'" + name + "\'");
		vars.put("physid", Integer.toString(irid));
		DB.insert(vars, tableName);
	}

}