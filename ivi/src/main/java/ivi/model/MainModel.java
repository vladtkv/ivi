package ivi.model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionListResponse;

import ivi.view.SubscriptionListView;
import ivi.view.VideoListView;

public class MainModel {
	private SubscriptionListView subscriptionListView;
	private VideoListView videoListView;
	private YouTube youtube;
	private ArrayList<Subscription> subscriptionList;
	private ArrayList<SearchResult> videoList;
	private String currentChannelID="";
	private Desktop desktop=null;
	public MainModel(YouTube yt){
		youtube=yt;
		subscriptionListView=new SubscriptionListView(this);
		videoListView=new VideoListView(this);
		
		subscriptionList=(ArrayList<Subscription>) getSubscriptionList();
		if(subscriptionList!=null){
			Iterator<Subscription> iterator=subscriptionList.iterator();
			while(iterator.hasNext()){
				subscriptionListView.addSubscriptionData(iterator.next());
			}
		}
		if(Desktop.isDesktopSupported())
			desktop=Desktop.getDesktop();
	}
	public void openView(){
		subscriptionListView.open();
	}
	public void accessChannelVideoList(int index){
		String channelID=subscriptionList.get(index).getSnippet().getResourceId().getChannelId();
		Object[] data=new Object [2];
		Date date;
		if(channelID.compareTo(currentChannelID)!=0){
			currentChannelID=channelID;
			videoListView.clearVideoTable();
			videoList=(ArrayList<SearchResult>) getVideoList(subscriptionList.get(index).getSnippet().getResourceId().getChannelId());
			Iterator<SearchResult> iterator=videoList.iterator();
			SearchResult video;
			while(iterator.hasNext()){
				video=iterator.next();
				data[0]=video.getSnippet().getTitle();
				date=new Date();
				date.setTime(video.getSnippet().getPublishedAt().getValue());
				data[1]=date;
				videoListView.addVideo(data);
			}
		}
		videoListView.enableRowSorter();
		videoListView.open();
	}
	public boolean openBrowser(int index){
		if(desktop!=null)
		{
			try {
				desktop.browse(new URI(getVideoURL(videoList.get(index))));
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	private String getVideoURL(SearchResult searchResult){
		return "https://www.youtube.com/watch?v="+searchResult.getId().getVideoId();
	}
	public List<Subscription> getSubscriptionList(){
		try {
			YouTube.Subscriptions.List subscritpionRequest = youtube.subscriptions().list("contentDetails");
			subscritpionRequest.setMine(true);
			subscritpionRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
		    SubscriptionListResponse subscriptionResult=subscritpionRequest.execute();
		     
		    List<Subscription> subscriptionList = subscriptionResult.getItems();
		    if (subscriptionList != null) {
		    	// Define a list to store items in the list of subscriptions.
		        List<Subscription> subscritionItemList = new ArrayList<Subscription>();
		          
		        YouTube.Subscriptions.List subscrListItemRequest=youtube.subscriptions().list("id,contentDetails,snippet");
		        // Only retrieve data used in this application, thereby making
		        // the application more efficient. See:
		        // https://developers.google.com/youtube/v3/getting-started#partial
		        subscrListItemRequest.setFields("items(snippet/title,snippet/publishedAt,snippet/resourceId/channelId),nextPageToken,pageInfo");
		        subscrListItemRequest.setPart("snippet");
		        subscrListItemRequest.setMine(true);

		        String nextToken = "";
		        // Call the API one or more times to retrieve all items in the
		        // list. As long as the API response returns a nextPageToken,
		        // there are still more items to retrieve.
		        do {
		        	subscrListItemRequest.setPageToken(nextToken);
		            SubscriptionListResponse subscriptionItemResult = subscrListItemRequest.execute();
		            subscritionItemList.addAll(subscriptionItemResult.getItems());
		            nextToken = subscriptionItemResult.getNextPageToken();
		        } while (nextToken != null);
		        return subscritionItemList;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<SearchResult> getVideoList(String channelID){
		try {
			YouTube.Search.List searchRequest = youtube.search().list("snippet");
			searchRequest.setChannelId(channelID);
			searchRequest.setType("video");
			
			searchRequest.setFields("items/snippet,items/id/videoId,nextPageToken,pageInfo");
		    SearchListResponse searchResult=searchRequest.execute();
		     
		    List<SearchResult> searchResultList = searchResult.getItems();
		    if (searchResultList != null) {
		    	// Define a list to store items in the list of subscriptions.
		        List<SearchResult> videoItemList = new ArrayList<SearchResult>();
		          
		        searchRequest=youtube.search().list("snippet");
		        // Only retrieve data used in this application, thereby making
		        // the application more efficient. See:
		        // https://developers.google.com/youtube/v3/getting-started#partial
		        searchRequest.setChannelId(channelID);
				searchRequest.setType("video");
				
				searchRequest.setFields("items/snippet,items/id/videoId,nextPageToken,pageInfo");

		        String nextToken = "";
		        // Call the API one or more times to retrieve all items in the
		        // list. As long as the API response returns a nextPageToken,
		        // there are still more items to retrieve.
		        do {
		        	searchRequest.setPageToken(nextToken);
		            searchResult = searchRequest.execute();
		            videoItemList.addAll(searchResult.getItems());
		            nextToken = searchResult.getNextPageToken();
		        } while (nextToken != null);
		        return videoItemList;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
