package application;

import java.awt.Color;
import java.awt.geom.CubicCurve2D;
/**
 * A palyan levo utak megnevezese, amin a vonatok kozlekednek illetve, amik osszekotik az adott controlpointokat. Minden sin tulajdonkepp egy Bezier gorbe,
 *  melyet vegpontjai es kontrollpontjai hataroznak meg. 
 * @author Tsurhe
 */
public class Sin {
	private int id;
	private CubicCurve2D gorbe;
	public ControlPoint controlpoint1;
	public ControlPoint controlpoint2;
	public Mozdony mozdony;
	
	/**
	 * Sin konstruktora
	 */
	public Sin(){
	//	System.out.println("called: sin constructor");
		GlobalLogger.log("called: sin constructor");
		controlpoint2 = new Megallo(null, null, Color.ORANGE);
	}
	
	/**
	 * Uj utvonalat ad a mozdonyanak (visszakap egy sint es mivel tarolja a rajta levo vonatokat, 
	 * igy azok fifo szeru mukodese miatt a reglereggben rajta levonek adja az uj utat mert
	 * nyilvanvaloan annak kell, kulonbozo esetben mar utkozes tortent volna
	 */
	public void giveNext(){
//		System.out.println("called: sin -giveNext");
		GlobalLogger.log("called: sin -giveNext");
		controlpoint1 = new ControlPoint(null, null);
		controlpoint1.giveDirection(this);
	}
}
	
	
