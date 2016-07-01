import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import ProjectEmployee.AuthenticatedEmployee;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.ProjectEmployee;
import ProjectEmployee.ProjectEmployeeCatalogue;
import ProjectEmployee.SubSystem.SubSystemCatalogue;
import RequirementUtilization.*;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.*;

public class Main {
	public static void main(String[] args) throws ParseException {
		
		// test resources
//		FinancialResourceCatalogue fCat = new FinancialResourceCatalogue();
//		fCat.addResource("finanTest", 1,1000,"model1","hello Test");
//		fCat.getFinancialResource(3).editResource("changed name", 1, 10, "changed model", "changed");
//		fCat.readAllResources();
//		fCat.getResource(11);
//		fCat.deleteResource(18);
		
//		InformationResourceCatalogue infCat = new InformationResourceCatalogue();
//		infCat.addResource("infoTest",1,"2016-10-10","hello Test");
//		infCat.getInformationResource(4).editResource("inf", 1, "2016-10-10", "changed desc");
//		infCat.readAllResources();
//		infCat.deleteResource(16);

//		ModuleCatalogue mCat = new ModuleCatalogue();
//		mCat.addResource("moduleTest", 1, 10, "hello test");
//		mCat.getModule(3).editResource("edited module", 1, 10, "helloooo");
//		mCat.readAllResources();	
//		mCat.deleteResource(14);

//		PhysicalResourceCatalogue physCat = new PhysicalResourceCatalogue();
//		physCat.addResource("phys res test", 1, "model desc", "hello");
//		physCat.readAllResources();
//		physCat.getPhysicalResource(3).editResource("physName changed", 1, "model changed", "hello changed");
//		physCat.deleteResource(15);

		// test employee
//		EmployeeCatalogue empCat = new EmployeeCatalogue();
//		empCat.signUp(false, "negar Ghorbani", "developer", "negar", "123", false, false);
//		empCat.readAllEmployees();
//		empCat.makeDecision(4, true);
//		
//		Employee emp = new Employee();
//		if((emp.login("negar","123"))){
//			System.out.println("huraaa : " + AuthenticatedEmployee.getInstance().getEmployee().getUsername());
//		}else{
//			System.out.println("shit");
//		}
//		System.out.println(empCat.getEmployee(4).getSectionId() + " &&&&&&&&&&");
//		empCat.deleteEmployee(4);
		
		//test project
//		EmployeeCatalogue empcat = new EmployeeCatalogue();
//		Employee emp = empcat.getEmployee(3);
//		ProjectCatalogue projCat = new ProjectCatalogue();
//		projCat.getProjects();
//		System.out.println(";;;;;;;;;;;;;");
//		projCat.addProject("negarProject", emp, "10", "Java");
//		projCat.searchProjects("10", "java");
//		projCat.deleteProject(2);
		
		// test ProjectEmployee
//		ProjectEmployeeCatalogue prempcat = new ProjectEmployeeCatalogue();
//		prempcat.addProjectEmployee(1, 1);
//		prempcat.addProjectEmployee(1, 2);
//		ArrayList<ProjectEmployee> premps = prempcat.getProjectEmployeesByProject(1);
//		System.out.println("%%%%%%%%%%%%%%");
//		for (int i = 0; i < premps.size(); i++) {
//			System.out.println(premps.get(i).getEmployee().getId());
//		}
//		prempcat.deleteProjectEmployee(1, 1);
//		prempcat.deleteProjectEmployee(1, 2);
		
		// test subSystem:
//		SubSystemCatalogue subCat = new SubSystemCatalogue();
//		subCat.addSubSystem("subTest",1,1);
//		System.out.println(subCat.getSubSystem(2).getId());
//		subCat.getSubSystemsByProject(1);
//		subCat.deleteSubSystem(2);
		
		//test Section
//		SectionCatalogue secCat = new SectionCatalogue();
//		secCat.addSection("secTest");
//		secCat.getSections();
//		secCat.deleteSection(7);

		
		//test resourcerequirement:
//		ResourceRequirementCatalogue resreqCat = new ResourceRequirementCatalogue();
//		resreqCat.addResourceRequirement(1, 1, 1, "2016-01-01", "2016-09-09");
//		ArrayList<ResourceRequirement> resreqs = resreqCat.getResourceRequirements();
//		for (int i = 0; i < resreqs.size(); i++) {
//			System.out.println(resreqs.get(i).toHashMap());
//		}
//		System.out.println("get:");
//		System.out.println(resreqCat.getResourceRequirement(1,1,1,"2016-04-04","2016-05-05").toHashMap());
//		System.out.println("satisfy:");
//		resreqCat.getResourceRequirement(1,1,1,"2016-04-04","2016-05-05").satisfy("2016-12-12");
//		System.out.println("edit:");
//		resreqCat.getResourceRequirement(1,1,1,"2016-04-04","2016-05-05").edit("2016-08-08", "2016-09-09", false, "1111-11-11");
//		resreqCat.deleteResourceRequirement(1,1,1,"2016-08-08","2016-09-09");

		
		// test projectresourceutilization
//		ProjectResourceUtilizationCatalogue projres = new ProjectResourceUtilizationCatalogue();
//		System.out.println("read all:");
//		ArrayList<ProjectResourceUtilization> pru = projres.getProjectResourceUtilizations();
//		for (int i = 0; i < pru.size(); i++) {
//			System.out.println(pru.get(i).toString());
//		}
//
//		System.out.println("add:");
//		projres.addProjectResourceUtilization(3,2,1,"2016-01-01","2016-01-01");
//		System.out.println("get :");
//		System.out.println(projres.getProjectResourceUtilization(3,2,1,"2016-01-01","2016-01-01").toHashMap());
//		projres.getProjectResourceUtilization(3,2,1,"2016-01-01","2016-01-01").edit("2016-04-04", "2016-04-05");
//		System.out.println("delete");
//		projres.deleteProjectResourceUtilization(3, 2, 1,"2016-04-04","2016-04-05");
		

		//test sign up
//		EmployeeCatalogue empcat = new EmployeeCatalogue();
//		empcat.signUp(false, "negar", "developer", "negar", "123", false);
//		ArrayList<HashMap<String, String>> registered_users;
//		registered_users=empcat.getRegistrations();
//		System.out.println(registered_users);
//		empcat.makeDecision(9, true);
		
//		MaintainingModuleCatalogue mmcat = new MaintainingModuleCatalogue();
//		mmcat.addMaintainingModule(5,"edit", 5);
//		ArrayList<MaintainingModule> mm = mmcat.getMaintainingModules(5);
//		for (int i = 0; i < mm.size(); i++) {
//			System.out.println(mm.get(i).toHashMap());
//		}
//		mmcat.deleteMaintainingModule(2);
		
	}
}
