import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane = new AnchorPane();

        Image bgimage = new Image("/yasumo.png",400,400,false,true);
        BackgroundImage bg = new BackgroundImage(bgimage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);

        Text game = new Text("BOMBERMAN GAME");
        ButtonPress but1 = new ButtonPress("Play");
        ButtonPress but2 = new ButtonPress("Highscore");
        ButtonPress but3 = new ButtonPress("Exit");

        game.setLayoutX(120);
        game.setLayoutY(100);
        but1.setLayoutX(100);
        but1.setLayoutY(200);
        but2.setLayoutX(100);
        but2.setLayoutY(250);
        but3.setLayoutX(100);
        but3.setLayoutY(300);

        pane.getChildren().addAll(but1, but2, but3, game);
        pane.setBackground(new Background(bg));
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}