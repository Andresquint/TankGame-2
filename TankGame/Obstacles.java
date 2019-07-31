package TankGame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Obstacles {
    private Walls Obstacle;
    private Walls Border;
    private ArrayList<Walls> W_List;
    private BufferedImage img1;
    private BufferedImage img2;

    public Obstacles() {

        try {
            img2 = ImageIO.read(Obstacles.class.getResource("Resources/Wall2.gif"));
            img1 = ImageIO.read(Obstacles.class.getResource("Resources/Wall1.gif"));
        } catch (Exception e) {
            System.out.println("Incorrect Usage... File Could Not Be Located!");
        }

        W_List = new ArrayList<Walls>();
    }

    public void draw() {
        for (int i = 384; i < 1856; i += 32) {
            for(int j = 288; j < 384; j+= 32){
                Border = new Walls(false, i, j, img2);
                W_List.add(Border);
            }
        }

        for (int i = 384; i < 1856; i += 32) {
            for(int j = 1200; j < 1296; j+= 32){
                Border = new Walls(false, i, j, img2);
                W_List.add(Border);
            }
        }

        for (int i = 288; i < 1296; i += 32) {
            for(int j = 288; j < 384; j+= 32){
                Border = new Walls(false, j, i, img2);
                W_List.add(Border);
            }
        }

        for (int i = 288; i < 1296; i += 32) {
            for(int j = 1856; j < 1952; j+= 32){
                Border = new Walls(false, j, i, img2);
                W_List.add(Border);
            }
        }

        for (int i = 416; i < 512; i += 32) {
            Obstacle = new Walls(true, i, 512, img1);
            W_List.add(Obstacle);
        }

        for (int i = 800; i < 1600; i += 32) {
            Obstacle = new Walls(false, i, 700, img2);
            W_List.add(Obstacle);
        }

        for (int i = 928; i < 1720; i += 32) {
            Obstacle = new Walls(true, i, 544, img1);
            W_List.add(Obstacle);
        }

        for (int i = 448; i < 508; i += 32) {
            Obstacle = new Walls(true, i, 544, img1);
            W_List.add(Obstacle);
        }

        for (int i = 768; i < 860; i += 32) {
            Obstacle = new Walls(true, i, 544, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1344; i < 1760; i += 32) {
            Obstacle = new Walls(false, i, 544, img2);
            W_List.add(Obstacle);
        }

        for (int i = 512; i < 640; i += 32) {
            Obstacle = new Walls(true, i, 576, img1);
            W_List.add(Obstacle);
        }

        for (int i = 704; i < 928; i += 32) {
            Obstacle = new Walls(true, i, 576, img1);
            W_List.add(Obstacle);
        }

        for (int i = 544; i < 704; i += 32) {
            Obstacle = new Walls(true, i, 640, img1);
            W_List.add(Obstacle);
        }

        for (int i = 730; i < 832; i += 32) {
            Obstacle = new Walls(true, i, 640, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1024; i < 1088; i += 32) {
            Obstacle = new Walls(true, i, 640, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1120; i < 1440; i += 32) {
            Obstacle = new Walls(true, i, 640, img1);
            W_List.add(Obstacle);
        }

        for (int i = 416; i < 608; i += 32) {
            Obstacle = new Walls(true, i, 672, img1);
            W_List.add(Obstacle);
        }

        for (int i = 832; i < 1024; i += 32) {
            Obstacle = new Walls(true, i, 672, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1568; i < 1664; i += 32) {
            Obstacle = new Walls(true, i, 736, img1);
            W_List.add(Obstacle);
        }

        for (int i = 448; i < 704; i += 32) {
            Obstacle = new Walls(true, i, 768, img1);
            W_List.add(Obstacle);
        }

        for (int i = 960; i < 1384; i += 32) {
            Obstacle = new Walls(true, i, 768, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1344; i < 1568; i += 32) {
            Obstacle = new Walls(true, i, 768, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1344; i < 1504; i += 32) {
            Obstacle = new Walls(true, i, 800, img1);
            W_List.add(Obstacle);
        }

        for (int i = 704; i < 1440; i += 32) {
            Obstacle = new Walls(true, i, 832, img1);
            W_List.add(Obstacle);
        }

        for (int i = 992; i < 1152; i += 32) {
            Obstacle = new Walls(false, i, 864, img2);
            W_List.add(Obstacle);
        }

        for (int i = 432; i < 670; i += 32) {
            Obstacle = new Walls(false, i, 510, img2);
            W_List.add(Obstacle);
        }

        for (int i = 852; i < 1568; i += 32) {
            Obstacle = new Walls(true, i, 928, img1);
            W_List.add(Obstacle);
        }

        for (int i = 544; i < 992; i += 32) {
            Obstacle = new Walls(true, i, 928, img1);
            W_List.add(Obstacle);
        }

        for (int i = 416; i < 672; i += 32) {
            Obstacle = new Walls(true, i, 960, img1);
            W_List.add(Obstacle);
        }

        for (int i = 928; i < 1024; i += 32) {
            Obstacle = new Walls(false, i, 960, img2);
            W_List.add(Obstacle);
        }

        for (int i = 1216; i < 1504; i += 32) {
            Obstacle = new Walls(true, i, 960, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1216; i < 1504; i += 32) {
            Obstacle = new Walls(true, i, 1024, img1);
            W_List.add(Obstacle);
        }

        for (int i = 512; i < 640; i += 32) {
            Obstacle = new Walls(true, i, 1056, img1);
            W_List.add(Obstacle);
        }

        for (int i = 704; i < 928; i += 32) {
            Obstacle = new Walls(false, i, 1056, img2);
            W_List.add(Obstacle);
        }

        for (int i = 1120; i < 1376; i += 32) {
            Obstacle = new Walls(true, i, 1056, img1);
            W_List.add(Obstacle);
        }


        for (int i = 544; i < 772; i += 32) {
            Obstacle = new Walls(true, i, 1088, img1);
            W_List.add(Obstacle);
        }

        for (int i = 1344; i < 1760; i += 32) {
            Obstacle = new Walls(true, i, 1088, img1);
            W_List.add(Obstacle);
        }

        for (int i = 416; i < 512; i += 32) {
            Obstacle = new Walls(true, i, 1120, img1);
            W_List.add(Obstacle);
        }

        for (int i = 832; i < 1024; i += 32) {
            Obstacle = new Walls(true, i, 1120, img1);
            W_List.add(Obstacle);
        }
    }

    public ArrayList getArray() {
        return W_List;
    }
}
