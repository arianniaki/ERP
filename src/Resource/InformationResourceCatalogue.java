package Resource;

import java.util.HashMap;

public class InformationResourceCatalogue extends ResourceCatalogue {

	public InformationResourceCatalogue() {
		super();
		tableName = "infores";
	}

	public void addResource(int rid, int irid, String name) {
		super.addResource(rid,name);

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put("irname", "\'" + name + "\'");
		vars.put("irid", Integer.toString(irid));
		DB.insert(vars, tableName);
	}

}