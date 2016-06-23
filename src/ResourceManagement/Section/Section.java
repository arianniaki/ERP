package ResourceManagement.Section;

import java.util.ArrayList;

import ProjectEmployee.SubSystem.SubSystem;

public class Section {

	String name;
	ArrayList<SubSystem> subSystems;
	
	public Section(String name){
		this.name = name;
		subSystems = new ArrayList<SubSystem>();
	}
	
	public void addSubSystem(SubSystem sub){
		subSystems.add(sub);
	}
	
}
