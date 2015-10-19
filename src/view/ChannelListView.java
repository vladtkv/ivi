package view;

import global.Global;

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

import model.MainModel;
import model.type.Channel;
import swing.Frame;
import swing.NonEditableTableModel;

public class ChannelListView {
	private final Color BACKGROUND_COLOR=new Color(200,221,242);
	private final Font FONT_BOLD=new Font("Verdana", Font.BOLD, 15);
	private final Font FONT_PLAIN=new Font("Verdana", Font.PLAIN, 15);
	private Frame frame;
	private JPanel mainPanel=new JPanel();
	private JPanel footerPanel=new JPanel();
	private NonEditableTableModel channelTableModel;
	private MainModel mainModel;
	public ChannelListView(MainModel mm){
		mainModel=mm;
		
		frame=new Frame(Global.PROGRAM_CAPTION_STRING, new Dimension(525, 520), 0, JFrame.EXIT_ON_CLOSE, null);
		//create channel table
		Object[] channelTableColumn={"Канал", "Доступно"};
		channelTableModel=new NonEditableTableModel(null, channelTableColumn);
		JTable channelTable=new JTable(channelTableModel);
		//add double click action
		channelTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==1 && e.getClickCount() == 2) {
					mainModel.accessChannelWebPage();
				}
		    }
		});
		channelTable.getColumnModel().getColumn(0).setMaxWidth(400);
		channelTable.getColumnModel().getColumn(1).setMaxWidth(100);
		channelTable.setRowHeight(25);
		channelTable.getTableHeader().setFont(FONT_BOLD);
		channelTable.getTableHeader().setBackground(BACKGROUND_COLOR);
		channelTable.setFont(FONT_PLAIN);
		JScrollPane channelScrollPane=new JScrollPane(channelTable);
		channelScrollPane.setAutoscrolls(true);
		channelScrollPane.setPreferredSize(new Dimension(500,445));
		//create footer
		BoxLayout footerLayout=new BoxLayout(footerPanel, BoxLayout.X_AXIS);
		footerPanel.setLayout(footerLayout);
		JLabel devLabel=new JLabel("IVI Developers Team");
		devLabel.setFont(FONT_PLAIN);
		JLabel verLabel=new JLabel("ver. <ALPHA>");
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
	public void addChannelData(Channel channel){
		channelTableModel.addRow(channel.getData());
	}
}
