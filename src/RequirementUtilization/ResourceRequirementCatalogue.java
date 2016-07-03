package RequirementUtilization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import AccessRight.AccessRight;
import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import Report.Report;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.Resource;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class ResourceRequirementCatalogue{
	
	DataBase DB;
	String tableName;
	
	public ResourceRequirementCatalogue() {
		DB = new DataBase();
		tableName = "resourcerequirement";
	}
	
	public ArrayList<ResourceRequirement> getResourceRequirements() {
		ArrayList<ResourceRequirement> resReqs = new ArrayList<ResourceRequirement>();
		Table table = new Table(tableName);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			ProjectCatalogue pcat = new ProjectCatalogue();
			SectionCatalogue scat = new SectionCatalogue();
			ResourceCatalogue rcat = new ResourceCatalogue();
			Project pr = pcat.getProject(Integer.parseInt(result.get(i).get("pid")));
			Section sc = scat.getSection(Integer.parseInt(result.get(i).get("sid")));
			Resource rs = rcat.getResource(Integer.parseInt(result.get(i).get("rid")));
			ResourceRequirement rr = new ResourceRequirement(Integer.parseInt(result.get(i).get("resreqid")),pr, sc, rs, result.get(i).get("fromdate"), result.get(i).get("todate"),Boolean.parseBoolean(result.get(i).get("is_satisfied")), result.get(i).get("satisfydate"));
			resReqs.add(rr);
		}
		return resReqs;
	}
	
	public ResourceRequirement getResourceRequirement(int resreqId){
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("resreqId", Integer.toString(resreqId));
		ResultSet res = DB.select("resourcerequirement",vars,null);
		ProjectCatalogue pcat = new ProjectCatalogue();
		SectionCatalogue scat = new SectionCatalogue();
		ResourceCatalogue rcat = new ResourceCatalogue();
		ResourceRequirement rr = null;
		Project pr;
		
		try {
			if(res.next()){
				pr = pcat.getProject(res.getInt("pid"));
				Section sc = scat.getSection(res.getInt("sid"));
				Resource rs = rcat.getResource(res.getInt("rid"));
				rr = new ResourceRequirement(resreqId, pr, sc, rs, res.getString("fromdate"), res.getString("todate"),res.getBoolean("is_satisfied"),res.getString("satisfydate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rr;
	}
	
	public long addResourceRequirement(int rid, int sid, int pid, String from, String to) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+rid+"\'");
		vars.put("sid", "\'"+sid+"\'");
		vars.put("pid", "\'"+pid+"\'");
		vars.put("fromdate", "\'"+from+"\'");
		vars.put("todate", "\'"+to+"\'");
		vars.put("is_satisfied", "false");
		vars.put("satisfydate", "\'"+"1111-11-11"+"\'");
		
		long pk=DB.insert(vars, "resourcerequirement");
		System.out.println("inserted into resourcerequirement table: " + pk);
		return pk;
	}
	
	public void deleteResourceRequirement(int resreqId) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("resreqid", Integer.toString(resreqId));
		DB.delete(vars, "resourcerequirement");
	}
	
	
	public Report getReport(Project project){
		Report rep = new Report();
		Table table = new Table(tableName);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("pid",  "\'"+project.getId()+"\'");
		ArrayList<HashMap<String,String>> results = table.search(vars);
		rep.setResults(results);
		for(int i=0; i<results.size(); i++){
			String line="";
			SectionCatalogue secCat = new SectionCatalogue();
			ResourceCatalogue resCat = new ResourceCatalogue();
			ProjectCatalogue projCat = new ProjectCatalogue();
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
	
	public ArrayList<HashMap<String, String>> Search(HashMap<String, String> searchvars){
		
		Table table = new Table("resourcerequirement");
		ArrayList<HashMap<String, String>> result = table.search(searchvars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;		
	}


}