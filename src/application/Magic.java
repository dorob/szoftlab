package application;

import java.io.BufferedReader;
import java.io.FileReader;

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
			
			
		}
		buf.close();
		}catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
}
