package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

/**
 * Vezerlo osztaly a szkeleton hasznalatahoz, mellyel consolos feluleten iranyithatjuk az applikaciot, es tesztelhetjuk le egyes fuggvenyeit.
 * @author Tsurhe
 *
 */
public class Szkeleton_control {
	static File wd;
	Menu menu;
	
	/**
	 * Ez hivodik meg eloszor az applikacio inditasakor.
	 * @param args
	 */
	public static void main (String[] args){
		String path = System.getProperty("user.dir");
		wd = new File(path);
		
		
		Szkeleton_control con = new Szkeleton_control();
		/**start the controller */
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
				+ "\n\r To see the valid command, type in: help");
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
		
		commands.add("mkdir");
		commands.add("cd");
		commands.add("pwd");
		commands.add("ls");
		commands.add("help");
		commands.add("head");
		commands.add("cp");
		commands.add("length");
		commands.add("tail");
		commands.add("reclist");
		commands.add("rm");
		commands.add("mv");
		commands.add("cat");
		commands.add("wc");
		
		menu = new Menu();  ////ezt kiszedni innen h kesobb induljon 
		
		try{
			menu.Init();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//amig olvasunk a consolerol, addig folyamatosan inditjuk a kivant teszteket, melyeket fentebb emlitettunk
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				GlobalLogger.log("INPUT: " + line);
				
				String[] parts = line.split(" ");
	
				if (parts[0].equals("scores"))
					menu.getJatek().showScores();
				
				else if (parts[0].equals("init")){
					if(menu.getJatek().getLevel()==null)
						menu.getJatek().setLevel(new Palya());
					menu.getJatek().getLevel().init();
				}
				
				else if	(parts[0].equals("stop")){
				//	System.out.println("Is it a station (Not Tunnel, nor Switch)? y/n");
					GlobalLogger.log("Is it a station (Not Tunnel, nor Switch)? y/n");
					line = br.readLine();
					GlobalLogger.log("INPUT: " + line);
					if(line.equals("y")){
						//Ellenorizzuk, hogy leszallnak-e a megallonal (azonos szin)
						//System.out.println("You arrived at a Station. Do you want to get off? y/n");
						GlobalLogger.log("You arrived at a Station. Do you want to get off? y/n");
						
						line = br.readLine();
						GlobalLogger.log("INPUT: " + line);
						
						if(line.equals("y")){
						//	System.out.println("....getting off:");
							GlobalLogger.log("....getting off:");
							Mozdony m = menu.getJatek().getLevel().getVehicles().get(0);
							m.getUtvonal().get(0).controlpoint2.perform(m);
						}else ;
					}
					menu.getJatek().getLevel().getVehicles().get(0).doneMoving();
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
				else if (parts[0].equals("log"))
					GlobalLogger.Init(wd, parts);
				else if (parts[0].equals("mkdir"))
					con.mkdir(parts);
				else if (parts[0].equals("pwd"))
					con.pwd(parts);
				else if (parts[0].equals("cd"))
					con.cd(parts);
				else if (parts[0].equals("ls"))
					con.ls(parts);
				else if (parts[0].equals("cp"))
					con.cp(parts);
				else if (parts[0].equals("head"))
					con.head(parts);
				else if (parts[0].equals("help"))
					con.help(commands);
				else if (parts[0].equals("length"))
					con.length(parts);
				else if (parts[0].equals("tail"))
					con.tail(parts);
				else if (parts[0].equals("reclist"))
					con.reclist(wd, " ");
				else if (parts[0].equals("rm"))
					con.rm(parts);
				else if (parts[0].equals("mv"))
					con.mv(parts);
				else if (parts[0].equals("cat"))
					con.cat(parts);
				else if (parts[0].equals("wc"))
					con.wc(parts);
				else{
					//System.err.println("Unknow command. Commands list is:" + commands); //Kiirjuk a felismert parancsokat, amennyiben a felhasznalo ervenytelent hasznalna
					GlobalLogger.log("Unknow command. Commands list is:" + commands);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * mapppat csinal
	 * @param cmd parancs parameterei (uj mappa neve)
	 */
	protected void mkdir(String[] cmd) {
		File ff = new File(wd, cmd[1]);
		ff.mkdir();
	}
	
	/**
	 * Kiirja h epp melyik mappaba vagyunk (teljes eleresi utvonalat
	 * @param cmd
	 * @throws IOException
	 */
	protected void pwd(String[] cmd) throws IOException {
		//System.out.println(wd.getCanonicalPath());
		GlobalLogger.log(wd.getCanonicalPath());
	}
	
	/**
	 * mappaba le
	 * @param cmd .. nel parent mappa, am mappa amibe lepni akar 
	 * @throws IOException
	 */
	protected void cd(String[] cmd) throws IOException {
		File check = new File(wd, cmd[1]);
		if (!check.exists()) {
			//System.err.println("Path doesn't exist");
			GlobalLogger.log("Path doesn't exist");
			check= wd;
			// throw new IllegalArgumentException("Path doesn't exist");
		}
		if (cmd[1].equals("..")) {
			wd = wd.getParentFile();
		} else {
			wd = check;
		}
	}
	
	
	/**
	 * listazza a mappaban a fajlokat, -l -el reszletessen ( file adatok pl...
	 * @param cmd
	 */
	protected void ls(String[] cmd) {
		String[] contains = wd.list();
		// check emptiness
		if (wd.list().length == 0)
			//System.out.println("Path doesn't exist");
			GlobalLogger.log("Path doesn't exist");

		if (cmd.length > 1 && cmd[1].equals("-l")) {
			for (int i = 0; i < contains.length; i++) {
				File ff = new File(wd, contains[i]);
				if (ff.isDirectory())
					//System.out.println("d - " + contains[i]);
					GlobalLogger.log("d - " + contains[i]);
				else
					//System.out.println("f - " + contains[i] + " " + ff.length() + " byte");
					GlobalLogger.log("f - " + contains[i] + " " + ff.length() + " byte");
			}
		} else {
			for (int i = 0; i < contains.length; i++)
			//	System.out.println(contains[i]);
				GlobalLogger.log(contains[i]);
		}
	}
	
	/**
	 * masol fajlt masik faljlba ppl. "log.txt saved.dat"
	 * @param cmd
	 * @throws IOException
	 */
	protected void cp(String[] cmd) throws IOException {
		File from = new File(wd, cmd[1]);
		if (!from.exists())
			throw new IllegalArgumentException("Source doesn't exist");
		File to = new File(wd, cmd[2]);
		if (!to.exists())
			throw new IllegalArgumentException("Destination path doesn't exist");

		FileInputStream fis_from = new FileInputStream(from);
		FileOutputStream fos_to = new FileOutputStream(to);
		byte[] buffer = new byte[1024];
		int bytes_read;

		while ((bytes_read = fis_from.read(buffer)) != -1)
			fos_to.write(buffer, 0, bytes_read);

		fis_from.close();
		fos_to.close();
	}

	
	/**
	 * adott fajl elso 10 sora, vagy -n el megadva h hany sora
	 * @param cmd
	 * @throws IOException
	 */
	protected void head(String[] cmd) throws IOException {
		int num = 0;
		File check = new File(wd, cmd[cmd.length - 1]);
		if (!check.exists())
			throw new IllegalArgumentException("Path doesn't exist");
		FileReader fr = new FileReader(check);
		BufferedReader buf = new BufferedReader(fr);

		if (cmd[1].equals("-n")) {
			num = Integer.parseInt(cmd[2]);
		} else
			num = 10;
		for (int i = 0; i < num; i++) {
			String line = buf.readLine();
			if (line == null)
				break;
			//System.out.println(line);
			GlobalLogger.log(line);
		}
		buf.close();
	}
	
	/**
	 * kiirja a parancsokat meg ide ilyen hosszab szarba szepen h melyik fugveny mit csinal
	 * @param commands
	 */
	protected void help(ArrayList<String> commands){
		for(String tmp : commands){
		//	System.out.println(tmp);
			GlobalLogger.log(tmp);
		}
	}
	
	
	/**
	 * megszamolha a fajlban a szavak betuk es sorok szamat
	 * @param cmd a fajl
	 * @throws IOException
	 */
	protected void wc(String[] cmd) throws IOException {
		File check = new File(wd, cmd[1]);
		if (!check.exists())
			throw new IllegalArgumentException("File doesn't exist");
		Scanner in = new Scanner(check);

		int words = 0;
		int lines = 0;
		int chars = 0;
		while (in.hasNextLine()) {
			lines++;
			String line = in.nextLine();
			int spaces = 0;
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == ' ')
					spaces++;
			}
			chars += line.length() - spaces;
			words += new StringTokenizer(line, " ").countTokens();

		}
		//System.out.println("Sorok: " + lines + " szavak: " + words + " betûk " + chars);
		GlobalLogger.log("Sorok: " + lines + " szavak: " + words + " betûk " + chars);
		in.close();
	}

	
	/**
	 * kiirja a fajl tartalmat
	 * @param cmd a fajl neve abba a mappaba legyunk
	 * @throws IOException 
	 */
	protected void cat(String[] cmd) throws IOException {
		File check = new File(wd, cmd[1]);
		if (!check.exists())
			throw new IllegalArgumentException("File doesn't exist");
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(check)));
		while (true) {
			String line = buf.readLine();
			if (line == null)
				break;
			else
				//System.out.println(line);
				GlobalLogger.log(line);
		}
		buf.close();
	}

	/**
	 * atnevezes
	 * @param cmd
	 * @throws IOException
	 */
	protected void mv(String[] cmd) throws IOException {
		File from = new File(wd, cmd[1]);
		if (!from.exists())
			throw new IllegalArgumentException("Source doesn't exist");
		File to = new File(wd, cmd[2]);
		if (from.renameTo(to))
			//System.out.println("Renamed Successfuly to: " + to.getName());
			GlobalLogger.log("Renamed Successfuly to: " + to.getName());
	}

	/**
	 * torles
	 * @param cmd
	 * @throws Exception
	 */
	protected void rm(String[] cmd) throws Exception {
		File check = new File(wd, cmd[1]);
		if (!check.exists())
			//System.err.println("Path doesn't exist");
			GlobalLogger.log("Path doesn't exist");
		else if (check.delete())
			//System.out.println("Deleted successfully");
			GlobalLogger.log("Deleted successfully");
	}

	
	/**
	 * rekurziv kilistazza a fajlokat a mappan belul
	 * @param f
	 * @param tab
	 */
	protected void reclist(File f, String tab) {
		File[] list = f.listFiles();

		for (int i = 0; i < list.length; i++) {
		//	System.out.println(tab + list[i].getName());
			GlobalLogger.log(tab + list[i].getName());
			if (list[i].isDirectory()) {
				reclist(list[i], tab + "  ");
			}
		}
	}

	/**
	 * utso 10 sort irja ki
	 * @param cmd
	 * @throws IOException
	 */
	protected void tail(String[] cmd) throws IOException {
		File check = new File(wd, cmd[1]);
		InputStream is;
		if (!check.exists())
			throw new IllegalArgumentException("path doesn't exist");
		is = new FileInputStream(check);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		ArrayList<String> fifo = new ArrayList<String>();

		String tmp = "";
		while ((tmp = buf.readLine()) != null) {
			fifo.add(tmp);
			if (fifo.size() == 11)
				fifo.remove(0);
		}

		for (int i = 0; i < fifo.size(); i++)
			//System.out.println(fifo.get(i));
			GlobalLogger.log(fifo.get(i));
		buf.close();
	}

	
	/**
	 * fajl hosszat irj aki
	 * @param cmd
	 * @throws IOException
	 */
	protected void length(String[] cmd) throws IOException {
		File check = new File(wd, cmd[1]);
		if (!check.exists())
			throw new IllegalArgumentException("Path doesn't exist");
		//System.out.println(check.length());
		GlobalLogger.log(String.valueOf(check.length()));
	}
}
