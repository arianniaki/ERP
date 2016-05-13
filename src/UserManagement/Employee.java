package UserManagement;

public class Employee{
	private int id;
	private String name;
	private int sectionId;
	private boolean isManager;
	private String username;
	private String password;
	boolean loggedin;
	boolean login(String username, String password){
		
		return false;
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