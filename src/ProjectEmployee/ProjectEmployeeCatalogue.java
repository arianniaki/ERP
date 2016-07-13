package ProjectEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import AccessRight.AccessRightCatalogue;
import DataBase.DataBase;
import DataBase.Table;
import Report.Report;

public class ProjectEmployeeCatalogue {
	private static ProjectEmployeeCatalogue projectEmpCat = new ProjectEmployeeCatalogue();

	private DataBase DB;
	
	private ProjectEmployeeCatalogue(){
		DB = DB.getInstance();
	}
	
	public static ProjectEmployeeCatalogue getInstance(){
		return projectEmpCat;
	}
	
	
	public long addProjectEmployee(int pid, int empid, String from, String to){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", empid+"");
		vars.put("projid",pid+"");
		vars.put("fromdate","\'"+from+"\'");
		vars.put("todate","\'"+to+"\'");

		long pk=DB.insert(vars, "projectemployee");
		System.out.println("inserted into projectemployee table: " + pk);
		return pk;

	}
	
	public ArrayList<ProjectEmployee> getProjectEmployeesByProject(int pid){
		ArrayList<ProjectEmployee> projemp = new ArrayList<ProjectEmployee>();
		
		Table table = new Table("projectemployee");
		HashMap<String,String> vars = new HashMap<String,String>();
		vars.put("projid", Integer.toString(pid));
		
		ArrayList<HashMap<String, String>> result = table.search(vars);
		
		for (int i = 0; i < result.size(); i++) {
//			System.out.println(result.get(i).toString());
			ProjectCatalogue pcat = ProjectCatalogue.getInstance();
			EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
			Employee emp = empcat.getEmployee(Integer.parseInt(result.get(i).get("empid")));
			Project pr = pcat.getProject(Integer.parseInt(result.get(i).get("projid")));

			ProjectEmployee pe = new ProjectEmployee(Integer.parseInt(result.get(i).get("projempid")),pr,emp,result.get(i).get("fromdate"),result.get(i).get("todate"));
			projemp.add(pe);
		}
		return projemp;
	}
	
	public ProjectEmployee getProjectEmployee(int pid, int empid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projid", Integer.toString(pid));
		vars.put("empid", Integer.toString(empid));
		
		ResultSet res = DB.select("projectemployee",vars,null);

		ProjectCatalogue pcat = ProjectCatalogue.getInstance();
		EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
		ProjectEmployee premp = null;
		try {
			if(res.next()){
				Project pr = pcat.getProject(res.getInt("projid"));
				Employee emp = empcat.getEmployee(res.getInt("empid"));
				premp = new ProjectEmployee(Integer.parseInt(res.getString("projempid")),pr, emp,res.getString("fromdate"),res.getString("todate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return premp;
	}
	
	
	public void deleteProjectEmployee (int pid, int empid){
	
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projid", Integer.toString(pid));
		vars.put("empid", Integer.toString(empid));
		
		DB.delete(vars, "projectemployee");


	}
	public Report getCirculationReport(Employee emp){
		Report rep = new Report();
		Table table = new Table("projectemployee");
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid",  "\'"+emp.getId()+"\'");
		ArrayList<HashMap<String,String>> results = table.search(vars);
		rep.setResults(results);
		for(int i=0; i<results.size(); i++){
			String line="";
			ProjectCatalogue projCat = ProjectCatalogue.getInstance();
			EmployeeCatalogue empcat = EmployeeCatalogue.getInstance();
			results.get(i).put("empname", ""+empcat.getEmployee(Integer.valueOf(results.get(i).get("empid"))).getName());
			results.get(i).put("pname", ""+projCat.getProject((Integer.valueOf(results.get(i).get("projid")))).getName());
			for(String key : results.get(i).keySet()){
				line+= results.get(i).get(key).toString()+", ";
			}
			line = line.substring(0, line.length() - 2);
			rep.addLine(line);
		}
		return rep;
	}
}
