package TankGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Objects {

    protected int x, y, width, height, speed;
    protected Image img;

    public Objects(BufferedImage img, int x, int y, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.speed = speed;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int a) {
        this.x = a;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int b) {
        this.y = b;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void draw(Graphics g, ImageObserver Img_Observer) {

        g.drawImage(img, x, y, Img_Observer);

    }
}
