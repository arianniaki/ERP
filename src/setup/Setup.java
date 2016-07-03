package setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Setup {
	String username = "postgres";
	String password = "123456m.";
	String url = "jdbc:postgresql://localhost:";
	String port ="5432";
	String database = null;
	public Setup(String iusername, String ipassword, String iport) throws FileNotFoundException, SQLException {
		System.out.println(iusername+ipassword+iport);
		username = iusername;
		password = ipassword;
		port = iport;
		this.buildDB();
		this.createTables();
	}
	private void buildDB(){
		
		// your cPanel username and password here - the user has right to
		// create/drop databases
		Connection connection = null;
		Statement statement = null;

		try {
			database = "erp";
			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection(url+port+"/postgres", username, password);
			statement = connection.createStatement();

			System.out.println(statement.executeUpdate("CREATE DATABASE "+database));
			statement.close();
			connection.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void createTables() throws SQLException, FileNotFoundException{
		Connection connection = null;
		Statement statement = null;
		connection = DriverManager.getConnection(url+port+"/"+database, username, password);
		statement = connection.createStatement();
		File file = new File("buildDB.input");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			String stm = sc.nextLine();
			System.out.println(statement.executeUpdate(stm));
		}
		sc.close();
		System.out.println("tables added!");
		connection.close();
	}
	public void addSampleData() throws SQLException, FileNotFoundException{
		Connection connection = null;
		Statement statement = null;
		connection = DriverManager.getConnection(url+port+"/"+database, username, password);
		statement = connection.createStatement();
		File file = new File("sampledata.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			String stm = sc.nextLine();
			System.out.println("LINE "+ stm +" "+sc.hasNextLine());
			System.out.println(statement.executeUpdate(stm));
		}
		sc.close();
		connection.close();
	}
	public static void main(String[] args) {
		try {
			Setup setup = new Setup("postgres", "123456m.", "5432");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
