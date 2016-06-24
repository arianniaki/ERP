package RequirementUtilization;


import java.util.HashMap;

import DataBase.DataBase;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.Resource;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class ResourceRequirement{
	String from;
	String to;
	Project project;
	Section section;
	Resource resource;
	boolean isSatisfied;
	String satisfyDate;
	DataBase DB;
	
	public ResourceRequirement(Project project, Section section, Resource resource, String from, String to){
		this.from = from;
		this.to = to;
		this.project = project;
		this.section = section;
		this.resource = resource;
		isSatisfied = false;
		DB = new DataBase();
	}
	
	public void edit(int pid, int sid, int rid, String from, String to ,boolean isSatisfied){
		this.from = from;
		this.to = to;
		ProjectCatalogue pcat = new ProjectCatalogue();
		SectionCatalogue scat = new SectionCatalogue();
		ResourceCatalogue rcat = new ResourceCatalogue();
		this.project = pcat.getProject(pid);
		this.section = scat.getSection(sid);
		this.resource = rcat.getResource(rid);
		this.isSatisfied = isSatisfied;
		
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("rid", Integer.toString(rid));
		setVars.put("sid", Integer.toString(sid));
		setVars.put("pid", Integer.toString(pid));
		setVars.put("fromdate", "\'"+from+"\'");
		setVars.put("todate", "\'"+to+"\'");
		setVars.put("is_satisfied", Boolean.toString(isSatisfied));

		submitToDB(setVars, rid, sid, pid);
	}
	
	public void satisfy(String date){
		isSatisfied = true;
		this.satisfyDate = date;
		
	}
	
	public void submitToDB(HashMap<String, String> setVars, int rid, int sid, int pid) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("rid", Integer.toString(rid));
		condVars.put("sid", Integer.toString(sid));
		condVars.put("pid", Integer.toString(pid));

		DB.update(condVars, setVars, "resourcerequirement");
	}
	
	public String toString(){
		String str = "{rid="+this.resource.getId()+", sid="+this.section.getId()+", pid="+this.project.getId()+ ", fromdate="+this.from + ", todate="+this.to+"}";
		return str;
	}
	
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> resreq = new HashMap<String,String>();
		resreq.put("rid", Integer.toString(this.resource.getId()));
		resreq.put("sid",Integer.toString(this.section.getId()));
		resreq.put("pid",Integer.toString(this.project.getId()));
		resreq.put("fromdate", this.from);
		resreq.put("todate", this.to);
		return resreq;
	}

}