package application;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import javafx.geometry.Point2D;
//import javafx.scene.shape.Shape;

/**
 * Olyan ControlPoint, amelyre alagut szaj epitheto, illetve bonthato le. Ahol alagut van,
 * ott a vonat minden esetben az alagutban halad tovabb, nem masik uton.
 * @author Tsurhe
 *
 */
public class Alagut extends Switcher{
	private boolean isBuilt;
	
	/**
	 * Alagut construktor megkapja a poziciojat es az alakjat amit kesobb nyomni lehet
	 * @param shape Az alakja
	 * @param tmp A pozicioja
	 */
	public Alagut(Shape shape, int x, int y, int id) {
		super(shape, x, y, id);
		GlobalLogger.log("		-called: Alagut constructor");
	}
	
	
	/**
	 * Megnezi, hogy felepitettuk-e mar ezt az alagutszajat.
	 * @return visszaadja a hiv tesztem eredmenyet
	 */
	public boolean isBuilt() {
		GlobalLogger.log("		-called: Alagut constructor");
		return isBuilt;
	}
	
	public void setBuilt(boolean isBuilt) {
		GlobalLogger.log("called: setBuilt");
		this.isBuilt = isBuilt;
	}
	
	
	
	@Override
	/**
	 * Letrehoz vagy lerombol egy alagutat.
	 * @param m referencia a hivo mozdonyra
	 */
	public void perform(Mozdony m){
		GlobalLogger.log("called: Alagut perform");
	/*	try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			GlobalLogger.log("Build or destroy? b/d");
			String line = br.readLine();
			GlobalLogger.log("----INPUT: " + line);
			if(line.equals("b"))
				this.build();
			else if(line.equals("d"))
				this.destroy();
			else {
				GlobalLogger.log("Tunnel failed");
			}
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		
	}
	
	/**
	 * Felepiti az alagutat.
	 */
	public void build(){
		GlobalLogger.log("called: Alagut build");
		isBuilt=true;
	}
	
	/**
	 * Lerombolja az alagutat.
	 */
	public void destroy(){
		GlobalLogger.log("called: Alagut destroy");
		isBuilt=false;
	}
}
