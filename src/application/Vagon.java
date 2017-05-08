package application;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Az utasok nyilvantartasat es kezeleset vegzi, valamint koveti a mozdonyt, amihez tartozik.
 * @author Tsurhe
 */
public class Vagon implements Serializable{
	
	private ArrayList<Point2D>koor;
	private Color color;
	private Color formerc;
	public boolean isEmpty = false;
	
	
	/**
	 * Vagon konstruktora
	 */
	public Vagon(Color c){
		GlobalLogger.log("called: vagon constructor");
		color=c;
		if(color == Color.BLACK)
			isEmpty = true;
	}
	public Vagon(){
		GlobalLogger.log("called: vagon default constructor");
		color=Color.RED;
	}
	
	/**
	 * Ezzel a fuggvennyel mozgatjuk a vagonokat
	 
	public void move(){
		GlobalLogger.log("called: vagon- move");
	}
	*/
	
	/**
	 * Ezen keresztul kerdezzuk meg, hogy le akarnak-e szallni az utasok az adott vagonrol
	 * @param c A megallo szine, ami melett a vonat eppen elhalad
	 */
	public boolean getDown(Color c){
		GlobalLogger.log("called: vagon -getDown");
		if(c==color){
			formerc=color;
			color=Color.WHITE; //A KESZ VONATOK FEHEREK LESZNEK 
			isEmpty=true;
			return true;
		}
		return false;
	}
	
	/**
	 * igazzal ter vissza ha ide fol tudtak szallni, hamissal ha nem
	 * @param c A megallo szine
	 */
	public boolean getUp(Color c){
		GlobalLogger.log("called: vagon -getUp");
		if(c==formerc){
			color = c;
			isEmpty=false;
			return true;
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		return "Vagon [color=" + color + ", isEmpty=" + isEmpty + "]";
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
