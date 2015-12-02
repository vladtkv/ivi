package ivi.youtube;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.common.collect.Lists;

public class GoogleAuthorizer {
	public Credential authorize() {
		Credential credential = null;
		if (checkInternetConnection()) {
			// Use YouTube API to access service data
			// This OAuth 2.0 access scope allows for read-only access to the
			// authenticated user's account, but not other types of account
			// access.
			List<String> scopes = Lists
					.newArrayList("https://www.googleapis.com/auth/youtube.readonly");
			try {
				credential = Auth.authorize(scopes, "mysubscriptions");

			} catch (GoogleJsonResponseException e) {
				e.printStackTrace();
				System.err.println("There was a service error: "
						+ e.getDetails().getCode() + " : "
						+ e.getDetails().getMessage());

			} catch (IOException e) {
				System.err.println("User authorization failed (access_denied)");
			}
		}
		return credential;
	}

	public YouTube getYouTubeObject(Credential credential) {
		return new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY,
				credential).setApplicationName("ivi-youtube-client").build();
	}

	private boolean checkInternetConnection() {
		try {
			URL url = new URL("http://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
