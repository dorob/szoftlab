package application;

import java.util.ArrayList;


/**
 * A legjobb idot elert jatekosok adatait taroljuk ebben a toplistaban. Az osszes megnyert jatek eredmenyet meg lehet itt tekinteni,
 *  a jatekos nevevel egyutt.
 * @author Tsurhe
 */
public class Scoreboard {
	private ArrayList<Player> helyezes;
	
	/**
	 * Scoreboard constructor
	 */
	public Scoreboard(){
		System.out.println("called: scoreboard constructor");
		GlobalLogger.log("called: scoreboard constructor");
		helyezes = new ArrayList<Player>();
	}
	
	/**
	 * Kimenti fajlba a helyezeseket
	 */
	public void save(){
		System.out.println("called scoreboard - save");
		GlobalLogger.log("called scoreboard - save");
	}
	
	/**
	 * Betolti fajlbol a helyezeseket
	 */
	public void load(){
		System.out.println("called: Scoreboard -load");
		GlobalLogger.log("called: Scoreboard -load");
	}
	
	/**
	 * Nyert jatek eseten rendezve hozzad egy uj jatekos eredmenyen a helyezeshez
	 * @param s A jatekos neve
	 * @param i A jatekos idoeredmenye
	 */
	public void addHelyezes(String s, int i) {
		System.out.println("called: scoreboard -addHelyezes");
		GlobalLogger.log("called: scoreboard -addHelyezes");
		System.out.println("--players sorted"); //comparator megirasa utan egyszeru lesz a sort-olas
		GlobalLogger.log("--players sorted");
	}
	
	// generalt fuggvenyek
	public Scoreboard getScoreboard(){ 
		return this;
	}
	public void SetScoreboard(Scoreboard in) {
		
	}
	public ArrayList<Player> getHelyezes() {
		return helyezes;
	}
	public void setHelyezes(ArrayList<Player> helyezes) {
		this.helyezes = helyezes;
	}
}
