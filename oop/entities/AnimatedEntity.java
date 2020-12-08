package oop.entities;

import javafx.scene.image.Image;
import oop.graphics.Sprite;

public abstract class AnimatedEntity extends Entity {
    protected int _animate = 0;
    protected final int MAX_ANIMATE = 4000;

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void animate() {
        if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
    }

    public boolean collide(Entity e, double a , double b) {
        double leftA = x + a;                        double leftB = e.getX();
        double rightA = x + Sprite.SCALED_SIZE + a;  double rightB = e.getX() + Sprite.SCALED_SIZE;
        double topA = y + b ;                        double topB = e.getY();
        double bottomA = y + Sprite.SCALED_SIZE + b; double bottomB = e.getY() + Sprite.SCALED_SIZE;
        if (( bottomA > topB ) && ( topA < bottomB ) && ( rightA > leftB ) && ( leftA < rightB )  )
        {
            return true;
        }
        return false;
    }
}
