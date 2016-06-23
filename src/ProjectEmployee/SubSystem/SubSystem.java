package ProjectEmployee.SubSystem;

import java.util.ArrayList;

import ProjectEmployee.Project;
import ResourceManagement.Section.Resource.Module;

public class SubSystem {

	String name;
	Project project;
	ArrayList<Module> modules;
	
	public SubSystem(String name, Project proj){
		this.name = name;
		project = proj;
		modules = new ArrayList<Module>();
	}
	
	public void addModule(Module module){
		modules.add(module);
	}
	
	public void editSubSystem(String name, Project project){
		this.name = name;
		this.project = project;
	}
	
}
