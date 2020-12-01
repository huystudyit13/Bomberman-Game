package entities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    protected boolean placebomb = false;

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                left = true;
                System.out.println("1");
            }
            if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                right = true;
            }
            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                up = true;
            }
            if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                down = true;
            }
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                left = false;
                System.out.println("1");
            }
            if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                right = false;
            }
            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                up = false;
            }
            if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                down = false;
            }
        }
    }
}
