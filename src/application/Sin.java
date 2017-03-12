package application;

import java.awt.geom.CubicCurve2D;

public class Sin {
	private int id;
	private CubicCurve2D gorbe;
	public ControlPoint controlpoint1;
	public ControlPoint controlpoint2;
	public Mozdony mozdony;
	
	/**
	 * Sin konstruktor
	 */
	public Sin(){
		System.out.println("called: sin constructor");
	}
	
	public void giveNext(){
		System.out.println("called: sin giveNext");
		controlpoint1 = new ControlPoint(null, null);
		controlpoint1.giveDirection(this);
	}
}
	
	
