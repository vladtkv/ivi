package ivi.view;

import ivi.Program;
import ivi.model.MainModel;
import ivi.swing.Frame;
import ivi.swing.NonEditableVideoTableModel;
import ivi.swing.TableDateCellRenderer;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class VideoListView {
	private final Color BACKGROUND_COLOR=new Color(200,221,242);
	private final Font FONT_BOLD=new Font("Verdana", Font.BOLD, 15);
	private final Font FONT_PLAIN=new Font("Verdana", Font.PLAIN, 15);
	private Frame frame;
	private JPanel mainPanel=new JPanel();
	private JPanel footerPanel=new JPanel();
	private JTable videoTable;
	private NonEditableVideoTableModel videoTableModel;
	private MainModel mainModel;
	private boolean rowSorterEnabled=false;
	public VideoListView(MainModel mm){
		mainModel=mm;
		
		frame=new Frame(Program.PROGRAM_CAPTION_STRING, new Dimension(725, 620), 0, JFrame.DISPOSE_ON_CLOSE, null);
		//create channel table
		Object[] subscriptionTableColumn={"Video", "Published"};
		videoTableModel=new NonEditableVideoTableModel(null, subscriptionTableColumn);
		videoTable=new JTable(videoTableModel);
		//add double click action
		videoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==1 && e.getClickCount() == 2) {
					if(!mainModel.openBrowser(videoTable.convertRowIndexToModel(videoTable.getSelectedRow()))){
						JOptionPane.showMessageDialog(null, "There was an error while openning video on YouTube!", Program.PROGRAM_CAPTION_STRING, JOptionPane.WARNING_MESSAGE);
					}
				}
		    }
		});
		videoTable.getColumnModel().getColumn(0).setMaxWidth(525);
		videoTable.getColumnModel().getColumn(1).setMaxWidth(200);
		videoTable.getColumnModel().getColumn(1).setCellRenderer(new TableDateCellRenderer());
		videoTable.setRowHeight(25);
		videoTable.getTableHeader().setFont(FONT_BOLD);
		videoTable.getTableHeader().setBackground(BACKGROUND_COLOR);
		videoTable.setFont(FONT_PLAIN);
		JScrollPane videoScrollPane=new JScrollPane(videoTable);
		videoScrollPane.setAutoscrolls(true);
		videoScrollPane.setPreferredSize(new Dimension(700,545));
		videoScrollPane.setMaximumSize(new Dimension(700,545));
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

		mainPanel.add(videoScrollPane);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(footerPanel, BorderLayout.SOUTH);
	}
	public void open(){
		frame.setVisible(true);
	}
	public void addVideo(Object[] data){
		videoTableModel.addRow(data);
	}
	public void enableRowSorter(){
		if(!rowSorterEnabled){
			RowSorter<TableModel> sorter=new TableRowSorter<TableModel>(videoTableModel);
			videoTable.setRowSorter(sorter);
			sorter.toggleSortOrder(1);
			sorter.toggleSortOrder(1);
			rowSorterEnabled=true;
		}
	}
	public void clearVideoTable(){
//		int rowCount=videoTableModel.getRowCount();
		while(videoTableModel.getRowCount()>0)
			videoTableModel.removeRow(0);
	}
}
