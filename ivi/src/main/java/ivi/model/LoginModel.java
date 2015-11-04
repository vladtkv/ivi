package ivi.model;

import java.util.List;

import javax.swing.JOptionPane;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.common.collect.Lists;

import ivi.Program;
import ivi.view.LoginView;
import ivi.youtube.Auth;

public class LoginModel {
	private LoginView loginView;
	public LoginModel(){
		//open login view
		loginView=new LoginView(this);
		loginView.open();
	}
	public void login(){
		//Use YouTube API to access service data
		// This OAuth 2.0 access scope allows for read-only access to the
        // authenticated user's account, but not other types of account access.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.readonly");
        YouTube youtube=null;
		try {
			Credential credential=Auth.authorize(scopes, "mysubscriptions");
			youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName("ivi-youtube-client").build();
			
		} catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());

        } catch (Throwable t) {
            t.printStackTrace();
        }
		//Create main model
		if(youtube!=null){
			new MainModel(youtube);
			loginView.close();
		}
		else{
			JOptionPane.showMessageDialog(null, "There was an error while connecting to the YouTube service!", Program.PROGRAM_CAPTION_STRING, JOptionPane.WARNING_MESSAGE);
		}			
	}
}
