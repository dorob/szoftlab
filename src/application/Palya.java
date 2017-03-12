package application;



import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
	public ControlPoint findCP(int x, int y){
		System.out.println("called: palya findCP");
		if(y==1)
			return cp.get(3); //hogy alagutat adjon a teszthez
		else return cp.get(2);	//mivel ez a switcher, elesbe majd megkeresi
		}
	
	public boolean checkCompleted(){
		System.out.println("called: level checkCompleted");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Veget ert a palya? y/n");
			String line = br.readLine();
			if(line.equals("y"))
				return true;
			else return false; 
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
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
