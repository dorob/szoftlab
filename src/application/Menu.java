package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
	
private Engine jatek;

	public Menu(){
		System.out.println("called: Menu constructor");
	}

	public static void main(String[] args){
		Menu mm = new Menu();
		mm.Init();
	}
	public void Init(){
		System.out.println("called: engine init");
		ArrayList<String> commands= new ArrayList<String>(); // ebbe basszatok bele az uj parancsokat
		commands.add("scores");
		commands.add("init");
		commands.add("move");
		try{
			jatek = new Engine();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				String[] parts = line.split(" ");
	
				if (parts[0].equals("scores"))
					jatek.showScores();
				else if (parts[0].equals("init"))
					jatek.getLevel().init();
				else if (parts[0].equals("move"))
					jatek.run();
				
				else
					System.err.println("Unknow command. Commands list is:" + commands);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
