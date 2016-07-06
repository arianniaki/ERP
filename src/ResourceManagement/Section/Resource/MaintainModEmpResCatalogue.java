package ResourceManagement.Section.Resource;

import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;

public class MaintainModEmpResCatalogue {
	DataBase DB;
	String resTableName;
	String empTableName;
	public MaintainModEmpResCatalogue() {
		DB = DB.getInstance();
		resTableName = "maintainmodres";
		empTableName = "maintainmodemp";
	}
	
	public ArrayList<Employee> getEmployees(int  maintainid){
		ArrayList<Employee> empRes = new ArrayList<Employee>();
		Table table = new Table(empTableName);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("maintainid", Integer.toString(maintainid));
		ArrayList<HashMap<String, String>> result = table.search(vars);
		EmployeeCatalogue empcat = new EmployeeCatalogue();
		for (int i = 0; i < result.size(); i++) {
			Employee emp = empcat.getEmployee(Integer.parseInt(result.get(i).get("empid")));
			empRes.add(emp);
			
				}
		return empRes;
	}
	
	public ArrayList<Resource> getResources(int  maintainid){
		ArrayList<Resource> resRes = new ArrayList<Resource>();
		Table table = new Table(resTableName);
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("maintainid", Integer.toString(maintainid));
		ArrayList<HashMap<String, String>> result = table.search(vars);
		ResourceCatalogue rescat = new ResourceCatalogue();
		for (int i = 0; i < result.size(); i++) {
			Resource res = rescat.getResource(Integer.parseInt(result.get(i).get("rid")));
			resRes.add(res);
		}
		return resRes;
	}
	
	public long addEmployee(int empid, int maintainid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", "\'"+empid+"\'");
		vars.put("maintainid", "\'"+maintainid+"\'");	
		long pk=DB.insert(vars, empTableName);
		return pk;
	}
	
	
	public long addResource(int resid, int maintainid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+resid+"\'");
		vars.put("maintainid", "\'"+maintainid+"\'");	
		long pk=DB.insert(vars, resTableName);
		return pk;
	}
	
	public void deleteEmployee(int empid, int maintainid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", "\'"+empid+"\'");
		vars.put("maintainid", "\'"+maintainid+"\'");	
		DB.delete(vars, empTableName);
	}
	
	public void deleteResource(int resid, int maintainid) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("rid", "\'"+resid+"\'");
		vars.put("maintainid", "\'"+maintainid+"\'");	
		DB.delete(vars, resTableName);
	}
	
}
