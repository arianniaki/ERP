
package GUI.Form;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Form{
	private ArrayList<Field> fields;
	private String formName;
	private JPanel formPanel;
	public Form(ArrayList<Field> inputFields, String inputFormName){
		fields = new ArrayList<Field>(inputFields);
		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
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
	public void addTextField(Field field) {
		JPanel child = new TextJPanel(field);
		formPanel.add(child);
	}
	public void addComboField(Field field) {
		JPanel child = new ComboBoxJPanel(field);
		formPanel.add(child);
	}
	public void addCheckField(Field field) {
		JPanel child = new CheckBoxJPanel(field);
		formPanel.add(child);
	}
	public JPanel getJPanel(){
		return this.formPanel;
	}
}