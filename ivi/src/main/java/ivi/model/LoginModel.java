package ivi.model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.YouTube;

import ivi.view.LoginView;
import ivi.youtube.GoogleAuthorizer;

public class LoginModel {
	private LoginView loginView;
	private GoogleAuthorizer google;
	private ExponentialBackOff backoff;

	public LoginModel(GoogleAuthorizer g) {
		google = g;
		// create login view
		loginView = new LoginView(this);
		// configure exponential backoff
		backoff=new ExponentialBackOff();
		backoff.setInitialIntervalMillis(500);
		backoff.setMaxElapsedTimeMillis(35000);
		backoff.setMaxIntervalMillis(9000);
		backoff.setMultiplier(1.5);
	}

	public void initView() {
		loginView.init();
	}

	public void openView() {
		loginView.open();
	}

	public boolean login() {
		//Use exponential backoff
		backoff.reset();
		Credential credential=null;
		long backoffInterval=backoff.nextBackOffMillis();
		while(backoffInterval!=-1){
			credential = google.authorize();
			if(credential==null){
				System.out.println("Connection failed!");
				System.out.println("Waiting "+backoffInterval+" millis...");
				try {
					Thread.sleep(backoffInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				backoffInterval=backoff.nextBackOffMillis();
			}
			else{
				backoffInterval=-1;
			}
		}
		if (credential != null) {
			YouTube youtube = google.getYouTubeObject(credential);
			// Create main model
			MainModel mainModel = new MainModel();
			mainModel.init(youtube);
			mainModel.openView();
			loginView.close();
			return true;
		} else {
			return false;
		}
	}
}
