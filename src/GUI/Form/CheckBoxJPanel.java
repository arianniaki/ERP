package GUI.Form;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.util.ArrayList;

import javax.swing.JPanel;


public class CheckBoxJPanel extends FieldPanel {
	private CheckBoxTable cbt;
	public CheckBoxJPanel(Field field) {
		super(field.getName());
		Object[][] options = new Object[field.options.size()][2];
		String[] columns = {field.getName(),"Check Box"};
		for (int i = 0; i < field.options.size(); i++){
			options[i][0] = field.options.get(i);
			options[i][1] = Boolean.FALSE;
		}
		cbt = new CheckBoxTable(options,columns);
		JPanel labelPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		add(labelPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
		JLabel lab = new JLabel(field.getLabel(), JLabel.RIGHT);
		lab.setLabelFor(cbt);
		labelPanel.add(lab);
		fieldPanel.add(cbt);
	}

	@Override
	public ArrayList<String> getValues() {
		return cbt.getValues();
	}
	
	public ArrayList<String> getCheckedValues() {
		return cbt.getSelectedValues();
	}

}


