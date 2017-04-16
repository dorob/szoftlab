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
	Palya tmp = new Palya();
	public void loadShit(String filename){
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
				tmp.getVehicles().add(mtmp);
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
						cp.add(new Megallo(null, Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2]), szin(attrs[3]), Boolean.valueOf(attrs[4])));
						break;
					}
					case "Switcher":{
						cp.add(new Switcher(null, Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3])));
						break;
					}
					case "Alagut":{
						cp.add(new Alagut(null, Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3])));
						break;
					}
				}
			}
			
			/**
			 * sinek beolvasasa es kontrolpontokkal valo osszekapcsolasa
			 */
			int numOfSins = Integer.parseInt(buf.readLine());
			for(int i = 0; i < numOfSins; i++){
				Sin stmp = new Sin();
				String[] idxs = buf.readLine().split(" ");
				stmp.controlpoint1 = cp.get(Integer.parseInt(idxs[0]));
				stmp.controlpoint2 = cp.get(Integer.parseInt(idxs[1]));
				cp.get(Integer.parseInt(idxs[0])).addWay(stmp);
				cp.get(Integer.parseInt(idxs[1])).addWay(stmp);
			}
			tmp.getCp().addAll(cp);
			
			
			
		}
		buf.close();
		}catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	
	/**
	 * A Stringbol konkret szint kepez
	 * @param in A szin szoveggel megadva
	 * @return A szin szin osztallyal megadva
	 */
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
	
	/**
	 * Ket bemeneti szoveget hasonlit ossze es visszater igazzal, ha megegyeznek, valamint
	 * hamissal, ha kulonboznek
	 * @param result Az egyik bemenet. (Tipikusan, amit a tesztunk kimenetkent adott)
	 * @param expected A masik bemenet. (Tipikusan az elore megadot elvart kimenet)
	 * @return Igaz, ha egyezik, Hamis ha kulonbozik
	 */
	public boolean compareText(String result, String expected){
		try{
			BufferedReader res = new BufferedReader(new FileReader(result));
			BufferedReader exp = new BufferedReader(new FileReader(expected));
			ArrayList<Integer> atLine = new ArrayList<Integer>();
			int lineNum = 0;
			while(true){
				String resLine = res.readLine();
				String expLine = exp.readLine();
				/**
				 * Ha mindket szovegen vegigertunk akkor kilepunk
				 */
				if(resLine == null && expLine == null)
					break;
				/**
				 * Ha valamelyiken meg nem ertunk vegig, akkor NullPointerException elkerulese vegett
				 * mar nem hasonlitunk, de feljegyezzunk a kulonbozo sor szamat
				 */
				if(resLine == null || expLine == null)
					atLine.add(lineNum);
				else if(resLine.compareTo(expLine) != 0){
					atLine.add(lineNum);
				}
				lineNum++;
			}
			
			/**
			 * Ha ures a hibas sorok szamanak listaja, akkor egyezik a ket sziveg
			 */
			if(atLine.size() < 1)
				return true;
			else{
				System.out.println("Differencies at lines: " + atLine);
				return false;
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args){
		Magic m = new Magic();
	//	m.loadShit("");
		System.out.println(m.compareText("result1.txt", "expected1.txt"));
	}
	
}
