package RequirementUtilization;


import java.awt.Component;
import java.util.HashMap;
import java.util.Hashtable;

import javax.sound.sampled.Port;

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
	int resReqId;
	DataBase DB;
	
	public ResourceRequirement(int resReqId,Project project, Section section, Resource resource, String from, String to,boolean isSatisfied, String satisfyDate){
		this.from = from;
		this.to = to;
		this.project = project;
		this.section = section;
		this.resource = resource;
		this.isSatisfied = isSatisfied;
		this.satisfyDate = satisfyDate;
		this.resReqId = resReqId;
		DB = new DataBase();
		
	}
	
	public void edit(String from, String to ,boolean isSatisfied, String satisfyDate){

		this.satisfyDate = satisfyDate;

		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("fromdate", "\'"+from+"\'");
		setVars.put("todate", "\'"+to+"\'");
		setVars.put("is_satisfied", Boolean.toString(isSatisfied));
		setVars.put("satisfydate", "\'"+satisfyDate+"\'");
		submitToDB(setVars);
		this.from = from;
		this.to = to;
	}
	
	public void satisfy(String date){
		isSatisfied = true;
		this.satisfyDate = date;
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("is_satisfied","true");
		setVars.put("satisfydate", "\'"+date+"\'");
		submitToDB(setVars);
	}
	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("resreqid", Integer.toString(this.resReqId));
		DB.update(condVars, setVars, "resourcerequirement");
	}
	
	public String toString(){
		String str = "{rid="+this.resource.getId()+", sid="+this.section.getId()+", pid="+this.project.getId()+ ", fromdate="+this.from + ", todate="+this.to+"}";
		return str;
	}
	
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> resreq = new HashMap<String,String>();
		resreq.put("resReqid", Integer.toString(resReqId));
		resreq.put("rid", Integer.toString(this.resource.getId()));
		resreq.put("sid",Integer.toString(this.section.getId()));
		resreq.put("pid",Integer.toString(this.project.getId()));
		resreq.put("fromdate", this.from);
		resreq.put("todate", this.to);
		resreq.put("is_satisfied", Boolean.toString(this.isSatisfied));
		resreq.put("satisfydate", this.satisfyDate);
		resreq.put("resourceName", this.resource.getName());
		resreq.put("projectName", this.resource.getName());
		resreq.put("sectionName", this.resource.getName());


		return resreq;
	}

	public Resource getResource() {
		// TODO Auto-generated method stub
		
		return resource;
	}

	public Section getSection() {
		// TODO Auto-generated method stub
		return section;
	}
	
	public Project getProject() {
		// TODO Auto-generated method stub
		return project;
	}

}