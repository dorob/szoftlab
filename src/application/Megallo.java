package application;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
//import javafx.geometry.Point2D;
//import javafx.scene.shape.Shape;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Olyan ControlPoint ahol az utasoknak lehetoseguk van leszallni. A megallok szinnel
 * azonositottak, amegalloban nem all meg a vonat.
 * @author Tsurhe
 *
 */
public class Megallo extends ControlPoint{
	private Color color;
	private boolean getUp=false;
	/**
	 * Megallo konstruktor.
	 * @param al A megallo shape-je.
	 * @param pos A megallo helyzete a palyan.
	 * @param col A megallo szine.
	 */
	public Megallo(Shape al, Point2D pos, Color col) {
		super(al, pos);
	//	System.out.println("	-called: megallo constructor");
		GlobalLogger.log("	-called: megallo constructor");
		color=col;
	}
	
	public Color getColor(){ 
	//	System.out.println("called: Megallo -getColor");
		GlobalLogger.log("called: Megallo -getColor");
		return Color.GREEN;
		}
	public void setColor(){}
	
	/**
	 * Uj iranyt ad a megallon athalado vonatnak.
	 * @param a Az iranytkero sin.
	 */
	@Override
	public Sin giveDirection(Sin a){
//		System.out.println("called: megallo -giveDirection");
		GlobalLogger.log("called: megallo -giveDirection");
		return a;
		}
	
	/**
	 * Leszallitja az utasokat ha egyezik a szin. Ha folszallo van, akkor megprobal folszallni, 
	 * illetve ha az sikeres is volt, akkor innen tobbet nem lehet folszallni
	 * @param m A mozdony ami a megallon athalad.
	 */
	@Override
	public void perform(Mozdony m){
//		System.out.println("called: megallo perform -leszallas");
		GlobalLogger.log("called: megallo -perform -leszallas");
		try{
		BufferedReader tmp = new BufferedReader(new InputStreamReader(System.in));
		GlobalLogger.log("Could the passengers get up (they found empty places)? y/n");
		if(tmp.readLine().equals("y")){
				m.stationArrive(color, true);
				//getup =true
		}
		else 
			m.stationArrive(color, false);
				
		
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isGetUp() {
		return getUp;
	}

	public void setGetUp(boolean getUp) {
		this.getUp = getUp;
	}
	
}
