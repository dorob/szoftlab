package application;

import java.util.ArrayList;

/**
 * 
 * @author Tsurhe
 *
 */
public class Scoreboard {
private ArrayList<Player> helyezes;

/**
 * Scoreboard constructor
 */
public Scoreboard(){
	System.out.println("called: scoreboard constructor");
	helyezes = new ArrayList<Player>(); //itt lefut a player construktor?? valszeg nm
}
public void save(){}
public void load(){
	System.out.println("called: Scoreboard load");
}
public void addHelyezes(String s, int i) {}
public Scoreboard getScoreboard(){ return this;}
public void SetScoreboard(Scoreboard in) {}

public ArrayList<Player> getHelyezes() {
	return helyezes;
}
public void setHelyezes(ArrayList<Player> helyezes) {
	this.helyezes = helyezes;
}


}
