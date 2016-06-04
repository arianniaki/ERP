package GUI.Form;

import java.util.ArrayList;

public class Form{
	private ArrayList<Field> fields;
	private String formName;
	public Form(ArrayList<Field> inputFields, String inputFormName){
		fields = new ArrayList<Field>(inputFields);
		setFormName(inputFormName);
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public int size(){
		return this.fields.size();
	}
	public Field getFieldAt(int index){
		return fields.get(index);
	}
}