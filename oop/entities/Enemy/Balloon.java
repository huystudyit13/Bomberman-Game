package oop.entities.Enemy;

import javafx.scene.image.Image;

import oop.graphics.Sprite;


public class Balloon extends Enemy {
    public Balloon(int xUnit, int yUnit, Image img, double _speed) {
        super(xUnit, yUnit, img, _speed);

        _direction = 1;
    }

    @Override
    public void calculateMove() {
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