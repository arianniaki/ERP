package RequirementUtilization;

import java.util.HashMap;

import DataBase.DataBase;
import ProjectEmployee.Project;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.Resource.Resource;

public class ProjectResourceUtilization {

	Project project;
	Section section;
	Resource resource;
	String from;
	String to;
	DataBase DB;
	int pruId;
	

	public ProjectResourceUtilization(int pruid, Project project, Section section, Resource resource, String from, String to){
		this.project = project;
		this.section = section;
		this.resource = resource;
		this.from = from;
		this.to = to;
		this.pruId = pruid;
		DB = DB.getInstance();
	}
	
	public int getId(){
		return pruId;
	}
	
	
	public void edit(String from, String to){
		
		HashMap<String, String> setVars = new HashMap<String, String>();

		setVars.put("fromdate", "\'"+from+"\'");
		setVars.put("todate", "\'"+to+"\'");
		submitToDB(setVars);
		this.from = from;
		this.to = to;
 

	}
	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("presutilid", Integer.toString(this.pruId));

		DB.update(condVars, setVars, "projectresourceutilization");
	}
		
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> resreq = new HashMap<String,String>();
		resreq.put("presutilid", Integer.toString(this.pruId));
		resreq.put("rid", Integer.toString(this.resource.getId()));
		resreq.put("sid",Integer.toString(this.section.getId()));
		resreq.put("pid",Integer.toString(this.project.getId()));
		resreq.put("fromdate", this.from);
		resreq.put("todate", this.to);
		resreq.put("resourceName", this.resource.getName());
		resreq.put("projectName", this.resource.getName());
		resreq.put("sectionName", this.resource.getName());


		return resreq;
	}

	

}
