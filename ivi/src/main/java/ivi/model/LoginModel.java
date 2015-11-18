package ivi.model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.YouTube;
import ivi.view.LoginView;
import ivi.youtube.GoogleAuthorizer;

public class LoginModel {
	private LoginView loginView;
	private GoogleAuthorizer google;
	public LoginModel(GoogleAuthorizer g){
		google=g;
		//create login view
		loginView=new LoginView(this);
	}
	public void openView(){
		loginView.open();
	}
	public boolean login(){ 
		Credential credential=google.authorize();
		if(credential!=null){
			YouTube youtube=google.getYouTubeObject(credential);
			//Create main model
			MainModel mainModel=new MainModel(youtube);
			mainModel.openView();
			loginView.close();
			return true;
		}
		else{
			return false;
		}
	}	
}
