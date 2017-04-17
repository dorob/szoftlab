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
	//ez nem kell
	//private boolean isBuilt=false;
	
	/**
	 * Alagut construktor megkapja a poziciojat es az alakjat amit kesobb nyomni lehet
	 * @param shape Az alakja
	 * @param tmp A pozicioja
	 */
	public Alagut(Shape shape, int parseInt, int parseInt2, int aktiv_) {
		super(shape, parseInt, parseInt2, aktiv_);
		GlobalLogger.log("		-called: Alagut constructor");
	}
	
	
	/**
	 * Megnezi, hogy felepitettuk-e mar ezt az alagutszajat.
	 * @return visszaadja a hiv tesztem eredmenyet
	 */
	/*public void isBuilt() {
		GlobalLogger.log("		-called: Alagut constructor");
		//return isBuilt;
	}*/
	/*
	public void setBuilt(boolean isBuilt) {
		GlobalLogger.log("called: setBuilt");
		this.isBuilt = isBuilt;
	}*/
	
	
	
	@Override
	/**
	 * Letrehoz vagy lerombol egy alagutat.
	 * @param m referencia a hivo mozdonyra
	 */
	public void perform(Mozdony m) throws CollideException {
		GlobalLogger.log("called: Alagut perform");
		if(Palya.newSin == null){
			if(Palya.openAlagut1==null){
				Palya.openAlagut1=this;	
			}
			else{	//ha ugyanarra kattintok masodszor, akkor torlom az alagutat
				if(Palya.openAlagut1.getID()==this.id)
						Palya.openAlagut1=null;
				else{
					//ha letrejott a ket alagut, akkor meghivom a buildet
					Palya.openAlagut2=this;
					build();
				}	
			}	
		}
		else{	
			destroy(); 
		}
	}
	
	/**
	 * Felepiti az alagutat.
	 */
	public void build(){
	GlobalLogger.log("called: Alagut build");
		Palya.newSin=new Sin(Palya.openAlagut1, Palya.openAlagut2);
		Palya.openAlagut1.getWays().add(Palya.newSin);
		Palya.openAlagut2.getWays().add(Palya.newSin);
		((Alagut) Palya.openAlagut1).Switch();
		((Alagut) Palya.openAlagut2).Switch();
	}
	
	public void Switch(){
		aktiv=ways.size()-1;
	}
	
	/**
	 * Lerombolja az alagutat.
	 */
	public void destroy() throws CollideException{
		GlobalLogger.log("called: Alagut destroy");
		if(Palya.newSin.mozdony != null){
			throw new CollideException("utkozes");//end game
		}
		else{
		Palya.openAlagut1.getWays().remove(Palya.openAlagut1.getWays().size()-1);
		Palya.openAlagut2.getWays().remove(Palya.openAlagut2.getWays().size()-1);
		((Alagut) Palya.openAlagut1).Switch();
		((Alagut) Palya.openAlagut2).Switch();
		Palya.newSin=null;
		Sin.num--;
		Palya.openAlagut1=null;
		Palya.openAlagut2=null;
		
		}
		//isBuilt=false;
	}


	@Override
	public String toString() {
		return "Alagut [aktiv=" + aktiv + ", id=" + id + ", ways=" + ways + "]";
	}


	
}
