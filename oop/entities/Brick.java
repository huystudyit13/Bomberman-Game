package oop.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends Entity {

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }

}
