package ivi.swing;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableDateCellRenderer extends DefaultTableCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//	Date date=new Date();
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        value = sdf.format((Date) value);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
