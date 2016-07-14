import java.util.HashMap;

import ResourceManagement.Section.Resource.InformationResourceCatalogue;


public class test {

	public static void main(String[] args) {
		InformationResourceCatalogue infoCat = new InformationResourceCatalogue();
		HashMap<String,String> searchvars = new HashMap<String,String>();
		searchvars.put("irname", "\'"+"information document"+"\'");
		infoCat.SearchResource(searchvars);
	}
}
