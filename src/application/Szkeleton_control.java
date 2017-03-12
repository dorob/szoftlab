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
		commands.add("start");
		commands.add("move");
		commands.add("stop" + " " + "-getoff");
		commands.add("exit");
		commands.add("win");
		commands.add("collide");
		commands.add("switch");
		commands.add("tunnel" + " " + "-build/destroy");
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
				else if (parts[0].equals("init")){
					if(menu.getJatek().getLevel()==null)
						menu.getJatek().setLevel(new Palya());
					menu.getJatek().getLevel().init();
				}
				else if (parts[0].equals("move")){
					menu.getJatek().run();
					}
				else if	(parts[0].equals("stop")){
					menu.getJatek().getLevel().getVehicles().get(0).doneMoving();
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
					return;
					}
				else if (parts[0].equals("collide")){
					menu.getJatek().collisionDetection();
					}
				else if (parts[0].equals("start")){
					Engine e = menu.getJatek();
					e.run();
					}
				else if (parts[0].equals("switch")){
					ControlPoint find = menu.getJatek().getLevel().findCP(0, 0);
					find.perform(null);
				}
				else if (parts[0].equals("tunnel")){
					ControlPoint find = menu.getJatek().getLevel().findCP(0, 1); //csak hogy alagutat adjon vissza
					find.perform(null);
				}
				else
					System.err.println("Unknow command. Commands list is:" + commands);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.err.println("Szkeleton control ended");
		
		
	}
}
