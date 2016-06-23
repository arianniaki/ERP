package ProjectEmployee;


public class AuthenticatedEmployee {
	
	private static AuthenticatedEmployee authenticatedEmployee = new AuthenticatedEmployee();
	private Employee employee;
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
	
	public static Employee getEmployee(){
		return authenticatedEmployee.employee;
	}
	
	public static boolean logoutEmployee(){
		if(authenticatedEmployee.employee == null)
			return false;
		authenticatedEmployee.employee = null;
		return true;
		
	}

	
}
