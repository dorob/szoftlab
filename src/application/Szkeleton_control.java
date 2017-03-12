package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Szkeleton_control {
	Menu menu;
	
	public static void main (String[] args){
		Szkeleton_control con = new Szkeleton_control();
		con.Init();
	}
	
	public void Init(){
		System.out.println("called: szkeleton init");
		menu = new Menu();
		
		ArrayList<String> commands= new ArrayList<String>(); // ebbe basszatok bele az uj parancsokat
		commands.add("scores");
		commands.add("init");
		commands.add("move");
		commands.add("megall");
		commands.add("exit");
		try{
			menu.Init();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				String[] parts = line.split(" ");
	
				if (parts[0].equals("scores"))
					menu.getJatek().showScores();
				else if (parts[0].equals("init"))
					menu.getJatek().getLevel().init();
				else if (parts[0].equals("move")){
					menu.getJatek().run();
					}
				else if	(parts[0].equals("megall")){
					menu.getJatek().getLevel().getVehicles().get(0).doneMoving();
					System.out.println("leszall? y/n");
					line = br.readLine();
					if(line.equals("y")){
						System.out.println("....szallas:");
						Mozdony m = menu.getJatek().getLevel().getVehicles().get(0);
						m.getUtvonal().get(0).controlpoint2.perform(m);
					}else continue;
				}
				else if (parts[0].equals("exit")){
					break;
					}
				
				else
					System.err.println("Unknow command. Commands list is:" + commands);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
}
