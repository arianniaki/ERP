package ProjectEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import AccessRight.AccessRight;
import AccessRight.AccessRightCatalogue;
import DataBase.DataBase;
import DataBase.Table;
import Report.Report;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class EmployeeCatalogue {
	private static EmployeeCatalogue employeeCat = new EmployeeCatalogue();
	DataBase DB;
	String tablename = "employee";
	private EmployeeCatalogue() {
		DB = DB.getInstance();
	}
	public static EmployeeCatalogue getInstance(){
		return employeeCat;
	}
	public void makeDecision(int empid, boolean decision) {

		if (decision) {
			ResourceCatalogue resCat = new ResourceCatalogue();
			String empname = getEmployee(empid).getName();
			int rid = (int) resCat.addResource(empname,6);

			HashMap<String, String> setVars = new HashMap<String, String>();
			setVars.put("is_confirmed", "true");
			setVars.put("sid", Integer.toString(6));
			setVars.put("rid", Integer.toString(rid));
			setVars.put("accessrightid", Integer.toString(1));
			HashMap<String, String> condVars = new HashMap<String, String>();
			condVars.put("empid", Integer.toString(empid));
			DB.update(condVars, setVars, tablename);

		} else {
			HashMap<String, String> vars = new HashMap<String, String>();
			vars.put("empid", Integer.toString(empid));
			DB.delete(vars, tablename);
		}
	}

	public ArrayList<HashMap<String, String>> getRegistrations() {

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("is_confirmed", "false");
		Table table = new Table(tablename);
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;

	}
	

	public ArrayList<HashMap<String, String>> getConfirmedEmployees() {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("is_confirmed", "true");
		Table table = new Table(tablename);
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
			AccessRight acc = new AccessRight(Integer.parseInt(result.get(i).get("accessrightid")));
			result.get(i).put("accessrightname", acc.getName());
			SectionCatalogue secCat = SectionCatalogue.getInstance();
			Section sec = secCat.getSection(Integer.parseInt(result.get(i).get("sid")));
			result.get(i).put("sectionname", sec.getName());
		}
		return result;
	}

	
	
	public ArrayList<HashMap<String, String>> readAllEmployees() {
		Table table = new Table(tablename);
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
			AccessRight acc = new AccessRight(Integer.parseInt(result.get(i).get("accessrightid")));
			result.get(i).put("accessrightname", acc.getName());
		}
		return result;
	}

	public void deleteEmployee(int id) {
		ResourceCatalogue resCat = new ResourceCatalogue();
		EmployeeCatalogue empCat = new EmployeeCatalogue();

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(id));
		DB.delete(vars, tablename);
		resCat.deleteResource(empCat.getEmployee(id).getResId());

	}

	public Employee signUp(boolean ismodir, String empname, String post, String username,
			String password, boolean is_loggedin) {

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empname", "\'" + empname + "\'");
		vars.put("post", "\'" + post + "\'");
		vars.put("ismodir", Boolean.toString(ismodir));
		vars.put("username", "\'" + username + "\'");
		vars.put("password", "\'" + password + "\'");
		vars.put("is_loggedin", Boolean.toString(is_loggedin));
		vars.put("is_confirmed", Boolean.toString(false));

		long pk = DB.insert(vars, tablename);
		Employee employee = new Employee();
		employee.getFromDB((int) pk);
		return employee;
		
	}


	public Employee addEmployee(String empname, String post, int sectionId, String username,
			String password, boolean is_loggedin, boolean is_confirmed) {

		ResourceCatalogue resCat = new ResourceCatalogue();
		int rid = (int) resCat.addResource(empname,sectionId);

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empname", "\'" + empname + "\'");
		vars.put("sid", Integer.toString(sectionId));
		vars.put("post", "\'" + post + "\'");
		vars.put("username", "\'" + username + "\'");
		vars.put("password", "\'" + password + "\'");
		vars.put("is_loggedin", Boolean.toString(is_loggedin));
		vars.put("is_confirmed", Boolean.toString(is_confirmed));
		vars.put("rid", Integer.toString(rid));

		long pk = DB.insert(vars, tablename);
		if(pk==-1)
			return null;
		else{
			Employee employee = new Employee();
			employee.getFromDB((int) pk);
			employee.setDefaultAccessRight();
			return employee;
				
		}
	}
	

	public Employee getEmployee(int empid) {
		Employee emp = new Employee();
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(empid));
		ResultSet res = DB.select(tablename,vars,null);
		try {
			if (res.next()) {
				emp.setId(res.getInt("empid"));
				emp.setName(res.getString("empname"));
				emp.setUsername(res.getString("username"));
				emp.setSectionId(res.getInt("sid"));
				emp.setPassword(res.getString("password"));
				emp.setAccessRight(new AccessRight(res.getInt("accessrightid")));
				emp.setResId(res.getInt("rid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;

	}
	
	public ArrayList<HashMap<String, String>> SearchEmployee(HashMap<String, String> searchvars){
		
		Table table = new Table(tablename);
		ArrayList<HashMap<String, String>> result = table.search(searchvars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
			AccessRight acc = new AccessRight(Integer.parseInt(result.get(i).get("accessrightid")));
			result.get(i).put("accessrightname", acc.getName());
		}
		return result;		
	}
	
	public Report getReport(){
		
		Report rep = new Report();
		ArrayList<HashMap<String,String>> results = readAllEmployees();
		rep.setResults(results);
		for(int i=0; i<results.size(); i++){
			String line="";
			SectionCatalogue secCat = SectionCatalogue.getInstance();
			results.get(i).put("sname", ""+secCat.getSection(Integer.valueOf(results.get(i).get("sid"))).getName());
			for(String key : results.get(i).keySet()){
				line+= results.get(i).get(key).toString()+", ";
			}
			line = line.substring(0, line.length() - 2);
			rep.addLine(line);
		}
		return rep;
	}
}