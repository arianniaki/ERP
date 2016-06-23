package GUI.Form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** @see http://stackoverflow.com/questions/4526779 */
public class CheckBoxTable extends JPanel {
    private final int CHECK_COL = 1;
    private Object[][] DATA;
    private String[] COLUMNS;
    private DataModel dataModel;
    private JTable table;
    private DefaultListSelectionModel selectionModel;

    public CheckBoxTable(Object[][] inpData, String[] inpCols) {
        super(new BorderLayout());
    	DATA = inpData;
    	COLUMNS = inpCols;
    	dataModel = new DataModel(DATA, COLUMNS);
    	table = new JTable(dataModel);
        this.add(new JScrollPane(table));
        table.setPreferredScrollableViewportSize(new Dimension(250, 175));
        selectionModel = (DefaultListSelectionModel) table.getSelectionModel();
    }

    private class DataModel extends DefaultTableModel {

        public DataModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == CHECK_COL) {
                return getValueAt(0, CHECK_COL).getClass();
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == CHECK_COL;
        }
    }

    private class SelectionAction extends AbstractAction {

        boolean value;

        public SelectionAction(String name, boolean value) {
            super(name);
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < dataModel.getRowCount(); i++) {
                if (selectionModel.isSelectedIndex(i)) {
                    dataModel.setValueAt(value, i, CHECK_COL);
                }
            }
        }
    }
    
    public ArrayList<String> getSelectedValues(){
    	ArrayList<String> selected = new ArrayList<String>();
    	for(int i=0; i<dataModel.getRowCount(); i++){
    		if((Boolean) dataModel.getValueAt(i, 1))
    			selected.add((String) dataModel.getValueAt(i, 0));
    	}
    	return selected;
    }
    
    public ArrayList<String> getValues(){
    	ArrayList<String> ret = new ArrayList<String>();
    	for(int i=0; i<dataModel.getRowCount(); i++){
    		ret.add((String) dataModel.getValueAt(i, 0));
    	}
    	return ret;
    }
}
