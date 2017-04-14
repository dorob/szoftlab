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
	public void perform(Mozdony m){
		if(Palya.newSin == null){
			if(Palya.openAlagut1==null){
				Palya.openAlagut1=this;	
			}
			else{	//ha ugyanarra kattintok másodszor, akkor törlöm az alagutat
				if(Palya.openAlagut1.getID==this.id)
						Palya.openAlagut1=null;
				else{
					//ha létrejött a két alagut, akkor meghívom a buildet
					Palya.openAlagut2=this;
					build();
				}
				
			}
			
		
			
		}
		else{	
			destroy();
			 
		}

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
		Palya.newSin=new Sin(Palya.openAlagut1, Palya.openAlagut2);
		Palya.openAlagut1.getWays().add(Palya.newSin);
		Palya.openAlagut2.getWays().add(Palya.newSin);
	}
	
	/**
	 * Lerombolja az alagutat.
	 */
	public void destroy(){
		GlobalLogger.log("called: Alagut destroy");
		if(Palya.newSin.mozdony=! null); //end game
		else{
		Palya.openAlagut1.getWays().remove(Palya.openAlagut1.getWays().size()-1);
		Palya.openAlagut2.getWays().remove(Palya.openAlagut2.getWays().size()-1);
		Palya.newSin=null;
		Sin.num--;
		Palya.openAlagut1=null;
		Palya.openAlagut2=null;
		
		}
		//isBuilt=false;
	}
}
