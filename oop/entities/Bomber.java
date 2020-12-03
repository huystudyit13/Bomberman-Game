package oop.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oop.Main;


public class Bomber extends Character {
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        move(Main.getScene());

    }

    private void move(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: W: up = true; break;
                    case DOWN: S: down = true; break;
                    case RIGHT: D:right = true; break;
                    case LEFT: A: left = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: W: up = false; break;
                    case DOWN: S: down = false; break;
                    case RIGHT: D:right = false; break;
                    case LEFT: A: left = false; break;
                }
            }
        });

    }

}
