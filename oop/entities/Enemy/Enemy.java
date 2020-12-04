package oop.entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.entities.Entity;
import oop.entities.ai.AI;
import oop.entities.AnimatedEntity;
import oop.graphics.Sprite;
import oop.Main;

public abstract class Enemy extends AnimatedEntity {
    protected double speed;
    protected final double MAX_STEPS;
    protected final double rest;
    protected double _steps;
    protected boolean _alive = true;
    protected boolean _moving = false;

    protected AI _ai;

    protected int _direction = -1;

    public Enemy(int xUnit, int yUnit, Image img, double _speed) {
        super(xUnit, yUnit, img);

        speed = _speed;

        MAX_STEPS = 16 / speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        _steps = MAX_STEPS;
    }

    @Override
    public void update() {

        if(_alive) {
            chooseSprite();
            img = _sprite.getFxImage();

            animate();
            calculateMove();
        }
        else {
            _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 20);
        }
    }


    public void calculateMove() {
        // TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
        // TODO: sử dụng move() để di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        int xa = 0, ya = 0;
        if(_steps <= 0){
            _direction = _ai.calculateDirection();
            _steps = MAX_STEPS;
        }

        if(_direction == 0) ya--;
        if(_direction == 2) ya++;
        if(_direction == 3) xa--;
        if(_direction == 1) xa++;

        if(canMove(xa, ya)) {
            _steps -= 1 + rest;
            move(xa * speed, ya * speed);
            _moving = true;
        } else {
            _steps = 0;
            _moving = false;
        }
    }

    public void move(double xa, double ya) {
        if(!_alive) return;
        y += ya;
        x += xa;
    }

    public boolean canMove(double x, double y) {
        double xr = x, yr = y - 16; //subtract y to get more accurate results

        //the thing is, subract 15 to 16 (sprite size), so if we add 1 tile we get the next pixel tile with this
        //we avoid the shaking inside tiles with the help of steps
        if(_direction == 0) { yr += _sprite.getSize() -1 ; xr += _sprite.getSize()/2; }
        if(_direction == 1) {yr += _sprite.getSize()/2; xr += 1;}
        if(_direction == 2) { xr += _sprite.getSize()/2; yr += 1;}
        if(_direction == 3) { xr += _sprite.getSize() -1; yr += _sprite.getSize()/2;}

        int xx = (int) ((xr / 16) + (int)x);
        int yy = (int) ((yr / 16) +(int)y);

        //Entity a = _board.getEntity(xx, yy, this); //entity of the position we want to go

        return true;
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }

    protected abstract void chooseSprite();
}
