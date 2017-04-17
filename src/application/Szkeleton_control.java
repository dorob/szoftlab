package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Vezerlo osztaly a szkeleton hasznalatahoz, mellyel consolos feluleten iranyithatjuk az applikaciot, es tesztelhetjuk le egyes fuggvenyeit.
 * @author Tsurhe
 *
 */
public class Szkeleton_control {
	static File wd;
	Menu menu;
	Magic magic= new Magic();
	private BufferedReader br;
	/**
	 * Ez hivodik meg eloszor az applikacio inditasakor.
	 * @param args
	 */
	public static void main (String[] args){
		String path = System.getProperty("user.dir");
		wd = new File(path);
		
		
		Szkeleton_control con = new Szkeleton_control();
		/**start the controller **/
		con.Init(con);
		//jelezzuk a szkeleton leallasat, ez tulajdonkeppen nem error, de igy latvanyosabb
		System.err.println("Szkeleton control ended");		
		GlobalLogger.stop();
	}
	
	/**
	 * Inicializalo fuggveny, ami mar hivhat nem statikus fuggvenyeket. Ez fogja olvasni a parancsainkat, es vegrehajtja azokat.
	 * @param con 
	 */
	public void Init(Szkeleton_control con){
		System.out.println("EVERY TIME YOU START THE APPLICATION YOU MUST START WITH THE 'init' COMMAND!!"
				+ "\n\r To start logging into a file, type in: log 'filename'"
				+ "\n\r To see the valid command, type in: help"
				+ "\n\r To read test input from file, navigate into its folder and type in: load 'filename'\n\r");
		ArrayList<String> commands= new ArrayList<String>(); // itt taroljuk a megvalosithato parancsokat
		/**
		 * Ezzel tesztelhetjuk a toplista megtekinteset.
		 */
		commands.add("scores");							
		
		/**
		 * Ezzel tesztelhetjuk a kezdo inicializalast.
		 */
		commands.add("test");
		
		/**
		 * Ezzel teszteljuk a jatek mozgatasat, valamint inicializalas elott hivva inicializalhatunk is vele,
		 * amennyibenaz "Utso palya volt"-ra "n" -al valaszolunk.
		 */
		commands.add("run");
		
		/**
		 * Ezzel teszteljuk a jatekbol valo kilepest.
		 */
		commands.add("exit");
		
		/**
		 * Ezzel teszteljuk a kapcsolok allitgatasat
		 */
		commands.add("switch");
		
		/**
		 * Ezzel teszteljuk az alagutak epiteset, valamint lebontasat
		 */
		commands.add("tunnel" + " " + "-build/destroy");
		
		commands.add("load");
		commands.add("log");
		System.out.println(commands.size());
		menu = new Menu();  ////ezt kiszedni innen h kesobb induljon 
		
		try{
			menu.Init();
			br = new BufferedReader(new InputStreamReader(System.in));
			//amig olvasunk a consolerol, addig folyamatosan inditjuk a kivant teszteket, melyeket fentebb emlitettunk
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				GlobalLogger.log("----INPUT: " + line);
				
				String[] parts = line.split(" ");
	
				if (parts[0].equals("scores"))
					menu.getJatek().showScores();
				
				else if (parts[0].equals("test")){
					if(parts[1]!=null){
						menu.getJatek().setLevel(magic.loadShit(parts[1]));
					}
				}
				
				
				else if (parts[0].equals("exit")){
					menu.getJatek().exit();
					break;
					}
				
				else if (parts[0].equals("run")){
					menu.getJatek().run();
					}
				
				else if (parts[0].equals("perform")){
					ControlPoint find = menu.getJatek().getLevel().findCP(0, 0); //0,0 hogy switchert kapjunk vissza
					find.perform(null);
				}
				
				else if (parts[0].equals("tunnel")){
					ControlPoint find = menu.getJatek().getLevel().findCP(0, 1); // 0,1 hogy alagutat adjon vissza (csak szkeletonba azonosit igy)
					find.perform(null);
				}
				else if (parts[0].equals("log"))
					GlobalLogger.Init(wd, parts);
				else if (parts[0].equals("load"))
					con.load(parts);
				else{
				//Kiirjuk a felismert parancsokat, amennyiben a felhasznalo ervenytelent hasznalna
					GlobalLogger.log("Unknow command. Commands list is:" + commands);
				}
			}
			if(br!=null)
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * kiirja a parancsokat meg ide ilyen hosszab szarba szepen h melyik fugveny mit csinal
	 * @param commands
	 */
	protected void help(ArrayList<String> commands){
		for(String tmp : commands){
			GlobalLogger.log(tmp);
		}
	}
	

	/**
	 * Fajlbol beolvassa a prarancsokat, es elvegzi oket
	 * @param cmd A fajl eleresi utvonala
	 * @throws FileNotFoundException 
	 */
	protected void load(String[] cmd) throws FileNotFoundException{
		File check = new File(wd, cmd[1]);
		if (!check.exists())
			throw new IllegalArgumentException("File doesn't exist");
		br = new BufferedReader(new InputStreamReader(new FileInputStream(check)));	
	}
	
}
