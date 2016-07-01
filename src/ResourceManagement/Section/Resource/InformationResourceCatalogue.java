package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class InformationResourceCatalogue extends ResourceCatalogue {

	public InformationResourceCatalogue() {
		super();
		tableName = "infores";
	}

	public long addResource(String name,int SectionId, String createDate, String desc) {
		long resid = super.addResource(name, SectionId);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", resid+"");
		vars.put("irname", "\'" + name + "\'");
		vars.put("createdate", "\'" + createDate + "\'");
		vars.put("description", "\'" + desc + "\'");

		return DB.insert(vars, tableName);
	}
	
	public InformationResource getInformationResource(int irid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("irid", irid+"");
		ResultSet res = DB.select(tableName, vars, null);
		InformationResource infores = new InformationResource();
		try {
			if(res.next()){
				infores.setId(res.getInt("rid"));
				infores.setName(res.getString("irname"));
				infores.description = res.getString("description");
				infores.createDate = res.getString("createdate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infores;
	}



}