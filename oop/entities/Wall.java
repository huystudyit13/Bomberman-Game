package oop.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }



    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e, double a , double b) {
        return false;
    }
}
