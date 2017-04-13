package application;



import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.geom.Point2D;
//import javafx.geometry.Point2D;
//import javafx.scene.image.Image;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Shape;
/**
 * Palyaelemek betoltese illetve tarolasa.Itt tarolunk minden interakciora kepesobjektumot:
 * Vonatok ControlPointok hatter
 * @author Tsurhe
 *
 */
public class Palya {
	
	private BufferedImage hatter;   
	private ArrayList<ControlPoint> cp;
	private ArrayList<Mozdony> vehicles;
	public static Point2D openAlagut;    //ha mar felepult 1 alagut szaj azt itt taroljuk igy latszodik, hogy hova kell a sint huzni
	
	/**
	 * palya konstruktor
	 */
	public Palya(){	
		GlobalLogger.log("called: Palya constructor");
		cp = new ArrayList<ControlPoint>();
		vehicles = new ArrayList<Mozdony>();
	}
	
	/**
	 * Az adott ControlPoint kikeresese
	 * @param x x koordinata.
	 * @param y y koordinata.
	 * @return A koordinatak alapjan megadott ControlPoint.
	 */
	public ControlPoint findCP(int x){
		// kesobb kattintas koordinatai alapjan
		GlobalLogger.log("called: palya -findCP");
		return cp.get(x);
		}
	
	/**
	 * Ellenorzi, hogy minden vonatnak kiurult-e a valamennyi vagonja.
	 * @return Minden vagon kiurult-e.
	 */
	public boolean checkCompleted(){
		GlobalLogger.log("called: level -checkCompleted");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//		System.out.println("Veget ert a palya? y/n");
			GlobalLogger.log("Veget ert a palya? y/n");
			String line = br.readLine();
			GlobalLogger.log("----INPUT: " + line);
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
	//	System.out.println("called: Palya -run");
		GlobalLogger.log("called: Palya -run");
		for(Mozdony m: vehicles) m.move(); 	
	}
	
	//Generalt fuggvenyek.
	
	public BufferedImage getHatter() {
		return hatter;
	}
	
	public void setHatter(BufferedImage hatter) {
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
