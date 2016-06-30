package ProjectEmployee;

public class ProjectEmployee {

	Project project;
	Employee employee;

	public ProjectEmployee(Project pr, Employee emp){
		employee = emp;
		project = pr;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
	public Project getProject(){
		return project;
	}
	
}
