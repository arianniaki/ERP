package ProjectEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;

public class EmployeeCatalogue {
	DataBase DB;

	public EmployeeCatalogue() {
		DB = new DataBase();
	}

	public void makeDecision(int empid, boolean decision) {

		if (decision) {
			HashMap<String, String> setVars = new HashMap<String, String>();
			setVars.put("is_confirmed", "true");
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
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(id));
		DB.delete(vars, "employee");
	}

	public void addEmployee(int empid, boolean ismodir, String empname, String post, int sectionId, String username,
			String password, boolean is_loggedin, boolean is_confirmed) {

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(empid));
		vars.put("empname", "\'" + empname + "\'");
		vars.put("sectionid", Integer.toString(sectionId));
		vars.put("post", "\'" + post + "\'");
		vars.put("ismodir", Boolean.toString(ismodir));
		vars.put("username", "\'" + username + "\'");
		vars.put("password", "\'" + password + "\'");
		vars.put("is_loggedin", Boolean.toString(is_loggedin));
		vars.put("is_confirmed", Boolean.toString(is_confirmed));

		DB.insert(vars, "employee");
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;

	}

}