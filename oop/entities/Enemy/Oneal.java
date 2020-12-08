package oop.entities.Enemy;

import oop.graphics.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img, double _speed) {
        super(xUnit, yUnit, img, _speed);
        _direction = 1;
    }

    @Override
    protected void calculateMove() {
        double xa = 0, ya = 0;

        if(_steps <= 0){
            _direction = random.nextInt(4);
            _steps = MAX_STEPS;
        }

        if(_direction == 0) ya--; // len tren
        if(_direction == 2) ya++; // xuong
        if(_direction == 3) xa--; // phai
        if(_direction == 1) xa++; // trai

        if(canMove(xa, ya)) {
            _steps -= 1;
            move(xa * speed, ya * speed);
            _moving = true;
        } else {
            _steps = 0;
            _moving = false;
        }
    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
                img = _sprite.getFxImage();
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
                img = _sprite.getFxImage();
                break;
        }
    }


}