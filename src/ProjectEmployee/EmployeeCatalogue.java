package ProjectEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import AccessRight.AccessRight;
import DataBase.DataBase;
import DataBase.Table;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class EmployeeCatalogue {
	DataBase DB;

	public EmployeeCatalogue() {
		DB = new DataBase();
	}

	public void makeDecision(int empid, boolean decision) {

		if (decision) {
			ResourceCatalogue resCat = new ResourceCatalogue();
			String empname = getEmployee(empid).getName();
			int rid = (int) resCat.addResource(empname,6);

			HashMap<String, String> setVars = new HashMap<String, String>();
			setVars.put("is_confirmed", "true");
			setVars.put("sectionid", Integer.toString(6));
			setVars.put("rid", Integer.toString(rid));
			setVars.put("accessrightid", Integer.toString(1));
			HashMap<String, String> condVars = new HashMap<String, String>();
			condVars.put("empid", Integer.toString(empid));
			DB.update(condVars, setVars, "EMPLOYEE");

		} else {
			HashMap<String, String> vars = new HashMap<String, String>();
			vars.put("empid", Integer.toString(empid));
			DB.delete(vars, "employee");
		}
	}

	public ArrayList<HashMap<String, String>> getRegistrations() {

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("is_confirmed", "false");
		Table table = new Table("employee");
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;

	}
	

	public ArrayList<HashMap<String, String>> readAllEmployees() {
		Table table = new Table("employee");
		ArrayList<HashMap<String, String>> result = table.readAll();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;
	}

	public void deleteEmployee(int id) {
		ResourceCatalogue resCat = new ResourceCatalogue();
		EmployeeCatalogue empCat = new EmployeeCatalogue();

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(id));
		DB.delete(vars, "employee");
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

		long pk = DB.insert(vars, "employee");
		Employee employee = new Employee();
		employee.getFromDB((int) pk);
		return employee;
		
	}


	public Employee addEmployee(boolean ismodir, String empname, String post, int sectionId, String username,
			String password, boolean is_loggedin, boolean is_confirmed) {

		ResourceCatalogue resCat = new ResourceCatalogue();
		int rid = (int) resCat.addResource(empname,sectionId);

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empname", "\'" + empname + "\'");
		vars.put("sectionid", Integer.toString(sectionId));
		vars.put("post", "\'" + post + "\'");
		vars.put("ismodir", Boolean.toString(ismodir));
		vars.put("username", "\'" + username + "\'");
		vars.put("password", "\'" + password + "\'");
		vars.put("is_loggedin", Boolean.toString(is_loggedin));
		vars.put("is_confirmed", Boolean.toString(is_confirmed));
		vars.put("rid", Integer.toString(rid));

		long pk = DB.insert(vars, "employee");
		Employee employee = new Employee();
		employee.getFromDB((int) pk);
		return employee;
		
	}

	public Employee getEmployee(int empid) {
		Employee emp = new Employee();
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(empid));
		ResultSet res = DB.select("employee",vars,null);
		try {
			if (res.next()) {
				emp.setId(res.getInt("empid"));
				emp.setName(res.getString("empname"));
				emp.setUsername(res.getString("username"));
				emp.setSectionId(res.getInt("sectionid"));
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
		
		Table table = new Table("employee");
		ArrayList<HashMap<String, String>> result = table.search(searchvars);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		return result;		
	}


}