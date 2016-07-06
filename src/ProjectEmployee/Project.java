package ProjectEmployee;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import ProjectEmployee.SubSystem.SubSystem;

public class Project {
	int pid;
	String name;
	String description;
	String projectSize;
	String technologiesUsed;
	boolean isComplete;
	Employee projectManager;
	DataBase DB;
	
	
	public Project(String name, String desc, String size, Employee manager, String tech){
		DB = DB.getInstance();
		this.name = name;
		description = desc;
		projectSize = size;
		projectManager = manager;
		technologiesUsed = tech;
		isComplete = false;
	}
	
	public void setId(int inputId) {
		this.pid = inputId;
	}

	public int getId() {
		return this.pid;
	}

	
	public void editProject(String name, String desc, String size, Employee manager, String tech, boolean isComp){
		this.name = name;
		description = desc;
		projectSize = size;
		projectManager = manager;
		technologiesUsed = tech;
		isComplete = isComp;
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("projname", "\'"+name+"\'");
		setVars.put("projectmanager", Integer.toString(manager.getId()));
		setVars.put("size", "\'"+size+"\'");
		setVars.put("tech", "\'"+tech+"\'");
		setVars.put("is_complete", "\'"+Boolean.toString(isComp)+"\'");
		submitToDB(setVars);

	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("projid", Integer.toString(this.pid));
		DB.update(condVars, setVars, "project");
	}

	
}
