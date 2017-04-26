package application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;


/**
 * A jatek futasaert felelos objektum.
 * @author Tsurhe
 *
 */
public class Engine extends JPanel{
	private Palya level;
	private Scoreboard toplista;
	private String nev = "player";
	private int time = 0;
	private int palya = 1;
	
	
	Shape shape;
	Rectangle box = new Rectangle(0, 0, 20, 10);
	Point2D pos;
	int index = 0;
	double angle;
	Timer timer;
	
	/**
	 * Engine konstruktora
	 */
	public Engine(){
		GlobalLogger.log("called: Engine constructor");
		toplista = new Scoreboard();
		toplista.load();
		
	}
	
	/**
	 * A jatek focklusanak futtatasa. Utkozesi hiba eseten leallitja a programot
	 */
	public void run(){
		GlobalLogger.log("called: Engine -run");
	
		if(level.checkCompleted()){
			GlobalLogger.log("  level completed");
			if(!nextLevel()){
				win();
			}
		}
		else{
			try {
				level.run();
				repaint();
			} catch (CollideException e) {
				GlobalLogger.log(e.getMessage());
				this.exit();
			}
		}
	}
	/**
	 * A kovetkezo palya betolteset vegzi, ha nincs tobb palya false-al ter vissza, a jatekos nyert.
	 * @return Van e kovetkezo palya.
	 */
	public boolean nextLevel(){ 
		GlobalLogger.log("called: Engine -nextLevel");		
		try {
	         FileInputStream fileIn = new FileInputStream("level"+palya+".ser");
	         palya++;
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         level = (Palya) in.readObject();
	         in.close();
	         fileIn.close();
	         GlobalLogger.log("called: Palya -load");
	         
	      }catch(IOException i) {
	         return false;	         
	      }catch(ClassNotFoundException c) {
	         System.out.println("Palya class not found");	         
	      }		
		return true;
		}
	/**
	 * A jatek vegen hivodik meg, megallit minden folyamatot es hozzaadja a jatekost a toplistahoz.
	 */
	public void win(){
		GlobalLogger.log("called: Engine -win");
		toplista.addHelyezes(nev, time);
		toplista.save();
		this.exit();
	}
	/**
	 * Megnyitja a toplistat.
	 */
	public void showScores(){
		GlobalLogger.log("called: Engine -showScores");
		String s="";
		//toplista.load(); // serialize ellenorzes
		Collections.sort(toplista.getHelyezes());
		
		for(Player p: toplista.getHelyezes()){
			s+=p.toString()+'\n';
		}
		
		GlobalLogger.log(s);
		
	}
	/**
	 * A vonatok utkozeset detektalja.
	 */
	public void collisionDetection(){
		GlobalLogger.log("called: Engine -collisionDetection");
	}
	
	/**
	 * Leallitja a folyamatokat esbezarja a programot.
	 */
	public void exit(){
		GlobalLogger.log("called: Engine -exit");
		return;
	}
		
	
	protected void paintComponent(Graphics g) {
       	super.paintComponent(g);
           Graphics2D g2d = (Graphics2D) g.create();
           applyQualityRenderingHints(g2d);
           g2d.setColor(Color.blue);
           //minden mozdny kirajz
           for(Mozdony m : this.level.getVehicles()){
		        shape = m.getUtvonal().gorbe;
	        	pos = m.pos;
	        	angle = m.angle;
	        	
		        int x = (getWidth() - shape.getBounds().width) / 2; //ovalgenyok kozee
		        int y = (getHeight() - shape.getBounds().height) / 2;
		        g2d.translate(x, y);
		        g2d.draw(shape);
		           
		        AffineTransform at = new AffineTransform();
		        if(pos!=null){
		        	Rectangle bounds = box.getBounds();
		           	at.rotate(angle, (bounds.width/2), (bounds.height/2));
		           	Path2D player = new Path2D.Double(box, at);
		           	
		           	g2d.translate(pos.getX() - (bounds.width / 2), pos.getY() - (bounds.height / 2));
		               g2d.setColor(Color.RED);
		               g2d.draw(player);
		           }
	           }
	           g2d.dispose();
    }
	
	public void applyQualityRenderingHints(Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }
	

	
	
	
	
	//Generalt fv.-nyek.
	public Palya getLevel() {
		GlobalLogger.log("called: Engine -getLevel");
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
