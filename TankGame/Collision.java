package TankGame;

import java.awt.*;

public class Collision {

    Rectangle left;
    Rectangle right;
    Rectangle up;
    Rectangle down;
    Rectangle W;
    Rectangle A;

    public Collision() {
    }

    public void AllTanks_Rec(Tanks a, OtherTanks b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
        if (A.intersects(W)) {
            b.Exploded(true);
            a.Exploded(true);
        }

    }

    public void PowerUp_Rec(Tanks a, PowerUp b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
        if (A.intersects(W)) {
            if (b.getSpeed() == 0) {
                a.set_YellowPower(true);
                a.Powers(true);
                b.Yellow_Shield(true);
            }
            if (b.getSpeed() == 1) {
                a.set_GreenPower(true);
                a.Powers(true);
                b.Green_Shield(true);
            }
        }
    }

    public void Direction_Rec(OtherTanks a, Walls b) {
        left = new Rectangle(a.getX(), a.getY() + 1, 1, a.getHeight() - 2);
        right = new Rectangle(a.getX() + a.getWidth() +1, a.getY() + 1, 1, a.getHeight() - 2);
        up = new Rectangle(a.getX() + 1, a.getY(), a.getWidth() - 2, 1);
        down = new Rectangle(a.getX() + 1, a.getY() + a.getHeight() - 2, a.getWidth() - 2, 1);

        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);

        if (up.intersects(W)) {
            a.Direction(1);
            a.y += 1;
        } else if (down.intersects(W)) {
            a.Direction(3);
            a.y -= 1;
        } else if (left.intersects(W)) {
            a.Direction(2);
            a.x += 1;
        } else if (right.intersects(W)) {
            a.Direction(0);
            a.x -= 1;
        }

    }

    public void Collision_Rec(Bullets a, Walls b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
        if (A.intersects(W)) {
            if (b.Destructable) {
                b.Wall_Exploded();
                a.Collision();
                main.walls.getArray().remove(b);
                main.Bullet_Array.remove(a);
            } else {
                a.Collision();
                main.Bullet_Array.remove(a);
            }
        }
    }

    public void OTanks_Rec(OtherTanks a, Bullets b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);

        if (A.intersects(W)) {
            if (b.Player_Bullet()) {
                b.Player_Bullet();
                main.Bullet_Array.remove(b);
                a.Exploded(false);
            }
        }
    }

    public void PTanks_Rec(Tanks a, Bullets b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);

        if (A.intersects(W) && a.Cool_Down() == 0) {
            if (!b.Player_Bullet()) {
                b.Collision();
                main.Bullet_Array.remove(b);
                a.Exploded(false);
            }
        }

    }
}
