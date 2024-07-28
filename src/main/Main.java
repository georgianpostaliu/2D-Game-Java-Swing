package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame(); //window creation
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the window will close, eg: when pressing X
        window.setResizable(false); // fixed window size
        window.setTitle("2d Adventure"); // Title displayed on top of the window

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // adding the Game Panel to the window

        window.pack(); // window can be sized to fit the preferred size and layouts of its subcomponents (=main.GamePanel)


        window.setLocationRelativeTo(null); //if no value is specified, by default the window is centered on the screen
        window.setVisible(true); //window is visible

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
