import Resource.InformationResourceCatalogue;
import Resource.Resource;
import Resource.ResourceCatalogue;
import UserManagement.Employee;

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
		InformationResourceCatalogue rscat = new InformationResourceCatalogue();
		rscat = new InformationResourceCatalogue();
//		rscat.addResource(8, "sanad8");
		rscat.readAllResources();
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
	}
}
