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
	
	public void move(){}
	public boolean CollisionDetection(){ return false;}
	public void doneMoving(){}
	public void addWay(Sin s){}
	public void stationArrive(Color c){}
	

}
