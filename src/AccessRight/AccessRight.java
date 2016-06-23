package AccessRight;

public class AccessRight {

	String name;
	String description;
	
	public AccessRight(int id){
		if(id == 1)
			this.name = "default";
		if(id == 2)
			this.name = "intermediate";
		if(id == 3)
			this.name = "super";

	}
	
	public String getName(){
		return this.name;
	}
}
