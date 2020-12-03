package oop.entities;

import javafx.scene.image.Image;

public abstract class Character extends AnimatedEntity {
    protected int _direction = -1;
    protected boolean _alive = false;
    protected boolean _moving = false;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();

}
