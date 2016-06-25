package GUI.Form;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PanelBuilder {
	private Form form;
	public PanelBuilder(Form inputForm){
		this.form = inputForm;
	}
	public void makeForm(){
		for(int i=0; i<form.size(); i++){
			Field field = form.getFieldAt(i);
			if(field.getType().equals("text")){
				form.addTextField(field);
			}
			else if(field.getType().equals("comboBox")){
				form.addComboField(field);
			}
			else if(field.getType().equals("checkBox")){
				form.addCheckField(field);
			}
		}
	}

}
