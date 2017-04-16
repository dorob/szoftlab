package sample;

import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import java.lang.Runnable;
import java.lang.Thread;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * jelenleg annyit tud h 2 mozdony megy egymasnak es konzolba kiirja h utk ha utkoznek ez megy vegtelensegig odavissza
 * @author DANI
 *
 */


public class lkjh extends Application {

  public Rectangle mozdony = new Rectangle(20, 50, 60,20);
  public Rectangle mozdony2 = new Rectangle(500, 600, 60,20);

  public PathTransition mozdony_mozditas = new PathTransition();
  public PathTransition mozdony2_mozditas = new PathTransition();
  public static int nm = 0;
  
    @Override
    public void start(Stage primaryStage) throws Exception{
        mozdony.setFill(Color.AQUA);
        mozdony2.setFill(Color.ORANGE);

        
    	final double wid = 800;
        final double heig = 800;
    	
        final double moz1Rad = 10;
        final double moz2Rad = 20;
    	
    	
    ///////////////////////////////////	
    	Pane root = new Pane();
  /*  	
    	public Rectangle mozdony = new Rectangle(20,20);
    	public Rectangle mozdony2 = new Rectangle(60,20);
  */
    	//moz1 starts from 20, 50
    	Circle moz1 = new Circle(20, 50, moz1Rad, Color.RED);
    	//moz2 starts from 500, 600
  //  	Circle moz2 = new Circle(500, 600, moz2Rad, Color.GREEN);
    	//curve with controlpoint on 600, 200
    	CubicCurveTo sin = new CubicCurveTo(20, 50, 600, 200, 500, 600);
    	
    	Path path = new Path();
    	path.getElements().add(new MoveTo(20, 50));
    	path.getElements().add(sin);
    	PathTransition moz1Anim = new PathTransition(Duration.seconds(8),mozdony);
    	moz1Anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
    	moz1Anim.setPath(path);
    	moz1Anim.setNode(mozdony);
    	moz1Anim.setAutoReverse(true);
    	moz1Anim.setCycleCount(Animation.INDEFINITE);
    	moz1Anim.play();
    	
        Scene palya = new Scene(root,wid,heig);
        
//eza resz meg nincs
        palya.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                Path tmp = new Path();
                tmp.getElements().add(new MoveTo(450, 500));
                tmp.getElements().add(new CubicCurveTo(450,500,400,400,600,550));

                root.getChildren().add(tmp);
                mozdony_mozditas.setPath(null);
                mozdony_mozditas.setPath(tmp);
                mozdony_mozditas.setNode(mozdony);
                mozdony_mozditas.setDuration(Duration.seconds(4));
                mozdony_mozditas.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent arg0) {
                        mozdony_mozditas.play();
                    }
                });
            }
        });
        
      Circle moz2 = new Circle(500, 600, moz2Rad, Color.GREEN);
    	//curve with controlpoint on 600, 200
    	CubicCurveTo sin2 = new CubicCurveTo(500, 600, 600, 200, 20, 50);
    	
    	Path path2 = new Path();
    	path2.getElements().add(new MoveTo(500, 600));
    	path2.getElements().add(sin2);
    	PathTransition moz2Anim = new PathTransition(Duration.seconds(8),mozdony2);
    	moz2Anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
    	moz2Anim.setPath(path2);
    	moz2Anim.setNode(mozdony2);
    	moz2Anim.setAutoReverse(true);
    	moz2Anim.setCycleCount(Animation.INDEFINITE);
    	
        
        
//true at collision
        BooleanBinding coll = Bindings.createBooleanBinding(() -> {
        	Point2D moz1Poz = mozdony.localToParent(20, 50); //starts from 20.20
        	Point2D moz2Poz = mozdony2.localToParent(500, 600); //starts from 500 .600
        	return (moz1Poz.distance(moz2Poz) < 50 ); //width is 20
        	
        }, mozdony.translateXProperty(), mozdony.translateYProperty());
       
        coll.addListener((obs, wasColl, isNowColl) -> {
        	
        	if(isNowColl){
        		
        		System.out.println("meow" + " " +nm);
        		nm++;
        	}
        });
        moz2Anim.play();
        
        root.getChildren().add(path);
        root.getChildren().add(mozdony);
        root.getChildren().add(mozdony2);
     
        
        primaryStage.setTitle("Vonat");
        primaryStage.setScene(palya);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
