package TankGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Bullets extends Objects {
    private int xDir;
    private int yDir;
    private BufferedImage photo;
    private boolean check;
    private boolean PlayerTank;
    private short angle;
    private AffineTransform trans;

    public Bullets(BufferedImage img, int x, int y, int xDirections, int yDirections, boolean PlayerTank, short angle) {
        super(img, x, y, 20);
        xDir = xDirections;
        yDir = yDirections;
        photo = img;
        check = false;
        this.PlayerTank = PlayerTank;
        this.angle = angle;
    }

    public void Update_Data() {
        if (!check) {
            x += xDir * 10;
            y += yDir * 10;
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver Img_Observer) {
        if (!check) {
            trans = AffineTransform.getTranslateInstance(x, y);
            trans.rotate(Math.toRadians(angle), photo.getWidth() / 2, photo.getHeight() / 2);
            Graphics2D graphic2D = (Graphics2D) g;
            graphic2D.drawImage(img, trans, Img_Observer);
        }
    }

    public void Collision() {
        check = true;
    }

    public boolean Player_Bullet() {
        return PlayerTank;
    }
}
