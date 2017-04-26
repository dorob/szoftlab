package application;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Menu {
	
private Engine jatek;

	public Menu(){
		GlobalLogger.log("called: Menu constructor");
	}

	public void Init(){
		GlobalLogger.log("called: engine -init");
		jatek = new Engine();
		
		JFrame frame = new JFrame("GO GRAPHIC BIIITHC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scroll = new JScrollPane(jatek);
		frame.getContentPane().add(scroll);
		
		frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
	}
	public Engine getJatek() {
		return jatek;
	}
	public void setJatek(Engine jatek) {
		this.jatek = jatek;
	}
	
}
