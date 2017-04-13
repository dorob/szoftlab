package application;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;


/**
 * A legjobb idot elert jatekosok adatait taroljuk ebben a toplistaban. Az osszes megnyert jatek eredmenyet meg lehet itt tekinteni,
 *  a jatekos nevevel egyutt.
 * @author Tsurhe
 */
public class Scoreboard implements Serializable{
	private ArrayList<Player> helyezes;
	
	/**
	 * Scoreboard constructor
	 */
	public Scoreboard(){
	//	System.out.println("called: scoreboard constructor");
		GlobalLogger.log("called: scoreboard constructor");
		helyezes = new ArrayList<Player>();
		helyezes.add(new Player("a", 8));
		helyezes.add(new Player("a", 5));
		helyezes.add(new Player("a", 6));
		helyezes.add(new Player("a", 7));
		Collections.sort(helyezes);
		
	}
	
	/**
	 * Kimenti fajlba a helyezeseket
	 */
	public void save(){
	//	System.out.println("called scoreboard - save");
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("Scoreboard.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();	         
	      }catch(IOException i) {
	         GlobalLogger.log("Scoreboard save failed.");;
		GlobalLogger.log("called scoreboard - save");
	      }
	}
	
	/**
	 * Betolti fajlbol a helyezeseket
	 */
	public Scoreboard load(){
//		System.out.println("called: Scoreboard -load");
		Scoreboard e = null;
		try {
	         FileInputStream fileIn = new FileInputStream("Scoreboard.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Scoreboard) in.readObject();
	         in.close();
	         fileIn.close();
	         GlobalLogger.log("called: Scoreboard -load");
	         return e;
	      }catch(IOException i) {
	         GlobalLogger.log("File not found");	        
	      }catch(ClassNotFoundException c) {
	         System.out.println("Scoreboard class not found");
	         c.printStackTrace();
	      }		
		return null;
	}
	
	/**
	 * Nyert jatek eseten rendezve hozzad egy uj jatekos eredmenyen a helyezeshez
	 * @param s A jatekos neve
	 * @param i A jatekos idoeredmenye
	 */
	public void addHelyezes(String s, int i) {
	//	System.out.println("called: scoreboard -addHelyezes");
		GlobalLogger.log("called: scoreboard -addHelyezes");		
		helyezes.add(new Player(s,i));
		Collections.sort(helyezes);
//		System.out.println("--players sorted"); //comparator megirasa utan egyszeru lesz a sort-olas
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
