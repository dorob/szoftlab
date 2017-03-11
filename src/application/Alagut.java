package application;

public class Alagut extends Switcher{

	private boolean isBuilt;
	
	public boolean isBuilt() {
		System.out.println("called: isBuilt");
		return isBuilt;
	}
	public void setBuilt(boolean isBuilt) {
		System.out.println("called: setBuilt");
		this.isBuilt = isBuilt;
	}
	
	
	public void build(){
		System.out.println("called: build");
	}
	public void destroy(){
		System.out.println("called: destroy");
	}
}
