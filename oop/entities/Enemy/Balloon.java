package oop.entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.entities.Entity;
import oop.graphics.Sprite;
import oop.entities.ai.AILow;

public class Balloon extends Enemy {
    public Balloon(int xUnit, int yUnit, Image img, double _speed) {
        super(xUnit, yUnit, img, _speed);

        //_sprite = Sprite.balloom_left1;
        _ai = new AILow();
        _direction = _ai.calculateDirection();
    }

    @Override
    public void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 20);
                img = _sprite.getFxImage();
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 20);
                img = _sprite.getFxImage();
                break;
        }
    }

}
