package oop.entities.Bomb;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.Main;
import oop.entities.AnimatedEntity;
import oop.entities.Entity;
import oop.graphics.Sprite;

public class Bomb extends AnimatedEntity {
    public double _timeToExplode = 1;
    public int _timeAfter = 40;

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

    protected void explode() {
        _exploded = true;

        Entity eR = new Flame((int)(x+48)/48,(int) y/48, Sprite.explosion_horizontal_right_last2.getFxImage());
        Entity eL = new Flame((int)(x-48)/48,(int) (y)/48, Sprite.explosion_horizontal_left_last2.getFxImage());
        Entity eU = new Flame((int)(x)/48,(int) (y-48)/48, Sprite.explosion_vertical_top_last2.getFxImage());
        Entity eD = new Flame((int)x/48,(int) (y+48)/48, Sprite.explosion_vertical_down_last2.getFxImage());


        Main.flame.add(eR);
        Main.flame.add(eL);
        Main.flame.add(eU);
        Main.flame.add(eD);
    }

    @Override
    public boolean collide(Entity e, double a, double b) {
        return true;
    }
}
