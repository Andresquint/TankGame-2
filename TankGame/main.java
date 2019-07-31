package TankGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JApplet;

public class main extends JApplet implements Runnable {

    static Obstacles walls;
    static ArrayList<Bullets> Bullet_Array;
    static ArrayList<OtherTanks> Other_Array;
    static ArrayList<PowerUp> Player_Array;
    public boolean Free1 = true;
    public boolean Free2 = true;
    Thread thread;
    GameEvents event1;
    GameEvents event2;
    BufferedImage Buff_Img;
    Graphics2D gfx;
    Image backgroundImg;
    Player player1;
    Player player2;
    Collision CC;
    int width = 1080, height = 720;
    JLabel P1Life = new JLabel();
    JLabel P2Life = new JLabel();
    JPanel panel = new JPanel();
    JPanel miniMap = new JPanel();
    Sound sound;
    private BufferedImage photo, Start_Game, Game_Over;
    private BufferedImage Screen1;
    private BufferedImage Screen2;
    private BufferedImage Mini_Screen;
    private BufferedImage yellowPowerUp;
    private BufferedImage greenPowerUp;
    private BufferedImage[] P1Lives = new BufferedImage[4];
    private BufferedImage[] P2Lives = new BufferedImage[4];
    private Random newRand;
    private Random powerupRandom = new Random();
    private int timer, endTimer;

