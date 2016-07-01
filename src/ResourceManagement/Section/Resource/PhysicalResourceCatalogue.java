package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PhysicalResourceCatalogue extends ResourceCatalogue {

	public PhysicalResourceCatalogue() {
		super();
		tableName = "physres";
	}

	public long addResource(String name,int SectionId, String modeldesc, String desc) {
		long resid = super.addResource(name, SectionId);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("physname", "\'" + name + "\'");
		vars.put("modeldesc", "\'" + modeldesc + "\'");
		vars.put("description", "\'" + desc + "\'");

		return DB.insert(vars, tableName);
	}
	
	public PhysicalResource getPhysicalResource(int physId){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("physid", physId+"");
		ResultSet res = DB.select(tableName, vars, null);
		PhysicalResource physres = new PhysicalResource();
		try {
			if(res.next()){
				physres.setId(res.getInt("rid"));
				physres.setName(res.getString("physname"));
				physres.description = res.getString("description");
				physres.modeldesc = res.getString("modeldesc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return physres;
	}

	

}