package DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ProjectEmployee.AuthenticatedEmployee;

public class DataBase {
	private static DataBase database = new DataBase();
	private Connection c = null;
	private Statement stmt = null;
	private String dbName = "jdbc:postgresql://localhost:5432/erp";
	private String user = "postgres";
	private String pass = "123456m.";
	private String port = "5432";
	public static DataBase getInstance(){
		return database;
	}
	private DataBase(){
		File file = new File("userInfo.txt");
		try {
			Scanner scn = new Scanner(file);
			user = scn.nextLine();
			pass = scn.nextLine();
			this.setPort(scn.nextLine());
			scn.close();
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbName, user, pass);
			c.setAutoCommit(false); 
			System.out.println("insert : Opened database successfully");
		} catch (FileNotFoundException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
		dbName = "jdbc:postgresql://localhost:"+port+"/erp";
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}



	public long insert(HashMap<String, String> vars, String tableName) {
		long pk=0;
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO " + tableName + " (";
			for (String key : vars.keySet()) {
				sql += key + ",";
			}
			sql = sql.substring(0, sql.length() - 1) + ") VALUES (";
			for (String value : vars.values()) {
				sql += value + ",";
			}
			sql = sql.substring(0, sql.length() - 1) + ");";
			System.out.println(sql);
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			pk = rs.getLong(1);
			System.out.println("SA:"+pk);

			stmt.close();
			c.commit();
			System.out.println("insert : Closed database successfully");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return -1;
		}
		System.out.println("Records created successfully");
		return pk;

	}

	public ResultSet select(String tableName, HashMap<String, String> vars, ArrayList<String> groupBy) {
		try {
			stmt = c.createStatement();
			String sql = new String();
			if(groupBy!=null){
				sql = "SELECT "+groupBy.get(0)+", sid, count(*) FROM "+tableName;
			}
			else{
				sql = "SELECT * FROM " + tableName;
			}
			if(vars!=null){
				sql += " where ";
				for (String key : vars.keySet()) {
					sql += key + "=" + vars.get(key) + " AND ";
				}
				sql = sql.substring(0, sql.length() - 5);
			}
			if(groupBy!=null){
				sql += " group by ";
				for (int i=0; i<groupBy.size(); i++)
					sql += groupBy.get(i) + ", ";
				sql = sql.substring(0, sql.length() - 2);
			}
			sql+=";";
			ResultSet rs = stmt.executeQuery(sql);
			// stmt.close();
			// c.close();
			return rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}

	public ResultSet search(String tableName, HashMap<String, String> exactVars, HashMap<String,String> simVars, ArrayList<String> groupBy) {
		try {
			stmt = c.createStatement();
			String sql = new String();
			if(groupBy!=null){
				sql = "SELECT "+groupBy.get(0)+", sid, count(*) FROM "+tableName;
			}
			else{
				sql = "SELECT * FROM " + tableName;
			}
			if(exactVars!=null){
				sql += " where ";
				for (String key : exactVars.keySet()) {
					sql += key + "=" + exactVars.get(key) + " AND ";
				}
				for (String key : simVars.keySet()) {
					sql += key + "~" + simVars.get(key) + " AND ";
				}
				sql = sql.substring(0, sql.length() - 5);
			}
			if(groupBy!=null){
				sql += " group by ";
				for (int i=0; i<groupBy.size(); i++)
					sql += groupBy.get(i) + ", ";
				sql = sql.substring(0, sql.length() - 2);
			}
			sql+=";";
			ResultSet rs = stmt.executeQuery(sql);
			// stmt.close();
			// c.close();
			return rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}

	public boolean update(HashMap<String, String> condVars,
			HashMap<String, String> setVars, String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			stmt = c.createStatement();
			String sql = "UPDATE " + tableName + " set ";

			for (String key : setVars.keySet()) {
				sql += key + "=" + setVars.get(key) + ", ";
			}
			sql = sql.substring(0, sql.length() - 2) + " where ";
			for (String key : condVars.keySet()) {
				sql += key + "=" + condVars.get(key) + " AND ";
			}
			sql = sql.substring(0, sql.length() - 5) + ";";
			System.out.println(sql);
			int out = stmt.executeUpdate(sql);
			c.commit();
			if (out > 0) {
				System.out.println("Operation done successfully");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	public ResultSet getColumns (String tableName){
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT column_name, data_type FROM information_schema.columns where table_schema = 'public' and table_name = \'" + tableName + "\'"+ ";");

			return rs;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}


	}

	public boolean delete(HashMap<String, String> vars, String tableName) {
		try {
			stmt = c.createStatement();
			String sql = "DELETE from " + tableName + " where ";
			for (String key : vars.keySet()) {
				sql += key + "=" + vars.get(key) + " AND ";
			}
			sql = sql.substring(0, sql.length() - 5) + ";";
			System.out.println("sql : "+ sql);

			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			System.out.println("delete : Closed database successfully");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Operation done successfully");
		return false;
	}

	public void connectionClose() {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void setConnection() {
		try {
			c = DriverManager.getConnection(dbName, user, pass);
			c.setAutoCommit(false); //changed from false to true to stop this error:current transaction is aborted, commands ignored until end of transaction block
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}