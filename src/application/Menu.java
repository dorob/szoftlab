package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
	
private Engine jatek;

	public Menu(){
		System.out.println("called: Menu constructor");
	}
/* alapbol ez fog futni de most a control miatt nem ez a main
	public static void main(String[] args){
		Menu mm = new Menu();
		mm.Init();
	}
*/
	public void Init(){
		System.out.println("called: engine init");
		jatek = new Engine();
	}
	public Engine getJatek() {
		return jatek;
	}
	public void setJatek(Engine jatek) {
		this.jatek = jatek;
	}
	
}
