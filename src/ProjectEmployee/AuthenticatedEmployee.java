package ProjectEmployee;


public class AuthenticatedEmployee {
	
	private static AuthenticatedEmployee authenticatedEmployee = new AuthenticatedEmployee();
	private Employee employee = null;
	private AuthenticatedEmployee(){}
	
	public static AuthenticatedEmployee getInstance(){
		return authenticatedEmployee;
	}
	
	public static boolean setEmployee(Employee emp){
		if(authenticatedEmployee.employee != null || emp == null)
			return false;
		authenticatedEmployee.employee = emp;
		return true;
	}
	
	public Employee getEmployee(){
		return authenticatedEmployee.employee;
	}
	
	public boolean logoutEmployee(){
		if(authenticatedEmployee.employee == null)
			return false;
		authenticatedEmployee.employee = null;
		return true;
		
	}

	
}
