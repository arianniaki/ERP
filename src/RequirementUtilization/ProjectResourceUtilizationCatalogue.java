package RequirementUtilization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import Report.Report;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.Resource;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class ProjectResourceUtilizationCatalogue {
	private static ProjectResourceUtilizationCatalogue pruCat = new ProjectResourceUtilizationCatalogue();
	private DataBase DB;
	private String tableName;
	
	private ProjectResourceUtilizationCatalogue() {
		// TODO Auto-generated constructor stub
		DB = DB.getInstance();
		tableName = "projectresourceutilization";
	}
	
	public static ProjectResourceUtilizationCatalogue getInstance(){
		return pruCat;
	}
	
	
	public ArrayList<ProjectResourceUtilization> getProjectResourceUtilizations() {
		ArrayList<ProjectResourceUtilization> ProjResUtil = new ArrayList<ProjectResourceUtilization>();
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			
			System.out.println(result.get(i).toString());
			ProjectCatalogue pcat = ProjectCatalogue.getInstance();
			SectionCatalogue scat = SectionCatalogue.getInstance();
			ResourceCatalogue rcat = new ResourceCatalogue();
			
			Project pr = pcat.getProject(Integer.parseInt(result.get(i).get("pid")));
			Section sc = scat.getSection(Integer.parseInt(result.get(i).get("sid")));
			Resource rs = rcat.getResource(Integer.parseInt(result.get(i).get("rid")));
			ProjectResourceUtilization pru = new ProjectResourceUtilization(Integer.parseInt(result.get(i).get("presutilid")),pr, sc, rs, result.get(i).get("fromdate"), result.get(i).get("todate"));
			ProjResUtil.add(pru);
		}
		return ProjResUtil;
	}
	

	public ProjectResourceUtilization getProjectResourceUtilization(int presutilId){
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("presutilId", Integer.toString(presutilId));
		ResultSet res = DB.select("ProjectResourceUtilization",vars,null);
		
		ProjectCatalogue pcat = ProjectCatalogue.getInstance();
		SectionCatalogue scat = SectionCatalogue.getInstance();
		ResourceCatalogue rcat = new ResourceCatalogue();
		
		ProjectResourceUtilization pru = null;
		Project pr;
		try {
			if(res.next()){
				pr = pcat.getProject(res.getInt("pid"));
				Section sc = scat.getSection(res.getInt("sid"));
				Resource rs = rcat.getResource(res.getInt("rid"));
				pru = new ProjectResourceUtilization(res.getInt("presutilid"),pr, sc, rs,res.getString("fromdate"), res.getString("todate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pru;
	}

	public ArrayList<ProjectResourceUtilization> getProjectResourceUtilizationbyProject(int pid){
		
		ArrayList<ProjectResourceUtilization> ProjResUtil = new ArrayList<ProjectResourceUtilization>();

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("pid", "\'"+pid+"\'");

		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			
			ProjectCatalogue pcat = ProjectCatalogue.getInstance();
			SectionCatalogue scat = SectionCatalogue.getInstance();
			ResourceCatalogue rcat = new ResourceCatalogue();
			
			Project pr = pcat.getProject(Integer.parseInt(result.get(i).get("pid")));
			Section sc = scat.getSection(Integer.parseInt(result.get(i).get("sid")));
			Resource rs = rcat.getResource(Integer.parseInt(result.get(i).get("rid")));
			ProjectResourceUtilization pru = new ProjectResourceUtilization(Integer.parseInt(result.get(i).get("presutilid")),pr, sc, rs, result.get(i).get("fromdate"), result.get(i).get("todate"));
			ProjResUtil.add(pru);
		}
		return ProjResUtil;


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
	
	
	public void deleteProjectResourceUtilization(int presutilId) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("presutilId", Integer.toString(presutilId));
		DB.delete(vars, "projectresourceutilization");
	}
	public Report getCirculationReport(Resource res){
		Report rep = new Report();
		Table table = new Table(tableName);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid",  "\'"+res.getId()+"\'");
		ArrayList<HashMap<String,String>> results = table.search(vars);
		rep.setResults(results);
		for(int i=0; i<results.size(); i++){
			String line="";
			SectionCatalogue secCat = SectionCatalogue.getInstance();
			ResourceCatalogue resCat = new ResourceCatalogue();
			ProjectCatalogue projCat = ProjectCatalogue.getInstance();
			results.get(i).put("sname", ""+secCat.getSection(Integer.valueOf(results.get(i).get("sid"))).getName());
			results.get(i).put("rname", ""+resCat.getResource((Integer.valueOf(results.get(i).get("rid")))).getName());
			results.get(i).put("pname", ""+projCat.getProject((Integer.valueOf(results.get(i).get("pid")))).getName());
			for(String key : results.get(i).keySet()){
				line+= results.get(i).get(key).toString()+", ";
			}
			line = line.substring(0, line.length() - 2);
			rep.addLine(line);
		}
		return rep;
	}

}
