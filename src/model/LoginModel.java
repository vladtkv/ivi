package model;

import global.Global;

import javax.swing.JOptionPane;

import view.LoginView;

public class LoginModel {
	private LoginView loginView;
	public LoginModel(){
		//open login view
		loginView=new LoginView(this);
		loginView.open();
	}
	public void login(){
		JOptionPane.showMessageDialog(null, "<YOU HAVE SUCCESSFULLY LOGGED IN ! >", Global.PROGRAM_CAPTION_STRING, JOptionPane.INFORMATION_MESSAGE);
		loginView.close();
		//use youtube api to access service data
		//create main model
		new MainModel();
	}
}
