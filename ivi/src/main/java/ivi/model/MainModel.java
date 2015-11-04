package ivi.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionListResponse;

import ivi.Program;
import ivi.view.SubscriptionListView;

public class MainModel {
	private SubscriptionListView subscriptionListView;
	private YouTube youtube;
	public MainModel(YouTube yt){
		youtube=yt;
		subscriptionListView=new SubscriptionListView(this);
		subscriptionListView.open();
		
		List<Subscription> youTubeSubscriptionList=getSubscriptionList();
		if(youTubeSubscriptionList!=null){
			Iterator<Subscription> iterator=youTubeSubscriptionList.iterator();
			while(iterator.hasNext()){
				subscriptionListView.addSubscriptionData(iterator.next());
			}
		}
	}
	public void accessChannelWebPage(){
		JOptionPane.showMessageDialog(null, "<REDIRECT TO THE YOUTUBE WEB PAGE>", Program.PROGRAM_CAPTION_STRING, JOptionPane.INFORMATION_MESSAGE);
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
		        subscrListItemRequest.setFields("items(snippet/title,snippet/publishedAt),nextPageToken,pageInfo");
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
}
