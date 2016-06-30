package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class FinancialResourceCatalogue extends ResourceCatalogue {

	public FinancialResourceCatalogue() {
		super();
		tableName = "finanres";
	}

	public long addResource(String name, int netValue, String modeldesc, String desc){
		long resid = super.addResource(name);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("finanname", "\'" + name + "\'");
		vars.put("netvalue", Integer.toString(netValue));
		vars.put("modeldesc", "\'" + modeldesc + "\'");
		vars.put("description", "\'" + desc + "\'");

		return DB.insert(vars, tableName);
	}

}