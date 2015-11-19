package ivi.swing;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonEditableTableModel(Object arg0[][], Object arg1[]){
		super(arg0, arg1);
	}
	@Override
	public Class<?> getColumnClass(int column){
		return super.getColumnClass(column);
	}
		
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
