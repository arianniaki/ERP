package Resource;

import java.util.HashMap;

public class FinancialResourceCatalogue extends ResourceCatalogue {

	public FinancialResourceCatalogue() {
		super();
		tableName = "finanres";
		nameInDb = "finanname";
	}

	public void addResource(int rid, int irid, String name) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put(nameInDb, "\'" + name + "\'");
		vars.put("finanid", Integer.toString(irid));
		DB.insert(vars, tableName);
	}

}