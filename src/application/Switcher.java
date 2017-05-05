package application;

//import javafx.geometry.Point2D;
//import javafx.scene.shape.Shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.Point;
/**
 * Valtokat reprezentalja, Sin szakaszok kozotti kapcsolatokat allit, ezzel a jatekos iranyithatja, hogy melyik uton haladjon tovabb a vonat.
 * @author Tsurhe
 */
public class Switcher extends ControlPoint{
	protected int aktiv;
	public int[] rgbb = new int[3];
	/**
	 * Switcher konstruktora
	 * @param shape Az alakja
	 * @param parseInt Az x koordinataja
	 * @param parseInt2 Az y koordinataja
	 * @param aktiv_ Az aktiv sin szamat tartalmazza
	 */
	public Switcher(int parseInt, int parseInt2, int aktiv_, int r, int g, int b) {
		super(parseInt, parseInt2);
		super.alak = new Ellipse2D.Double(parseInt-8, parseInt2-8, 16, 16);
		aktiv=aktiv_;
		rgbb[0] = r;
		rgbb[1] = g;
		rgbb[2] = b;
		GlobalLogger.log("		-called: Switcher constructor");
	}
	
	/**
	 * A feluldefinialt perform fuggveny, ami valtast indit
	 *  @param m A mozdony, mely oda ert hozza (lehet null is)
	 */
	@Override
	public void perform(Mozdony m) throws CollideException {
//		System.out.println("called: switcher -perform");
		GlobalLogger.log("called: switcher -perform");
		Switch();
	}

	/**
	 * Egy sinnek aki kerdezi visszaad egy iranyt ami a kovetkezo sin(utirany) lesz.
	 * @param prev Az a sin ahol jelenleg van a mozdony, ami kerte az iranyadast.
	 * @return Az a sin ami a mozdony kovetkezo sinje.
	 * @throws CollideException Kivetel amikor olyan helyrol jon vonat, amerrre nincs valto ezert utkozik
	 */
	@Override
	public Sin giveDirection(Sin prev, Mozdony asker) throws CollideException {
		GlobalLogger.log("called: controlpoint giveDirection");
		if(prev.equals(ways.get(0)))
			return ways.get(aktiv);
		else if(prev.equals(ways.get(aktiv)))
			return ways.get(0);
		//ha egyik iranybol se jon akkor utkoznie kell mert nincs arra nyitva valto
		else throw new CollideException("utkozes");
	}
	
	/**
	 * Ez vegzi tenylegesen a valtast, vagyis az aktiv sin indexet valtoztatja
	 */
	public void Switch(){
		GlobalLogger.log("called: switcher -switch");
		if (ways.size() == aktiv + 1)
			aktiv = 1;
		else
			aktiv ++;
	}

	@Override
	public String toString() {
		return "Switcher";
	}
	
}
