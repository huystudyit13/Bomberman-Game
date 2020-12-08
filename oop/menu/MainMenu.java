package oop.menu;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import oop.menu.ButtonMenu;

import java.io.IOException;


public class MainMenu extends Application {
    private static Scene scene;
    private AnchorPane pane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bomberman Game");

        pane = new AnchorPane();


        Image bgimage = new Image("/oop/res/buttonmenu/yasumo.png",400,400,false,true);
        BackgroundImage bg = new BackgroundImage(bgimage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);

        ButtonMenu t = new ButtonMenu("Play");
        ButtonMenu t1 = new ButtonMenu("Highscore");
        ButtonMenu t2 = new ButtonMenu("Exit");

        t.setLayoutX(100);
        t.setLayoutY(200);
        t1.setLayoutX(100);
        t1.setLayoutY(250);
        t2.setLayoutX(100);
        t2.setLayoutY(300);

        pane.getChildren().addAll(t,t1,t2);
        pane.setBackground(new Background(bg));
        scene = new Scene(pane, 400, 400);

        primaryStage.setScene(scene);
        if (primaryStage != null) {
            System.out.println("ok");
        }

        primaryStage.show();

    }
}
