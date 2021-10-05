package de.nosswald.chess.gui;

import de.nosswald.chess.Chess;

import javax.swing.*;
import java.awt.*;

/**
 * @author Noah Gerber
 * @author Nils Osswald
 */
public class GameFrame extends JFrame
{
    private GameScreen screen;

    public GameFrame()
    {
        // setup frame
        this.setTitle(Chess.APP_NAME + " | v" + Chess.APP_VERSION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int)(screenSize.getWidth() * 0.8), (int)(screenSize.getHeight() * 0.8));

        // load content
        screen = new GameScreen();
        this.setContentPane(screen);

        this.setVisible(true);
    }

    public GameScreen getScreen()
    {
        return screen;
    }
}
