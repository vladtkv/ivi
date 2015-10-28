package ivi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo( name = "getversion")
public class VersionMojo extends AbstractMojo
{
	public void execute() throws MojoExecutionException
	{
		getLog().info("Getting version." );
		OutputStream os = null; 
		InputStream is = null; 
		//
 		Runtime runtime = Runtime.getRuntime(); 
 		Process process = null; 
 		try { 
 			process = runtime.exec(new String[] { "git", "describe" }); 
 		} catch (IOException e) { 
 			throw new RuntimeException(e); 
 		} 
 		os = process.getOutputStream(); 
 		is = process.getInputStream(); 
 		String version=null;
 		Scanner sc = new Scanner(is); 
		while (sc.hasNext()) { 
				version=sc.next();
		}
 		sc.close();
 		try { 
 			os.close(); 
 			is.close();
 		} catch (IOException e) { 
 			throw new RuntimeException(e); 
 		} 		
 		getLog().info("Version-" +version);
 		//save version to Version.class
 		String root=new File("").getAbsolutePath();
 		File dir=new File(root+"/target/generated-sources/");
 		if(!dir.exists())
			dir.mkdirs();
 		File file=new File("target/generated-sources/Version.java");
 		getLog().info("Generating version class.");
 		try {
			OutputStream out=new FileOutputStream(file);
			out.write("package ivi;public class Version {".getBytes());
			out.write("public static final String PROGRAM_VERSION=\"".getBytes());
			out.write((version+"\";").getBytes());
			out.write("}".getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		getLog().info("Done.");
	}
}
