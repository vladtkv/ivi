package ivi.view;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionSnippet;

public class JUITest {
	private static SubscriptionListView slv;
	@BeforeClass
	public static void initTest(){
		Assume.assumeTrue(System.getProperty("ivi.test.uiskip")==null);
		slv=new SubscriptionListView(null);
		//Add test subscriptions
		Subscription subscriptionA=new Subscription();
		SubscriptionSnippet snippetA=new SubscriptionSnippet();
		snippetA.setTitle("Test subscription A");
		subscriptionA.setSnippet(snippetA);
		Subscription subscriptionB=new Subscription();
		SubscriptionSnippet snippetB=new SubscriptionSnippet();
		snippetB.setTitle("Test subscription B");
		subscriptionB.setSnippet(snippetB);
		
		slv.addSubscriptionData(subscriptionA);
		slv.addSubscriptionData(subscriptionB);
	}
	@Test
	public void addSubscriptionTest(){
		Assert.assertEquals(2, slv.getSubscriptionTableRowCount());
	}
}
