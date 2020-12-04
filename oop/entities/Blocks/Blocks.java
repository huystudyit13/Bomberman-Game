package oop.entities.Blocks;

import javafx.scene.image.Image;
import oop.entities.Entity;

public abstract class Blocks extends Entity {
    public Blocks(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {

    }
}
