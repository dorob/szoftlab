package application;



import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Palya {
	
	private BufferedImage hatter;
	private ArrayList<ControlPoint> cp;
	private ArrayList<Mozdony> vehicels;
	public Point2D openAlagut;
	
	public void init(){}
	
	public ArrayList<Mozdony> getVehicles(){ return new ArrayList<Mozdony>();}
	public ControlPoint findCP(){
		System.out.println("called: findCP");
		Point2D tmp = new Point2D(0, 0);
		Shape s = new Rectangle(0, 0);
		return new ControlPoint(s, tmp);
		}
	public boolean checkCompleted(){return false;}
	public void run(){}
	
}
