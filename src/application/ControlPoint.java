package application;


import java.awt.Shape;
import java.awt.Point;
import java.awt.Rectangle;
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
	public ControlPoint(int parseInt, int parseInt2) {
		GlobalLogger.log("called: ControlPoint constructor");
		alak = new Rectangle(parseInt-5, parseInt2-5, 10, 10);
		hely = new Point(parseInt, parseInt2);
		id = num;
		num++;
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
	 * @param asker 
	 * @return Az a sin ami a mozdony kovetkezo sinje.
	 * @throws CollideException Utkozes eseten dobja
	 */
	public Sin giveDirection(Sin prev, Mozdony asker) throws CollideException{
		GlobalLogger.log("called: controlpoint giveDirection");
		//Ha ez nem egy valto vagy alagut vagy elagazas hanem egy sima controllpoint, akkor 2 sin johet ki belole.
		if (ways.size() == 2) {
			if (ways.get(0).controlpoint1.equals(prev.controlpoint1) && ways.get(0).controlpoint2.equals(prev.controlpoint2))
				return ways.get(1);
			else
				return ways.get(0);
		}
		//ha ez egy elagazas. felvetelkor 0-1 2-3 iranyokba fognak menni az elagazasban in-out koncepcio szerint
		else if(ways.size() == 4){
			//0asrol jon
			if (ways.get(0).getId() == prev.getId())
				return ways.get(1);
			//1esrol jon
			else if(ways.get(1).getId() == prev.getId())
				return ways.get(0);
			//2esrol
			else if(ways.get(2).getId() == prev.getId())
				return ways.get(3);
			//3asrol
			else if(ways.get(3).getId() == prev.getId())
				return ways.get(2);
		}
		throw new CollideException("givDir hiba");			
	}
	
	/**
	 * Feluldefinialando fv. minden ControlPoint sajat esemenyet hivja meg. pl megalloban megallas
	 * vagy Switchernel valtas. 
	 * @param m Referencia a mozdonyra ami a megalloban van.
	 * @throws CollideException 
	 */
	public void perform(Mozdony m) throws CollideException{
		GlobalLogger.log("called: perform");
		
	}
	
	@Override
	public String toString() {
		return "ControlPoint [id=" + id + ", ways=" + ways + "]";
	}
	public int getID(){
		return id;
	}
	public ArrayList<Sin> getWays(){
		return ways;
	}

}
