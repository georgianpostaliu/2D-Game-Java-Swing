package main;
import java.awt.*;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {

        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40); // we instantiate our font here and not in the draw method for
        // better performance, as if it was to be instantiated in the game loop, the font would be instantiated 60
        // times, for every frame of the game.
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You win!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is: "  + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawString("Key = " + gp.player.hasKey, 50, 50);
            g2.drawString("Items = " + gp.player.hasItem, 50, 100);

            //TIME
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize *11, 50);
        }


    }
}
