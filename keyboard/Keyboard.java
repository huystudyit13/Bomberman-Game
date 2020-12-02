import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;
    public boolean placebomb;

    public void update() {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int  input = e.getKeyCode();

        if (input == KeyEvent.VK_UP) {

        }
        if (input == KeyEvent.VK_DOWN) {

        }
        if (input == KeyEvent.VK_RIGHT) {

        }
        if (input == KeyEvent.VK_LEFT) {

        }
        if (input == KeyEvent.VK_SPACE) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
