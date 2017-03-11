package application;

import java.awt.Color;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;


public class Megallo extends ControlPoint{
	private Color color;
	
	public Megallo(Shape al, Point2D pos, Color col) {
		super(al, pos);
		System.out.println("called: megallo constructor");
		color=col;
	}
	
	public Color getColor(){ 
		System.out.println("called: getColor");
		return Color.GREEN;
		}
	public void setColor(){}
	@Override
	public Sin giveDirection(Sin a){
		System.out.println("called: giveDirection");
		return a;
		}
	@Override
	public void perform(){
		System.out.println("called: megallo perform");
	}
	
}
