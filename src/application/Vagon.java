package application;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Vagon {
	private ArrayList<Point2D>koor;
	private Color color;
	private boolean isEmpty;
	
	
	/**
	 * Vagon contructor
	 */
	public Vagon(){
		System.out.println("called: vagon constructor");
		color=color.RED;  //most csak szemleltetes celjabol.
	}
	public void move(){
		System.out.println("called: vagon move");
	}
	public void getDown(Color c){
		System.out.println("called: vagon getDown");
	}
	
	
	
	
	
	
	public ArrayList<Point2D> getKoor() {
		return koor;
	}
	public void setKoor(ArrayList<Point2D> koor) {
		this.koor = koor;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	

}
