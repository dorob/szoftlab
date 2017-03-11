package application;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Mozdony {
	
	private ArrayList<Vagon> vagonok;
	private ArrayList<Sin> ways;
	private Rectangle2D forma;
	private ArrayList<Sin> utvonal;
	private boolean isDone;
	
	public Mozdony(){
		System.out.println("called: mozdony constructor");
		vagonok = new ArrayList<Vagon>();
		ways = new ArrayList<Sin>();
		vagonok.add(new Vagon());
		ways.add(new Sin());
	}
	
	public void move(){
		System.out.println("called: mozdony move");
		vagonok.get(0).move(); //javafx-be mar nm igy lesz
	}
	public boolean CollisionDetection(){ return false;}
	public void doneMoving(){}
	public void addWay(Sin s){}
	public void stationArrive(Color c){}
	
	
}
