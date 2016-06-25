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
	
	public ResourceRequirement(Project project, Section section, Resource resource, String from, String to,boolean isSatisfied){
		this.from = from;
		this.to = to;
		this.project = project;
		this.section = section;
		this.resource = resource;
		this.isSatisfied = isSatisfied;
		DB = new DataBase();
	}
	
	public void edit(String from, String to ,boolean isSatisfied){
		this.from = from;
		this.to = to;
		ProjectCatalogue pcat = new ProjectCatalogue();
		SectionCatalogue scat = new SectionCatalogue();
		ResourceCatalogue rcat = new ResourceCatalogue();

		int rid = this.resource.getId();
		int pid = this.project.getId();
		int sid = this.section.getId();
		this.isSatisfied = isSatisfied;
		
		HashMap<String, String> setVars = new HashMap<String, String>();

		setVars.put("fromdate", "\'"+from+"\'");
		submitToDB(setVars, rid, sid, pid);
		setVars.clear();

		setVars.put("todate", "\'"+to+"\'");
		submitToDB(setVars, rid, sid, pid);
		setVars.clear();

		setVars.put("is_satisfied", Boolean.toString(isSatisfied));
		submitToDB(setVars, rid, sid, pid);
		setVars.clear();


	}
	
	public void satisfy(String date){
		isSatisfied = true;
		this.satisfyDate = date;
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("is_satisfied","true");
		submitToDB(setVars, this.resource.getId(), this.section.getId(), this.project.getId());
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
		resreq.put("is_satisfied", Boolean.toString(this.isSatisfied));

		return resreq;
	}

}