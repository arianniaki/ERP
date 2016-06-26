package RequirementUtilization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.Resource;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class ProjectResourceUtilizationCatalogue {

	DataBase DB;
	String tableName;
	
	public ProjectResourceUtilizationCatalogue() {
		// TODO Auto-generated constructor stub
		DB = new DataBase();
		tableName = "projectresourceutilization";
	}
	
	public ArrayList<ProjectResourceUtilization> getProjectResourceUtilizations() {
		ArrayList<ProjectResourceUtilization> ProjResUtil = new ArrayList<ProjectResourceUtilization>();
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			
			System.out.println(result.get(i).toString());
			ProjectCatalogue pcat = new ProjectCatalogue();
			SectionCatalogue scat = new SectionCatalogue();
			ResourceCatalogue rcat = new ResourceCatalogue();
			
			Project pr = pcat.getProject(Integer.parseInt(result.get(i).get("pid")));
			Section sc = scat.getSection(Integer.parseInt(result.get(i).get("sid")));
			Resource rs = rcat.getResource(Integer.parseInt(result.get(i).get("rid")));
			ProjectResourceUtilization pru = new ProjectResourceUtilization(pr, sc, rs, result.get(i).get("fromdate"), result.get(i).get("todate"));
			ProjResUtil.add(pru);
		}
		return ProjResUtil;
	}
	

	public ProjectResourceUtilization getProjectResourceUtilization(int rid, int sid, int pid){
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", Integer.toString(rid));
		vars.put("sid", Integer.toString(sid));
		vars.put("pid", Integer.toString(pid));

		ResultSet res = DB.select("ProjectResourceUtilization",vars,null);

		
		ProjectCatalogue pcat = new ProjectCatalogue();
		SectionCatalogue scat = new SectionCatalogue();
		ResourceCatalogue rcat = new ResourceCatalogue();
		
		ProjectResourceUtilization pru = null;
		Project pr;
		try {
			if(res.next()){
				pr = pcat.getProject(res.getInt("pid"));
				Section sc = scat.getSection(res.getInt("sid"));
				Resource rs = rcat.getResource(res.getInt("rid"));
				pru = new ProjectResourceUtilization(pr, sc, rs,res.getString("fromdate"), res.getString("todate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pru;
	}

	
	public long addProjectResourceUtilization(int rid, int sid, int pid, String from, String to) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+rid+"\'");
		vars.put("sid", "\'"+sid+"\'");
		vars.put("pid", "\'"+pid+"\'");
		vars.put("fromdate", "\'"+from+"\'");
		vars.put("todate", "\'"+to+"\'");

		
		long pk=DB.insert(vars, "projectresourceutilization");
		System.out.println("inserted into projectresourceutilization table: " + pk);
		return pk;
	}
	
	public void deleteProjectResourceUtilization(int rid, int sid, int pid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sid", Integer.toString(sid));
		vars.put("rid", Integer.toString(rid));
		vars.put("pid", Integer.toString(pid));

		DB.delete(vars, "projectresourceutilization");
	}

}
