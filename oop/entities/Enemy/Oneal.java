package oop.entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import oop.entities.Entity;
import oop.graphics.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img, double _speed) {
        super(xUnit, yUnit, img, _speed);
    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
                break;
        }
    }


}
