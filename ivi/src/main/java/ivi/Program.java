package ivi;

import java.util.Scanner;

import ivi.model.LoginModel;

public class Program {
	public static String version;
	public static final String PROGRAM_CAPTION_STRING="IVI YouTube Client";
	public static void main(String... args){
		new LoginModel();
		//get program version
		Scanner sc=new Scanner(Program.class.getClass().getResourceAsStream("/version.dat"));
		version=sc.next();
		sc.close();
	}
}
