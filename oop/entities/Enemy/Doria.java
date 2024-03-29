package oop.entities.Enemy;

import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.audio.Audio;
import oop.entities.Bomb.Bomb;
import oop.entities.Brick;
import oop.entities.Entity;
import oop.entities.Player.Bomber;
import oop.entities.Wall;
import oop.graphics.Sprite;

public class Doria extends Enemy{
    Bomber bomber;
    public Doria(int xUnit, int yUnit, Image img, double _speed, Bomber _bomber) {
        super(xUnit, yUnit, img, _speed);
        _direction = 1;
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
                _sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, _animate, 20);
                img = _sprite.getFxImage();
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, _animate, 20);
                img = _sprite.getFxImage();
                break;
        }
    }

    public boolean canMove(double x, double y) {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Wall && collide(e,x,y)) return false;
            else if (e instanceof Bomber && collide(e,x,y)) {
                Audio.bomberdie();
                e.set_alive(false);
                return false;
            }
        }
        for (Entity e : BombermanGame.bomb) {
            if ((e instanceof Bomb && collide(e,x,y))) return false;
        }
        return true;
    }
}
