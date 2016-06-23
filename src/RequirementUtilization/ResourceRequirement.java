package RequirementUtilization;

import ProjectEmployee.Project;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.Resource.Resource;

public class ResourceRequirement{
	int duration;
	Project project;
	Section section;
	Resource resource;
	boolean isSatisfied;
	
	public ResourceRequirement(int duration, Project project, Section section, Resource resource){
		this.duration = duration;
		this.project = project;
		this.section = section;
		this.resource = resource;
		isSatisfied = false;
	}
	
	public void edit(int duration, Project project, Section section, Resource resource, boolean isSatisfied){
		this.duration = duration;
		this.project = project;
		this.section = section;
		this.resource = resource;
		this.isSatisfied = isSatisfied;
	}
	
	public void satisfy(){
		isSatisfied = true;
	}
}