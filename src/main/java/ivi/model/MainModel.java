package ivi.model;

import ivi.global.Global;

import javax.swing.JOptionPane;

import ivi.model.type.Channel;
import ivi.view.ChannelListView;

public class MainModel {
	private ChannelListView channelListView;
	public MainModel(){
		channelListView=new ChannelListView(this);
		channelListView.open();
		//adding testing data
		channelListView.addChannelData(new Channel("Some funny video", 2));
		channelListView.addChannelData(new Channel("Cool video", 1));
		channelListView.addChannelData(new Channel("Video channel", 0));
		channelListView.addChannelData(new Channel("Don`t forget about study !!!", 15));
	}
	public void accessChannelWebPage(){
		JOptionPane.showMessageDialog(null, "<REDIRECT TO THE YOUTUBE CHANNEL WEB PAGE>", Global.PROGRAM_CAPTION_STRING, JOptionPane.INFORMATION_MESSAGE);
	}
}
