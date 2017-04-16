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
	
	private ArrayList<Vagon> vagonok = new ArrayList<Vagon>();
	private Sin ways; //elozo ut amin voltam
	private Rectangle2D forma;
	private Sin utvonal; //amin most vagyok
	private boolean isDone;
	/**
	 * Mozdony konstruktor
	 */
	public Mozdony(){
		GlobalLogger.log("called: mozdony default constructor");
	}
	/**
	 * A vonat mozgatasa 2 ControlPoint kozott egy sinen.
	 * @throws CollideException 
	 */
	public void move() throws CollideException{
		GlobalLogger.log("called: mozdony -move");
		for(Vagon m: vagonok) m.move(); 
		try{ 
			doneMoving();
		}catch(CollideException e){
		throw e;
		}
	}
	/**
	 * Ezzel vizsgalhatjuk avonatok utkozeset.
	 * @return utkozik e a vonat.
	 */
	public boolean CollisionDetection(){ return false;}
	/**
	 * Akkor hivodik mikor elfogynak az eppen adott utak amin a vonatnak vegig kell mennie.
	 * @throws CollideException 
	 */
	public void doneMoving() throws CollideException{
		try{
			GlobalLogger.log("called: mozdony -doneMoving");
		utvonal.giveNext(this);
		}catch(CollideException e){
			throw e;
		}
	}
	/**
	 * Hozzaad a vonat utvonalahoz egy sint
	 * @param s A hozzaadando sin.
	 */

	public void addWay(Sin s){
		ways = utvonal;
		ways.mozdony = null;
		utvonal = s;
		utvonal.mozdony = this;
	}


	/**
	 * Mikor a megalloba er ezt a fuggvenyt hivja a vonat, 
	 * @param c megallo szine
	 * @param getUp Van e ott folszallo
	 * @return Igaz, ha sikerult folszallni barmelyik vagonjan
	 */
	public boolean stationArrive(Color c, boolean getUp){
		GlobalLogger.log("called: mozdony -stationArrive");
		for(Vagon v:this.vagonok){
			if(!v.isEmpty)
				if(!v.getDown(c))  
					break;
		}
		if(getUp){
			for(Vagon v:this.vagonok){
				if(v.isEmpty)
					if(v.getUp(c))
						return true;
			}
		}
		return false;
		
	}

	
	
	
	//Generaltfuggvenyek.
	
	@Override
	public String toString() {
		return "Mozdony [vagonok=" + vagonok + "]";
	}
	public ArrayList<Vagon> getVagonok() {
		return vagonok;
	}

	public void setVagonok(ArrayList<Vagon> vagonok) {
		this.vagonok = vagonok;
	}
	
	public void addVagon(Vagon v){ vagonok.add(v); }

	public Sin getWays() {
		return ways;
	}

	public void setWays(Sin ways) {
		this.ways = ways;
	}

	public Rectangle2D getForma() {
		return forma;
	}

	public void setForma(Rectangle2D forma) {
		this.forma = forma;
	}

	public Sin getUtvonal() {
		return utvonal;
	}

	public void setUtvonal(Sin utvonal) {
		this.utvonal = utvonal;
	}
	
	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
}
