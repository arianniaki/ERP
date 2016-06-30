package ProjectEmployee.SubSystem;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import ProjectEmployee.Project;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.Module;

public class SubSystem {

	int sid;
	String name;
	Project project;
	Section section;
	ArrayList<Module> modules;
	DataBase DB;
	
	
	public void setId(int sid){
		this.sid = sid; 
	}
	public int getId(){
		return sid;
	}
	public SubSystem(String name, Project proj, Section sec){
		this.name = name;
		project = proj;
		section = sec;
		modules = new ArrayList<Module>();
		DB = new DataBase();
	}
	
	public void addModule(Module module){
		modules.add(module);
	}
	
	public void editSubSystem(String name, int secid){
		this.name = name;
		SectionCatalogue secCat = new SectionCatalogue();
		this.section = secCat.getSection(secid);
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sname", "\'" + name + "\'");
		vars.put("sectionid", Integer.toString(secid));
		submitToDB(vars);

	}
	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("sid", Integer.toString(this.sid));
		DB.update(condVars, setVars, "subsystem");
	}
	
}
