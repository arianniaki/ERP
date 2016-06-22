package GUI.Form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComboBoxJPanel extends FieldPanel {
	private JComboBox comboField = new JComboBox();
	private JButton b = new JButton("Add items");
	private String selected_item = new String();
	public JComboBox getComboBox(){
		return comboField;
	}
	public ComboBoxJPanel(Field field) {
		// TODO Auto-generated constructor stub
		super(field.getName());
		for (int i = 0; i < field.options.size(); i++)
		      comboField.addItem(field.options.get(i));
		JPanel labelPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		add(labelPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
		JLabel lab = new JLabel(field.getLabel(), JLabel.RIGHT);
		lab.setLabelFor(comboField);
		labelPanel.add(lab);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.add(comboField);
		fieldPanel.add(p);
	}

	@Override
	public ArrayList<String> getValues() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(comboField.getSelectedItem().toString());
		System.out.println(comboField.getSelectedItem().toString());
		return ret;
	}
	
	public String selected_Choice(){
		return selected_item;
	}
	
}
