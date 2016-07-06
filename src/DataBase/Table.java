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
	ResultSet rs;
	
	public Table(String tableName) {
		DB = DB.getInstance();
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
		try {
			rs = DB.select(tableName,null,null);
			return getTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			DB.connectionClose();
			return null;
		}

	}

	public ArrayList<HashMap<String,String>> search(HashMap<String, String> vars){
		try {
			HashMap<String,String> exactvars = new HashMap<String, String>();
			HashMap<String,String> simvars = new HashMap<String, String>();
			for(String key : vars.keySet()){
				for(int i=0; i< columns.size(); i++){
					if(columns.get(i).name.equals(key)){
						if(columns.get(i).type.contains("char")){
							simvars.put(key, vars.get(key));
						}
						else
							exactvars.put(key, vars.get(key));
					}
				}
			}
			rs = DB.search(tableName,exactvars,simvars,null);
			return getTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			DB.connectionClose();
			return null;
		}
	}
	
	public ArrayList<HashMap<String,String>> getReportTable(){
		try {
			ArrayList<String> groupBy = new ArrayList<String>();
			String specName = columns.get(1).name;
			groupBy.add(specName);
			groupBy.add("sid");
			columns = new ArrayList<Column>();
			Column c1 = new Column(specName,"character");
			columns.add(c1);
			Column c2 = new Column("sid","integer");
			columns.add(c2);
			Column c3 = new Column("count","integer");
			columns.add(c3);
			
			rs = DB.select(tableName,null,groupBy);
			return getTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			DB.connectionClose();
			return null;
		}
	}
	private ArrayList<HashMap<String, String>> getTable() throws SQLException{
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
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
				}else if (col.type.equals("character varying")){
					val.put(col.name, rs.getString(col.name));
				}else if (col.type.equals("boolean")){
					val.put(col.name, Boolean.toString(rs.getBoolean(col.name)));
				}

			}
			result.add(val);		
		}
		DB.connectionClose();
		return result;
	}
}
