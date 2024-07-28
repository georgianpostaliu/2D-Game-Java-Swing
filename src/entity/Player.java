package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY; // where we draw player on the screen, as the screen moves once with the character
    //this coordinates never change, as the character its meant to be always at the center of the screen
    public int hasKey = 0; // variable to store the number of keys the player has.
    public int hasItem = 0; // variable to store the number of items the player has.
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
    //necessary, because the origin of every tile is in the left corner, because of that the player character will be
    // a bit off the actual center, by dividing half of the sides of each tile for X and Y, we can resolve this issue.
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32; //hit box parameters

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // player default values, position, speed
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21; // where we draw the character in world map, this coordinates will always change,
        // as we move along in the world map, the screen will follow us, keeping us centered, but our position
        // related to the world map will always change
        speed = 6;
        direction = "down"; // player character will always face down, facing us
    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_right_2.png"));

            idleUp = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_idle_up.png"));
            idleDown = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_idle_down.png"));
            idleLeft = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_idle_left.png"));
            idleRight = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/mc_idle_right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i) {

        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;
                case "Boots":
                    speed += 2;
                    gp.obj[i] = null;
                    break;
                case "Uranium":
                    hasItem++;
                    gp.obj[i] = null;
                    if (hasItem > 3) {
                        gp.ui.gameFinished = true;

                    }
                    break;
            }
        }
        //any number is fine, as long as it's not used by the object array's index
    }
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white); //sets a color to use for drawing objects
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); //draw a rectangle and fills it with the specified color

        BufferedImage image = null;
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    } else {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    } else {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    } else {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    } else {
                        image = right2;
                    }
                    break;
            }
        } else {
            switch (direction) {
                case "up":
                    image = idleUp;
                    break;
                case "down":
                    image = idleDown;
                    break;
                case "left":
                    image = idleLeft;
                    break;
                case "right":
                    image = idleRight;
                    break;
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        //image observer, draws as much of the specified image as has already been scaled to fit inside the specified
        // rectangle
    }
}
