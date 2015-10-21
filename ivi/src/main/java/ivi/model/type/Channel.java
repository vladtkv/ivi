package ivi.model.type;

public class Channel {
	private String channelCaption;
	private int newVideoNumber;
	public Channel(String cc, int nvn){
		channelCaption=cc;
		newVideoNumber=nvn;
	}
	public String getChannelCaption() {
		return channelCaption;
	}
	public int getNewVideoNumber() {
		return newVideoNumber;
	}
	public Object[] getData(){
		Object[] data={channelCaption, newVideoNumber};
		return data;
	}
}
