package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DataBase {
	static Connection c = null;
	static Statement stmt = null;

	public static void main(String[] args) {
		HashMap<String, String> vars = new HashMap<String, String>();
		// vars.put("empid", "6");
		// vars.put("empname", "\'arian\'");
		// vars.put("sectionid", "1");
		// vars.put("ismodir", "false");

		// insert(vars, "EMPLOYEE");
		HashMap<String, String> vars2 = new HashMap<String, String>();
		vars2.put("empid", "123");
		vars2.put("empname", "\'arian\'");
		ResultSet rs = select(vars2, "EMPLOYEE");
		try {
			while (rs.next()) {
				int id = rs.getInt("empid");
				String name = rs.getString("empname");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println();
			}
			rs.close();
			c.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("empid", "123");
		condVars.put("empname", "\'arian\'");
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("empname", "\'negar\'");
		update(condVars, setVars, "EMPLOYEE");
	}

	static public boolean insert(HashMap<String, String> vars, String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ERP", "postgres",
					"123456m.");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

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
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
		return true;

	}
	public static ResultSet select(HashMap<String, String> vars, String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ERP", "postgres",
					"123456m.");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "SELECT * FROM " + tableName + " where ";
			for (String key : vars.keySet()) {
				sql += key + "=" + vars.get(key) + " AND ";
			}
			sql = sql.substring(0, sql.length() - 5) + ";";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			// stmt.close();
			// c.close();
			return rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public static boolean update(HashMap<String, String> condVars,
			HashMap<String, String> setVars, String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ERP", "postgres",
					"123456m.");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE " + tableName + " set ";// SALARY = 25000.00
															// where ID=1;";
			for (String key : setVars.keySet()) {
				sql += key + "=" + setVars.get(key) + " AND ";
			}
			sql = sql.substring(0, sql.length() - 5) + " where ";
			for (String key : condVars.keySet()) {
				sql += key + "=" + condVars.get(key) + " AND ";
			}
			sql = sql.substring(0, sql.length() - 5) + ";";
			System.out.println(sql);
			int out = stmt.executeUpdate(sql);
			c.commit();
			if(out > 0){
				System.out.println("Operation done successfully");
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public boolean delete(HashMap<String, String> vars, String tableName) {
		return false;
	}
}