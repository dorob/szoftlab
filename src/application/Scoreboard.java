package application;

import java.util.ArrayList;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

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
public void save(){
	System.out.println("called scoreboard - save");
}
public void load(){
	System.out.println("called: Scoreboard load");
}
public void addHelyezes(String s, int i) {
	System.out.println("called scoreboard addHelyezes");
	System.out.println("--players sorted"); //ezt ilyen collections.sort szeruvel ha mar megvan a comparator
}
public Scoreboard getScoreboard(){ return this;}
public void SetScoreboard(Scoreboard in) {}

public ArrayList<Player> getHelyezes() {
	return helyezes;
}
public void setHelyezes(ArrayList<Player> helyezes) {
	this.helyezes = helyezes;
}


}
