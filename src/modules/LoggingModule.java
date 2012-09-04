package modules;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LoggingModule {

	public static final int INFO = 0;
	public static final int DEBUG = 1;
	public static final int NOTICE = 2;
	public static final int WARNING = 3;
	public static final int ERROR = 4;
	public static final int CRITICAL = 5;
	public static final int ALERT = 6;
	public static final int EMERGENCY = 7;

	private static String[] logFiles = {"info.txt", "debug.txt", "notice.txt", "warning.txt",
		"error.txt", "critical.txt", "alert.txt", "emergency.txt"};


	public static void log (int LEVEL, String message){
		logMessage(logFiles[LEVEL], message);
	}


	private static void logMessage(String filename, String message) {

		try{
			// Create file 
			FileWriter fstream = new FileWriter(filename,true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("The " + filename.substring(0, filename.indexOf(".")).toUpperCase() + " log entry at " + System.currentTimeMillis() + " is: " + message);
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}
