package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Alagut osztaly leirasat ide kene copizni elso doksibol
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
		// TODO Auto-generated constructor stub
		System.out.println("		-called: Alagut constructor");
	}
	
	
	
	public boolean isBuilt() {
		System.out.println("called: isBuilt");
		return isBuilt;
	}
	public void setBuilt(boolean isBuilt) {
		System.out.println("called: setBuilt");
		this.isBuilt = isBuilt;
	}
	
	@Override
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
	
	public void build(){
		System.out.println("called: Alagut build");
	}
	public void destroy(){
		System.out.println("called: Alagut destroy");
	}
}
