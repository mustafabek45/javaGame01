package game;

import javax.swing.*;

public class MyGame extends JFrame {
    public MyGame(String title) {
        super(title);
    }

    public static void main(String[] args) {

        MyGame screen=new MyGame("My Game");

        screen.setResizable(false);
        screen.setFocusable(false);

        screen.setSize(800,600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameScreen game = new GameScreen();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        screen.add(game);
        screen.setVisible(true);
    }

}