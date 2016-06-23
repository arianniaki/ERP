package RequirementUtilization;


import ProjectEmployee.Project;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.Resource.Resource;

public class ResourceRequirement{
	String from;
	String to;
	Project project;
	Section section;
	Resource resource;
	boolean isSatisfied;
	
	public ResourceRequirement(Project project, Section section, Resource resource, String from, String to){
		this.from = from;
		this.to = to;
		this.project = project;
		this.section = section;
		this.resource = resource;
		isSatisfied = false;
	}
	
	public void edit(Project project, Section section, Resource resource, String from, String to ,boolean isSatisfied){
		this.from = from;
		this.to = to;
		this.project = project;
		this.section = section;
		this.resource = resource;
		this.isSatisfied = isSatisfied;
	}
	
	public void satisfy(){
		isSatisfied = true;
	}
}