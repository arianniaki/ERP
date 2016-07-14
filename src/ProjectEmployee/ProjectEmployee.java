package ProjectEmployee;

import java.util.HashMap;

public class ProjectEmployee {

	Project project;
	Employee employee;
	String from;
	String to;
	int projempId;
	public ProjectEmployee(int projempId, Project pr, Employee emp, String from, String to){
		employee = emp;
		project = pr;
		this.from = from;
		this.to = to;
		this.projempId = projempId;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
	public Project getProject(){
		return project;
	}
	
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> projEmp = new HashMap<String,String>();
		projEmp.put("projempid", Integer.toString(this.projempId));
		projEmp.put("pid", Integer.toString(this.project.getId()));
		projEmp.put("projectname", this.project.getName());
		projEmp.put("empid",Integer.toString(this.employee.getId()));
		projEmp.put("fromdate", this.from);
		projEmp.put("todate", this.to);

		return projEmp;
	}
	
}
