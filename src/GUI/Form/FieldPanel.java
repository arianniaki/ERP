package GUI.Form;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class FieldPanel extends JPanel{
	String name;
	public FieldPanel(String name) {
		// TODO Auto-generated constructor stub
		super(new BorderLayout());
		this.name = name;
	}
	abstract public ArrayList<String> getValues();
	public String getName(){
		return this.name;
	}
}
