package application;



import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Palya {
	
	private Image hatter;   //nem buffered kell, a bufferedet e segitsegeve alitod elo de itt nmkell plusz az swinges
	private ArrayList<ControlPoint> cp;
	private ArrayList<Mozdony> vehicles;
	public Point2D openAlagut;
	
	
	public Palya(){
		System.out.println("called: Palya constructor");
		cp = new ArrayList<ControlPoint>();
		vehicles = new ArrayList<Mozdony>();
	}
	public void init(){
		//itt lesz am fajlbol beolvassa egesz palyat ahelyett mostmeg csak bemutatja construktorokat
		
		cp.add(new ControlPoint(null, null));
		cp.add(new Megallo(null, null, null));
		cp.add(new Switcher(null, null));
		cp.add(new Alagut(null, null));
		
		vehicles.add(new Mozdony());
		
	}
	
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
