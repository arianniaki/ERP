package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class FinancialResourceCatalogue extends ResourceCatalogue {

	public FinancialResourceCatalogue() {
		super();
		tableName = "finanres";
	}

	public long addResource(String name,int SectionId, int netValue, String modeldesc, String desc){
		long resid = super.addResource(name,SectionId);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("finanname", "\'" + name + "\'");
		vars.put("netvalue", Integer.toString(netValue));
		vars.put("modeldesc", "\'" + modeldesc + "\'");
		vars.put("description", "\'" + desc + "\'");

		return DB.insert(vars, tableName);
	}
	
	public FinancialResource getFinancialResource(int rid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", rid+"");
		ResultSet res = DB.select(tableName, vars, null);
		FinancialResource finanres = new FinancialResource();
		try {
			if(res.next()){
				finanres.setId(res.getInt("rid"));
				finanres.setName(res.getString("finanname"));
				finanres.description = res.getString("description");
				finanres.modeldesc = res.getString("modeldesc");
				finanres.netValue = res.getInt("netvalue");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finanres;
	}



}