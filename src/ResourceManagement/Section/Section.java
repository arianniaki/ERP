package ResourceManagement.Section;

import java.util.ArrayList;

import ProjectEmployee.SubSystem.SubSystem;

public class Section {

	int sid;
	String name;
	
	public Section(String name){
		this.name = name;
	}
	
	public void setId(int inputId) {
		this.sid = inputId;
	}

	public int getId() {
		return this.sid;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
}
