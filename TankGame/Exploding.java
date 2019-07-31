package TankGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Exploding {

    private ArrayList<BufferedImage> list;
    private int image;
    private boolean done;
    private int speed;
    private Sound music;

    public Exploding() throws IOException {

        list = new ArrayList<BufferedImage>();
        list.add(ImageIO.read(Exploding.class.getResource("Resources/big_explosion_0.png")));
        list.add(ImageIO.read(Exploding.class.getResource("Resources/big_explosion_1.png")));
        list.add(ImageIO.read(Exploding.class.getResource("Resources/big_explosion_2.png")));
        list.add(ImageIO.read(Exploding.class.getResource("Resources/big_explosion_3.png")));
        list.add(ImageIO.read(Exploding.class.getResource("Resources/big_explosion_4.png")));
        list.add(ImageIO.read(Exploding.class.getResource("Resources/big_explosion_5.png")));
        list.add(ImageIO.read(Exploding.class.getResource("Resources/big_explosion_6.png")));

        image = 0;
        done = false;
        speed = 3;
        music = new Sound("Resources/Explosion_large.wav");
    }

    public BufferedImage draw() {
        speed--;
        if (speed == 0) {
            image++;
            speed = 3;
        }
        if (image == 5) {
            done = true;
        }
        return list.get(image);
    }

    public boolean Done() {
        music.stop();
        return done;
    }

    public void ResetAll() {
        image = 0;
        speed = 3;
        done = false;
    }

    public void start() {
        music.start();
    }
}
