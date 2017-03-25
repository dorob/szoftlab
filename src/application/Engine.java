package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * A jatek futasaert felelos objektum.
 * @author Tsurhe
 *
 */
public class Engine {
	private Palya level;
	private Scoreboard toplista;
	private String nev;
	private int time;
	
	/**
	 * Engine konstruktora
	 */
	public Engine(){
	//	System.out.println("called: Engine constructor");
		GlobalLogger.log("called: Engine constructor");
		toplista = new Scoreboard();
		
	}
	
	/**
	 * A jatek focklusanak futtatasa.
	 */
	public void run(){
	//	System.out.println("called: Engine -run");
		GlobalLogger.log("called: Engine -run");
	
		if(level==null){
	//		System.out.println("  level = null");
			GlobalLogger.log("  level = null");
			nextLevel();
		}
		if(level!=null)
		level.run();
	}
	/**
	 * A kovetkezo palya betolteset, illetve ha nincs tobb palya a win szekvencia meghivasat
	 * vegzi.
	 * @return Van e kovetkezo palya.
	 */
	public boolean nextLevel(){ 
	//	System.out.println("called: Engine -nextLevel");
		GlobalLogger.log("called: Engine -nextLevel");
		
		try{
		//	System.out.println("Utso palya volt? y/n");
			GlobalLogger.log("Utso palya volt? y/n");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			GlobalLogger.log("INPUT: " + line);
			if(line.equals("y"))
				return true;
			level = new Palya();
			level.init();
			}catch (Exception e){
				e.printStackTrace();
			}
		return false;
		}
	/**
	 * A jatek vegen hivodik meg, megallit minden folyamatot es hozzaadja a jatekost a toplistahoz.
	 */
	public void win(){
	//	System.out.println("called: Engine -win");
		GlobalLogger.log("called: Engine -win");
		toplista.addHelyezes(nev, time);
		toplista.save();
		this.exit();
	}
	/**
	 * Megnyitja a toplistat.
	 */
	public void showScores(){
	//	System.out.println("called: Engine -showScores");
		GlobalLogger.log("called: Engine -showScores");
		toplista.load();
	}
	/**
	 * A vonatok utkozeset detektalja.
	 */
	public void collisionDetection(){
//		System.out.println("called: Engine -collisionDetection");
		GlobalLogger.log("called: Engine -collisionDetection");
		try{
	//		System.out.println("Utkoznek? y/n");
			GlobalLogger.log("Utkoznek? y/n");
			
			//beolvassuk a valaszt
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			GlobalLogger.log("INPUT: " + line);
			
			if(line.equals("y"))
				this.exit();
			else return;
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Leallitja a folyamatokat esbezarja a programot.
	 */
	public void exit(){
	//	System.out.println("called: Engine -exit");
		GlobalLogger.log("called: Engine -exit");
		return;
	}
	
	//Generalt fv.-nyek.
	public Palya getLevel() {
//		System.out.println("called: Engine -getLevel");
		GlobalLogger.log("called: Engine -getLevel");
		return level;
	}
	public void setLevel(Palya level) {
		this.level = level;
	}
	public Scoreboard getToplista() {
		return toplista;
	}
	public void setToplista(Scoreboard toplista) {
		this.toplista = toplista;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	

}
