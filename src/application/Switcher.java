package application;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public class Switcher extends ControlPoint{

	public Switcher(Shape shape, Point2D tmp) {
		super(shape, tmp);
		// TODO Auto-generated constructor stub
		System.out.println("		-called: Switcher constructor");
	}
	public int activ;
	
	@Override
	public void perform(Mozdony m){
		System.out.println("called: switcher perform");
		Switch();
	}
	public void Switch(){
		System.out.println("called: switcher switch");
	}
	
}
