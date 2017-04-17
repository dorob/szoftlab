package application;



import java.awt.image.BufferedImage;
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
	//alagut be és kijáratot tároljuk
	public static ControlPoint openAlagut1;
	public static ControlPoint openAlagut2;
	public static Sin newSin;
	
	/**
	 * palya konstruktor
	 */
	public Palya(){
		GlobalLogger.log("called: Palya constructor");
		cp = new ArrayList<ControlPoint>();
		vehicles = new ArrayList<Mozdony>();
	}
	

	/*public ControlPoint findCP(int x){
		// kesobb kattintas koordinatai alapjan
		GlobalLogger.log("called: palya -findCP");
		return cp.get(x);
		}*/

	/**
	 * A koordinatakkal megadott ControlPoint kikeresese
	 * @param x x koordinata.
	 * @param y y koordinata.
	 * @return A koordinatak alapjan megadott ControlPoint, vagy ha nem létezik akkor null.
	 */
	public ControlPoint findCP(int x, int y) {
		GlobalLogger.log("called: palya -findCP");
		for (ControlPoint iter: cp) {
			if (iter.hely.getX() == x && iter.hely.getY() == y)
				return iter;
		}
		return null;
	}
	
	/**
	 * Ellenorzi, hogy minden vonatnak kiurult-e a valamennyi vagonja.
	 * @return Minden vagon kiurult-e.
	 */
	public boolean checkCompleted(){
		GlobalLogger.log("called: level -checkCompleted");
			for(Mozdony m: vehicles ){
				for(Vagon v : m.getVagonok()) {
					if(!v.isEmpty()) return false;
				}
				
			}
		return true;
		}
	/**
	 * A palya futtatasaertfelelos fuggveny.
	 */
	public void run(){
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
	
	
	public void setVehicles(ArrayList<Mozdony> vehicles) {
		this.vehicles = vehicles;
	}
	
	public ArrayList<Mozdony> getVehicles(){
		return vehicles;
	}
	
	
	
}
