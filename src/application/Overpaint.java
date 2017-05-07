package application;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

public class Overpaint extends LayerUI<JComponent> {
	public static BufferedImage bf =null;
	
	
	@Override
	public void paint(Graphics g, JComponent c){
		super.paint(g, c);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(bf, 0, 0, null);
   		g2.dispose();
	}
}
