package oop.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.Audio;
import oop.Main;
import oop.entities.AnimatedEntity;
import oop.entities.Brick;
import oop.entities.Entity;
import oop.entities.Wall;
import oop.graphics.Sprite;

import javax.swing.*;

public class Bomb extends AnimatedEntity {
    public double _timeToExplode = 120;
    public int _timeAfter = 40;
    private boolean up = true;
    private boolean down = true;
    private boolean right = true;
    private boolean left = true;

    public boolean _exploded = false;
    public boolean kt = false;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(_timeToExplode > 0)
            _timeToExplode--;
        else {
            if(!_exploded) {
                explode();
                increaseBombNumber();
                Audio.bombexplode();
            }
            else ;

            if(_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        animate();

        if(_exploded) {
            _sprite =  Sprite.bomb_exploded2;
            if (kt) {
                //remove();
            }
            kt = true;
        } else
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);

        img = _sprite.getFxImage();
        gc.drawImage(img,x,y);
    }

    public void explode() {
        _exploded = true;
            if (checkRight()) {
                Entity eR = new Flame((int) (x + Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last2.getFxImage());
                Main.flame.add(eR);
            }
            if (checkDown()) {
                Entity eD = new Flame((int) x / Sprite.SCALED_SIZE, (int) (y + Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last2.getFxImage());
                Main.flame.add(eD);
            }
            if (checkLeft()) {
                Entity eL = new Flame((int) (x - Sprite.SCALED_SIZE) / 48, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last2.getFxImage());
                Main.flame.add(eL);
            }
            if (checkUp()) {
                Entity eU = new Flame((int) (x) / Sprite.SCALED_SIZE, (int) (y - Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last2.getFxImage());
                Main.flame.add(eU);
            }
    }

    public boolean checkUp() {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,0,-(Sprite.SCALED_SIZE))) return false;
        }
        return true;
    }

    public boolean checkDown() {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,0,Sprite.SCALED_SIZE)) return false;
        }
        return true;
    }

    public boolean checkRight() {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,Sprite.SCALED_SIZE,0)) return false;
        }
        return true;
    }

    public boolean checkLeft() {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,-(Sprite.SCALED_SIZE),0)) return false;
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

    public static void decreaseBombNumber() {
        Main.BOMBNUM--;
    }
    public void increaseBombNumber() {
        Main.BOMBNUM++;
    }
}