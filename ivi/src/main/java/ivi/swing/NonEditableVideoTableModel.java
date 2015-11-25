package ivi.swing;

import java.sql.Date;

import javax.swing.table.DefaultTableModel;

public class NonEditableVideoTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonEditableVideoTableModel(Object arg0[][], Object arg1[]){
		super(arg0, arg1);
	}
	@Override
	public Class<?> getColumnClass(int column){
		if(column==1)
			return Date.class;
		else
			return super.getColumnClass(column);
	}
		
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
