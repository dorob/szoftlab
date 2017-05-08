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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;


/**
 * A jatek futasaert felelos objektum.
 * @author Tsurhe
 *
 */
public class Engine extends JPanel implements ActionListener, MouseWheelListener, MouseListener{
	
	
	
	
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
	Timer count;
	public double zoom = 1d;
	BufferedImage imageBuffer; 
	
	
	
	/**
	 * Engine konstruktora
	 */
	public Engine(){
		super();
		GlobalLogger.log("called: Engine constructor");
		timer = new Timer(200, this);
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		toplista = new Scoreboard();
		toplista.load();
		this.addMouseWheelListener(this);
		this.addMouseListener(this);
		this.requestFocusInWindow();
		this.setVisible(true);
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
		//		repaint();
		//		invalidate();
				paintComponent(this.getGraphics());
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
	
	@Override
	public void paintComponent(Graphics g) {
		try {
           Graphics2D g2d = (Graphics2D) g.create();
   //        g2d.scale(zoom, zoom);
           super.paintComponent(g2d);
           applyQualityRenderingHints(g2d);
           imageBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
           
		   Graphics2D g2 = imageBuffer.createGraphics();
		   g2.setBackground(Color.LIGHT_GRAY);
		   applyQualityRenderingHints(g2);
           g2.setColor(Color.lightGray);
           g2.scale(zoom, zoom);
        //   g2.fillRect(0, 0, getWidth(), getHeight());
           g2.setColor(Color.BLUE);
           //sinek kirajozlasa
           for(ControlPoint c : this.level.getCp())
        	  for(Sin ss : c.getWays())
        		  g2.draw(ss.gorbe);
          
           //minden mozdny kirajz
           for(Mozdony m : this.level.getVehicles()){
        	   g2.setColor(Color.PINK);
	        	pos = m.pos;
	        	angle = m.angle;
		        AffineTransform at = new AffineTransform();
		        if(pos!=null){
		        	Rectangle bounds = box.getBounds();
		        	at.translate(pos.getX() - (bounds.width / 2), pos.getY() - (bounds.height / 2));
		        	at.rotate(angle, (bounds.width/2), (bounds.height/2));
				    g2.transform(at);       	
				    //tenyleges mozdonyrajzolas
		           	g2.fill(box);          
		           	//takaritas kovi elem rajzolasahoz
					g2.transform(at.createInverse());
					
		           	//vagonok rajzolas
		           	for(int i =0; i< m.getVagonok().size(); i++){
		           		if(i < m.pointsack.size()){
		           			g2.setColor(m.getVagonok().get(i).getColor());
		           			at = new AffineTransform();
		           			int j = m.anglesack.size()-i-1; // enelkul forditott sorrendbe rajzolta a vagonokat
		           			at.translate(m.pointsack.get(j).getX() - (bounds.width / 2), m.pointsack.get(j).getY() - (bounds.height / 2));
		           			at.rotate(m.anglesack.get(j), (bounds.width/2), (bounds.height/2));
		           			g2.transform(at);
		           			g2.fill(box);
		           			g2.transform(at.createInverse());
		           		}
		           	}
		        }
           }
           
           //controlpointok kirajzolas
		   for(ControlPoint cp : this.level.getCp()){
			   if(cp.toString().equals("Megallo")){
				   Megallo mm = (Megallo) cp; //hogy kinyerjuk a szinet
				   g2.setColor(mm.getColor());
				   g2.fill(mm.alak);
			   }
			   
			   else if(cp.toString().equals("Switcher")){
				   g2.setColor(new Color(((Switcher)cp).rgbb[0], ((Switcher)cp).rgbb[1], ((Switcher)cp).rgbb[2]));
				   g2.fill(cp.alak);
				   g2.setColor(Color.white);
				   g2.drawString(Integer.toString(((Switcher)cp).aktiv), cp.hely.x, cp.hely.y);
			   }
			   else if(cp.toString().equals("Alagut")){
				   g2.setColor(new Color(((Switcher)cp).rgbb[0], ((Switcher)cp).rgbb[1], ((Switcher)cp).rgbb[2]));
				   g2.fill(cp.alak);
				   g2.setColor(Color.white);
				   g2.drawString("A:"+Integer.toString(((Switcher)cp).aktiv), cp.hely.x, cp.hely.y);
			   }
		   }
		   
           g2d.drawImage(imageBuffer, 0, 0, this);
           Overpaint.bf=imageBuffer;
        //   		g2d.dispose();
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
/*
	private ArrayList<JButton> butt = new ArrayList<JButton>();
	public void initButtons(){
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
				butt.add(tmp);
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
				butt.add(tmp);
				this.add(tmp);
			}
		}
	}
	*/
	
	
	
	
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

	/**
	 * Jatek Inditasa
	 */
	public void work(){
		// this.nextLevel(); ha majd mar vannak palyak ser-ber
		timer.start();
		/**
		 * Anonim osztallyal ido szamitas
		 */
		count = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				time++;
				System.out.println(time);
			}
			
		});
		count.start();
		
	}
	
	/**
	 * A thread kezelese (mozgatas)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		run();
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		if(mwe.getWheelRotation() < 0)
			zoom+=0.1;
		else 
			zoom -= 0.1;
		if(zoom < 0)
			zoom = 0;	
		this.paintComponent(getGraphics());
	}

	@Override
	public void mouseClicked(MouseEvent ke) {
		
	}

	public boolean compareArrays(int[] array1, int[] array2) {
	    for (int i = 0; i < array1.length; i++)
	        if(array1[i] != array2[i])
	        	return false;
	    return true;
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent ke) {
		int rgb = imageBuffer.getRGB(ke.getX(), ke.getY());
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >>8 ) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
	
		if(red != 192 || green != 192 || blue != 192){
		int comp[] = {red, green, blue};
		for(ControlPoint cp : level.getCp()){
			if( cp.toString().equals("Switcher") || cp.toString().equals("Alagut")){
				if(compareArrays(((Switcher)cp).rgbb, comp)){
					try {
						cp.perform(null);
					} catch (CollideException e) {
						e.printStackTrace();
					}
				}
			}
		}
		}
		this.paintComponent(getGraphics());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
