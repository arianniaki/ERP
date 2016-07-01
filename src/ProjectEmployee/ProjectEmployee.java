package ProjectEmployee;

public class ProjectEmployee {

	Project project;
	Employee employee;
	String from;
	String to;

	public ProjectEmployee(Project pr, Employee emp, String from, String to){
		employee = emp;
		project = pr;
		this.from = from;
		this.to = to;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
	public Project getProject(){
		return project;
	}
	
}
