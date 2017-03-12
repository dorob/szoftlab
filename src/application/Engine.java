package application;

public class Engine {
	private Palya level;
	private Scoreboard toplista;
	private String nev;
	private int time;
	
	/**
	 * Engine konstruktora
	 */
	public Engine(){
		System.out.println("called: Engine constructor");
		level = new Palya();
		toplista = new Scoreboard();
		
	}
	
	
	public void run(){
		System.out.println("called: Engine run");
		level.run();
	}
	public boolean nextLevel(){ 
		System.out.println("called: nextLevel");
		return false;
		}
	public void win(){
		System.out.println("called: Engine win");
		toplista.addHelyezes(nev, time);
		toplista.save();
	}
	public void showScores(){
		System.out.println("called: showScores");
		toplista.load();
	}
	public void collisionDetection(){
		System.out.println("called: collisionDetection");
	}
	public void exit(){
		System.out.println("called: exit");
	}
	
	
	public Palya getLevel() {
		System.out.println("called: getLevel");
		return level;
	}
	public void setLevel(Palya level) {
		this.level = level;
	}
	public Scoreboard getToplista() {
		return toplista;
	}
	public void setToplista(Scoreboard toplista) {
		this.toplista = toplista;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	

}
