package application;



import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
/**
 * Palyaelemek betoltese illetve tarolasa.Itt tarolunk minden interakciora kepesobjektumot:
 * Vonatok ControlPointok hatter
 * @author Tsurhe
 *
 */
public class Palya {
	
	private Image hatter;   //nem buffered kell, a bufferedet e segitsegeve alitod elo de itt nmkell plusz az swinges
	private ArrayList<ControlPoint> cp;
	private ArrayList<Mozdony> vehicles;
	public Point2D openAlagut;
	
	/**
	 * palya konstruktor
	 */
	public Palya(){
		System.out.println("called: Palya constructor");
		GlobalLogger.log("called: Palya constructor");
		cp = new ArrayList<ControlPoint>();
		vehicles = new ArrayList<Mozdony>();
	}
	/**
	 * palya inicializalasa.
	 */
	public void init(){
		//itt lesz am fajlbol beolvassa egesz palyat ahelyett mostmeg csak bemutatja construktorokat
		System.out.println("called: palya init");
		GlobalLogger.log("called: palya init");
		cp.add(new ControlPoint(null, null));
		cp.add(new Megallo(null, null, null));
		cp.add(new Switcher(null, null));
		cp.add(new Alagut(null, null));
		
		vehicles.add(new Mozdony());
		
	}
	
	/**
	 * Az adott ControlPoint kikeresese
	 * @param x x koordinata.
	 * @param y y koordinata.
	 * @return A koordinatak alapjan megadott ControlPoint.
	 */
	public ControlPoint findCP(int x, int y){
		System.out.println("called: palya findCP");
		GlobalLogger.log("called: palya findCP");
		if(y==1)
			return cp.get(3); //hogy alagutat adjon a teszthez
		else return cp.get(2);	//mivel ez a switcher, elesbe majd megkeresi
		}
	
	/**
	 * Ellenorzi, hogy minden vonatnak kiurult-e a valamennyi vagonja.
	 * @return Minden vagon kiurult-e.
	 */
	public boolean checkCompleted(){
		System.out.println("called: level checkCompleted");
		GlobalLogger.log("called: level checkCompleted");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Veget ert a palya? y/n");
			GlobalLogger.log("Veget ert a palya? y/n");
			String line = br.readLine();
			if(line.equals("y"))
				return true;
			else return false; 
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		}
	/**
	 * A palya futtatasaertfelelos fuggveny.
	 */
	public void run(){
		System.out.println("called: Palya run");
		GlobalLogger.log("called: Palya run");
		vehicles.get(0).move(); 	//ez egy for-each lesz vegigmenve vonatokon
	}
	
	//Generalt fuggvenyek.
	
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
