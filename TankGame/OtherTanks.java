package TankGame;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OtherTanks extends Objects {

    private BufferedImage OtherTank, Right, Up, Left, Down;
    private int direction = 0;
    private BufferedImage photo;
    private BufferedImage Buff_Img;
    private AffineTransform trans = new AffineTransform();
    private AffineTransformOp Trans_Op;
    private BufferedImage[] Bar_Img;
    private int Health_Bar;
    private Objects Bar;
    private int Timer;
    private boolean exploded;
    private Exploding GameOver;

    public OtherTanks(BufferedImage photo, int x, int y) throws IOException {
        super(photo, x, y, 1);
        this.photo = photo;
        OtherTank = photo;
        Health_Bar = 3;
        Bar_Img = new BufferedImage[4];
        Bar_Img[0] = ImageIO.read(OtherTanks.class.getResource("Resources/Health-Bar.png"));
        Bar_Img[1] = ImageIO.read(OtherTanks.class.getResource("Resources/Health-Bar1.png"));
        Bar_Img[2] = ImageIO.read(OtherTanks.class.getResource("Resources/Health-Bar2.png"));
        Bar_Img[3] = ImageIO.read(OtherTanks.class.getResource("Resources/Health-Bar3.png"));
        Right = Rotation(0);
        Left = Rotation(180);
        Up = Rotation(90);
        Down = Rotation(-180);
        exploded = false;
        Direction(0);
        GameOver = new Exploding();

    }

    public void Direction(int level) {
        Random random = new Random();
        while (direction == level) {
            direction = random.nextInt(4);
        }
        if (direction == 0) {
            OtherTank = Right;
        } else if (direction == 1) {
            OtherTank = Up;
        } else if (direction == 2) {
            OtherTank = Left;
        } else if (direction == 3) {
            OtherTank = Down;
        }
    }

    private BufferedImage Rotation(double level) {
        trans.rotate(Math.toRadians(level), photo.getWidth() / 2.0, photo.getHeight() / 2.0);
        Trans_Op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
        return Trans_Op.filter(photo, null);
    }

    public void Exploded(boolean tank) {
        if (tank || Health_Bar == 0) {
            exploded = true;
        } else {
            Health_Bar--;
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver Img_Observer) {
        if (!exploded) {
            try {
                update();
            } catch (IOException ex) {
                Logger.getLogger(OtherTanks.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (Health_Bar == 3) {
                Bar = new Objects(Bar_Img[0], x, y - 25, 0);
                Bar.draw(g, Img_Observer);
            } else if (Health_Bar == 2) {
                Bar = new Objects(Bar_Img[1], x, y - 25, 0);
                Bar.draw(g, Img_Observer);
            } else if (Health_Bar == 1) {
                Bar = new Objects(Bar_Img[2], x, y - 25, 0);
                Bar.draw(g, Img_Observer);
            } else {
                Bar = new Objects(Bar_Img[3], x, y - 25, 0);
                Bar.draw(g, Img_Observer);
            }
            g.drawImage(OtherTank, x, y, Img_Observer);
        } else {
            GameOver.start();
            if (!GameOver.Done()) {
                g.drawImage(GameOver.draw(), x, y, Img_Observer);
            } else {
                main.Other_Array.remove(this);
            }
        }
    }

    public void update() throws IOException {
        if (direction == 0) {
            x += speed;
        } else if (direction == 1) {
            y -= speed;
        } else if (direction == 2) {
            x -= speed;
        } else if (direction == 3) {
            y += speed;
        }
        Timer++;
        if (Timer == 22) {
            Shoot();
            Timer = 0;
        }
    }

    public void Shoot() throws IOException {
        Buff_Img = ImageIO.read(OtherTanks.class.getResource("Resources/Weapon.gif"));
        Bullets bullet;
        int xBull = x + ((photo.getWidth() - 1) / 2);
        int yBull = y + ((photo.getHeight() - 1) / 2);
        if (direction == 0) {
            bullet = new Bullets(Buff_Img, xBull, yBull, 1, 0, false, (short) 0);
        } else if (direction == 1) {
            bullet = new Bullets(Buff_Img, xBull, yBull, 0, -1, false, (short) 90);
        } else if (direction == 2) {
            bullet = new Bullets(Buff_Img, xBull, yBull, -1, 0, false, (short) 180);
        } else {
            bullet = new Bullets(Buff_Img, xBull, yBull, 0, 1, false, (short) -180);
        }
        main.Bullet_Array.add(bullet);

    }
}
