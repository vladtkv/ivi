package ivi;

import ivi.model.LoginModel;

public class Program {
	public static String version;
	public static final String PROGRAM_CAPTION_STRING="IVI YouTube Client";
	public static void main(String... args){
		//load Version.class to get PROGRAM_VERSION constant
		try {
			Class<?> clazz=ClassLoader.getSystemClassLoader().loadClass("ivi.Version");
			version=(String) clazz.getField("PROGRAM_VERSION").get(new String());
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		new LoginModel();
	}
}
