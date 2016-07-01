package ResourceManagement.Section.Resource;

import java.util.HashMap;

import ProjectEmployee.Employee;

public class ModuleEmployee {
	private Module module;
	private Employee employee;
	public ModuleEmployee(Module inputModule, Employee inputEmployee){
		module = inputModule;
		employee = inputEmployee;
	}
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> resreq = new HashMap<String,String>();
		resreq.put("empid", Integer.toString(this.employee.getId()));
		resreq.put("modrid", Integer.toString(this.module.getId()));
		return resreq;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
