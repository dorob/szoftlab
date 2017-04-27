package application;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
//import javafx.geometry.Point2D;
//import javafx.scene.shape.Shape;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.corba.se.spi.ior.iiop.GIOPVersion;

/**
 * Olyan ControlPoint ahol az utasoknak lehetoseguk van leszallni. A megallok szinnel
 * azonositottak, amegalloban nem all meg a vonat.
 * @author Tsurhe
 *
 */
public class Megallo extends ControlPoint{
	private Color color;
	private boolean getUp;
	/**
	 * Megallo konstruktor.
	 * @param al A megallo shape-je.
	 * @param parseInt A megallo helyzete a palyan.
	 * @param parseInt A megallo helyzete a palyan.
	 * @param col A megallo szine.
	 * @param getUp_ A megalloban vannak-e felszallo utasok.
	 */	
	public Megallo(int parseInt, int parseInt2, Color szin, boolean getUp_) {
		super(parseInt, parseInt2);
		color=szin;
		getUp=getUp_;
		GlobalLogger.log("	-called: megallo constructor");
	}

	public Color getColor(){ 
		GlobalLogger.log("called: Megallo -getColor");
		return color;
		}
	public void setColor(Color color){
		this.color = color;
	}
	
	@Override
	public Sin giveDirection(Sin prev, Mozdony asker) throws CollideException{
		this.perform(asker);
		return super.giveDirection(prev, asker);
	}
	/**
	 * Leszallitja az utasokat ha egyezik a szin. Ha folszallo van, akkor megprobal folszallni, 
	 * illetve ha az sikeres is volt, akkor innen tobbet nem lehet folszallni
	 * @param m A mozdony ami a megallon athalad.
	 */
	@Override
	public void perform(Mozdony m){
		boolean passengers;
		GlobalLogger.log("called: megallo -perform -leszallas");
		passengers = m.stationArrive(color, getUp);
		if(passengers == true)
			getUp = false;
	}

	@Override
	public String toString() {
		return "Megallo";
	}

	public boolean isGetUp() {
		return getUp;
	}

	public void setGetUp(boolean getUp) {
		this.getUp = getUp;
	}
	
}
