package oop;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oop.entities.*;
import oop.entities.Enemy.Balloon;
import oop.entities.Enemy.Oneal;
import oop.entities.Player.Bomber;
import oop.graphics.Sprite;


public class Main extends Application  {
    private static final int mapWidth = 31;
    private static final int mapHeight = 13;
    public static final int WIDTH = mapWidth * 48;
    public static final int HEIGHT = mapHeight * 48;
    private GraphicsContext gc;
    private Canvas canvas;
    private TileMap tileMap;
    private int FPS = 30;
    private int targetTime = 1000 / FPS;
    private static Scene scene;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> bomb = new ArrayList<>();
    public static List<Entity> flame = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bomberman Game");

        // Tao canvas
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);
        // Them scene vao stage
        primaryStage.setScene(scene);
        primaryStage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        tileMap = new TileMap(stillObjects, entities);


    }

    public static Scene getScene(){
        return scene;
    }

    public void update() {
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        bomb.forEach(Entity::update);
        flame.forEach(Entity::update);

        for (int i = 0; i < entities.size(); i++) {
            Entity a = entities.get(i);
            if(a.isRemoved()) entities.remove(i);
        }

        for (int i = 0; i < stillObjects.size(); i++) {
            Entity a = stillObjects.get(i);
            if(a.isRemoved()) stillObjects.remove(i);
        }

        for (int i = 0; i < bomb.size(); i++) {
            Entity a = bomb.get(i);
            if(a.isRemoved()) bomb.remove(i);
        }

        for (int i = 0; i < flame.size(); i++) {
            Entity a = flame.get(i);
            if(a.isRemoved()) flame.remove(i);
        }

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomb.forEach(g -> g.render(gc));
        flame.forEach(g -> g.render(gc));
    }
}
