package oop;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import oop.entities.Entity;
import oop.entities.Player.Bomber;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    private static final int mapWidth = 31;
    private static final int mapHeight = 13;
    public static final int WIDTH = mapWidth * 48;
    public static final int HEIGHT = mapHeight * 48;
    private GraphicsContext gc;
    private Canvas canvas;
    private TileMap tileMap;
    private static Scene scene;

    public static double SPEEDNUM = 2;
    public static int BOMBNUM = 1;
    public static int FLAMENUM = 1;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> bomb = new ArrayList<>();
    public static List<Entity> flame = new ArrayList<>();
    public static List<Entity> item = new ArrayList<>();

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
        //Audio.backgr();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        //tao map
        tileMap = new TileMap(stillObjects, entities, item);
    }

    public static Scene getScene(){
        return scene;
    }

    public void update() {
        bomb.forEach(Entity::update);
        entities.forEach(Entity::update);
        flame.forEach(Entity::update);
        item.forEach(Entity::update);

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
        item.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomb.forEach(g -> g.render(gc));
        flame.forEach(g -> g.render(gc));
    }

    public static Bomber getBomber() {

        for (int i = 0; i < entities.size(); i++) {
            Entity a = entities.get(i);
            if(a instanceof Bomber) return (Bomber) a;
        }
        return null;
    }


}
