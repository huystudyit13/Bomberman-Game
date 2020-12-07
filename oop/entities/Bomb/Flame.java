package oop.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.Main;
import oop.entities.Entity;

public class Flame extends Entity {
    public int _timeAfter = 40;
    protected int _direction;
    private int _radius;
    protected int xOrigin, yOrigin;
    protected FlameSegment[] _flameSegments = new FlameSegment[0];

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
            remove();
            _timeAfter = 40;
        }
    }

    private void createFlameSegments() {
        /**
         * tính toán độ dài Flame, tương ứng với số lượng segment
         */
        _flameSegments = new FlameSegment[calculatePermitedDistance()];

        /**
         * biến last dùng để đánh dấu cho segment cuối cùng
         */

        // TODO: tạo các segment dưới đây
        boolean last = false;

        int _x = (int) x;
        int _y = (int) y;
        for (int i = 0; i < _flameSegments.length; i++) {
            last = (i == _flameSegments.length -1) ? true : false;

            switch (_direction) {
                case 0: _y--; break;
                case 1: _x++; break;
                case 2: _y++; break;
                case 3: _x--; break;
            }
            _flameSegments[i] = new FlameSegment(_x, _y, _direction, last);
        }
    }

    private int calculatePermitedDistance() {
        // TODO: thực hiện tính toán độ dài của Flame
        int radius = 0;
        int _x = (int) x;
        int _y = (int) y;
        while(radius < _radius) {
            if (_direction == 0) _y--;
            if (_direction == 1) _x++;
            if (_direction == 2) _y++;
            if (_direction == 3) _x--;

            for (Entity e : Main.stillObjects) {

                if (e instanceof Bomb) ++radius; //explosion has to be below the bom

                // if(e.collide(this) == false) //cannot pass thru
                //  break;

                ++radius;
            }
        }
        return radius;

    }

    @Override
    public boolean collide(Entity e, double a, double b) {
        return false;
    }
}