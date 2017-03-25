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
	private Shape alak;
	private Point2D hely;
	private ArrayList<Sin> ways = new ArrayList<Sin>();
	/**
	 * ControlPoint konstruktor.
	 * @param shape controlpoint kinezete.
	 * @param tmp 	palyan levo helye.
	 */
	public ControlPoint(Shape shape, Point2D tmp){
	//	System.out.println("called: ControlPoint constructor");
		GlobalLogger.log("called: ControlPoint constructor");
		alak = shape;
		hely = tmp;
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
