package ResourceManagement.Section.Resource;

import java.util.HashMap;

import DataBase.DataBase;

public class MaintainingModule {

	int mmid;
	int duration;
	String changeType;
	Module module;
	DataBase DB;
	
	public MaintainingModule(Module module, int mmid, String changeType, int duration){
		this.mmid = mmid;
		this.module = module;
		this.changeType = changeType;
		this.duration = duration;
		DB = new DataBase();
	}
	
	public void edit(String changeType, int duration){
		this.changeType = changeType;
		this.duration = duration;
		HashMap<String, String> setVars = new HashMap<String, String>();
		setVars.put("changetype", "\'"+changeType+"\'");
		setVars.put("duration", Integer.toString(duration));
	}
	
	public void submitToDB(HashMap<String, String> setVars){
		HashMap<String, String> condVars = new HashMap<String, String>();
		condVars.put("maintainid", Integer.toString(this.mmid));
		DB.update(condVars, setVars, "maintainmodule");
	}
	
	public int getId(){
		return mmid;
	}
	
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> mm = new HashMap<String,String>();
		mm.put("modrid", Integer.toString(this.module.getId())+"");
		mm.put("maintainid",Integer.toString(this.mmid));
		mm.put("changetype", this.changeType);
		mm.put("duration",Integer.toString(this.duration));
		return mm;
	}

}
