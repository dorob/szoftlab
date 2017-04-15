package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;


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
		GlobalLogger.log("called: Engine constructor");
		toplista = new Scoreboard();
		
	}
	
	/**
	 * A jatek focklusanak futtatasa.
	 */
	public void run(){
		GlobalLogger.log("called: Engine -run");
	
		if(level.checkCompleted()){
			GlobalLogger.log("  level completed");
			if(!nextLevel()){
				win();
			}
		}
		else{
			level.run();
		}
	}
	/**
	 * A kovetkezo palya betolteset vegzi, ha nincs tobb palya false-al ter vissza, a jatekos nyert.
	 * @return Van e kovetkezo palya.
	 */
	public boolean nextLevel(){ 
		GlobalLogger.log("called: Engine -nextLevel");
		
		/*
		 * try{
		 *	uj palya betoltese betoltes(deszerializalas fajlbol)
		 *
		 *	pl: FileInputStream fileIn = new FileInputStream(new String("level"+number+".ser"));
		 *		number++;
		 *		a fajlnevek:level1,level2,...,leveln 
		 *		n+1nel kivetelt dob ebbol tudjuk h uccso palya volt
		 *	return true; // sikeresen betoltottuk a palyat
		 * }
		 * cath(IOException i){
		 * 	return false; // nincs tobb betoltendo palya, nyertunk
		 * }
		 * Fentihez hasonloan lesz majd csak a betoltes kulon fuggvennyel lesz (Magic.loadshit)
		 */
		return true;
		}
	/**
	 * A jatek vegen hivodik meg, megallit minden folyamatot es hozzaadja a jatekost a toplistahoz.
	 */
	public void win(){
		GlobalLogger.log("called: Engine -win");
		toplista.addHelyezes(nev, time);
		toplista.save();
		this.exit();
	}
	/**
	 * Megnyitja a toplistat.
	 */
	public void showScores(){
		GlobalLogger.log("called: Engine -showScores");
		String s="";
		//toplista.load(); // serialize ellenorzes
		Collections.sort(toplista.getHelyezes());
		
		for(Player p: toplista.getHelyezes()){
			s+=p.toString()+'\n';
		}
		
		GlobalLogger.log(s);
		
	}
	/**
	 * A vonatok utkozeset detektalja.
	 */
	public void collisionDetection(){
		GlobalLogger.log("called: Engine -collisionDetection");
		try{
			GlobalLogger.log("Utkoznek? y/n");
			
			//beolvassuk a valaszt
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			GlobalLogger.log("----INPUT: " + line);
			
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
		GlobalLogger.log("called: Engine -exit");
		return;
	}
	
	//Generalt fv.-nyek.
	public Palya getLevel() {
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
