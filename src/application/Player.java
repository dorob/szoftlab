package application;

public class Player implements Comparable{
	private String name;
	private int ertek;
	
	/**
	 * Player constructor
	 */
	public Player(){ //ez majd var egy nevet es egy erteket
		System.out.println("called: Player constructor");
	}
	public String toString(){ return "random string";};
	//public void compareTo(Player a){}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		System.out.println("players compared");
		return 0;
	}
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
