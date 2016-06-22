package ProjectEmployee;

import java.util.ArrayList;

import ProjectEmployee.SubSystem.SubSystem;

public class Project {

	String name;
	String description;
	String projectSize;
	String technologiesUsed;
	boolean isComplete;
	Employee projectManager;
	ArrayList<SubSystem> subSystems;
	ArrayList<Employee> employees;
	
	public Project(String name, String desc, String size, Employee manager, String tech){
		this.name = name;
		description = desc;
		projectSize = size;
		projectManager = manager;
		technologiesUsed = tech;
		isComplete = false;
		subSystems = new ArrayList<>();
		employees = new ArrayList<>();
	}
	
	public void editProject(String name, String desc, String size, Employee manager, String tech, boolean isComp, ArrayList<SubSystem> subs, ArrayList<Employee> emps){
		this.name = name;
		description = desc;
		projectSize = size;
		projectManager = manager;
		technologiesUsed = tech;
		isComplete = isComp;
		subSystems = subs;
		employees = emps;	
	}
	public void addSubSystem(SubSystem subSystem){
		if(!subSystems.contains(subSystem))
			subSystems.add(subSystem);
		else
			System.out.println("This subsystem already exits in this project");
	}
	
	public void addEmployee(Employee employee){
		if(!employees.contains(employee))
			employees.add(employee);
		else
			System.out.println("This employee already exits in this project");

	}
	
}
