package oop.entities.Player;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import oop.Main;
import oop.entities.Enemy.Balloon;
import oop.entities.Blocks.*;
import oop.entities.Entity;
import oop.graphics.Sprite;

public class Bomber extends Character {
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private int temp =1;


    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        _sprite = Sprite.player_dead3;
    }

    @Override
    public void update() {
        keyboard(Main.getScene());

        if (_alive) {
            chooseSprite();
            img = _sprite.getFxImage();

            animate();

            calculateMove();
        }
        else {
            while (temp > 1) {
                _sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, 20);
                temp -=1;
            }
            animate();
            img = _sprite.getFxImage();
        }

    }

    @Override
    public boolean collide(Entity e) {
        if( e instanceof Blocks) return false;
        return true;
    }

    private void keyboard(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: W: up = true; break;
                    case DOWN: S: down = true; break;
                    case RIGHT: D:right = true; break;
                    case LEFT: A: left = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: W: up = false; break;
                    case DOWN: S: down = false; break;
                    case RIGHT: D:right = false; break;
                    case LEFT: A: left = false; break;
                }
            }
        });

    }

    private void calculateMove() {
        // TODO: xử lý nhận tín hiệu điều khiển hướng đi từ _input và gọi move() để thực hiện di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        int xa = 0, ya = 0;
        if(up) ya--;
        if(down) ya++;
        if(left) xa--;
        if(right) xa++;

        if(xa != 0 || ya != 0)  {
            move(xa * 2, ya * 2);
            _moving = true;
        } else {
            _moving = false;
        }
    }

    public boolean canMove(double x, double y) {
        // TODO: kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
        for (int c = 0; c < 4; c++) { //colision detection for each corner of the player
            double xt = ((x + x) + c % 2 * 9) / 16; //divide with tiles size to pass to tile coordinate
            double yt = ((y + y) + c / 2 * 10 - 13) / 16; //these values are the best from multiple tests

            Entity a = new Balloon(14,1, Sprite.doll_left1.getFxImage(),2);
            if( a instanceof Wall) {
                System.out.println("gap tuong");
                return false;
            }
        }

        return true;
        //return false;
    }

    public void move(double xa, double ya) {
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: nhớ cập nhật giá trị _direction sau khi di chuyển
        if(xa > 0) _direction = 1;
        if(xa < 0) _direction = 3;
        if(ya > 0) _direction = 2;
        if(ya < 0) _direction = 0;

        if(canMove(0, ya)) { //separate the moves for the player can slide when is colliding
            y += ya;
        }

        if(canMove(xa, 0)) {
            x += xa;
        }
    }

    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }
}
