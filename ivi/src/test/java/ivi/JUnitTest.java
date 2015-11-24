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
	public void theBestTestInTheTestWorld(){
		String s1 = "SKR";
		String s2 = s1;
		String s3 = new String("SKR");
		Assert.assertEquals("SKR", s1);
		Assert.assertEquals(s1, s2);
		Assert.assertEquals(s2, s3);
	}
	
	@Test
	public void testForTest(){
		int var1 = 6;
		Assert.assertEquals(1, var1^7);
	}
}
