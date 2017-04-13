package application;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Az utasok nyilvantartasat es kezeleset vegzi, valamint koveti a mozdonyt, amihez tartozik.
 * @author Tsurhe
 */
public class Vagon {
	
	private ArrayList<Point2D>koor;
	private Color color;
	private boolean isEmpty;
	
	
	/**
	 * Vagon konstruktora
	 */
	public Vagon(Color c){
	//	System.out.println("called: vagon constructor");
		GlobalLogger.log("called: vagon constructor");
		color=c;
	}
	
	/**
	 * Ezzel a fuggvennyel mozgatjuk a vagonokat
	 */
	public void move(){
//		System.out.println("called: vagon -move");
		GlobalLogger.log("called: vagon- move");
	}
	
	/**
	 * Ezen keresztul kerdezzuk meg, hogy le akarnak-e szallni az utasok az adott vagonrol
	 * @param c A megallo szine, ami melett a vonat eppen elhalad
	 */
	public void getDown(Color c){
	//	System.out.println("called: vagon -getDown");
		GlobalLogger.log("called: vagon -getDown");
	}
	
	/**
	 * igazzal ter vissza ha ide fol tudtak szallni, hamissal ha nem
	 * @param c A megallo szine
	 */
	public boolean getUp(Color c){
		GlobalLogger.log("called: vagon -getUp");
		return true;
	}
	
	
	//generalt fuggvenyek
	public ArrayList<Point2D> getKoor() {
		return koor;
	}
	public void setKoor(ArrayList<Point2D> koor) {
		this.koor = koor;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
}
