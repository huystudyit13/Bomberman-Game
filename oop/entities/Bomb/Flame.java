package oop.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.audio.Audio;
import oop.entities.AnimatedEntity;
import oop.entities.Brick;
import oop.entities.Enemy.Balloon;
import oop.entities.Enemy.Enemy;
import oop.entities.Enemy.Oneal;
import oop.entities.Entity;
import oop.entities.Player.Bomber;

public class Flame extends AnimatedEntity {
    public int _timeAfter = 40;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }

    @Override
    public void update() {
        if (_timeAfter > 0 )
        {
            _timeAfter--;
        }
        else {
            check();
            remove();
            _timeAfter = 40;
        }
    }

    public void check() {
        for (int i = 0; i < BombermanGame.entities.size(); i++) {
            Entity e = BombermanGame.entities.get(i);
            if (e instanceof Brick && collide(e,0,0)) BombermanGame.entities.remove(i);
            else if (e instanceof Enemy && collide(e,0,0)) {
                Audio.botdie();
                BombermanGame.entities.remove(i);
            } else if (e instanceof Bomber && collide(e,0,0)) {
                Audio.bomberdie();
                BombermanGame.entities.get(i).set_alive(false);
            }
        }
    }
}