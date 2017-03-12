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
		toplista = new Scoreboard();
		
	}
	
	
	public void run(){
		System.out.println("called: Engine run");
		
		if(level==null){
			System.out.println("  level = null");
			nextLevel();
		}
		
		level.run();
	}
	public boolean nextLevel(){ 
		System.out.println("called: nextLevel");
		level = new Palya();
		level.init();
		return false;
		}
	public void win(){
		System.out.println("called: Engine win");
		toplista.addHelyezes(nev, time);
		toplista.save();
		this.exit();
	}
	public void showScores(){
		System.out.println("called: Engine showScores");
		toplista.load();
	}
	public void collisionDetection(){
		System.out.println("called: Engine collisionDetection");
		this.exit();
	}
	public void exit(){
		System.out.println("called: Engine exit");
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
