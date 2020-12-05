package oop.entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.entities.*;
import oop.entities.ai.AI;
import oop.graphics.Sprite;
import oop.Main;

import java.util.Random;

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
            //img = _sprite.getFxImage();

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
        Random rd = new Random();
        if(_steps <= 0){
            _direction = rd.nextInt(4);
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
        for (Entity e : Main.entities) {
            if ((e instanceof Wall && collide(e,x,y)) || (e instanceof Brick && collide(e,x,y))) return false;
        }
        for(Entity e : Main.stillObjects) {
            if(e instanceof Grass) return true;
        }
		return true;
	}

    @Override
    public boolean collide(Entity e, double a , double b) {
		double leftA = x + a;   double leftB = e.getX();
		double rightA = x + Sprite.SCALED_SIZE + a; double rightB = e.getX() + Sprite.SCALED_SIZE;
		double topA = y + b;     double topB = e.getY();
		double bottomA = y + Sprite.SCALED_SIZE + b;  double bottomB = e.getY() + Sprite.SCALED_SIZE;
		if (( bottomA > topB ) && ( topA < bottomB ) && ( rightA > leftB ) && ( leftA < rightB )  )
		{
			return true;
		}
		return false;
	}

    protected abstract void chooseSprite();
}