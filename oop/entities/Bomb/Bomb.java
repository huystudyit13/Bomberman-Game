package oop.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import oop.audio.Audio;
import oop.BombermanGame;
import oop.entities.AnimatedEntity;
import oop.entities.Brick;
import oop.entities.Entity;
import oop.entities.Player.Bomber;
import oop.entities.Wall;
import oop.graphics.Sprite;

public class Bomb extends AnimatedEntity {
    public double _timeToExplode = 120;
    public int _timeAfter = 40;
    private boolean up = true;
    private boolean down = true;
    private boolean right = true;
    private boolean left = true;
    private boolean atBomb = false;
    public boolean _exploded = false;

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
            _sprite =  Sprite.bomb_exploded;
            check();
        } else {
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
        }
        img = _sprite.getFxImage();
        gc.drawImage(img,x,y);
    }

    public void explode() {
        _exploded = true;
        if (BombermanGame.FLAMENUM == 1) {
            if (checkRight(1) != -1) {
                Entity eR = new Flame((int) (x + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage());
                BombermanGame.flame.add(eR);
            }
            if (checkDown(1) != -1) {
                Entity eD = new Flame((int) x / Sprite.SCALED_SIZE, (int) (y + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage());
                BombermanGame.flame.add(eD);
            }
            if (checkLeft(1) != -1) {
                Entity eL = new Flame((int) (x - Sprite.SCALED_SIZE) / 48, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage());
                BombermanGame.flame.add(eL);
            }
            if (checkUp(1) != -1) {
                Entity eU = new Flame((int) (x) / Sprite.SCALED_SIZE, (int) (y - Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage());
                BombermanGame.flame.add(eU);
            }
        } else {
            int cntR = 0;
            int cntL = 0;
            int cntU = 0;
            int cntD = 0;

            //flame right count
            for (int i = 1; i <= BombermanGame.FLAMENUM; i++) {
                if (checkRight(i) == -1) {
                    break;
                } else if (checkRight(i) == 1) {
                    cntR++;
                    break;
                }
                else cntR++;
            }
            for (int i = 1; i <= cntR; i++) {
                if (i == BombermanGame.FLAMENUM) {
                    Entity eR = new Flame((int) (x + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage());
                    BombermanGame.flame.add(eR);
                } else {
                    Entity eR = new Flame((int) (x + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage());
                    BombermanGame.flame.add(eR);
                }
            }

            //flame left count
            for (int i = 1; i <= BombermanGame.FLAMENUM; i++) {
                if (checkLeft(i) == -1) {
                    break;
                } else if (checkLeft(i) == 1) {
                    cntL++;
                    break;
                }
                else cntL++;
            }
            for (int i = 1; i <= cntL; i++) {
                if (i == BombermanGame.FLAMENUM) {
                    Entity eL = new Flame((int) (x - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage());
                    BombermanGame.flame.add(eL);
                } else {
                    Entity eL = new Flame((int) (x - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage());
                    BombermanGame.flame.add(eL);
                }
            }

            //flame up count
            for (int i = 1; i <= BombermanGame.FLAMENUM; i++) {
                if (checkUp(i) == -1) {
                    break;
                } else if (checkUp(i) == 1) {
                    cntU++;
                    break;
                }
                else cntU++;
            }
            for (int i = 1; i <= cntU; i++) {
                if (i == BombermanGame.FLAMENUM) {
                    Entity eU = new Flame((int) (x) / Sprite.SCALED_SIZE, (int) (y - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage());
                    BombermanGame.flame.add(eU);
                } else {
                    Entity eU = new Flame((int) (x) / Sprite.SCALED_SIZE, (int) (y - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage());
                    BombermanGame.flame.add(eU);
                }
            }

            //flame down count
            for (int i = 1; i <= BombermanGame.FLAMENUM; i++) {
                if (checkDown(i) == -1) {
                    break;
                } else if (checkDown(i) == 1) {
                    cntD++;
                    break;
                }
                else cntD++;
            }
            for (int i = 1; i <= cntD; i++) {
                if (i == BombermanGame.FLAMENUM) {
                    Entity eD = new Flame((int) x / Sprite.SCALED_SIZE, (int) (y + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage());
                    BombermanGame.flame.add(eD);
                } else {
                    Entity eD = new Flame((int) x / Sprite.SCALED_SIZE, (int) (y + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage());
                    BombermanGame.flame.add(eD);
                }
            }
        }
    }

    public int checkUp(int i) {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Wall && collide(e,0,-(Sprite.SCALED_SIZE * i))) return -1;
            else if (e instanceof Brick && collide(e,0,-(Sprite.SCALED_SIZE * i))) return 1;
        }
        return 0;
    }

    public int checkDown(int i) {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Wall && collide(e,0,Sprite.SCALED_SIZE * i)) return -1;
            else if (e instanceof Brick && collide(e,0,Sprite.SCALED_SIZE * i)) return 1;
        }
        return 0;
    }

    public int checkRight(int i) {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Wall && collide(e,Sprite.SCALED_SIZE * i,0)) return -1;
            else if (e instanceof Brick && collide(e,Sprite.SCALED_SIZE * i,0)) return 1;
        }
        return 0;
    }

    public int checkLeft(int i) {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Wall && collide(e,-(Sprite.SCALED_SIZE * i),0)) return -1;
            else if (e instanceof Wall && collide(e,-(Sprite.SCALED_SIZE * i),0)) return 1;
        }
        return 0;
    }


    public void check() {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Bomber && collide(e,0,0)) {
                Audio.bomberdie();
                e.set_alive(false);
            }
        }
    }

    public static void decreaseBombNumber() {
        BombermanGame.BOMBNUM--;
    }

    public void increaseBombNumber() {
        BombermanGame.BOMBNUM++;
    }

    public void setAtBomb(boolean atBomb) {
        this.atBomb = atBomb;
    }

    public boolean isAtBomb() {
        return atBomb;
    }
}