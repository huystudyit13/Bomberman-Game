package oop.entities.Player;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import oop.audio.Audio;
import oop.BombermanGame;
import oop.Main;
import oop.entities.*;
import oop.entities.Bomb.Bomb;
import oop.entities.Enemy.Enemy;
import oop.entities.Item.BombPoweredUp;
import oop.entities.Item.FlamePoweredUp;
import oop.entities.Item.SpeedPoweredUp;
import oop.fileloader.TileMap;
import oop.graphics.Sprite;

public class Bomber extends Character {
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean placeBomb = false;
    private int temp = 3;


    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        _sprite = Sprite.player_dead3;
    }

    @Override
    public void update() {
        //System.out.println(BombermanGame.LEVEL);
        keyboard(BombermanGame.getScene());
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

        } else {
            if(temp > 0) {
                _sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, 30);
                animate();
                img = _sprite.getFxImage();
                temp--;
            } else {
                for (int i = 0; i < BombermanGame.entities.size(); i++) {
                    Entity e = BombermanGame.entities.get(i);
                    if(e instanceof Bomber) BombermanGame.entities.remove(i);
                }
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

                    case Z: TileMap.enemynum = 0; break;
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
                    case ENTER: Portal.isClearStage = false; break;
                }
            }
        });

    }

    private void calculateMove() {
        double xa = 0, ya = 0;
        if(up) ya--;
        if(down) ya++;
        if(left) xa--;
        if(right) xa++;

        if(xa != 0 || ya != 0)  {
            move(xa * BombermanGame.SPEEDNUM, ya * BombermanGame.SPEEDNUM);
            _moving = true;
        } else {
            _moving = false;
        }
    }

    public boolean canMove(double x, double y) {
        for (Entity e : BombermanGame.entities) {
            if ((e instanceof Wall && collide(e,x,y)) || (e instanceof Brick && collide(e,x,y))) {
                return false;
            } else if((e instanceof Portal) && collide(e,x,y) && !Portal.isClearStage) {
                if (TileMap.enemynum == 0) {
                    Portal.isClearStage = true;
                    return true;
                }
                else return false;
            }
        }
        for (int i = 0; i < BombermanGame.item.size(); i++) {
            Entity e = BombermanGame.item.get(i);
            if((e instanceof SpeedPoweredUp) && collide(e,0,0)) {
                Audio.item();
                SpeedPoweredUp._active = true;
                BombermanGame.SPEEDNUM += 0.5;
                BombermanGame.item.remove(i);
                return true;
            } else if ((e instanceof BombPoweredUp) && collide(e,0,0)) {
                Audio.item();
                BombPoweredUp._active = true;
                BombermanGame.BOMBNUM++;
                BombermanGame.item.remove(i);
                return true;
            } else if((e instanceof FlamePoweredUp) && collide(e,0,0)) {
                Audio.item();
                FlamePoweredUp._active = true;
                BombermanGame.FLAMENUM++;
                BombermanGame.item.remove(i);
                return true;
            }
        }

        for (Entity e : BombermanGame.bomb) {
            if (!collide(e, 0, 0)) {
                ((Bomb) e).setAtBomb(false);
            } else if (collide(e, x, y) && !((Bomb) e).isAtBomb()) {
                return false;
            }
        }
        return true;
    }

    public void move(double xa, double ya) {
        if(xa > 0) _direction = 1;
        if(xa < 0) _direction = 3;
        if(ya > 0) _direction = 2;
        if(ya < 0) _direction = 0;
        if(canMove(0, ya)) {
            y += ya;
        }

        if(canMove(xa, 0)) {
            x += xa;
        }

    }

    public void placeBomb(double x, double y) {
        if (BombermanGame.BOMBNUM > 0) {
            checkBomb();
            if (placeBomb) {
                Audio.placeBomb();
                Entity e = new Bomb((int) (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE,(int) (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                ((Bomb) e).setAtBomb(true);
                BombermanGame.bomb.add(e);
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

    public void checkBomb() {
        for (Entity e : BombermanGame.bomb) {
            if ((int) e.getX() / Sprite.SCALED_SIZE == (int) this.x / Sprite.SCALED_SIZE && (int) e.getY() / Sprite.SCALED_SIZE == (int) this.y / Sprite.SCALED_SIZE) {
                placeBomb = false;
            }
        }
    }
}