package TankGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Walls extends Objects {
    boolean Destructable;
    private boolean Destructed;

    public Walls(boolean B, int x, int y, BufferedImage img) {
        super(img, x, y, 0);
        Destructable = B;
        Destructed = false;
    }

    @Override
    public void draw(Graphics g, ImageObserver Img_Observer) {
        if (!Destructed) {
            g.drawImage(img, x, y, Img_Observer);
        }
    }

    public void Wall_Exploded() {
        Destructed = true;
    }
}
