package TankGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class PowerUp extends Objects {

    public PowerUp(BufferedImage img, int x, int y, int speed) {
        super(img, x, y, speed);
        this.img = img;
    }

    public void Yellow_Shield (boolean yellow) {
        main.Player_Array.remove(this);
    }

    public void Green_Shield (boolean green) {
        main.Player_Array.remove(this);
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void draw(Graphics g, ImageObserver Img_Observer) {
        g.drawImage(img, x, y, Img_Observer);
    }

}
