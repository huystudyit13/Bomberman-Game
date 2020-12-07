package oop.entities.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.entities.Entity;

public abstract class Item extends Entity {
    public static boolean _active = false;

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(_active) {
            remove();
            _active = false;
        }
    }

    @Override
    public void render(GraphicsContext gc){
        gc.drawImage(img,x,y);
    }


}
