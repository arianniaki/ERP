package ResourceManagement.Section.Resource;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.DataBase;
import DataBase.Table;
import ProjectEmployee.Employee;
import ProjectEmployee.EmployeeCatalogue;
import ProjectEmployee.Project;
import ProjectEmployee.ProjectCatalogue;
import ProjectEmployee.ProjectEmployee;

public class MaintainingModuleCatalogue {

	DataBase DB;
	
	public MaintainingModuleCatalogue(){
		DB = DB.getInstance();
	}
	
	public ArrayList<MaintainingModule> getMaintainingModules(int modrid){
	
		ArrayList<MaintainingModule> mms = new ArrayList<MaintainingModule>();

		Table table = new Table("maintainmodule");

		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modrid", Integer.toString(modrid));
				
		ArrayList<HashMap<String, String>> result = table.search(vars);
		for (int i = 0; i < result.size(); i++) {
			ModuleCatalogue modcat = new ModuleCatalogue();
			Module module = modcat.getModule(modrid);
			MaintainingModule mm = new MaintainingModule(module, Integer.parseInt(result.get(i).get("maintainid")), result.get(i).get("changetype"), Integer.parseInt(result.get(i).get("duration")));

			mms.add(mm);
		}
		return mms;
	}
	
	public long addMaintainingModule (int modrid, String changeType, int duration){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("modrid", modrid+"");
		vars.put("changeType", "\'"+changeType+"\'");
		vars.put("duration", duration+"");
		
		long pk=DB.insert(vars, "maintainmodule");
		System.out.println("inserted into maintainmodule table: " + pk);
		return pk;
	}
	
	public void deleteMaintainingModule(int mmid){
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("maintainid", Integer.toString(mmid));
		
		DB.delete(vars, "maintainmodule");


	}
}
