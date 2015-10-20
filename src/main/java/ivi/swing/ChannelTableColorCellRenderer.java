package ivi.swing;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ChannelTableColorCellRenderer extends DefaultTableCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if((Integer) value>0)
			c.setBackground(Color.GREEN);
		else
			c.setBackground(Color.WHITE);
		return this;
    }
}
