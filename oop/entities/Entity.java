package oop.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.RowCol2D;
import oop.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;

    protected Image img;

    protected Sprite _sprite;

    protected boolean _removed = false;

    protected boolean _alive = true;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( double xUnit, double yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    protected Entity() {
    }

    public double getY() {
        return y;
    }

    public double getX() {
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

    public int getXTile() {
        return RowCol2D.pixelToTile(x + 1);
    }
    public int getYTile() {
        return RowCol2D.pixelToTile(y + 1);
    }

    public void set_alive(boolean _alive) {
        this._alive = _alive;
    }

    public boolean is_alive() {
        return _alive;
    }
}