    public main() {
        timer = 100;
        endTimer = 200000;
        setBackground(Color.white);
        setFocusable(true);
        try {
            backgroundImg = ImageIO.read(main.class.getResource("Resources/Background.bmp"));
            Buff_Img = ImageIO.read(main.class.getResource("Resources/Tank2.gif"));
            Start_Game = ImageIO.read(main.class.getResource("Resources/Title.bmp"));
            Game_Over = ImageIO.read(main.class.getResource("Resources/YouWin.bmp"));

            yellowPowerUp = ImageIO.read(main.class.getResource("Resources/Shield1.gif"));
            greenPowerUp = ImageIO.read(main.class.getResource("Resources/Shield2.gif"));


            P1Lives[0] = ImageIO.read(Tanks.class.getResource("Resources/Player1-GameOver.bmp"));
            P1Lives[1] = ImageIO.read(Tanks.class.getResource("Resources/Player1-Heart1.bmp"));
            P1Lives[2] = ImageIO.read(Tanks.class.getResource("Resources/Player1-Heart2.bmp"));
            P1Lives[3] = ImageIO.read(Tanks.class.getResource("Resources/Player1-Heart3.bmp"));

            P2Lives[0] = ImageIO.read(Tanks.class.getResource("Resources/Player2-GameOver.bmp"));
            P2Lives[1] = ImageIO.read(Tanks.class.getResource("Resources/Player2-Heart1.bmp"));
            P2Lives[2] = ImageIO.read(Tanks.class.getResource("Resources/Player2-Heart2.bmp"));
            P2Lives[3] = ImageIO.read(Tanks.class.getResource("Resources/Player2-Heart3.bmp"));

        } catch (Exception e) {
            System.out.println("Incorrect Usage... File Could Not Be Located!");
        }
        Bullet_Array = new ArrayList<Bullets>();
        Other_Array = new ArrayList<OtherTanks>();
        Player_Array = new ArrayList<PowerUp>();

        try {
            player1 = new Player(1, 3);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player2 = new Player(2, 3);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        event1 = new GameEvents();
        event2 = new GameEvents();
        if (event1 == null || event2 == null) {
            System.out.println("Empty!");
        }
        KeyInputs key = new KeyInputs(event1, player1.Player_Tank(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        this.addKeyListener(key);
        this.event1.addObserver(player1.Player_Tank());

        KeyInputs key2 = new KeyInputs(event1, player2.Player_Tank(), KeyEvent.VK_S, KeyEvent.VK_X, KeyEvent.VK_Z, KeyEvent.VK_C, KeyEvent.VK_A);
        this.addKeyListener(key2);
        this.event1.addObserver(player2.Player_Tank());
        newRand = new Random();


        try {
            for (int i = 0; i < 10; i++) {
                int numx = newRand.nextInt(900) + 450;
                int numy = newRand.nextInt(500) + 350;
                Other_Array.add(new OtherTanks(Buff_Img, numx, numy));

            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

        }

        walls = new Obstacles();
        walls.draw();

        CC = new Collision();

        sound = new Sound("Resources/Music.mid");

        int n = powerupRandom.nextInt(10) + 1;
        if (n > 5) {
            Player_Array.add(new PowerUp(yellowPowerUp, 800, 450, 0));
            Player_Array.add(new PowerUp(greenPowerUp, 1400, 450, 1));
            Player_Array.add(new PowerUp(greenPowerUp, 1100, 650, 1));
        } else {
            Player_Array.add(new PowerUp(greenPowerUp, 800, 450, 1));
            Player_Array.add(new PowerUp(yellowPowerUp, 1400, 450, 0));
            Player_Array.add(new PowerUp(yellowPowerUp, 1100, 650, 0));

        }

    }

    public void Playe1_Screen() {

        if (player1.Player_Tank().xLocation() <= width / 2 && player1.Player_Tank().yLocation() <= height) {
            Screen1 = photo.getSubimage(0, 0, width / 2, height);
            Free1 = false;
        } else {
            Free1 = true;
        }

        if (player1.Player_Tank().xLocation() <= width / 2 && player1.Player_Tank().yLocation() >= 720) {
            Screen1 = photo.getSubimage(0, 1440 - height, width / 2, height);
            Free1 = false;
        } else {
            Free1 = true;
        }

        if (player1.Player_Tank().xLocation() >= 1970 - (width / 2) && player1.Player_Tank().yLocation() <= height) {
            Screen1 = photo.getSubimage(1920 - (width / 2), 0, width / 2, height);

            Free1 = false;
        } else {
            Free1 = true;
        }

        if (Free1) {
            Screen1 = photo.getSubimage(player1.Player_Tank().xLocation() - 270, player1.Player_Tank().yLocation() - 360, width / 2, height);

            if (player1.Player_Tank().xLocation() - 200 > 200 || player1.Player_Tank().xLocation() - 200 < 1520) {
                Screen1 = photo.getSubimage(player1.Player_Tank().xLocation() - 200, player1.Player_Tank().yLocation() - 300, width / 2, height);
                if (player1.Player_Tank().yLocation() - 300 > 300 || player1.Player_Tank().xLocation() - 200 < 840) {
                    Screen1 = photo.getSubimage(player1.Player_Tank().xLocation() - 200, player1.Player_Tank().yLocation() - 300, width / 2, height);
                }
            }

            Free1 = true;
        }

    }

    public void Player2_Screen() {

        if (player2.Player_Tank().xLocation() <= width / 2 && player2.Player_Tank().yLocation() <= height) {
            Screen2 = photo.getSubimage(0, 0, width / 2, height);
            Free2 = false;
        } else {
            Free2 = true;
        }

        if (player2.Player_Tank().xLocation() <= width / 2 && player2.Player_Tank().yLocation() >= 720) {
            Screen2 = photo.getSubimage(0, 1440 - height, width / 2, height);
            Free2 = false;
        } else {
            Free2 = true;
        }

        if (player2.Player_Tank().xLocation() >= 1970 - (width / 2) && player2.Player_Tank().yLocation() <= height) {
            Screen2 = photo.getSubimage(1920 - (width / 2), 0, width / 2, height);

            Free2 = false;
        } else {
            Free2 = true;
        }

        if (Free2) {
            if (player2.Player_Tank().xLocation() - 200 > 200 || player2.Player_Tank().xLocation() - 200 < 1520) {
                Screen2 = photo.getSubimage(player2.Player_Tank().xLocation() - 200, player2.Player_Tank().yLocation() - 300, width / 2, height);
                if (player2.Player_Tank().yLocation() - 300 > 300 || player2.Player_Tank().xLocation() - 200 < 840) {
                    Screen2 = photo.getSubimage(player2.Player_Tank().xLocation() - 200, player2.Player_Tank().yLocation() - 300, width / 2, height);
                }
            }

            Free2 = true;
        }

    }

    @Override
    public void paint(Graphics g) {
        if (photo == null) {

            photo = (BufferedImage) createImage(2240, 1680);

            gfx = photo.createGraphics();
        }
        Map_Setup();
        if (timer > 0) {
            timer--;
            g.drawImage(Start_Game, 0, 0, this);
        } else {
            if (player1.Player_Tank().Life_Count() >= 0) {
                g.drawImage(P1Lives[player1.Player_Tank().Life_Count()], 0, 0, panel);
            }
            if (player2.Player_Tank().Life_Count() >= 0) {
                g.drawImage(P2Lives[player2.Player_Tank().Life_Count()], 700, 0, panel);
            }

            Playe1_Screen();
            Player2_Screen();
            Mini_Screen = photo.getSubimage(288, 288, 1680, 1000);
            g.drawImage(Mini_Screen, width / 2 - 160, 0, 320, 200, miniMap);
            g.drawImage(Screen1, 0, 200, panel);
            g.drawImage(Screen2, (width + 2) / 2, 200, panel);
            if (Other_Array.isEmpty()) {
                while (endTimer > 0) {
                    endTimer--;
                    g.drawImage(Game_Over, 0, 0, 1080, 920, panel);
                }
                System.exit(0);
            }
        }
    }

    public void Map_Setup() {
        for (int i = 0; i < 2240; i += 320) {
            for (int j = 0; j < 1680; j += 240) {
                gfx.drawImage(backgroundImg, i, j, this);
            }
        }

        for (int e = 0; e < Other_Array.size(); e++) {
            CC.AllTanks_Rec(player1.Player_Tank(), Other_Array.get(e));
            CC.AllTanks_Rec(player2.Player_Tank(), Other_Array.get(e));
        }

        for (int i = 0; i < walls.getArray().size(); i++) {
            for (int q = 0; q < Other_Array.size(); q++) {
                CC.Direction_Rec(Other_Array.get(q), (Walls) walls.getArray().get(i));
            }
        }

        for (int i = 0; i < walls.getArray().size(); i++) {
            for (int b = 0; b < Bullet_Array.size(); b++) {
                CC.Collision_Rec(Bullet_Array.get(b), (Walls) walls.getArray().get(i));
            }
        }
        for (int i = 0; i < walls.getArray().size(); i++) {
            Walls d = (Walls) walls.getArray().get(i);
            d.draw(gfx, this);
        }

        for (int b = 0; b < Bullet_Array.size(); b++) {
            CC.PTanks_Rec(player1.Player_Tank(), Bullet_Array.get(b));
        }
        for (int b = 0; b < Bullet_Array.size(); b++) {
            CC.PTanks_Rec(player2.Player_Tank(), Bullet_Array.get(b));
        }
        for (int e = 0; e < Other_Array.size(); e++) {
            for (int b = 0; b < Bullet_Array.size(); b++) {
                CC.OTanks_Rec(Other_Array.get(e), Bullet_Array.get(b));
            }
        }

        for (int i = 0; i < Player_Array.size(); i++) {
            CC.PowerUp_Rec(player1.Player_Tank(), Player_Array.get(i));
            CC.PowerUp_Rec(player2.Player_Tank(), Player_Array.get(i));
        }

        for (int i = 0; i < Other_Array.size(); i++) {
            Other_Array.get(i).draw(gfx, this);
        }
        player1.Player_Tank().draw(gfx, this);
        player2.Player_Tank().draw(gfx, this);

        for (int i = 0; i < Bullet_Array.size(); i++) {
            Bullet_Array.get(i).Update_Data();
            Bullet_Array.get(i).draw(gfx, this);
        }

        for (int i = 0; i < Player_Array.size(); i++) {
            Player_Array.get(i).draw(gfx, this);
        }
        if (player1.Player_Tank().Life_Count() <= 0 && player2.Player_Tank().Life_Count() <= 0) {
            stop();
        }
    }

    @Override
    public void run() {

        Thread Game = Thread.currentThread();
        while (thread == Game) {
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        sound.start();
    }

    @Override
    public void stop() {
        sound.stop();
    }
}
