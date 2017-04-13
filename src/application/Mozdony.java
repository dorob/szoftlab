package application;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
/**
 * A palyan mozgo vonatok "vezetoje". Minden vonat 1 mozdonybol es tetszoleges szamu vagonbol all.
 * @author Tsurhe
 *
 */
public class Mozdony {
	
	private ArrayList<Vagon> vagonok;
	private ArrayList<Sin> ways;
	private Rectangle2D forma;
	private ArrayList<Sin> utvonal;
	private boolean isDone;
	/**
	 * Mozdony konstruktor
	 */
	public Mozdony(){
//		System.out.println("called: mozdony constructor");
		GlobalLogger.log("called: mozdony constructor");
<<<<<<< HEAD
		vagonok = new ArrayList<Vagon>();
		ways = new ArrayList<Sin>();
		
		vagonok.add(new Vagon());
		ways.add(new Sin());
		
		utvonal = new ArrayList<Sin>();
		utvonal.add(new Sin());
=======
>>>>>>> 6dee537372489843e60893bd3b8c0fd52e1ea890
	}
	/**
	 * A vonat mozgatasa 2 ControlPoint kozott egy sinen.
	 */
	public void move(){
	//	System.out.println("called: mozdony -move");
		GlobalLogger.log("called: mozdony -move");
		for(Vagon m: vagonok) m.move(); 
		if(ways.isEmpty()) 
			doneMoving();
	}
	/**
	 * Ezzel vizsgalhatjuk avonatok utkozeset.
	 * @return utkozik e a vonat.
	 */
	public boolean CollisionDetection(){ return false;}
	/**
	 * Akkor hivodik mikor elfogynak az eppen adott utak amin a vonatnak vegig kell mennie.
	 */
	public void doneMoving(){
	//	System.out.println("called: mozdony -doneMoving");
		GlobalLogger.log("called: mozdony -doneMoving");
		utvonal.get(0).giveNext();
	}
	/**
	 * Hozzaad a vonat utvonalahoz egy sint
	 * @param s A hozzaadando sin.
	 */
	public void addWay(Sin s){}
	/**
	 * Mikor a megalloba er ezt a fuggvenyt hivja a vonat, 
	 * @param c megallo szine
	 * @param getUp Van e ott folszallo
	 * @return Igaz, ha sikerult folszallni barmelyik vagonjan
	 */
	public boolean stationArrive(Color c, boolean getUp){
//		System.out.println("called: mozdony -stationArrive");
		GlobalLogger.log("called: mozdony -stationArrive");
		vagonok.get(0).getDown(c);
		if(getUp == true)
			if(vagonok.get(0).getUp(c)==true)
				return true;
		return false;
	}

	//Generaltfuggvenyek.
	
	public ArrayList<Vagon> getVagonok() {
		return vagonok;
	}

	public void setVagonok(ArrayList<Vagon> vagonok) {
		this.vagonok = vagonok;
	}

	public ArrayList<Sin> getWays() {
		return ways;
	}

	public void setWays(ArrayList<Sin> ways) {
		this.ways = ways;
	}

	public Rectangle2D getForma() {
		return forma;
	}

	public void setForma(Rectangle2D forma) {
		this.forma = forma;
	}

	public ArrayList<Sin> getUtvonal() {
		return utvonal;
	}

	public void setUtvonal(ArrayList<Sin> utvonal) {
		this.utvonal = utvonal;
	}
	
	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
}
