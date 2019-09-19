package Sikuli.Sikuli;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;

import com.jcraft.jsch.*;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class SimPointSikuli {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Screen s = new Screen();
	    

	    try {
	    	
	    	//startServer();
        	launchSimApp(s);
        	startTest(s);

        } catch (Exception e) {     

            e.printStackTrace();
            System.out.println("existed with error code");

        }
    }
	
	public static void startTest(Screen s)
	{
    	
		try {
			s.wait("images/winSessions.png");
			
	        s.click("images/btnDismiss.png");
	        s.click("images/tabSessions.png");
	        s.click("images/tabPanels.png");
	
	        s.wait("images/menuReplayFromFile.png");
	        
	
	        s.click("images/menuReplayFromFile.png");
	        s.wait("images/btnBrowse.png");
	
	        s.click("images/btnBrowse.png");
	        
	        s.type("C:\\SikuliX\\gclog_Simpoint.txt");
	
	        s.click("images/btnOpen.png");
	        
	        s.click("images/rbtnAll.png");
	        s.click("images/rbtnAll2.png");
	        
	        Iterator<Match> j = s.getScreen().findAll("images/rbtnAll.png");

	        while  (j.hasNext())
	        {
	        	Match checkbox = j.next();
	        	checkbox.highlight((float) 1.0);
	        	s.getScreen().click(checkbox);
	        }
	        
	        //s.click("images/tabResults.png");
	        //s.click("images/btnStart.png");
	
	        assertNotNull("Verify correct value", s.exists("images/btnStart.png"));     
		}
		catch ( FindFailed f)
		{
			f.printStackTrace();
		}
	}
	private static void launchSimApp(Screen s)
	{
		try
		{
		    //Runtime r = Runtime.getRuntime();
		    //Process p2 = r.exec("C:\\Users\\Ashish\\Downloads\\SimpointClient-3.2.403\\SimPointClient\\SimpointClient.exe"); //absolute or relative path
		    double timer = 10;
			s.click("images/btnStartMenu.png");
		    s.wait("images/runTxtBox.png");
		    s.type("C:\\Users\\Ashish\\Downloads\\SimpointClient-3.2.403\\SimPointClient\\SimpointClient.exe" + Key.ENTER);
		    s.wait(timer);
		    s.click("images/btnLicenseOK.png");
		    //s.waitVanish("images/btnLicenseOK.png");
		    s.wait("images/simPointClient.png", 10);
		    s.wait(timer);
		}
		catch(FindFailed ex)
		{
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	private static void startServer()
	{

		try {
		    String command = "plink -ssh -pw India!@12 root@139.162.45.27";

		    Runtime r = Runtime.getRuntime ();
		    Process p = r.exec (command);
		    
		    InputStream std = p.getInputStream ();
		    OutputStream out = p.getOutputStream ();
		    InputStream err = p.getErrorStream ();

		    out.write ("ls -l\n".getBytes ());
		    out.flush ();

		    Thread.sleep (1000);

		    int value = 0;
		    if (std.available () > 0) {
		        System.out.println ("STD:");
		        value = std.read ();
		        System.out.print ((char) value);

		        while (std.available () > 0) {
		            value = std.read ();
		            System.out.print ((char) value);
		        }
		    }

		    if (err.available () > 0) {
		        System.out.println ("ERR:");
		        value = err.read ();
		        System.out.print ((char) value);

		        while (err.available () > 0) {
		            value = err.read ();
		            System.out.print ((char) value);
		        }
		    }

		    //p.destroy (); 
		}
		catch (Exception e) {
		    e.printStackTrace ();
		}
	}

}
