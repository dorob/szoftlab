package application;

import java.awt.Color;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Olyan ControlPoint ahol az utasoknak lehetoseguk van leszallni. A megallok szinnel
 * azonositottak, amegalloban nem all meg a vonat.
 * @author Tsurhe
 *
 */
public class Megallo extends ControlPoint{
	private Color color;
	
	/**
	 * Megallo konstruktor.
	 * @param al A megallo shape-je.
	 * @param pos A megallo helyzete a palyan.
	 * @param col A megallo szine.
	 */
	public Megallo(Shape al, Point2D pos, Color col) {
		super(al, pos);
		System.out.println("	-called: megallo constructor");
		color=col;
	}
	
	public Color getColor(){ 
		System.out.println("called: getColor");
		return Color.GREEN;
		}
	public void setColor(){}
	/**
	 * Uj iranyt ad a megallon athalado vonatnak.
	 * @param a Az iranytkero sin.
	 */
	@Override
	public Sin giveDirection(Sin a){
		System.out.println("called: giveDirection");
		return a;
		}
	/**
	 * Leszallitja az utasokat ha egyezik a szin.
	 * @param m A mozdony ami a megallon athalad.
	 */
	@Override
	public void perform(Mozdony m){
		System.out.println("called: megallo perform-leszallas");
		m.stationArrive(color);
	}
	
}
