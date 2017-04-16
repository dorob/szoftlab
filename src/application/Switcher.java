package application;

//import javafx.geometry.Point2D;
//import javafx.scene.shape.Shape;

import java.awt.Shape;
import java.awt.Point;
/**
 * Valtokat reprezentalja, Sin szakaszok kozotti kapcsolatokat allit, ezzel a jatekos iranyithatja, hogy melyik uton haladjon tovabb a vonat.
 * @author Tsurhe
 */
public class Switcher extends ControlPoint{
	private int aktiv;
	
	/**
	 * Switcher konstruktora
	 * @param shape Az alakja
	 * @param parseInt Az x koordinataja
	 * @param parseInt2 Az y koordinataja
	 * @param aktiv_ Az aktiv sin szamat tartalmazza
	 */
	public Switcher(Shape shape, int parseInt, int parseInt2, int aktiv_) {
		super(shape, parseInt, parseInt2);
		aktiv=aktiv_;
		GlobalLogger.log("		-called: Switcher constructor");
	}
	
	/**
	 * A feluldefinialt perform fuggveny, ami valtast indit
	 *  @param m A mozdony, mely oda ert hozza (lehet null is)
	 */
	@Override
	public void perform(Mozdony m){
//		System.out.println("called: switcher -perform");
		GlobalLogger.log("called: switcher -perform");
		Switch();
	}

	/**
	 * Egy sinnek aki kerdezi visszaad egy iranyt ami a kovetkezo sin(utirany) lesz.
	 * @param prev Az a sin ahol jelenleg van a mozdony, ami kerte az iranyadast.
	 * @return Az a sin ami a mozdony kovetkezo sinje.
	 */
	@Override
	public Sin giveDirection(Sin prev){
		GlobalLogger.log("called: controlpoint giveDirection");
		return ways.get(aktiv);
	}
	
	/**
	 * Ez vegzi tenylegesen a valtast, vagyis az aktiv sin indexet valtoztatja
	 */
	public void Switch(){
//		System.out.println("called: switcher -switch");
		GlobalLogger.log("called: switcher -switch");
		if (ways.size() == aktiv + 1)
			aktiv = 0;
		else
			aktiv ++;
	}
	
}
