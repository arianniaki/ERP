package GUI.Form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextJPanel extends FieldPanel {
	private JTextField textField;
	public JTextField getTextField() {
		return textField;
	}
	public TextJPanel(Field field) {
		super(field.getName());

		JPanel labelPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		add(labelPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
		textField = new JTextField();
		textField.setColumns(field.getWidth());
		JLabel lab = new JLabel(field.getLabel(), JLabel.RIGHT);
		lab.setLabelFor(textField);
		labelPanel.add(lab);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.add(textField);
		fieldPanel.add(p);
		textField.setText("");
	}
	@Override
	public ArrayList<String> getValues() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(this.textField.getText().toString());
		return ret;
	}
}
