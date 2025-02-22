import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// An alternative implementation of Example 3,
//    using the Timeline, KeyFrame, and Duration classes.

// Animation of Earth rotating around the sun. (Hello, world!)
public class CirclulurMotion extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle( "Timeline Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 852, 480 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image earth = new Image( "Poly.png" );
        Image sun   = new Image( "morris.png" );
        Image space = new Image( "background.jpg" );
        Image space2 = new Image( "axe.jpg" );
        Image space3 = new Image( "switch.jpg" );


        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                        double x = 232 + 128 * Math.cos(t);
                        double y = 232 + 128 * Math.sin(t);

                        // Clear the canvas
                        gc.clearRect(0, 0, 852,480);

                        // background image clears canvas
                        gc.drawImage( space, 0, 0 );
                        gc.drawImage( space2, x + y + 50, x );
                        gc.drawImage( space3, y + x, y );
                        gc.drawImage( earth, x, y );
                        gc.drawImage( sun, 200 + y, x );

                    }
                });

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();

        theStage.show();
    }
}