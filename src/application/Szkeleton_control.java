package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Vezerlo osztaly a szkeleton hasznalatahoz, mellyel consolos feluleten iranyithatjuk az applikaciot, es tesztelhetjuk le egyes fuggvenyeit.
 * @author Tsurhe
 *
 */
public class Szkeleton_control {
	Menu menu;
	
	/**
	 * Ez hivodik meg eloszor az applikacio inditasakor.
	 * @param args
	 */
	public static void main (String[] args){
		Szkeleton_control con = new Szkeleton_control();
		con.Init();
		//jelezzuk a szkeleton leallasat, ez tulajdonkeppen nem error, de igy latvanyosabb
		System.err.println("Szkeleton control ended");		
	}
	
	/**
	 * Inicializalo fuggveny, ami mar hivhat nem statikus fuggvenyeket. Ez fogja olvasni a parancsainkat, es vegrehajtja azokat.
	 */
	public void Init(){
		System.out.println("called: szkeleton init");
		menu = new Menu();
		
		ArrayList<String> commands= new ArrayList<String>(); // itt taroljuk a megvalosithato parancsokat
		/**
		 * Ezzel tesztelhetjuk a toplista megtekinteset.
		 */
		commands.add("scores");							
		
		/**
		 * Ezzel tesztelhetjuk a kezdo inicializalast.
		 */
		commands.add("init");
		
		/**
		 * Ezzel teszteljuk a jatek mozgatasat, valamint inicializalas elott hivva inicializalhatunk is vele,
		 * amennyibenaz "Utso palya volt"-ra "n" -al valaszolunk.
		 */
		commands.add("start");
		
		/**
		 * Ezzel teszteljuk azt, hogy vegigment egy sinen es beert egy controlPointba, ahonnan uj utvonalat var,
		 * valamint ha az a controlpoint, akkor ott le is szallhatnak az utasok.
		 */
		commands.add("stop" + " " + "-getoff");
		
		/**
		 * Ezzel teszteljuk a jatekbol valo kilepest.
		 */
		commands.add("exit");
		
		/**
		 * Ezzel teszteljuk a jatek megnyereset
		 */
		commands.add("win");
		
		/**
		 * Ezzel adunk lehetoseget az utkozes ellenorzesere
		 */
		commands.add("collide");
		
		/**
		 * Ezzel teszteljuk a kapcsolok allitgatasat
		 */
		commands.add("switch");
		
		/**
		 * Ezzel teszteljuk az alagutak epiteset, valamint lebontasat
		 */
		commands.add("tunnel" + " " + "-build/destroy");
		
		/**
		 * Ezzel teszteljuk az adott palya veghezvitelebol kovetkezo kovetkezo palya betolteset, valamint
		 * kovetkezo palya hianyaban a jatek megnyereset
		 */
		commands.add("nextlevel");
		
		try{
			menu.Init();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//amig olvasunk a consolerol, addig folyamatosan inditjuk a kivant teszteket, melyeket fentebb emlitettunk
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				String[] parts = line.split(" ");
	
				if (parts[0].equals("scores"))
					menu.getJatek().showScores();
				
				else if (parts[0].equals("init")){
					if(menu.getJatek().getLevel()==null)
						menu.getJatek().setLevel(new Palya());
					menu.getJatek().getLevel().init();
				}
				
				else if	(parts[0].equals("stop")){
					menu.getJatek().getLevel().getVehicles().get(0).doneMoving();
					//Ellenorizzuk, hogy le leszallnak-e (vagyis megallo van e a controlpointnal)
					System.out.println("Get off? y/n");
					line = br.readLine();
					if(line.equals("y")){
						System.out.println("....szallas:");
						Mozdony m = menu.getJatek().getLevel().getVehicles().get(0);
						m.getUtvonal().get(0).controlpoint2.perform(m);
					}else continue;
				}
				
				else if (parts[0].equals("exit")){
					menu.getJatek().exit();
					break;
					}
				
				else if (parts[0].equals("win")){
					menu.getJatek().win();
					break;
					}
				
				else if (parts[0].equals("collide")){
					menu.getJatek().collisionDetection();
					}
				
				else if (parts[0].equals("start")){
					menu.getJatek().run();
					}
				
				else if (parts[0].equals("switch")){
					ControlPoint find = menu.getJatek().getLevel().findCP(0, 0); //0,0 hogy switchert kapjunk vissza
					find.perform(null);
				}
				
				else if (parts[0].equals("tunnel")){
					ControlPoint find = menu.getJatek().getLevel().findCP(0, 1); // 0,1 hogy alagutat adjon vissza (csak szkeletonba azonosit igy)
					find.perform(null);
				}
				
				else if (parts[0].equals("nextlevel")){
					Engine e =menu.getJatek();
					if(!e.getLevel().checkCompleted()){
						
					}else{
						if(e.nextLevel()){
							e.win();
							break;
						}
						}
				}
				else
					System.err.println("Unknow command. Commands list is:" + commands); //Kiirjuk a felismert parancsokat, amennyiben a felhasznalo ervenytelent hasznalna
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
