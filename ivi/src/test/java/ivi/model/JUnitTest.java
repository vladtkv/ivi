package ivi.model;

import ivi.model.LoginModel;
import ivi.model.MainModel;
import ivi.view.SubscriptionListView;
import ivi.youtube.GoogleAuthorizer;

import org.junit.*;

import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionSnippet;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

import static org.mockito.Mockito.*;

public class JUnitTest {
	
	private static LoginModel lm;
	private static MainModel mm;
	private static GoogleAuthorizer googleAuthorizerMock;
	private Subscription sub;
	private SubscriptionSnippet subSnip;
	private SubscriptionListView subListView;
	private static SearchResult searchRes;
	
	@BeforeClass
	public static void initTest(){
		googleAuthorizerMock=mock(GoogleAuthorizer.class);
		lm=new LoginModel(googleAuthorizerMock);
		mm=new MainModel();
		searchRes = new SearchResult();
		searchRes.setId(new ResourceId().setVideoId("TestVideoID"));
	}
	
	@Test
	public void authentificationFailedTest(){
		doReturn(null).when(googleAuthorizerMock).authorize();
		lm.login();
		verify(googleAuthorizerMock).authorize();
		verify(googleAuthorizerMock, never()).getYouTubeObject(null);
	}
	
	@Test
	public void addSubscriptionDataTest(){
		Assume.assumeTrue(System.getProperty("ivi.test.uiskip")==null);
		subListView = new SubscriptionListView(null);
		sub = new Subscription();
		subSnip = new SubscriptionSnippet();
		subSnip.setTitle("AWESOME TITLE");
		sub.setSnippet(subSnip);
		Assert.assertEquals(false, subListView.addSubscriptionData(null));
		Assert.assertEquals(true, subListView.addSubscriptionData(sub));
	}
	
	@Test
	public void searchResultTest(){
		Assert.assertEquals("https://www.youtube.com/watch?v=TestVideoID",
		mm.getVideoURL(searchRes));
	}
}
