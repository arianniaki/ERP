package ProjectEmployee.SubSystem;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import ProjectEmployee.Project;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.Module;

public class SubSystem {

	int subId;
	String name;
	Project project;
	Section section;
	DataBase DB;
	
	
	public void setId(int sid){
		this.subId = sid; 
	}
	public int getId(){
		return subId;
	}
	public SubSystem(String name, Project proj, Section sec){
		this.name = name;
		project = proj;
		section = sec;
		DB = new DataBase();
	}
		
	public void editSubSystem(String name, int secid){
		this.name = name;
		SectionCatalogue secCat = new SectionCatalogue();
		this.section = secCat.getSection(secid);
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("sname", "\'" + name + "\'");
		vars.put("subid", Integer.toString(secid));
		submitToDB(vars);

	}
	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("subid", Integer.toString(this.subId));
		DB.update(condVars, setVars, "subsystem");
	}
	
}
