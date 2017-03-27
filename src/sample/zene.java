package sample;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class zene extends Application {
	  public Rectangle mozdony = new Rectangle(60,20);
	  public Rectangle mozdony2 = new Rectangle(60,20);

	  public PathTransition mozdony_mozditas = new PathTransition();
	  public PathTransition mozdony2_mozditas = new PathTransition();
    @Override
    public void start(Stage primaryStage)
    {
        //Add a scene
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);

        Media pick = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "aa.mp3");
        MediaPlayer player = new MediaPlayer(pick);
        player.play();

        //Add a mediaView, to display the media. Its necessary !
        //This mediaView is added to a Pane
        MediaView mediaView = new MediaView(player);
        ((Group)scene.getRoot()).getChildren().add(mediaView);

        mozdony2.setFill(Color.YELLOW); //új

        Point2D p1 = new Point2D(500,300);
        Point2D p2 = new Point2D(300,270);
        Point2D p3 = new Point2D(100,210);

        Polygon poly = new Polygon();
        poly.getPoints().addAll(new Double[]{
                20.0,20.0,
                p1.getX(), p1.getY(),
                p2.getX(), p2.getY(),
                p3.getX(), p3.getY() });
        poly.setFill(Color.BLUE);

        CubicCurveTo sin = new CubicCurveTo(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY());
        CubicCurveTo sin2 = new CubicCurveTo(60,210,160,400,450,500);

        CubicCurveTo mozdony2_sin = new CubicCurveTo(160,400,60,210,p3.getX(),p3.getY());
        CubicCurveTo mozdony2_sin2 = new CubicCurveTo(p2.getX(),p2.getY(),p1.getX(),p1.getY(),20,20);

        Rectangle megallo = new Rectangle(335,400,40,60);
        megallo.setRotate(-62);
        megallo.setFill(Color.RED);

        Path mozdony_utvonal = new Path();
        mozdony_utvonal.getElements().add(new MoveTo(20,20));
        mozdony_utvonal.getElements().add(sin);
        mozdony_utvonal.getElements().add(sin2);


        Path mozdony2_utvonal = new Path();
        mozdony2_utvonal.getElements().add(new MoveTo(450,500));
        mozdony2_utvonal.getElements().add(mozdony2_sin);
        mozdony2_utvonal.getElements().add(mozdony2_sin2);


        mozdony2_mozditas.setDuration(Duration.seconds(6));
        mozdony2_mozditas.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        mozdony2_mozditas.setPath(mozdony2_utvonal);
        mozdony2_mozditas.setNode(mozdony2);
        mozdony2_mozditas.setAutoReverse(true);
        mozdony_mozditas.setDuration(Duration.seconds(6));
        mozdony_mozditas.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        mozdony_mozditas.setPath(mozdony_utvonal);
        mozdony_mozditas.setNode(mozdony);
        mozdony2_mozditas.play();
        mozdony_mozditas.play();


        scene.setOnKeyPressed(e -> {
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
//true at collision
        BooleanBinding coll = Bindings.createBooleanBinding(() -> {
        	Point2D moz1Poz = mozdony.localToParent(20, 20); //starts from 20.20
        	Point2D moz2Poz = mozdony2.localToParent(450, 500); //starts from 450 .500
        	return (moz1Poz.distance(moz2Poz) < 20 ); //width is 20
        	
        }, mozdony.translateXProperty(), mozdony.translateYProperty());

        coll.addListener((obs, wasColl, isNowColl) -> {
        	if(isNowColl){
        		System.out.println("utk");
        	}
        });

        root.getChildren().add(poly);
        root.getChildren().add(mozdony_utvonal);
        root.getChildren().add(mozdony2_utvonal);
        root.getChildren().add(mozdony);
        root.getChildren().add(mozdony2);
        root.getChildren().add(megallo);
        
        

        primaryStage.setTitle("Vonat");
        
        
        
        
        
        
        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
         launch(args);
    }
}