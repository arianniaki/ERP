package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import RequirementUtilization.ResourceRequirement;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;

public class MakeModuleCatalogue {
	DataBase DB;
	String resTableName;
	String empTableName;
	public MakeModuleCatalogue(){
		DB = DB.getInstance();
		resTableName = "moduleresourceutilization";
		empTableName = "moduleemployee";
	}
	
	public ArrayList<Employee> getEmployees(int modrid){
		ArrayList<Employee> empRes = new ArrayList<Employee>();
		Table table = new Table(empTableName);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modrid", Integer.toString(modrid));
		ArrayList<HashMap<String, String>> result = table.search(vars);
		EmployeeCatalogue empcat = new EmployeeCatalogue();
		for (int i = 0; i < result.size(); i++) {
			Employee emp = empcat.getEmployee(Integer.parseInt(result.get(i).get("empid")));
			empRes.add(emp);
			
				}
		return empRes;
	}
	
	public ArrayList<Resource> getResources(int  modrid){
		ArrayList<Resource> resRes = new ArrayList<Resource>();
		Table table = new Table(resTableName);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modrid", Integer.toString(modrid));
		ArrayList<HashMap<String, String>> result = table.search(vars);
		ResourceCatalogue rescat = new ResourceCatalogue();
		for (int i = 0; i < result.size(); i++) {
			Resource res = rescat.getResource(Integer.parseInt(result.get(i).get("rid")));
			resRes.add(res);
		}
		return resRes;
	}
	
	public long addEmployee(int empid, int modrid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", "\'"+empid+"\'");
		vars.put("modrid", "\'"+modrid+"\'");	
		long pk=DB.insert(vars, empTableName);
		return pk;
	}
	
	public long addResource(int resid, int modrid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+resid+"\'");
		vars.put("modrid", "\'"+modrid+"\'");	
		long pk=DB.insert(vars, resTableName);
		return pk;
	}
	
	public void deleteEmployee(int empid, int modrid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", "\'"+empid+"\'");
		vars.put("modrid", "\'"+modrid+"\'");	
		DB.delete(vars, empTableName);
	}
	
	public void deleteResource(int resid, int modrid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+resid+"\'");
		vars.put("modrid", "\'"+modrid+"\'");	
		DB.delete(vars, resTableName);
	}
	
}
