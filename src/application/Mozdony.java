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
		
		utvonal = new ArrayList<Sin>();
		utvonal.add(new Sin());
	}
	
	public void move(){
		System.out.println("called: mozdony move");
		vagonok.get(0).move(); //javafx-be mar nm igy lesz
		if(ways.isEmpty()) 
			doneMoving();
	}
	public boolean CollisionDetection(){ return false;}
	public void doneMoving(){
		System.out.println("called: mozdony doneMoving");
		utvonal.get(0).giveNext();
	}
	public void addWay(Sin s){}
	public void stationArrive(Color c){
		System.out.println("called: mozdony stationArrive");
		vagonok.get(0).getDown(c);
	}

	
	
	public ArrayList<Vagon> getVagonok() {
		return vagonok;
	}

	public void setVagonok(ArrayList<Vagon> vagonok) {
		this.vagonok = vagonok;
	}

	public ArrayList<Sin> getWays() {
		return ways;
	}

	public void setWays(ArrayList<Sin> ways) {
		this.ways = ways;
	}

	public Rectangle2D getForma() {
		return forma;
	}

	public void setForma(Rectangle2D forma) {
		this.forma = forma;
	}

	public ArrayList<Sin> getUtvonal() {
		return utvonal;
	}

	public void setUtvonal(ArrayList<Sin> utvonal) {
		this.utvonal = utvonal;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
}
