package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import global.Global;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.LoginModel;
import swing.Frame;
import swing.ImageComponent;

public class LoginView {
	private Frame frame;
	private JPanel mainPanel=new JPanel();
	private LoginModel loginModel;
	public LoginView(LoginModel lm){
		loginModel=lm;
		
		frame=new Frame(Global.PROGRAM_CAPTION_STRING, new Dimension(350, 425), 0, JFrame.EXIT_ON_CLOSE, null);
		
		BoxLayout mainPanelLayout=new BoxLayout(mainPanel, BoxLayout.X_AXIS);
		mainPanel.setLayout(mainPanelLayout);
		
		JPanel panel=new JPanel();
		BoxLayout panelLayout=new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(panelLayout);
		panel.add(Box.createVerticalGlue());
		//add google image
		JPanel imagePanel=new JPanel();
		imagePanel.add(new ImageComponent("img/google_logo.png", new Dimension(200, 70)));
		//
		panel.add(imagePanel);
		//add button panel
		JPanel buttonPanel=new JPanel();
		JButton loginButton=new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginModel.login();
			}
		});
		buttonPanel.add(loginButton);
		//
		panel.add(buttonPanel);
		panel.add(Box.createVerticalGlue());
		
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(panel);
		mainPanel.add(Box.createHorizontalGlue());
		
		frame.add(mainPanel);
	}
	public void open(){
		frame.setVisible(true);
	}
	public void close(){
		frame.dispose();
	}
}
