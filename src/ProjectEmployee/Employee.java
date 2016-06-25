package ProjectEmployee;

import java.util.HashMap;

import AccessRight.AccessRight;
import DataBase.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
	private int id;
	private String name;
	private int sectionId;
	private boolean isManager;
	private String username;
	private String password;
	public boolean loggedin;
	private String post;
	private AccessRight accessRight;
	private boolean is_confirmed;
	DataBase DB;

	public Employee() {
		DB = new DataBase();
	}
	
	public void editHuman(String name, int sectionId, String password, String post){
		this.name = name;
		this.sectionId = sectionId;
		this.password = password;
		this.post = post;
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("empname", "\'"+name+"\'");
		setVars.put("sectionid", Integer.toString(sectionId));
		setVars.put("password", "\'"+password+"\'");
		setVars.put("post", "\'"+post+"\'");
		submitToDB(setVars);

	}

	public void editEmployeeInformation(String name, String password){
		this.name = name;
		this.password = password;
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("empname", "\'"+name+"\'");
		setVars.put("password", "\'"+password+"\'");
		submitToDB(setVars);
	}

	
	public boolean login(String username, String password) {
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("username", "\'"+username+"\'");
		vars.put("password", "\'"+password+"\'");
		System.out.println(vars.toString());
		ResultSet results = DB.select("Employee", vars, null);

		boolean ret = false;
		try {
			if (results.next()) {
				getFromDB(results.getInt("empId"));
				AuthenticatedEmployee auth = AuthenticatedEmployee.getInstance();
				if(auth.setEmployee(this) && this.is_confirmed){
					this.loggedin = true;
					HashMap<String, String> setVars = new HashMap<String, String>();
					setVars.put("is_loggedin", Boolean.toString(this.loggedin));
					submitToDB(setVars);
					ret = true;
				}
			}
			results.close();

		} catch (SQLException e) {
			System.out.println(e);
			ret = false;
		}
		return ret;
	}

	public boolean logout() {
		AuthenticatedEmployee auth = AuthenticatedEmployee.getInstance();
		if(auth.getEmployee().getId() == this.id){
			this.loggedin = false;
			HashMap<String, String> setVars = new HashMap<String, String>();
			setVars.put("is_loggedin", Boolean.toString(this.loggedin));
			submitToDB(setVars);
			auth.logoutEmployee();
			return true;
		}
		return false;
		
	}

	public void setId(int inputId) {
		this.id = inputId;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String inputName) {
		this.name = inputName;
	}

	public String getName() {
		return this.name;
	}

	public void setSectionId(int inputSectionId) {
		this.sectionId = inputSectionId;
	}

	public int getSectionId() {
		return this.sectionId;
	}

	public void setUsername(String inputUsername) {
		this.username = inputUsername;
	}

	public String getUsername() {
		return this.username;
	}

	void setPassword(String inputPassword) {
		this.password = inputPassword;
	}

	String getPassword() {
		return this.password;
	}

	public void promoteManager() {
		this.isManager = true;
	}

	public void demoteManager() {
		this.isManager = false;
	}

	public void setDefaultAccessRight(){
		setAccessRight(new AccessRight(1));
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("accesrightid", 1+"");
		submitToDB(setVars);
	}

	public boolean setAccessRight(int id){
		if(AuthenticatedEmployee.getInstance().getEmployee().accessRight.getName().equals("super")){
			accessRight = new AccessRight(id);
			HashMap<String, String> setVars = new HashMap<String, String>();
			setVars.put("accessrightid", id+"");
			submitToDB(setVars);
			return true;
		}
		return false;
	}

	public void submitToDB(HashMap<String, String> setVars) {
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("empid", Integer.toString(this.id));
		DB.update(condVars, setVars, "EMPLOYEE");

	}

	public boolean getFromDB(int empId){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", Integer.toString(empId));
		ResultSet rs = DB.select("Employee", vars, null);
		try {
			if (rs.next()) {
				this.id = rs.getInt("empid");
				this.name = rs.getString("empname");
				this.isManager = rs.getBoolean("ismodir");
				this.password = rs.getString("password");
				this.username = rs.getString("username");
				this.sectionId = rs.getInt("sectionid");
				this.post = rs.getString("post");
				this.setAccessRight(new AccessRight(rs.getInt("accessrightid")));
				this.is_confirmed = rs.getBoolean("is_confirmed");
			}
			rs.close();
			DB.connectionClose();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return false;
		}
	}


	public AccessRight getAccessRight() {
		return accessRight;
	}


	public void setAccessRight(AccessRight accessRight) {
		this.accessRight = accessRight;
	}
}