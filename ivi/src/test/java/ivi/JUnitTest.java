package ivi;

import ivi.model.LoginModel;
import ivi.youtube.GoogleAuthorizer;

import org.junit.*;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

import static org.mockito.Mockito.*;

public class JUnitTest {
	private static LoginModel lm;
	private static GoogleAuthorizer googleAuthorizerMock;
	private static SearchResult searchRes;
	@BeforeClass
	public static void initTest(){
		googleAuthorizerMock=mock(GoogleAuthorizer.class);
		lm=new LoginModel(googleAuthorizerMock);
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
	public void theBestTestInTheTestWorld(){
		String s1 = "SKR";
		String s2 = s1;
		String s3 = new String("SKR");
		Assert.assertEquals("SKR", s1);
		Assert.assertEquals(s1, s2);
		Assert.assertEquals(s2, s3);
	}
	
	@Test
	public void searchResultTest(){
		Assert.assertEquals("https://www.youtube.com/watch?v=TestVideoID",
		"https://www.youtube.com/watch?v="+searchRes.getId().getVideoId());
	}
}
