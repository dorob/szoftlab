package application;


import java.awt.Shape;
import java.awt.Point;
import java.util.ArrayList;

//import javafx.geometry.Point2D;
//import javafx.scene.shape.Shape;

/**
 * Sineket osszekoto specialis pontok. Ilyenek a megallo, a valto, vagy az alagut.
 *  Megmondja az oda erkezo vonatoknak, hogy merre menjenek tovabb.

 * @author Tsurhe
 *
 */
public class ControlPoint {
	protected int id;
	public static int num = 0;
	protected Shape alak;
	protected Point hely;
	protected ArrayList<Sin> ways = new ArrayList<Sin>();
	/**
	 * ControlPoint konstruktor, ami beallitja helyenek koordinatait.
	 * @param object controlpoint kinezete.
	 * @param parseInt elso koordinataja a CP-nek.
	 * @param parseInt2 masodik koordinataja a CP-nek
	 */
	public ControlPoint(Shape object, int parseInt, int parseInt2) {
		GlobalLogger.log("called: ControlPoint constructor");
		hely = new Point(parseInt, parseInt2);
	}
	/**
	 * hozzaad egy sint a mozdony utvonalahoz.
	 * @param s a sin amit hozzad az utvonalhoz.
	 */
	public void addWay(Sin s){
		ways.add(s);
		GlobalLogger.log("sin added");
	}
	/**
	 * Egy sinnek aki kerdezi visszaad egy iranyt ami a kovetkezo sin(utirany) lesz.
	 * @param prev Az a sin ahol jelenleg van a mozdony, ami kerte az iranyadast.
	 * @return Az a sin ami a mozdony kovetkezo sinje.
	 */
	public Sin giveDirection(Sin prev){
		GlobalLogger.log("called: controlpoint giveDirection");
		//Ha ez nem egy valto hanem egy sima controllpoint, akkor 2 sin johet ki belole.
		if (ways.size() == 2) {
			if (ways.get(0).controlpoint1.equals(prev.controlpoint1) && ways.get(0).controlpoint2.equals(prev.controlpoint2))
				return ways.get(1);
			else
				return ways.get(0);
		}
		return null;			//CSAK aTMENETI, ITT INKaBB KIVeTELT KeNE DOBNI!!
	}
	/**
	 * Feluldefinialando fv. minden ControlPoint sajat esemenyet hivja meg. pl megalloban megallas
	 * vagy Switchernel valtas. 
	 * @param m Referencia a mozdonyra ami a megalloban van.
	 */
	public void perform(Mozdony m){
		GlobalLogger.log("called: perform");
	}
	public int getID(){
		return id;
	}
	public ArrayList<Sin> getWays(){
		return ways;
	}
}
