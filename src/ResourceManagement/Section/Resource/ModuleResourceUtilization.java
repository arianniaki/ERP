package ResourceManagement.Section.Resource;

import java.util.HashMap;

public class ModuleResourceUtilization {
	private Module module;
	private Resource resource;
	public ModuleResourceUtilization(Module inputModule, Resource inputResource){
		module = inputModule;
		resource = inputResource;
	}
	public HashMap<String,String> toHashMap(){
		HashMap<String,String> resreq = new HashMap<String,String>();
		resreq.put("rid", Integer.toString(this.resource.getId()));
		resreq.put("modrid", Integer.toString(this.module.getId()));
		return resreq;
	}

	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
