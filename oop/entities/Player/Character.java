package oop.entities.Player;

import javafx.scene.image.Image;
import oop.entities.AnimatedEntity;


public abstract class Character extends AnimatedEntity {
    protected int _direction = -1;

    protected boolean _moving = false;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();

}
