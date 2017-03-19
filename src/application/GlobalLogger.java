package application;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;


public class GlobalLogger {
	static FileWriter fw;
	public static void log(String s){
		try{
			LocalDateTime ldt = LocalDateTime.now();
			String tmp = ldt + " " + s;
			fw.write(System.lineSeparator() + tmp );
			fw.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void Init(){
			try {
				fw = new FileWriter("Log.txt", true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void stop(){
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
