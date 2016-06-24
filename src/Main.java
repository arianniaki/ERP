import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ProjectEmployee.AuthenticatedEmployee;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.SubSystem.SubSystemCatalogue;
import RequirementUtilization.*;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.*;

public class Main {
	public static void main(String[] args) throws ParseException {
		// test employee
		/*Employee emp = new Employee();
		if((emp.login("ali","123"))){
			System.out.println("huraaa" + AuthenticatedEmployee.getInstance().getEmployee().getUsername());
		}else{
			System.out.println("shit");
		}*/
		PhysicalResourceCatalogue physResCat = new PhysicalResourceCatalogue();
		physResCat.getReport().printRep();
		
		//Employee gholam = new Employee();
		//gholam.getFromDB(1);
		//gholam.setAccessRight(1);
		
//		if(emp.logout()){
//			System.out.println("logged out");
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
//
//		InformationResourceCatalogue rscat = new InformationResourceCatalogue();
		ResourceCatalogue rs = new ResourceCatalogue();
//		rs.readAllResources();
//		System.out.println();
//		rscat.addResource(90, 0, "temp");
//		System.out.println();
//		rscat.readAllResources();
//		System.out.println();
//		rs.readAllResources();
//
//		rscat.deleteResource(90);
//		System.out.println();
//		rscat.readAllResources();
//		System.out.println();
//		rs.readAllResources();

//		
//		// test EmployeeCatalogue
//		empcat.readAllEmployees();
//		empcat.addEmployee(12, false, "gholi", "hammal", 5, "gholi", "123", false);
//		System.out.println(":::::: after insert ::::::::");
//		empcat.readAllEmployees();
//		empcat.deleteEmployee(10);
//		System.out.println(":::::: after delete ::::::::");
//
//		empcat.readAllEmployees();
//
		
		//test project
//		EmployeeCatalogue empcat = new EmployeeCatalogue();
//
//		Employee emp = empcat.getEmployee(12);
//
//		ProjectCatalogue projCat = new ProjectCatalogue();
//		projCat.getProjects();
//		System.out.println(";;;;;;;;;;;;;");
//		projCat.searchProjects("10", "java");
		
		// test subSystem:
//		SubSystemCatalogue subCat = new SubSystemCatalogue();
//		subCat.addSubSystem("sub1", 6);
//		subCat.addSubSystem("sub2", 7);
//		subCat.getSubSystems();
//		subCat.deleteSubSystem(2);
//		subCat.getSubSystems();
		
		//test Section
//		SectionCatalogue secCat = new SectionCatalogue();
//		secCat.addSection("sec1");
//		secCat.addSection("sec2");
//		secCat.getSections();
//		secCat.deleteSection(7);
//		secCat.getSections();
//		secCat.deleteSection(8);
//		secCat.getSections();

//		String strDate = "2011-12-31";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//        java.util.Date date = sdf.parse(strDate);
//		Date sqldate = new Date(date.getTime());
//		System.out.println(sqldate);
//	    System.out.println(date.toString());
		
		//test resourcerequirement:
//		ResourceRequirementCatalogue resreqCat = new ResourceRequirementCatalogue();
//		resreqCat.getResourceRequirements();
//		resreqCat.addResourceRequirement(2, 1, 7, "2016-01-02", "2016-01-03");
//		resreqCat.getResourceRequirements();
//		resreqCat.deleteResourceRequirement(2, 1, 7);
//		resreqCat.getResourceRequirements();

		// test projectresourceutilization
		
		
//		ProjectResourceUtilizationCatalogue projres = new ProjectResourceUtilizationCatalogue();
//		projres.getProjectResourceUtilizations();
//		projres.addProjectResourceUtilization(2, 1, 7);
//		projres.getProjectResourceUtilizations();
//		projres.deleteProjectResourceUtilization(2, 1, 7);
//		projres.getProjectResourceUtilizations();

		
		
	}
}
