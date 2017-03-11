package application;


import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public class ControlPoint {
	private Shape alak;
	private Point2D hely;
	private ArrayList<Sin> ways = new ArrayList<Sin>();
	
	public ControlPoint(Shape shape, Point2D tmp){
		System.out.println("called: ControlPoint construcor");
		alak = shape;
		hely = tmp;
	}
	
	public void addWay(Sin s){
		ways.add(s);
		System.out.println("sin added");
	}
	
	public Sin giveDirection(Sin a){ 
		System.out.println("called: giveDirection");
		return a;
		}
	public void perform(){
		System.out.println("called: perform");
	}
}
