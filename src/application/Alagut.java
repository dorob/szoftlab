package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Olyan ControlPoint, amelyre alagut szaj epítheto, illetve bonthato le. Ahol alagut van,
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
	public Alagut(Shape shape, Point2D tmp) {
		super(shape, tmp);
		
		System.out.println("		-called: Alagut constructor");
	}
	
	
	/**
	 * Megnezi, hogy felepitettuk-e mar ezt az alagutszajat.
	 * @return visszaadja a hiv tesztem eredmenyet
	 */
	public boolean isBuilt() {
		System.out.println("called: isBuilt");
		return isBuilt;
	}
	public void setBuilt(boolean isBuilt) {
		System.out.println("called: setBuilt");
		this.isBuilt = isBuilt;
	}
	
	
	
	@Override
	/**
	 * Letrehoz vagy lerombol egy alagutat.
	 * @param m referencia a hivo mozdonyra
	 */
	public void perform(Mozdony m){
		System.out.println("called: Alagut perform");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Build or destroy? b/d");
			String line = br.readLine();
			if(line.equals("b"))
				this.build();
			else if(line.equals("d"))
				this.destroy();
			else System.err.println("Tunnel failed"); //rossz command
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Felepiti az alagutat.
	 */
	public void build(){
		System.out.println("called: Alagut build");
	}
	/**
	 * Lerombolja az alagutat.
	 */
	public void destroy(){
		System.out.println("called: Alagut destroy");
	}
}
