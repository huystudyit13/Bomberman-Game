package oop.entities.Player;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import oop.Main;
import oop.RowCol2D;
import oop.entities.*;
import oop.entities.Bomb.Bomb;
import oop.entities.Enemy.Balloon;
import oop.entities.Item.BombPoweredUp;
import oop.entities.Item.FlamePoweredUp;
import oop.entities.Item.Item;
import oop.entities.Item.SpeedPoweredUp;
import oop.graphics.Sprite;

public class Bomber extends Character {
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean placeBomb = false;
    private boolean temp = true;

    protected BombPoweredUp bombitem;

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        _sprite = Sprite.player_dead3;
    }

    @Override
    public void update() {
        //System.out.println("bombnumber : " + Main.BOMBNUM + "\tspeed : " + Main.SPEEDNUM + "\tflame : " + Main.FLAMENUM);
        //System.out.println(x + "\t" + y);
        keyboard(Main.getScene());

        if (_alive) {
            calculateMove();
            placeBomb(x,y);
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if (_alive) {
            chooseSprite();
            img = _sprite.getFxImage();

            animate();

        }
        else {
            if(temp) {
                _sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, 20);
                animate();
                img = _sprite.getFxImage();
                temp = false;
            }

        }

        gc.drawImage(img,x,y);
    }

    public boolean collide(Entity e, double a , double b) {
        double leftA = x + a;                             double leftB = e.getX();
        double rightA = x + Sprite.SCALED_SIZE - 10 + a;  double rightB = e.getX() + Sprite.SCALED_SIZE;
        double topA = y + b ;                             double topB = e.getY();
        double bottomA = y + Sprite.SCALED_SIZE + b - 10; double bottomB = e.getY() + Sprite.SCALED_SIZE;
        if (( bottomA > topB ) && ( topA < bottomB ) && ( rightA > leftB ) && ( leftA < rightB )  )
            {
                return true;
            }
        return false;
    }

    private void keyboard(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: up = true; break;
                    case DOWN: down = true; break;
                    case RIGHT: right = true; break;
                    case LEFT: left = true; break;
                    case W: up = true; break;
                    case A: left = true; break;
                    case S: down = true; break;
                    case D: right = true; break;

                    case SPACE: placeBomb = true; break;
                    case ENTER: Portal.isClearStage = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: up = false; break;
                    case DOWN: down = false; break;
                    case RIGHT: right = false; break;
                    case LEFT: left = false; break;
                    case W: up = false; break;
                    case A: left = false; break;
                    case S: down = false; break;
                    case D: right = false; break;

                    case SPACE: placeBomb = false; break;
                }
            }
        });

    }

    private void calculateMove() {
        // TODO: xử lý nhận tín hiệu điều khiển hướng đi từ _input và gọi move() để thực hiện di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        double xa = 0, ya = 0;
        if(up) ya--;
        if(down) ya++;
        if(left) xa--;
        if(right) xa++;

        if(xa != 0 || ya != 0)  {
            move(xa * Main.SPEEDNUM, ya * Main.SPEEDNUM);
            _moving = true;
        } else {
            _moving = false;
        }
    }

    public boolean canMove(double x, double y) {
        for (Entity e : Main.entities) {
            if ((e instanceof Wall && collide(e,x,y)) || (e instanceof Brick && collide(e,x,y))) {
                return false;
            }
            if((e instanceof Balloon) && collide(e,x,y)) {
                _alive = false;
                return true;
            }
            if((e instanceof Portal) && collide(e,x,y) && !Portal.isClearStage) {
                return false;
            }
        }
        for (int i = 0; i < Main.item.size(); i++) {
            Entity e = Main.item.get(i);
            if((e instanceof SpeedPoweredUp) && collide(e,x,y)) {
                SpeedPoweredUp._active = true;
                Main.SPEEDNUM += 0.5;
                Main.item.remove(i);
                return true;
            }
            else if ((e instanceof BombPoweredUp) && collide(e,x,y)) {
                BombPoweredUp._active = true;
                Main.BOMBNUM++;
                Main.item.remove(i);
                return true;
            }
            else if((e instanceof FlamePoweredUp) && collide(e,x,y)) {
                FlamePoweredUp._active = true;
                Main.FLAMENUM++;
                Main.item.remove(i);
                return true;
            }

        }

        return true;
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

    public void placeBomb(double x, double y) {
        if (Main.BOMBNUM > 0) {
            if (placeBomb) {
                Entity e = new Bomb((int) (x + 24) / 48,(int) (y + 24) / 48, Sprite.bomb.getFxImage());
                Main.bomb.add(e);
                Bomb.decreaseBombNumber();
                placeBomb = false;
            }
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
