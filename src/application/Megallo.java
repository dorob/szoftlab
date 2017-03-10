package application;

import java.awt.Color;

public class Megallo extends ControlPoint{

	private Color color;
	
	public Color getColor(){ return Color.GREEN;}
	public void setColor(){}
	@Override
	public Sin giveDirection(Sin a){return a;}
	@Override
	public void perform(){}
	
}
