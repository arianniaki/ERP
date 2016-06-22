package DataBase;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Table {
	DataBase DB;
	String tableName;
	ArrayList<Column> columns;
	ResultSet values;

	public Table(String tableName) {
		DB = new DataBase();
		this.tableName = tableName;
		try {
			ResultSet cols = DB.getColumns(tableName);
			columns = new ArrayList<Column>();
			while (cols.next()) {
				Column col = new Column();
				col.name = cols.getString("column_name");
				col.type = cols.getString("data_type");
				columns.add(col);
			}
			cols.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<HashMap<String, String>> readAll(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		try {
			ResultSet rs = DB.selectAll(tableName);
			while (rs.next()) {
				HashMap<String,String> val = new HashMap<String, String>();
				for (Column col : columns) {
					if (col.type.equals("integer")) {
						val.put(col.name, Integer.toString(rs.getInt(col.name)));
					}else if (col.type.equals("character")) {
						val.put(col.name, rs.getString(col.name));
					}else if (col.type.equals("date")) {
						Date date = rs.getDate((col.name));
						val.put(col.name, date.toString());
					}
				}
				result.add(val);		
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return null;
		}

	}

	public ArrayList<HashMap<String,String>> search(HashMap<String, String> vars){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		try {
			ResultSet rs = DB.select(vars,tableName);
			while (rs.next()) {
				HashMap<String,String> val = new HashMap<String, String>();
				for (Column col : columns) {
					if (col.type.equals("integer")) {
						val.put(col.name, Integer.toString(rs.getInt(col.name)));
					}else if (col.type.equals("character")) {
						val.put(col.name, rs.getString(col.name));
					}else if (col.type.equals("date")) {
						Date date = rs.getDate((col.name));
						val.put(col.name, date.toString());
					}
				}
				result.add(val);		
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return null;
		}
	}
}
