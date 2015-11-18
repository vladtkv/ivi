package ivi.view;

import ivi.Program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.google.api.services.youtube.model.Subscription;

import ivi.model.MainModel;
import ivi.swing.ChannelTableColorCellRenderer;
import ivi.swing.Frame;
import ivi.swing.NonEditableTableModel;

public class SubscriptionListView {
	private final Color BACKGROUND_COLOR=new Color(200,221,242);
	private final Font FONT_BOLD=new Font("Verdana", Font.BOLD, 15);
	private final Font FONT_PLAIN=new Font("Verdana", Font.PLAIN, 15);
	private Frame frame;
	private JPanel mainPanel=new JPanel();
	private JPanel footerPanel=new JPanel();
	private NonEditableTableModel subscriptionTableModel;
	private MainModel mainModel;
	public SubscriptionListView(MainModel mm){
		mainModel=mm;
		
		frame=new Frame(Program.PROGRAM_CAPTION_STRING, new Dimension(525, 520), 0, JFrame.EXIT_ON_CLOSE, null);
		//create channel table
		Object[] subscriptionTableColumn={"Subscrition", "Available"};
		subscriptionTableModel=new NonEditableTableModel(null, subscriptionTableColumn);
		JTable subscriptionTable=new JTable(subscriptionTableModel){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			//add highlighting feature
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				if(column==1){
					return super.prepareRenderer(new ChannelTableColorCellRenderer(), row, column);
				}
				return super.prepareRenderer(renderer, row, column);	
			}
		};
		//add double click action
		subscriptionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==1 && e.getClickCount() == 2) {
					mainModel.accessChannelWebPage();
				}
		    }
		});
		subscriptionTable.getColumnModel().getColumn(0).setMaxWidth(400);
		subscriptionTable.getColumnModel().getColumn(1).setMaxWidth(100);
		subscriptionTable.setRowHeight(25);
		subscriptionTable.getTableHeader().setFont(FONT_BOLD);
		subscriptionTable.getTableHeader().setBackground(BACKGROUND_COLOR);
		subscriptionTable.setFont(FONT_PLAIN);
		JScrollPane channelScrollPane=new JScrollPane(subscriptionTable);
		channelScrollPane.setAutoscrolls(true);
		channelScrollPane.setPreferredSize(new Dimension(500,445));
		//create footer
		BoxLayout footerLayout=new BoxLayout(footerPanel, BoxLayout.X_AXIS);
		footerPanel.setLayout(footerLayout);
		JLabel devLabel=new JLabel("IVI Developers Team");
		devLabel.setFont(FONT_PLAIN);
		JLabel verLabel=new JLabel(Program.version);
		verLabel.setFont(FONT_PLAIN);
		footerPanel.add(Box.createHorizontalStrut(10));
		footerPanel.add(devLabel);
		footerPanel.add(Box.createHorizontalGlue());
		footerPanel.add(verLabel);
		footerPanel.add(Box.createHorizontalStrut(10));
		
		mainPanel.add(channelScrollPane);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(footerPanel, BorderLayout.SOUTH);
	}
	public void open(){
		frame.setVisible(true);
	}
	public boolean addSubscriptionData(Subscription subscrition){
		if(subscrition!=null){
			Object[] data={subscrition.getSnippet().getTitle(), -1};
			subscriptionTableModel.addRow(data);
			return true;
		}
		else
			return false;
	}
	protected int getSubscriptionTableRowCount(){
		return subscriptionTableModel.getRowCount();
	}
}
