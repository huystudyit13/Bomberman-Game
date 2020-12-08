package oop;

import javafx.application.Application;
import javafx.stage.Stage;

import oop.menu.MainMenu;


public class Main extends Application  {
    private BombermanGame bombermanGame;
    private MainMenu mainMenu;


    @Override
    public void start(Stage primaryStage) throws Exception {
        /*mainMenu = new MainMenu();
        mainMenu.start(primaryStage);*/

        bombermanGame = new BombermanGame();
        bombermanGame.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}