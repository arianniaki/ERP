import Resource.FinancialResourceCatalogue;
import Resource.InformationResourceCatalogue;
import Resource.ModuleCatalogue;
import Resource.Resource;
import Resource.ResourceCatalogue;
import UserManagement.Employee;
import UserManagement.EmployeeCatalogue;

public class Main {
	public static void main(String[] args) {
		// test employee
//		Employee emp = new Employee();
//		if(!(emp.login("ali","124"))){
//			System.out.println("huraaa");
//		}else{
//			System.out.println("shit");
//		}
//		if(emp.login("ali", "123")){
//			System.out.println(emp.getName());
//			System.out.println(emp.loggedin);
//
//		}
//		if(emp.logout()){
//			System.out.println(emp.loggedin);
//		}
		
		// test resource
//		ModuleCatalogue mcat = new ModuleCatalogue();
//		System.out.println("all : ");
//		mcat.readAllResources();
//		mcat.addResource(6,2,"module 1");
//		mcat.addResource(5,2,"module 3");
//		System.out.println(":::::::after insert ::::::: ");
//		mcat.readAllResources();
//		mcat.deleteResource(5);
//		System.out.println(":::::::after delete ::::::: ");
//		mcat.readAllResources();

//		rscat.deleteResource(4);
//		System.out.println();
//		rscat.readAllResources();

//		ResourceCatalogue rscat = new ResourceCatalogue();
//		rscat = new ResourceCatalogue();
////		rscat.addResource(8, "sanad8");
//		rscat.readAllResources();
//		rscat.deleteResource(3);
//		System.out.println();
//		rscat.readAllResources();
		
		// test EmployeeCatalogue
		EmployeeCatalogue empcat = new EmployeeCatalogue();
		empcat.readAllEmployees();
		empcat.addEmployee(10, false, "gholamReza", "hammal", 5);
		System.out.println(":::::: after insert ::::::::");
		empcat.readAllEmployees();
		empcat.deleteEmployee(10);
		System.out.println(":::::: after delete ::::::::");

		empcat.readAllEmployees();

	}
}
