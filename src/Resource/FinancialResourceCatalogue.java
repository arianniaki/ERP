package Resource;

import java.util.HashMap;

public class FinancialResourceCatalogue extends ResourceCatalogue {

	public FinancialResourceCatalogue() {
		super();
		tableName = "finanres";
	}

	public void addResource(int rid, int irid, String name) {
		super.addResource(rid,name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put("finanname", "\'" + name + "\'");
		vars.put("finanid", Integer.toString(irid));
		DB.insert(vars, tableName);
	}

}