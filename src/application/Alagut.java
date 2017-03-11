package application;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Alagut osztaly leirasat ide kene copizni elso doksibol
 * @author Tsurhe
 *
 */
public class Alagut extends Switcher{
	private boolean isBuilt;
	
	/**
	 * Alagut construktor megkapja a poziciojat es az alakjat amit kesobb nyomni lehet
	 * @param shape Az alakja
	 * @param tmp A pozicioja
	 */
	public Alagut(Shape shape, Point2D tmp) {
		super(shape, tmp);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public boolean isBuilt() {
		System.out.println("called: isBuilt");
		return isBuilt;
	}
	public void setBuilt(boolean isBuilt) {
		System.out.println("called: setBuilt");
		this.isBuilt = isBuilt;
	}
	
	
	public void build(){
		System.out.println("called: build");
	}
	public void destroy(){
		System.out.println("called: destroy");
	}
}
