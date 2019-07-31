package TankGame;

import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.util.Observer;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tanks extends Objects implements Observer {
    public boolean up, down, left, right, shoot;
    private int health, Shield, shot;
    private AffineTransformOp Trans_Op;
    private BufferedImage img, bullet, photo;
    private BufferedImage[] Bar_Img;
    private int Health_Bar;
    private Objects Bar;
    private int LifeCount;
    private int xLocation;
    private int yLocation;
    private short angle;
    private int level;
    private boolean Lives, explosion;
    private Exploding GameOver;
    private AffineTransform trans;
    private int xCheck, yCheck;
    private int powerType = 0;
    private Rectangle Left_Rec;
    private Rectangle Right_Rec;
    private Rectangle Up_Rec;
    private Rectangle Down_Rec;
    private Rectangle Wall_Rec;

    public Tanks(int level, int x, int y, BufferedImage image, int count, int live) throws IOException {
        super(image, x, y, count);
        this.level = level;
        health = live;
        Health_Bar = 3;
        LifeCount = 3;
        img = image;
        Bar_Img = new BufferedImage[4];
        Bar_Img[0] = ImageIO.read(Tanks.class.getResource("Resources/Health-Bar.png"));
        Bar_Img[1] = ImageIO.read(Tanks.class.getResource("Resources/Health-Bar1.png"));
        Bar_Img[2] = ImageIO.read(Tanks.class.getResource("Resources/Health-Bar2.png"));
        Bar_Img[3] = ImageIO.read(Tanks.class.getResource("Resources/Health-Bar3.png"));
        bullet = ImageIO.read(Tanks.class.getResource("Resources/Rocket.gif"));
        Lives = true;
        Shield = 0;
        angle = 0;
        shot = 0;
        explosion = false;
        GameOver = new Exploding();

    }

    @Override
    public void draw(Graphics g, ImageObserver Img_Observer) {
        if (shot != 0) {
            shot--;
        }
        if (Shield != 0) {
            Shield--;
            Lives = !Lives;
        }
        if (explosion) {
            if (!GameOver.Done()) {
                g.drawImage(GameOver.draw(), x, y, Img_Observer);
            } else {
                explosion = false;
                GameOver.ResetAll();
                this.y = 1120;
                this.x = 600 * level;
            }
        } else if (Lives) {
            trans = AffineTransform.getTranslateInstance(x, y);
            trans.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
            Graphics2D graphic2D = (Graphics2D) g;
            graphic2D.drawImage(img, trans, null);
            if (Health_Bar == 3) {
                Bar = new Objects(Bar_Img[0], x, y - 20, 0);
                Bar.draw(g, Img_Observer);
            } else if (Health_Bar == 2) {
                Bar = new Objects(Bar_Img[1], x, y - 20, 0);
                Bar.draw(g, Img_Observer);
            } else if (Health_Bar == 1) {
                Bar = new Objects(Bar_Img[2], x, y - 20, 0);
                Bar.draw(g, Img_Observer);
            } else {
                Bar = new Objects(Bar_Img[3], x, y - 20, 0);
                Bar.draw(g, Img_Observer);
            }

        }
        xLocation = x;
        yLocation = y;
    }

    public void Exploded(boolean tank) {
        if (Shield == 0) {
            if (Health_Bar > 0 && !tank) {
                Health_Bar--;
            } else {
                explosion = true;
                if (health > 1) {
                    health--;
                    powerType = 0;
                    LifeCount = LifeCount - 1;
                    Health_Bar = 3;
                    speed = 2;
                    Shield = 20;
                    photo = img;
                } else {
                    LifeCount = LifeCount - 1;
                    Lives = false;
                }
            }
        }
    }

    public void set_YellowPower(boolean tank) {
        powerType = 1;
    }

    public void set_GreenPower(boolean tank) {
        powerType = 2;
    }

    public void Powers(boolean tank) {

        if (powerType == 1) {
            health = 3;
            Health_Bar = 3;
            LifeCount = 3;
        }
    }

    @Override
    public void update(Observable object, Object argument) {
        boolean check = false;
        if (Lives) {

            if (left) {
                angle -= 3;
                for (int i = 0; i < main.walls.getArray().size(); i++) {
                    if (New_Rectangle(this, (Walls) main.walls.getArray().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    angle += 3;
                }

            } else if (right) {
                angle += 3;
                for (int i = 0; i < main.walls.getArray().size(); i++) {
                    if (New_Rectangle(this, (Walls)main.walls.getArray().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    angle -= 3;
                }

            } else if (up) {
                xCheck = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
                yCheck = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
                x += xCheck;
                y += yCheck;
                for (int i = 0; i < main.walls.getArray().size(); i++) {
                    if (New_Rectangle(this, (Walls) main.walls.getArray().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    x -= xCheck;
                    y -= yCheck;
                }

            } else if (down) {
                xCheck = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
                yCheck = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
                x -= xCheck;
                y -= yCheck;
                for (int i = 0; i < main.walls.getArray().size(); i++) {
                    if (New_Rectangle(this, (Walls) main.walls.getArray().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    x += xCheck;
                    y += yCheck;
                }
            } else if (shoot && shot == 0) {
                Bullets b = new Bullets(bullet, x, y, (int) Math.round(6 * Math.cos(Math.toRadians(angle))),
                        (int) Math.round(6 * Math.sin(Math.toRadians(angle))), true, angle);
                main.Bullet_Array.add(b);
                shot = 10;

                if (powerType == 2) {
                    Bullets b2 = new Bullets(bullet, x + 30, y + 30, (int) Math.round(6 * Math.cos(Math.toRadians(angle))),
                            (int) Math.round(6 * Math.sin(Math.toRadians(angle))), true, angle);
                    main.Bullet_Array.add(b2);
                    shot = 10;
                }

                if (powerType == 2) {
                    Bullets b3 = new Bullets(bullet, x - 30, y - 30, (int) Math.round(6 * Math.cos(Math.toRadians(angle))),
                            (int) Math.round(6 * Math.sin(Math.toRadians(angle))), true, angle);
                    main.Bullet_Array.add(b3);
                    shot = 10;
                }
            }
        }
    }

    public int getXC() {
        return xCheck;
    }

    public int getYC() {
        return yCheck;
    }

    public int Cool_Down() {
        return Shield;
    }
    public int xLocation() {
        return xLocation;
    }
    public int yLocation() {
        return yLocation;
    }
    public int Life_Count() {
        return LifeCount;
    }

    public boolean New_Rectangle(Tanks a, Walls b) {

        Left_Rec = new Rectangle(a.getX(), a.getY(), 1, a.getHeight() - 1);
        Right_Rec = new Rectangle(a.getX() + a.getWidth() - 1, a.getY(), 1, a.getHeight() - 1);
        Up_Rec = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, 1);
        Down_Rec = new Rectangle(a.getX(), a.getY() + a.getHeight() - 1, a.getWidth() - 1, 1);

        Wall_Rec = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);

        if (Up_Rec.intersects(Wall_Rec)) {
            return true;
        } else if (Down_Rec.intersects(Wall_Rec)) {
            return true;
        } else if (Left_Rec.intersects(Wall_Rec)) {
            return true;
        } else return Right_Rec.intersects(Wall_Rec);
    }
}