package application;


import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Palya {
	private BufferedImage hatter;
	private ArrayList<ControlPoint> cp;
	private ArrayList<Mozdony> vehicels;
	public Point2D openAlagut;
	
	public void init(){}
	public ArrayList<Mozdony> getVehicles(){ return new ArrayList<Mozdony>();}
	public ControlPoint findCP(){return new ControlPoint();}
	public boolean checkCompleted(){return false;}
	public void run(){}
	
}
