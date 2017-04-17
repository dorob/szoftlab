package application;

public class Menu {
	
private Engine jatek;

	public Menu(){
		GlobalLogger.log("called: Menu constructor");
	}

	public void Init(){
		GlobalLogger.log("called: engine -init");
		jatek = new Engine();
	}
	public Engine getJatek() {
		return jatek;
	}
	public void setJatek(Engine jatek) {
		this.jatek = jatek;
	}
	
}
