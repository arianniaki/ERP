package UserManagement;
import java.util.HashMap;
import DataBase.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Employee{
	private int id;
	private String name;
	private int sectionId;
	private boolean isManager;
	private String username;
	private String password;
	boolean loggedin;
	DataBase DB;
	public void Employee(){
		DB = new DataBase();
	}
	boolean login(String username, String password){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("username", username);
		vars.put("password", password);
		ResultSet results = DB.select(vars, "Employee");
		boolean ret = false;
		try {
			if( results.next() ){
				getFromDB(results.getInt("empId"));
				this.loggedin = true;
				submitToDB();
				ret = true;
			}
			 results.close();
			
		} catch (SQLException e) {
			System.out.println(e);
			ret = false;
		}
		return ret;
	}
	boolean logout(){
		boolean ret = false;
		this.loggedin = false;
		submitToDB();
		return true;
	}
	void setId(int inputId){
		this.id = inputId;
	}
	int getId(){
		return this.id;
	}
	void setName(String inputName){
		this.name = inputName;
	}
	String getName(){
		return this.name;
	}
	void setSectionId(int inputSectionId){
		this.sectionId = inputSectionId;
	}
	int getSectionId(){
		return this.sectionId;
	}
	
	void setUsername(String inputUsername){
		this.username = inputUsername;
	}
	String getUsername(){
		return this.username;
	}
	
	void setPassword(String inputPassword){
		this.password = inputPassword;
	}
	String getPassword(){
		return this.password;
	}
	void promoteManager(){
		this.isManager = true;
	}
	void demoteManager(){
		this.isManager = false;
	}
	public void submitToDB(){
		/* link mizane be DB etelaAt ro too ye satre jadid insert mikone!*/
	}
	public void getFromDB(int empId){
		/* az DB migire hamaro set mikone! */
	}
	
}