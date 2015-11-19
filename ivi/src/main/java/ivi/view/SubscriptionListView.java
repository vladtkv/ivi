package ivi.view;

import ivi.Program;

import java.awt.BorderLayout;
import java.awt.Color;
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
import com.google.api.services.youtube.model.Subscription;

import ivi.model.MainModel;
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
	private final String DETAILED_LABEL=new String("<html><i><u>Show detailed</u></i></html>");
	public SubscriptionListView(MainModel mm){
		mainModel=mm;
		
		frame=new Frame(Program.PROGRAM_CAPTION_STRING, new Dimension(535, 520), 0, JFrame.EXIT_ON_CLOSE, null);
		//create channel table
		Object[] subscriptionTableColumn={"Subscrition", ""};
		subscriptionTableModel=new NonEditableTableModel(null, subscriptionTableColumn);
		JTable subscriptionTable=new JTable(subscriptionTableModel);
		//add double click action
		subscriptionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==1 && e.getClickCount() == 2) {
					mainModel.accessChannelVideoList(subscriptionTable.getSelectedRow());
				}
		    }
		});
		subscriptionTable.getColumnModel().getColumn(0).setMinWidth(385);
		subscriptionTable.getColumnModel().getColumn(1).setMinWidth(115);
		subscriptionTable.setRowHeight(25);
		subscriptionTable.getTableHeader().setFont(FONT_BOLD);
		subscriptionTable.getTableHeader().setBackground(BACKGROUND_COLOR);
		subscriptionTable.setFont(FONT_PLAIN);
		JPanel tablePanel=new JPanel();
		BoxLayout tablePanelLayout=new BoxLayout(tablePanel, BoxLayout.X_AXIS);
		tablePanel.setLayout(tablePanelLayout);
		JScrollPane channelScrollPane=new JScrollPane(subscriptionTable);
		channelScrollPane.setAutoscrolls(true);
		channelScrollPane.setPreferredSize(new Dimension(500,425));
		tablePanel.add(Box.createHorizontalGlue());
		tablePanel.add(channelScrollPane);
		tablePanel.add(Box.createHorizontalGlue());
		//create footer
		BoxLayout footerLayout=new BoxLayout(footerPanel, BoxLayout.X_AXIS);
		footerPanel.setLayout(footerLayout);
		JLabel devLabel=new JLabel("IVI Developers Team");
		devLabel.setFont(FONT_PLAIN);
		JLabel verLabel=new JLabel(Program.version);
		verLabel.setFont(FONT_PLAIN);
		footerPanel.add(Box.createHorizontalStrut(5));
		footerPanel.add(devLabel);
		footerPanel.add(Box.createHorizontalGlue());
		footerPanel.add(verLabel);
		footerPanel.add(Box.createHorizontalStrut(5));
		//main panel config
		BoxLayout mainPanelLayout=new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(mainPanelLayout);
		mainPanel.add(Box.createVerticalStrut(5));
		mainPanel.add(tablePanel);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(footerPanel, BorderLayout.SOUTH);
	}
	public void open(){
		frame.setVisible(true);
	}
	public boolean addSubscriptionData(Subscription subscrition){
		if(subscrition!=null){
			Object[] data={subscrition.getSnippet().getTitle(), DETAILED_LABEL};
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
