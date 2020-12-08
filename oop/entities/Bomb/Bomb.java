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
            _sprite =  Sprite.bomb_exploded;
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
        if (Main.FLAMENUM == 1) {
            if (checkRight(1) != -1) {
                Entity eR = new Flame((int) (x + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage());
                Main.flame.add(eR);
            }
            if (checkDown(1) != -1) {
                Entity eD = new Flame((int) x / Sprite.SCALED_SIZE, (int) (y + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage());
                Main.flame.add(eD);
            }
            if (checkLeft(1) != -1) {
                Entity eL = new Flame((int) (x - Sprite.SCALED_SIZE) / 48, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage());
                Main.flame.add(eL);
            }
            if (checkUp(1) != -1) {
                Entity eU = new Flame((int) (x) / Sprite.SCALED_SIZE, (int) (y - Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage());
                Main.flame.add(eU);
            }
        } else {
            int cntR = 0;
            int cntL = 0;
            int cntU = 0;
            int cntD = 0;

            //flame right count
            for (int i = 1; i <= Main.FLAMENUM; i++) {
                if (checkRight(i) == -1) {
                    break;
                } else if (checkRight(i) == 1) {
                    cntR++;
                    break;
                }
                else cntR++;
            }
            for (int i = 1; i <= cntR; i++) {
                if (i == Main.FLAMENUM) {
                    Entity eR = new Flame((int) (x + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage());
                    Main.flame.add(eR);
                } else {
                    Entity eR = new Flame((int) (x + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage());
                    Main.flame.add(eR);
                }
            }

            //flame left count
            for (int i = 1; i <= Main.FLAMENUM; i++) {
                if (checkLeft(i) == -1) {
                    break;
                } else if (checkLeft(i) == 1) {
                    cntL++;
                    break;
                }
                else cntL++;
            }
            for (int i = 1; i <= cntL; i++) {
                if (i == Main.FLAMENUM) {
                    Entity eL = new Flame((int) (x - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage());
                    Main.flame.add(eL);
                } else {
                    Entity eL = new Flame((int) (x - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, (int) y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage());
                    Main.flame.add(eL);
                }
            }

            //flame up count
            for (int i = 1; i <= Main.FLAMENUM; i++) {
                if (checkUp(i) == -1) {
                    break;
                } else if (checkUp(i) == 1) {
                    cntU++;
                    break;
                }
                else cntU++;
            }
            for (int i = 1; i <= cntU; i++) {
                if (i == Main.FLAMENUM) {
                    Entity eU = new Flame((int) (x) / Sprite.SCALED_SIZE, (int) (y - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage());
                    Main.flame.add(eU);
                } else {
                    Entity eU = new Flame((int) (x) / Sprite.SCALED_SIZE, (int) (y - Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage());
                    Main.flame.add(eU);
                }
            }

            //flame down count
            for (int i = 1; i <= Main.FLAMENUM; i++) {
                if (checkDown(i) == -1) {
                    break;
                } else if (checkDown(i) == 1) {
                    cntD++;
                    break;
                }
                else cntD++;
            }
            for (int i = 1; i <= cntD; i++) {
                if (i == Main.FLAMENUM) {
                    Entity eD = new Flame((int) x / Sprite.SCALED_SIZE, (int) (y + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage());
                    Main.flame.add(eD);
                } else {
                    Entity eD = new Flame((int) x / Sprite.SCALED_SIZE, (int) (y + Sprite.SCALED_SIZE * i) / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage());
                    Main.flame.add(eD);
                }
            }
        }
    }

    public int checkUp(int i) {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,0,-(Sprite.SCALED_SIZE * i))) return -1;
            else if (e instanceof Brick && collide(e,0,-(Sprite.SCALED_SIZE * i))) return 1;
        }
        return 0;
    }

    public int checkDown(int i) {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,0,Sprite.SCALED_SIZE * i)) return -1;
            else if (e instanceof Brick && collide(e,0,Sprite.SCALED_SIZE * i)) return 1;
        }
        return 0;
    }

    public int checkRight(int i) {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,Sprite.SCALED_SIZE * i,0)) return -1;
            else if (e instanceof Brick && collide(e,Sprite.SCALED_SIZE * i,0)) return 1;
        }
        return 0;
    }

    public int checkLeft(int i) {
        for (Entity e : Main.entities) {
            if (e instanceof Wall && collide(e,-(Sprite.SCALED_SIZE * i),0)) return -1;
            else if (e instanceof Wall && collide(e,-(Sprite.SCALED_SIZE * i),0)) return 1;
        }
        return 0;
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