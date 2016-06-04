package GUI.Form;

import java.util.ArrayList;

public class Field {
	private String type;
	private String label;
	private String value;
	ArrayList<String> options;
	private int width;
	public Field(String inputType, String inputLabel, ArrayList<String> inputOptions, int inputWidth){
		type = inputType;
		label = inputLabel;
		options = new ArrayList<String>(inputOptions);
		width = inputWidth;
	}
	public Field(String inputType, String inputLabel, String inputValue, int inputWidth){
		type = inputType;
		label = inputLabel;
		value = inputValue;
		width = inputWidth;
	}
	public String getValue(){
		return this.value;
	}
	public void setValue(String inputValue){
		value = inputValue;
	}
	public String getType(){
		return this.type;
	}
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.label;
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}
}
