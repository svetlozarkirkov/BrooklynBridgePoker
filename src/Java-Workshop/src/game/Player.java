package game;

import gfx.Assets;

import java.awt.*;

public class Player {
    private int x, y;
    private int velocity;
    private int width, height;
    private int health;

    private Rectangle boundingBox;

    public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;

    public Player() {
        this.x = 200;
        this.y = 300;
        this.width = 95;
        this.height = 130;
        this.health = 50;
        this.velocity = 2;
        this.boundingBox = new Rectangle(this.width, this.height);

        goingUp = false;
        goingDown = false;
        goingLeft = false;
        goingRight = false;
    }

    public int getHealth() {
        return this.health;
    }

    //Checks if the player intersects with something
    public boolean Intersects(Rectangle r) {
        if(this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }

    //Update the movement of the player
    public void tick() {
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

        if(goingUp) {
            this.y -= this.velocity;
        }
        if(goingDown) {
            this.y += this.velocity;
        }
        if(goingLeft) {
            this.x -= this.velocity;
        }
        if(goingRight) {
            this.x += this.velocity;
        }
    }

    //Draws the player
    public void render(Graphics g) {
        g.drawImage(Assets.player1, this.x, this.y, null);
    }
}
