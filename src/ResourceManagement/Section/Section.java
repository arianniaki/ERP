package ResourceManagement.Section;

import java.util.ArrayList;

import ProjectEmployee.SubSystem.SubSystem;

public class Section {

	int sid;
	String name;
	ArrayList<SubSystem> subSystems;
	
	public Section(String name){
		this.name = name;
		subSystems = new ArrayList<SubSystem>();
	}
	
	public void setId(int inputId) {
		this.sid = inputId;
	}

	public int getId() {
		return this.sid;
	}

	public void addSubSystem(SubSystem sub){
		subSystems.add(sub);
	}
	
}
