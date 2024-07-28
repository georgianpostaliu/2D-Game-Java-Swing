package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // scaling, necessary for modern display resolutions
    public final int tileSize = originalTileSize * scale; // 48X48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    // Created tile size, we scaled it for moder display resolutions, used the new tile size to determine the size of
    // the Game Panel. Resulting in a resolution of 768x576.

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(); // instantiating the main.KeyHandler
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread; //thread of execution for the game was constructed using a separate Runnable object "Thread".
    /* When the run() method calls, the code specified in run() is executed. It can be called multiple times. */

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH); // instantiating player obj and we pass GamePanel class and keyH
    public SuperObject obj[] = new SuperObject[13]; // 10 obj to be displayed
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improves game rendering performance
        this.addKeyListener(keyH); // recognize the key input of the player
        this.setFocusable(true);
    }

    public void setupGame () {

        aSetter.setObject();

        playMusic(0);
    }
    public void startGameThread() {

        gameThread = new Thread(this); // this = main.GamePanel, we pass main.GamePanel class to the constructor
        // Thread() to instantiate a new object
        gameThread.start();
    }

    //    @Override
//    public void run() {
//    //this is where we will create the game loop
//
//        double drawInterval = 1000000000/FPS; // 1 sec per 60 frames => 0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval; // the time when the game draws again (update +
//        repaint)
//
//        while(gameThread != null) { //as long as the gameThread exists, it will keep running
//            // 1. UPDATE: update information such as character positions
//            update();
//
//            // 2. DRAW: draw the screen with the updated information
//            repaint(); // that is how we call the paintComponent method
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; // we transform nanoseconds in milliseconds
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime); //.sleep method only works with milliseconds and not nanoseconds.
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    @Override
    public void run() {
        /*this is where we will create the game loop
        this is the delta time method, it does the same as the previous method, but it is more stable.
        it allows us to move the player character at the same speed, independently of system or frame rate.*/
        double drawInterval = 1000000000 / FPS; // 1 sec per 60 frames => 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        // Graphics - class that has many functions to draw objects on the screen
        super.paintComponent(g); //super - the parent class of Graphics, JPanel
        //we convert g from class Graphics to Graphics2D. Graphics2D class extends the Graphics class to provide more
        //sophisticated control over geometry, coordinate transformations, color management, and text layout.
        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        //regarding the time it takes to draw each frame
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        // TILE
        tileM.draw(g2);

        //OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj [i] != null) {
                obj [i].draw (g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        //DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose(); //dispose of this graphics context and release any system resources that it is using
    }
    public void playMusic (int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {

        sound.stop();
    }
    public void playSE (int i) { //sound effects method.

        sound.setFile(i);
        sound.play();
    }
}
