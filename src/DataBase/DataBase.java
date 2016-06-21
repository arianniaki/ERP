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
	static String dbName = "jdbc:postgresql://localhost:5432/erp";
	static String user = "postgres";
	static String pass = "123456m.";
	

	public long insert(HashMap<String, String> vars, String tableName) {
		long pk=0;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbName, user, pass);
			c.setAutoCommit(false);
			System.out.println("insert : Opened database successfully");
			System.out.println();
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
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
		return pk;

	}

	public ResultSet select(HashMap<String, String> vars, String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					dbName, "postgres",
					"123456m.");
			c.setAutoCommit(false);
			System.out.println("select : Opened database successfully");

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

	public boolean update(HashMap<String, String> condVars,
			HashMap<String, String> setVars, String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbName, user, pass);

			c.setAutoCommit(false);
			System.out.println("update : Opened database successfully");

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
			if (out > 0) {
				System.out.println("Operation done successfully");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}

	public ResultSet getColumns (String tableName){
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbName, user, pass);

			c.setAutoCommit(false);
			System.out.println("getColumns : Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT column_name, data_type FROM information_schema.columns where table_schema = 'public' and table_name = \'" + tableName + "\'"+ ";");

			return rs;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}


	}
	public ResultSet selectAll(String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbName, user, pass);

			c.setAutoCommit(false);
			System.out.println("SelectAll : Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM " + tableName + ";");
			
			return rs;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}

	}

	public boolean delete(HashMap<String, String> vars, String tableName) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbName, user, pass);

			c.setAutoCommit(false);
			System.out.println("delete : Opened database successfully");

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
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return false;
	}

	public void connectionClose() {
		try {
			c.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}