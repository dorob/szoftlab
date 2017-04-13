package application;
/**
 * A jatekost szimbolizalo objektum. Ebben taroljuk a jatekos nevet, illetve idejet.
 * @author Tsurhe
 */
public class Player implements Comparable<Player>{
	private String name;
	private int ertek;
	
	/**
	 * Player constructor
	 */
	public Player(String s, int i){ 
//		System.out.println("called: Player constructor");
		GlobalLogger.log("called: Player constructor");
		name =s;
		ertek=i;
	}
	
	/**
	 * Feluldefinialt kiirato fuggveny, amellyel felhasznalobaratibb kiiratas valik lehetove a Player-eken
	 */
	@Override
	public String toString(){ 
		return (name+": "+Integer.toString(ertek));
		}
	
	/**
	 * Feluldefinialt osszehasonlito fuggveny az egyszerubb sort-olas erdekeben
	 * @param o Amivel osszehasonlitjuk
	 */
	@Override
	public int compareTo(Player o) {
	//	System.out.println("players compared");
		GlobalLogger.log("players compared");
		if (ertek< o.ertek) return 1;
		
		if(ertek > o.ertek) return -1;
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
