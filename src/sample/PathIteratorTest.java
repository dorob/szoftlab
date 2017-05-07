package sample;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;
 
public class PathIteratorTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FlatteningPathIterator test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Shape s=new Ellipse2D.Float(10,10,200,100);
        Area a=new Area(new Ellipse2D.Float(150,20,100,200));
        a.add(new Area(s));
        PaintPanel app = new PaintPanel(a);
        JScrollPane scroll = new JScrollPane(app);
        frame.getContentPane().add(scroll);
 
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    static class PaintPanel extends JPanel {
        private final Rectangle box;
        
    	FlatteningPathIterator iter;
        Shape shape;
        ArrayList<Point2D> points;
        private double angle;
        int index=0;
        private Point2D pos;

        double formerangle;
        private Point2D formerpos;
        boolean vagon;
        double regiangle;
        private Point2D regipos;
        
        public PaintPanel(Shape s) {
            this.shape=s;
            box = new Rectangle(0, 0, 20, 10);
            iter=new FlatteningPathIterator(s.getPathIterator(new AffineTransform()), 1);
            points=new ArrayList<Point2D>();
            float[] coords=new float[6];
            while (!iter.isDone()) {
                iter.currentSegment(coords);
                double x=coords[0];
                double y=coords[1];
                points.add(new Point2D.Double(x, y));
                iter.next();
            }

            pos = points.get(0);
            
            Timer timer=new Timer(80, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	if(formerpos!=null){
                		regiangle = formerangle;
                		regipos=formerpos;
                	}
                	if(index < points.size()-1){
                		formerangle = angle;
                		angle = angleTo(pos, points.get(index+1));
                	}
                	
                		
                	index++;
                    if (index>=points.size()) {
                        index=0;
                    }
                    formerpos = pos;
                    pos=points.get(index);
                    
                    if(regipos!=null){
                    	vagon=true;
                    }
                    repaint();
                }
            });
            timer.start();
        }

        
        protected double angleTo(Point2D from, Point2D to) {
            double angle = Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
            return angle;
        }
        
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            applyQualityRenderingHints(g2d);
            g2d.setColor(Color.blue);
            int x = (getWidth() - shape.getBounds().width) / 2; //ovalgenyok kozee
            int y = (getHeight() - shape.getBounds().height) / 2;
            g2d.translate(x, y);
            g2d.draw(shape);
 
            AffineTransform at = new AffineTransform();
            if(pos!=null){
            	Rectangle bounds = box.getBounds();
            	at.translate(pos.getX() - (bounds.width / 2), pos.getY() - (bounds.height / 2));
            	at.rotate(angle, (bounds.width/2), (bounds.height/2));
            	
            	Path2D player = new Path2D.Double(box, at);
            	
            //	g2d.translate(pos.getX() - (bounds.width / 2), pos.getY() - (bounds.height / 2));
                g2d.setColor(Color.RED);
                g2d.draw(player);
                try {
					g2d.transform(at.createInverse());
				} catch (NoninvertibleTransformException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                g2d.draw(player);
                if(vagon){
                	at.translate(regipos.getX() - (bounds.width / 2), regipos.getY() - (bounds.height / 2));
                	at.rotate(regiangle, (bounds.width/2), (bounds.height/2));
                	
                	Path2D player2 = new Path2D.Double(box, at);
                	
               
                    g2d.setColor(Color.BLACK);
                    g2d.draw(player2);
                }
            }
        }
    }
        public static void applyQualityRenderingHints(Graphics2D g2d) {

            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        }
    }

