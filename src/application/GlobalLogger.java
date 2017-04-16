package application;

import java.io.File;
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
	static boolean writeInFile = false;
	/**
	 * Ezt a fuggvenyt hivva tudunk fajlba irni ( a fajl elozo tartalma nem torlodik)
	 * @param s Az irni kivant tartalom 
	 */
	public static void log(Object s){ //atirva stringrol hatha mukodik
		try{
			//a loghoz idot is adunk a konnyebb attekinthetoseg erdekeben
			LocalDateTime ldt = LocalDateTime.now();
			String tmp = ldt + " " + s.toString();
			System.out.println(s);
			if(writeInFile && fw !=null){
			fw.write(System.lineSeparator() + tmp );
			fw.flush();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Megnyitja a fajlt a kiirashoz
	 */
	public static void Init(File wd, String[] cmd){
			try {
				fw = new FileWriter(new File(wd, cmd[1]), true);
				writeInFile=true;
				log("+++++++ LOGGING STARTED +++++++");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Bezarja a fajlt
	 */
	public static void stop(){
		try {
			if(fw != null)
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
