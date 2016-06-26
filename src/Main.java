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
//		PhysicalResourceCatalogue physResCat = new PhysicalResourceCatalogue();
//		physResCat.getReport().printRep();
//		System.out.println("This is Resource Requirement Report:");
//		ResourceRequirementCatalogue resReqCat = new ResourceRequirementCatalogue();
//		ProjectCatalogue projCat = new ProjectCatalogue();
//		Project proj = projCat.getProject(1);
//		resReqCat.getReport(proj).printRep();
//		System.out.println("Yay! ");
		//Employee gholam = new Employee();
		//gholam.getFromDB(1);
		//gholam.setAccessRight(1);
//		ProjectResourceUtilizationCatalogue projResUtil = new ProjectResourceUtilizationCatalogue();
//		ResourceCatalogue resCat = new ResourceCatalogue();
//		Resource res = resCat.getResource(17);
//		projResUtil.getReport(res).printRep();
//		if(emp.logout()){
//			System.out.println("logged out");
//		}
		
		// test search resource
//		ModuleCatalogue mcat = new ModuleCatalogue();
//		HashMap <String,String> searchVars = new HashMap<String,String>();
//		searchVars.put("modname", "\'"+"module4"+"\'");
//		mcat.SearchResource(searchVars);
		
		
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
//		ResourceCatalogue rs = new ResourceCatalogue();
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
//		subCat.getSubSystemsbyProject(7);
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
//		ArrayList<ResourceRequirement> resreqs = resreqCat.getResourceRequirements();
//		for (int i = 0; i < resreqs.size(); i++) {
//			System.out.println(resreqs.get(i).toHashMap());
//		}
//		resreqCat.addResourceRequirement(3,2,8,"2016-01-01","2016-01-02");
//		System.out.println("get:");
//		System.out.println(resreqCat.getResourceRequirement(3,2,8,"2016-01-01","2016-01-02").toHashMap());
//		System.out.println("satisfy:");
//		resreqCat.getResourceRequirement(3,2,8,"2016-01-01","2016-01-02").satisfy("2016-09-09");
//		System.out.println("edit:");
//		resreqCat.getResourceRequirement(3,2,8,"2016-01-01","2016-01-02").edit("2016-04-04", "2016-04-05", false, "1111-11-11");
//		System.out.println("get:");
//		System.out.println(resreqCat.getResourceRequirement(3,2,8,"2016-04-04","2016-04-05").toHashMap());
//
//	
//		resreqCat.deleteResourceRequirement(3,2,8,"2016-01-01","2016-01-02");
//		System.out.println("after delete:");
//		ArrayList<ResourceRequirement> resreqsss = resreqCat.getResourceRequirements();
//		for (int i = 0; i < resreqsss.size(); i++) {
//			System.out.println(resreqsss.get(i).toString());
//		}
//


		// test projectresourceutilization
		
		
		ProjectResourceUtilizationCatalogue projres = new ProjectResourceUtilizationCatalogue();
//		System.out.println("read all:");
//		ArrayList<ProjectResourceUtilization> pru = projres.getProjectResourceUtilizations();
//		for (int i = 0; i < pru.size(); i++) {
//			System.out.println(pru.get(i).toString());
//		}
//
//		System.out.println("add:");
//		projres.addProjectResourceUtilization(3,2,9,"2016-01-01","2016-01-01");
//		System.out.println("get :");
//		System.out.println(projres.getProjectResourceUtilization(3,2,9,"2016-01-01","2016-01-01").toHashMap());
//		projres.getProjectResourceUtilization(3,2,9,"2016-01-01","2016-01-01").edit("2016-04-04", "2016-04-05");
//		System.out.println("get :");
//		System.out.println(projres.getProjectResourceUtilization(3,2,9,"2016-04-04","2016-04-05").toHashMap());
//		System.out.println("delete");
//		projres.deleteProjectResourceUtilization(3, 2, 9,"2016-01-04","2016-01-05");
//		System.out.println("read all");
//		ArrayList<ProjectResourceUtilization> prus = projres.getProjectResourceUtilizations();
//		for (int i = 0; i < prus.size(); i++) {
//			System.out.println(prus.get(i).toString());
//		}
		
//		ArrayList<ProjectResourceUtilization> pru = projres.getProjectResourceUtilizationbyProject(6);
//		for (int i = 0; i < pru.size(); i++) {
//			System.out.println(pru.get(i).toString());
//		}

		
		//test sign up
//		ArrayList<HashMap<String, String>> registered_users;

//		registered_users=empcat.getRegistrations();

//		empcat.makeDecision(5, false);
		
	}
}
