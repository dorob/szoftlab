package application;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Loggolo osztaly, amin duplikalunk minden kimeno es bemeno parancsot, ill. valaszt
 * @author Tsurhe
 *
 */
public class GlobalLogger {
	static FileWriter fw;
	
	/**
	 * Ezt a fuggvenyt hivva tudunk fajlba irni ( a fajl elozo tartalma nem torlodik)
	 * @param s Az irni kivant tartalom 
	 */
	public static void log(String s){
		try{
			//a loghoz idot is adunk a konnyebb attekinthetoseg erdekeben
			LocalDateTime ldt = LocalDateTime.now();
			String tmp = ldt + " " + s;
			fw.write(System.lineSeparator() + tmp );
			fw.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Megnyitja a fajlt a kiirashoz
	 */
	public static void Init(){
			try {
				fw = new FileWriter("Log.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Bezarja a fajlt
	 */
	public static void stop(){
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
