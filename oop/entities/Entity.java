package oop.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected Sprite _sprite;

    protected boolean _removed = false;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    protected Entity() {
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public void remove() {
        _removed = true;
    }

    public abstract void update();

    public abstract void render(GraphicsContext gc);

}
