package oop.entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.Audio;
import oop.entities.*;
import oop.entities.Bomb.Bomb;
import oop.entities.Player.Bomber;
import oop.graphics.Sprite;
import oop.Main;

public abstract class Enemy extends AnimatedEntity {
    protected double speed;
    protected final double MAX_STEPS;
    protected final double rest;
    protected double _steps;
    protected boolean _alive = true;
    protected boolean _moving = false;



    protected int _direction = -1;

    public Enemy(int xUnit, int yUnit, Image img, double _speed) {
        super(xUnit, yUnit, img);

        speed = _speed;

        MAX_STEPS = 180;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
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
            //img = _sprite.getFxImage();

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
        for (Entity e : Main.entities) {
            if ((e instanceof Wall && collide(e,x,y)) || (e instanceof Brick && collide(e,x,y))) return false;
            else if (e instanceof Bomber && collide(e,x,y)) {
                Audio.bomberdie();
                e.set_alive(false);
                return false;
            }
        }
        for (Entity e : Main.bomb) {
            if ((e instanceof Bomb && collide(e,x,y))) return false;
        }
        return true;
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