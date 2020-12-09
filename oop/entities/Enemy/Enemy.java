package oop.entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.audio.Audio;
import oop.BombermanGame;
import oop.entities.*;
import oop.entities.Bomb.Bomb;
import oop.entities.Player.Bomber;
import oop.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends AnimatedEntity {
    protected double speed;
    protected final double MAX_STEPS;
    protected double _steps;
    protected boolean _alive = true;
    protected boolean _moving = false;
    protected Random random = new Random();

    protected int _direction = -1;

    public Enemy(int xUnit, int yUnit, Image img, double _speed) {
        super(xUnit, yUnit, img);

        speed = _speed;

        MAX_STEPS = 180;
        _steps = MAX_STEPS;
    }

    @Override
    public void update() {
        if(_alive) {
            calculateMove();
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if(_alive) {
            chooseSprite();

            animate();
        }
        else {
            _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 20);
        }

        gc.drawImage(img,x,y);
    }

    protected abstract void calculateMove();
    protected abstract void chooseSprite();

    public void move(double xa, double ya) {
        if(!_alive) return;
        y += ya;
        x += xa;
    }

    public boolean canMove(double x, double y) {
        for (Entity e : BombermanGame.entities) {
            if ((e instanceof Wall && collide(e,x,y)) || (e instanceof Brick && collide(e,x,y))) return false;
            else if (e instanceof Bomber && collide(e,0,0)) {
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