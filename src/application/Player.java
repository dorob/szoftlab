package application;
/**
 * A jatekost szimbolizalo objektum. Ebben taroljuk a jatekos nevet, illetve idejet.
 * @author Tsurhe
 */
public class Player implements Comparable{
	private String name;
	private int ertek;
	
	/**
	 * Player constructor
	 */
	public Player(){ //ez majd var egy nevet es egy erteket
//		System.out.println("called: Player constructor");
		GlobalLogger.log("called: Player constructor");
	}
	
	/**
	 * Feluldefinialt kiirato fuggveny, amellyel felhasznalobaratibb kiiratas valik lehetove a Player-eken
	 */
	@Override
	public String toString(){ 
		return " ";
		}
	
	/**
	 * Feluldefinialt osszehasonlito fuggveny az egyszerubb sort-olas erdekeben
	 * @param o Amivel osszehasonlitjuk
	 */
	@Override
	public int compareTo(Object o) {
	//	System.out.println("players compared");
		GlobalLogger.log("players compared");
		return 0;
	}
	
	// generalt fuggvenyek
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getErtek() {
		return ertek;
	}
	public void setErtek(int ertek) {
		this.ertek = ertek;
	};
	
	
}
