package application;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
/**
 * A palyan mozgo vonatok "vezetoje". Minden vonat 1 mozdonybol es tetszoleges szamu vagonbol all.
 * @author Tsurhe
 *
 */
public class Mozdony implements Serializable{
	
	private ArrayList<Vagon> vagonok = new ArrayList<Vagon>();
	private Sin ways; //elozo ut amin voltam
	private Rectangle2D forma;
	private Sin utvonal; //amin most vagyok
	private boolean isDone;
	
	private int id;
	/**
	 * A mozdonyok egyedi azonositasahoz haszalt szamlalo
	 */
	private static int numme=0;
	
	/**
	 * A mozdony adott sinen valo koordinatai
	 */
	public  ArrayList<Point2D> points= new ArrayList<Point2D>();
	public int index=0;
	public double angle;
	public Point2D pos;
	public ArrayList<Point2D> pointsack = new ArrayList<Point2D>();
	public ArrayList<Double> anglesack = new ArrayList<Double>();
	
	/**
	 * Mozdony konstruktor
	 */
	public Mozdony(){
		GlobalLogger.log("called: mozdony default constructor");
		id = numme;
		numme++;
	}
	/**
	 * A vonat mozgatasa 2 ControlPoint kozott egy sinen.
	 * @throws CollideException 
	 */
	public void move() throws CollideException{
		GlobalLogger.log("called: mozdony -move");
		try{ 
			//tartsunk csak annyi pontot ahany vagon van
			if(pointsack.size() >= vagonok.size())
				pointsack.remove(0);
			//bedobjuk a vagonoknak
			pointsack.add(points.get(index));
			
			if(index < points.size()-1){
				angle = angleTo(pos, points.get(index+1));
				if(anglesack.size() >= vagonok.size())
					anglesack.remove(0);
				anglesack.add(angle);
			}
			
			index++;
			if(index >= points.size()){
				doneMoving();
			}
			pos = points.get(index);
		}catch(CollideException e){
		throw e;
		}
	}
	
	
	
	protected double angleTo(Point2D from, Point2D to) {
        double angle = Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
        return angle;
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
	 * @param reverse 
	 * @throws CollideException Ha vannak mar a sinen akkor utkozunk ralepeskor
	 */
	public void addWay(Sin s, boolean reverse) throws CollideException{
		ways = utvonal;
		ways.mozdony = null;
		if(s.mozdony != null)
			throw new CollideException("A vonatok utkoztek");
		utvonal = s;
		utvonal.mozdony = this;
		this.calcPos(reverse);
			
	}

	/**
	 * kiszamolja a konkret koordinatakat
	 * @param reverse 
	 */
	public void calcPos(boolean reverse){
		if(utvonal!=null){
			points.clear();
			FlatteningPathIterator iter=new FlatteningPathIterator(utvonal.gorbe.getPathIterator(new AffineTransform()), 0.15); // a szam csokkentesevel imabba a mozgas (kisebb reszekre bontja)
			float[] coords=new float[6];
            while (!iter.isDone()) {
                iter.currentSegment(coords);
                double x=coords[0];
                double y=coords[1];
                points.add(new Point2D.Double(x, y));
                iter.next();
            }
		}
		if(reverse)
			Collections.reverse(points);
		pos = points.get(0);
		index = 0;
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
		return "Mozdony [ID=" + this.id + ", Jelenlegi SIN: "+ utvonal.getId() +", vagonok=" + vagonok + "]";
	}
	public ArrayList<Vagon> getVagonok() {
		return vagonok;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		this.calcPos(false);
		System.out.println("-------------------------------------------");
	}
	
	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
}
