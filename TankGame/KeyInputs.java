package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class KeyInputs extends Observable implements KeyListener {

    private final Tanks tank;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    boolean[] keys;
    private GameEvents event;

    public KeyInputs(GameEvents event, Tanks tank, int up, int down, int left, int right, int shoot) {

        keys = new boolean[99];

        this.tank = tank;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.event = event;
    }

    @Override
    public void keyPressed(KeyEvent key) {

        int keyPressed = key.getKeyCode();

        if (keyPressed == up) {
            this.tank.up = true;
        }
        if (keyPressed == down) {
            this.tank.down = true;
        }
        if (keyPressed == left) {
            this.tank.left = true;
        }
        if (keyPressed == right) {
            this.tank.right = true;
        }
        if (keyPressed == shoot) {
            this.tank.shoot = true;
        }
        event.set_KeyEvent(key);
    }

    @Override
    public void keyReleased(KeyEvent key) {
        int keyPressed = key.getKeyCode();

        if (keyPressed == up) {
            this.tank.up = false;
        }
        if (keyPressed == down) {
            this.tank.down = false;
        }
        if (keyPressed == left) {
            this.tank.left = false;
        }
        if (keyPressed == right) {
            this.tank.right = false;
        }
        if (keyPressed == shoot) {
            this.tank.shoot = false;
        }
        event.set_KeyEvent(key);

    }

    @Override
    public void keyTyped(KeyEvent event) {

    }
}
