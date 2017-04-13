package application;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Magic {

	/**
	 * betoltendo map txt formaja:
	 * (n      //vonatok szama
	 * m(	  //az adott vonatba levo vagonok szama
	 * RED    // az elso vagon szine
	 * .
	 * .
	 * .))	  //vagonok szinei majd megint egy m majd szinek.. osszesen n*(1+m) sor olvasas txtbol
	 * 

	 * (l 	  // controlpontok szama
	 * <ControlPoint name> <posX> <posY> <esetleges attr>  //esetleges: ha megallo akkor egy RED, switchernel pl 2 
	 * .
	 * .
	 * )  // l db controlpoint
	 * 
	 * k     // sinek szama
	 * <cp1 index> <cp2 index>   //beolvasuk cp1 es cp2-t amit a new sinnek adunk attr. kent
	 * 							 //aztan mind2 cp ways-ehez hozzaadjuk a letrehozott uj sint
	 */
	
	public void loadShit(String filename){
		Palya tmp;
		try{
		FileReader fr = new FileReader("MagicMap1.txt");
		BufferedReader buf = new BufferedReader(fr);
		while(true){
			String line = buf.readLine();
			if(line == null) break;
			/**
			 * Vonat teljes betoltes vagonokkal
			 */
			int numOfVonat = Integer.parseInt(line);
			for(int i = 0; i < numOfVonat; i++){
				Mozdony mtmp = new Mozdony();
				int numOfVagon = Integer.parseInt(buf.readLine());
				for(int j = 0; j < numOfVagon; j++){
					String col = buf.readLine();
					mtmp.addVagon(new Vagon(szin(col)));
				}
			}
			
			/**
			 * Controlpointok betoltese
			 */
			int numOfControlPoints = Integer.parseInt(buf.readLine());
			ArrayList<ControlPoint> cp = new ArrayList<ControlPoint>();
			for(int i = 0; i < numOfControlPoints; i++){
				String complex = buf.readLine();
				String attrs[] = complex.split(" ");
				switch (attrs[0]){
					case "CP":{

						cp.add(new ControlPoint(null, Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2])));
						break;
					}
					case "Megallo":{
						cp.add(new Megallo(null, Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2]), szin(attrs[3])));
						break;
					}
					case "Switcher":{
						break;
					}
					case "Alagut":{
						break;
					}
				}
			}
			
			
		}
		buf.close();
		}catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	public Color szin(String in){
		switch (in){
		case "RED":{
			return Color.RED;
		}
		case "GREEN":{
			return Color.GREEN;
		}
		case "BLUE":{
			return Color.BLUE;
		}
		case "YELLOW":{
			return Color.YELLOW;
		}
		default: return null;
	}
	}
	
}
