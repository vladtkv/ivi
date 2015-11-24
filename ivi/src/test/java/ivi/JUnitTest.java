package ivi;

import ivi.model.LoginModel;
import ivi.youtube.GoogleAuthorizer;

import org.junit.*;

import static org.mockito.Mockito.*;

public class JUnitTest {
	private static LoginModel lm;
	private static GoogleAuthorizer googleAuthorizerMock;
	@BeforeClass
	public static void initTest(){
		googleAuthorizerMock=mock(GoogleAuthorizer.class);
		lm=new LoginModel(googleAuthorizerMock);	
	}
	@Test
	public void authentificationFailedTest(){
		doReturn(null).when(googleAuthorizerMock).authorize();
		lm.login();
		verify(googleAuthorizerMock).authorize();
		verify(googleAuthorizerMock, never()).getYouTubeObject(null);
	}
	
	@Test
	public void testForTest(){
		int var1 = 6;
		Assert.assertEquals(1, var1^7);
	}
}
