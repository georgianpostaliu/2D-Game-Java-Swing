package entity;

// stores variables that will be used in player, monster and NPC classes

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idleUp, idleDown, idleLeft, idleRight;
    //describe an Image with an accessible buffer of image data. Used to store our image files
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea; // hitbox of the player character
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
