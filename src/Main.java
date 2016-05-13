import UserManagement.Employee;

public class Main {
	public static void main(String[] args) {
		Employee emp = new Employee();
		if(!(emp.login("ali","124"))){
			System.out.println("huraaa");
		}else{
			System.out.println("shit");
		}
		if(emp.login("ali", "123")){
			System.out.println(emp.getName());
			System.out.println(emp.loggedin);

		}
		if(emp.logout()){
			System.out.println(emp.loggedin);
		}
	}
}
