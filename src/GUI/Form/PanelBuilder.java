package GUI.Form;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PanelBuilder {
	private Form form;
	JPanel formPanel;
	public PanelBuilder(Form inputForm){
		this.form = inputForm;
		this.formPanel  = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

	}
	public void makeForm(){
		for(int i=0; i<form.size(); i++){
			Field field = form.getFieldAt(i);
			JPanel child = null; 
			if(field.getType().equals("text")){
				System.out.println(field.getLabel());
				child = new TextJPanel(field);
			}
			else if(field.getType().equals("comboBox")){
				child = new ComboBoxJPanel(field);
			}
			else if(field.getType().equals("checkBox")){
				child = new CheckBoxJPanel(field);
			}
			formPanel.add(child);
		}
	}
	public JPanel getJPanel(){
		return this.formPanel;
	}
}
