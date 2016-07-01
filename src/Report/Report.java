package Report;

import java.util.ArrayList;
import java.util.HashMap;

public class Report {
	String content;
	ArrayList<HashMap<String, String>> results = null;
	public Report(){
		content  = new String();
	}
	public void addLine(String line){
		content += line;
		content += "\n";
	}
	public String getReport(){
		return this.content;
	}
	public void setResults(ArrayList<HashMap<String, String>> iresults) {
		results = iresults;
	}
	public ArrayList<HashMap<String,String>> getResults() {
		return results;
	}
	public void printRep(){
		if(results == null || results.size()==0)
			System.out.println("There is no item!");
		else{
			for(String key : results.get(0).keySet())
				System.out.print(key+", ");
			System.out.println();
			System.out.println(content);
		}
	}
}
