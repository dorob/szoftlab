package application;


import java.awt.Shape;
import java.awt.geom.Point2D;
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
	protected Shape alak;
	protected Point2D hely;
	protected ArrayList<Sin> ways = new ArrayList<Sin>();
	/**
	 * ControlPoint konstruktor.
	 * @param shape controlpoint kinezete.
	 * @param tmp 	palyan levo helye.
	 */
	public ControlPoint(Shape object, int parseInt, int parseInt2) {
		// TODO Auto-generated constructor stub
		//atirtam h 2 intet kap, abbol kell majd a point2d-t csinalni grafikus felulethez
		GlobalLogger.log("called: ControlPoint constructor");
	}
	/**
	 * hozzaad egysint a mozdon utvonalahoz.
	 * @param s a sin amit hozzad az utvonalhoz.
	 */
	public void addWay(Sin s){
		ways.add(s);
	//	System.out.println("sin added");
		GlobalLogger.log("sin added");
	}
	/**
	 * Egy sinnek aki kerdezi visszaad egy iranyt ami a kovetkezo sin(utirany) lesz.
	 * @param a Az asin ami kerte az iranyadast.
	 * @return Az a sin amit hozza kell adni az utvonalhoz.
	 */
	public Sin giveDirection(Sin a){ 
	//	System.out.println("called: controlpoint giveDirection");
		GlobalLogger.log("called: controlpoint giveDirection");
		return a;
		}
	/**
	 * Feluldefinialando fv. minden ControlPoint sajat esemenyet hivja meg. pl megalloban megallas
	 * vagy Switchernel valtas. 
	 * @param m Referencia a mozdonyra ami a megalloban van.
	 */
	public void perform(Mozdony m){
	//	System.out.println("called: perform");
		GlobalLogger.log("called: perform");
	}
}
