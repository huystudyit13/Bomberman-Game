package oop.entities.Enemy;

import javafx.scene.image.Image;
import oop.entities.Player.Bomber;
import oop.graphics.Sprite;

public class Doll extends Enemy {
    Bomber bomber;
    public Doll(int xUnit, int yUnit, Image img, double _speed, Bomber _bomber) {
        super(xUnit, yUnit, img, _speed);
        _direction = random.nextInt(4);
        bomber= _bomber;
    }

    @Override
    protected void calculateMove() {
        double xa = 0, ya = 0;
        double xPlayer = bomber.getCol() * 48 , yPlayer = bomber.getRow() * 48;

        if (xPlayer < x) _direction = 1;
        else if (xPlayer > x) _direction = 3;
        else if (yPlayer < y) _direction = 0;
        else if (yPlayer > y) _direction = 2;

        if(_direction == 0) ya--; // len tren
        if(_direction == 2) ya++; // xuong
        if(_direction == 1) xa--; // phai
        if(_direction == 3) xa++; // trai

        if(canMove(xa, ya)) {
            _steps -= 1 ;
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
                _sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, _animate, 20);
                img = _sprite.getFxImage();
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, _animate, 20);
                img = _sprite.getFxImage();
                break;
        }
    }
}
