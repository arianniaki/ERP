package ResourceManagement.Section.Resource;

import java.util.HashMap;

import ProjectEmployee.Employee;

public class MaintainModEmp {
	private Employee employee;
	private MaintainingModule maintainInfo;
	public MaintainingModule getMaintainInfo() {
		return maintainInfo;
	}
	public void setMaintainInfo(MaintainingModule maintainInfo) {
		this.maintainInfo = maintainInfo;
	}
	public MaintainModEmp(Employee inputEmployee, MaintainingModule inputMaintainInfo){
		maintainInfo = inputMaintainInfo;
		employee = inputEmployee;
	}
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> resreq = new HashMap<String,String>();
		resreq.put("empid", Integer.toString(this.employee.getId()));
		resreq.put("maintainid", Integer.toString(this.maintainInfo.getId()));
		return resreq;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
