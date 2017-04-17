package application;

import java.awt.Color;
import java.awt.geom.CubicCurve2D;
/**
 * A palyan levo utak megnevezese, amin a vonatok kozlekednek illetve, amik osszekotik az adott controlpointokat. Minden sin tulajdonkepp egy Bezier gorbe,
 *  melyet vegpontjai es kontrollpontjai hataroznak meg. 
 * @author Tsurhe
 */
public class Sin {
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

	private CubicCurve2D gorbe;
	public ControlPoint controlpoint1;
	public ControlPoint controlpoint2;
	public Mozdony mozdony;
	
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
			GlobalLogger.log("called: sin -giveNext");
			Sin next;
			if(mozdony.getWays()==null || controlpoint1.id == mozdony.getWays().controlpoint1.id)
				next = controlpoint2.giveDirection(this);
			else
				next = controlpoint1.giveDirection(this);
			mozdony.addWay(next);
		}catch(CollideException e){
			throw e;
		}
	}
	
	@Override
	public String toString() {
		if(mozdony != null)
			return "Sin [id=" + id + ", mozdonyID=" + mozdony.getId() + "]";
		else
			return "Sin [id=" + id + ", mozdonyID=" + mozdony + "]";
	}
}
	
	
