package ProjectEmployee;

import java.util.HashMap;

import DataBase.DataBase;

public class ProjectEmployee {

	Project project;
	Employee employee;
	String from;
	String to;
	int projempId;
	DataBase DB;

	public ProjectEmployee(int projempId, Project pr, Employee emp, String from, String to){
		employee = emp;
		project = pr;
		this.from = from;
		this.to = to;
		this.projempId = projempId;
		DB = DB.getInstance();
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
		projEmp.put("empname",this.employee.getName());
		projEmp.put("fromdate", this.from);
		projEmp.put("todate", this.to);

		return projEmp;
	}
	
public void edit(String from, String to){
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("fromdate", "\'"+from+"\'");
		setVars.put("todate", "\'"+to+"\'");
		submitToDB(setVars);
		this.from = from;
		this.to = to;
 

	}

public void submitToDB(HashMap<String, String> setVars) {
	HashMap<String, String> condVars = new HashMap<String, String>();
	condVars.put("projempid", Integer.toString(this.projempId));
	DB.update(condVars, setVars, "projectemployee");
}
	
	
}
