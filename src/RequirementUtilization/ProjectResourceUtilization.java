package RequirementUtilization;

import ProjectEmployee.Project;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.Resource.Resource;

public class ProjectResourceUtilization {

	Project project;
	Section section;
	Resource resource;
	

	public ProjectResourceUtilization(Project project, Section section, Resource resource){
		this.project = project;
		this.section = section;
		this.resource = resource;
	}
	
	public void edit(Project project, Section section, Resource resource){
		this.project = project;
		this.section = section;
		this.resource = resource;
	}

}
