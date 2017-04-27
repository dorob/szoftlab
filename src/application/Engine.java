package application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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
public class Engine extends JPanel implements ActionListener{
	private Palya level;
	private Scoreboard toplista;
	private String nev = "player";
	private int time = 0;
	private int palya = 1;
	private ArrayList<JButton> sButtons;
	private ArrayList<JButton> aButtons;
	
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
		this.setLayout(null);
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
		try {
		super.paintComponent(g);
           Graphics2D g2d = (Graphics2D) g.create();
           applyQualityRenderingHints(g2d);
           g2d.setColor(Color.blue);
           //sinek kirajozlasa
           for(ControlPoint c : this.level.getCp())
        	  for(Sin ss : c.getWays())
        		  g2d.draw(ss.gorbe);
          
           //minden mozdny kirajz
           for(Mozdony m : this.level.getVehicles()){
        	   g2d.setColor(Color.PINK);
	        	pos = m.pos;
	        	angle = m.angle;
		        AffineTransform at = new AffineTransform();
		        if(pos!=null){
		        	Rectangle bounds = box.getBounds();
		        	at.translate(pos.getX() - (bounds.width / 2), pos.getY() - (bounds.height / 2));
		        	at.rotate(angle, (bounds.width/2), (bounds.height/2));
				    g2d.transform(at);       	
				    //tenyleges mozdonyrajzolas
		           	g2d.fill(box);          
		           	//takaritas kovi elem rajzolasahoz
					g2d.transform(at.createInverse());
					
		           	//vagonok rajzolas
		           	for(int i =0; i< m.getVagonok().size(); i++){
		           		if(i < m.pointsack.size()){
		           			g2d.setColor(m.getVagonok().get(i).getColor());
		           			at = new AffineTransform();
		           			int j = m.anglesack.size()-i-1; // enelkul forditott sorrendbe rajzolta a vagonokat
		           			at.translate(m.pointsack.get(j).getX() - (bounds.width / 2), m.pointsack.get(j).getY() - (bounds.height / 2));
		           			at.rotate(m.anglesack.get(j), (bounds.width/2), (bounds.height/2));
		           			g2d.transform(at);
		           			g2d.fill(box);
		           			g2d.transform(at.createInverse());
		           		}
		           	}
		        }
           }
           
           //megallo kirajzolas
		   for(ControlPoint cp : this.level.getCp()){
			   if(cp.toString().equals("Megallo")){
				   Megallo mm = (Megallo) cp; //hogy kinyerjuk a szinet
				   g2d.setColor(mm.getColor());
				   g2d.fill(mm.alak);
			   }
			   
			   else if(cp.toString().equals("Switcher")){
				   g2d.setColor(new Color(255, 219, 112));
				   g2d.fill(cp.alak);
			   }
			   else if(cp.toString().equals("Alagut")){
				   g2d.setColor(new Color(216, 100, 197));
				   g2d.fill(cp.alak);
				   
			   }
		   }
		   
           
           		g2d.dispose();
			}catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}
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
	
	public void initButtons(){
		sButtons = new ArrayList<JButton>();
		aButtons = new ArrayList<JButton>();
		for(int i = 0; i < level.getCp().size(); i++){
			if(level.getCp().get(i).toString().equals("Switcher")){	
				JButton tmp = new JButton(Integer.toString(((Switcher) level.getCp().get(i)).aktiv));
				tmp.setMargin(new Insets(0, 0, 0, 0));
				tmp.setOpaque(false);
				tmp.setContentAreaFilled(false);
				tmp.setBorderPainted(false);
				tmp.addActionListener(this);
				tmp.putClientProperty("index", i); //mocsok megoldas hogy tarolja az indexet hogz kihey tartozik
				tmp.setBounds(level.getCp().get(i).hely.x-10, level.getCp().get(i).hely.y-10, 20, 20);
				this.add(tmp);
			}
			if(level.getCp().get(i).toString().equals("Alagut")){
				JButton tmp = new JButton(Integer.toString(((Switcher) level.getCp().get(i)).aktiv));
				tmp.setMargin(new Insets(0, 0, 0, 0));
				tmp.setOpaque(false);
				tmp.setContentAreaFilled(false);
				tmp.setBorderPainted(false);
				tmp.addActionListener(this);
				tmp.putClientProperty("index", i); //mocsok megoldas hogy tarolja az indexet hogz kihey tartozik
				tmp.setBounds(level.getCp().get(i).hely.x-10, level.getCp().get(i).hely.y-10, 20, 20);
				this.add(tmp);
			}
		}
	}
	
	
	
	
	
	//Generalt fv.-nyek.
	public Palya getLevel() {
		GlobalLogger.log("called: Engine -getLevel");
		return level;
	}
	public void setLevel(Palya level) {
		this.level = level;
		repaint();
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

	
	
	//gomb
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
		int idx = (int) ((JButton)ae.getSource()).getClientProperty( "index" ); //k
		level.getCp().get(idx).perform(null);
		((JButton)ae.getSource()).setText(Integer.toString(((Switcher)level.getCp().get(idx)).aktiv)); //kk
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
