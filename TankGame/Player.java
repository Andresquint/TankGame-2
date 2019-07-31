package TankGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private BufferedImage tankImage;

    private Tanks PlayerTank;
    private int level;
    private int life;
    private int speed;

    public Player(int level, int health) throws IOException {
        try {
            tankImage = ImageIO.read(Player.class.getResource("Resources/tank1.gif"));
        } catch (Exception e) {
            System.out.println("Image File is Empty...");
        }
        speed = 2;
        this.level = level;
        this.life = health;
        PlayerTank = new Tanks(level, 600 * this.level, 1120, tankImage, speed, life);

    }

    public Tanks Player_Tank() {
        return this.PlayerTank;
    }

}
