package application;

import java.awt.Color;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.io.Serializable;
/**
 * A palyan levo utak megnevezese, amin a vonatok kozlekednek illetve, amik osszekotik az adott controlpointokat. Minden sin tulajdonkepp egy Bezier gorbe,
 *  melyet vegpontjai es kontrollpontjai hataroznak meg. 
 * @author Tsurhe
 */
public class Sin implements Serializable{
	private int id;
	/**
	 * A sinek egyedi azonositasahoz haszalt szamlalo
	 */
	public static int num=0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public QuadCurve2D gorbe;
	public ControlPoint controlpoint1;
	public ControlPoint controlpoint2;
	public Mozdony mozdony;
	private boolean hide=false;
	/**
	 * Sin konstruktora
	 */
	public Sin(){
		GlobalLogger.log("called: sin constructor");
		id = num;
		num++;
	}
	public Sin(ControlPoint a, ControlPoint b){
		GlobalLogger.log("called: sin constructor");
		id = num;
		num++;
		controlpoint1=a;
		controlpoint2=b;
		gorbe = new QuadCurve2D.Float(controlpoint1.hely.x, controlpoint1.hely.y, 
				((b.hely.x+a.hely.x)/2)+Math.abs((b.hely.y-a.hely.y)/2),
				((b.hely.y+a.hely.y)/2)+Math.abs((b.hely.x-a.hely.x)/2),
				controlpoint2.hely.x, controlpoint2.hely.y);
	}
	
	public Sin(ControlPoint a, ControlPoint b,int x,int y){
		GlobalLogger.log("called: sin constructor");
		id = num;
		num++;
		controlpoint1=a;
		controlpoint2=b;
		gorbe = new QuadCurve2D.Float(controlpoint1.hely.x, controlpoint1.hely.y, 
				x, y,
				controlpoint2.hely.x, controlpoint2.hely.y);
	}
	
	/**
	 * Uj utvonalat ad a mozdonyanak (visszakap egy sint es mivel tarolja a rajta levo vonatokat,
	 * igy azok fifo szeru mukodese miatt a reglereggben rajta levonek adja az uj utat mert
	 * nyilvanvaloan annak kell, kulonbozo esetben mar utkozes tortent volna
	 * Ha a mozdon onnan kezdodik, vagyis a ways null, akkor default jobbra megy, vagyis a cp2 ad neki iranyt
	 * @throws CollideException Utkozeskor dobja
	 */
	public void giveNext(Mozdony asker) throws CollideException{
		try{
			boolean reverse = false;
			GlobalLogger.log("called: sin -giveNext");
			Sin next;
			if(mozdony.getWays()==null || controlpoint1.id == mozdony.getWays().controlpoint2.id){ //cp2.id a vege nm 1 mer kulonben visszavezeti
				next = controlpoint2.giveDirection(this, asker);
				if(next.controlpoint2.id == controlpoint2.id) // pl switcher a 2. idju de lehet amit ad az ellentetes iranyu
					reverse =true;
			}
			else{
				next = controlpoint1.giveDirection(this, asker);
				reverse = true;
			}
			asker.addWay(next, reverse);
		}catch(CollideException e){
			throw e;
		}
	}
	
	/**
	 * Sin kiiroja
	 */
	@Override
	public String toString() {
		if(mozdony != null)
			return "Sin [id=" + id + ", mozdonyID=" + mozdony.getId() + "]";
		else
			return "Sin [id=" + id + ", mozdonyID=" + mozdony + "]";
	}
	public boolean isHide() {
		return hide;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	
}
	
	
