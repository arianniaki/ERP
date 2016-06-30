package GUI.Form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SingleCheckBoxJPanel extends FieldPanel{
	JCheckBox checkbox;
	public SingleCheckBoxJPanel(Field field) {
		// TODO Auto-generated constructor stub
		super(field.getName());
		String optionString = field.options.get(0);
		checkbox = new JCheckBox(new CheckboxAction(optionString));
		JPanel labelPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		add(labelPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
		JLabel lab = new JLabel(field.getLabel(), JLabel.RIGHT);
		lab.setLabelFor(checkbox);
		labelPanel.add(lab);
		fieldPanel.add(checkbox);
	}
	private class CheckboxAction extends AbstractAction {
	    public CheckboxAction(String text) {
	        super(text);
	    }
	 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JCheckBox cbLog = (JCheckBox) e.getSource();
	        if (cbLog.isSelected()) {
	            System.out.println("Logging is enabled");
	        } else {
	            System.out.println("Logging is disabled");
	        }
	    }
	}

	@Override
	public ArrayList<String> getValues() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(checkbox.getText());
		return ret;
	}

	public ArrayList<String> getCheckedValues() {
		ArrayList<String> ret = new ArrayList<String>();
		if(checkbox.isSelected())
			ret.add(checkbox.getText());
		return ret;
	}
	
}
