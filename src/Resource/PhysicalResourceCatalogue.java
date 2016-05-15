package Resource;

import java.util.HashMap;

public class PhysicalResourceCatalogue extends ResourceCatalogue {

	public PhysicalResourceCatalogue() {
		super();
		tableName = "physres";
		nameInDb = "physname";
	}

	public void addResource(int rid, int irid, String name) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put(nameInDb, "\'" + name + "\'");
		vars.put("physid", Integer.toString(irid));
		DB.insert(vars, tableName);
	}

}