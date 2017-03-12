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
		System.out.println("called: palya init");
		cp.add(new ControlPoint(null, null));
		cp.add(new Megallo(null, null, null));
		cp.add(new Switcher(null, null));
		cp.add(new Alagut(null, null));
		
		vehicles.add(new Mozdony());
		
	}
	
	public ControlPoint findCP(){
		System.out.println("called: findCP");
		Point2D tmp = new Point2D(0, 0);
		Shape s = new Rectangle(0, 0);
		return new ControlPoint(s, tmp);
		}
	public boolean checkCompleted(){
		System.out.println("called: level checkCompleted");
		
		return false;
		}
	public void run(){
		System.out.println("called: Palya run");
		vehicles.get(0).move(); 	//ez egy for-each lesz vegigmenve vonatokon
	}
	public Image getHatter() {
		return hatter;
	}
	public void setHatter(Image hatter) {
		this.hatter = hatter;
	}
	public ArrayList<ControlPoint> getCp() {
		return cp;
	}
	public void setCp(ArrayList<ControlPoint> cp) {
		this.cp = cp;
	}
	public Point2D getOpenAlagut() {
		return openAlagut;
	}
	public void setOpenAlagut(Point2D openAlagut) {
		this.openAlagut = openAlagut;
	}
	public void setVehicles(ArrayList<Mozdony> vehicles) {
		this.vehicles = vehicles;
	}
	public ArrayList<Mozdony> getVehicles(){
		return vehicles;
	}
	
	
	
}
