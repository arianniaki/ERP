package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class DataBase{
	static Connection c = null;
    static Statement stmt = null;
    public static void main(){
    	HashMap<String, String> vars = new HashMap<String, String>();
    	vars.put("id", "123");
    	vars.put("name", "arian");
    	vars.put("is_modir", "false");
    	insert(vars, "EMPLOYEE");
    }
	public void DataBase(){
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ERP","postgres", "123456m.");
			c.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		} catch (SQLException e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	    
	}
	static public boolean insert(HashMap<String, String> vars, String tableName){
		try{
			stmt = c.createStatement();
			String sql = "INSERT INTO "+tableName+" (";
			for(String key : vars.keySet()){
				sql+=key+",";
			}
			sql = sql.substring(0,sql.length()-1)+") VALUES (";
			for(String value : vars.values()){
				sql+=value+",";
			}
			sql = sql.substring(0,sql.length()-1)+");";
			System.out.println(sql);
			return true;
		} catch (SQLException e){
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			return false;
		}
		
	}
	public boolean select(HashMap<String, String> vars, String tableName){
		return false;
	}
	public boolean update(HashMap<String, String> vars, String tableName){
		return false;
	}
	public boolean delete(HashMap<String, String> vars, String tableName){
		return false;
	}
}