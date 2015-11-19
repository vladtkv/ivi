package ivi.swing;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.google.api.client.util.DateTime;

public class TableDateCellRenderer extends DefaultTableCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	Date date=new Date();
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		date.setTime(((DateTime) value).getValue());
        value = sdf.format(date);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
