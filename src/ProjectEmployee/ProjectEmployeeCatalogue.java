package ProjectEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import RequirementUtilization.ResourceRequirement;
import ResourceManagement.Section.Section;
import ResourceManagement.Section.SectionCatalogue;
import ResourceManagement.Section.Resource.Resource;
import ResourceManagement.Section.Resource.ResourceCatalogue;

public class ProjectEmployeeCatalogue {

	DataBase DB;
	
	public ProjectEmployeeCatalogue(){
		DB = new DataBase();
	}
	
	public long addProjectEmployee(int pid, int empid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("empid", empid+"");
		vars.put("projid",pid+"");
		
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
			ProjectCatalogue pcat = new ProjectCatalogue();
			EmployeeCatalogue empcat = new EmployeeCatalogue();
			Employee emp = empcat.getEmployee(Integer.parseInt(result.get(i).get("empid")));
			Project pr = pcat.getProject(Integer.parseInt(result.get(i).get("projid")));

			ProjectEmployee pe = new ProjectEmployee(pr,emp);
			projemp.add(pe);
		}
		return projemp;
	}
	
	public ProjectEmployee getProjectEmployee(int pid, int empid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("projid", Integer.toString(pid));
		vars.put("empid", Integer.toString(empid));
		
		ResultSet res = DB.select("projectemployee",vars,null);

		ProjectCatalogue pcat = new ProjectCatalogue();
		EmployeeCatalogue empcat = new EmployeeCatalogue();
		ProjectEmployee premp = null;
		try {
			if(res.next()){
				Project pr = pcat.getProject(res.getInt("projid"));
				Employee emp = empcat.getEmployee(res.getInt("empid"));
				premp = new ProjectEmployee(pr, emp);
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
	
}
