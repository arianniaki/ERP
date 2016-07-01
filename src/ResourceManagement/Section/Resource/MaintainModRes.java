package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class MaintainModRes {
	private Resource resource;
	private MaintainingModule maintainInfo;
	public MaintainingModule getMaintainInfo() {
		return maintainInfo;
	}
	public void setMaintainInfo(MaintainingModule maintainInfo) {
		this.maintainInfo = maintainInfo;
	}
	public MaintainModRes(Resource inputResource, MaintainingModule inputMaintainInfo){
		resource = inputResource;
		maintainInfo = inputMaintainInfo;
	}
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> resreq = new HashMap<String,String>();
		resreq.put("rid", Integer.toString(this.resource.getId()));
		resreq.put("maintainid", Integer.toString(this.maintainInfo.getId()));
		return resreq;
	}

	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
