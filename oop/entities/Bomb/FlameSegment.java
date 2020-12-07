package oop.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import oop.entities.Entity;
import oop.graphics.Sprite;

public class FlameSegment extends Entity {
    protected boolean _last;

    /**
     *
     * @param _x
     * @param _y
     * @param direction
     * @param last cho biết segment này là cuối cùng của Flame hay không,
     *                segment cuối có sprite khác so với các segment còn lại
     */
    public FlameSegment(int _x, int _y, int direction, boolean last) {
        x = _x;
        y = _y;
        _last = last;


        switch (direction) {
            case 0:
                if(!last) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                }
                break;
            case 1:
                if(!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                }
                break;
            case 2:
                if(!last) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                }
                break;
            case 3:
                if(!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                }
                break;
        }
    }

    @Override
    public void update() {}

    @Override
    public void render(GraphicsContext gc) {

    }

}
