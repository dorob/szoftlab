package application;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Valtokat reprezentalja, Sin szakaszok kozotti kapcsolatokat allit, ezzel a jatekos iranyithatja, hogy melyik uton haladjon tovabb a vonat.
 * @author Tsurhe
 */
public class Switcher extends ControlPoint{
	private int aktiv;
	
	/**
	 * Switcher konstruktora
	 * @param shape Az alakja
	 * @param tmp A pozicioja
	 */
	public Switcher(Shape shape, Point2D tmp) {
		super(shape, tmp);
		System.out.println("		-called: Switcher constructor");
	}
	
	/**
	 * A feluldefinialt perform fuggveny, ami valtast indit 
	 */
	@Override
	public void perform(Mozdony m){
		System.out.println("called: switcher perform");
		Switch();
	}
	
	/**
	 * Ez vegzi tenylegesen a valtast, vagyis az aktiv sin indexet valtoztatja
	 */
	public void Switch(){
		System.out.println("called: switcher switch");
	}
	
}
