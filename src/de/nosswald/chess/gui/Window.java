package de.nosswald.chess.gui;

import de.nosswald.chess.Chess;

import javax.swing.*;
import java.awt.*;

/**
 * @author Noah Gerber
 */
public class Window extends JFrame
{
    private JPanel userInterface;

    public Window()
    {
        setTitle(Chess.APP_NAME + " v" + Chess.APP_VERSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (size.getWidth() * 0.8), (int) (size.getHeight() * 0.8));

        userInterface = new UserInterface();
        add(userInterface);

        setVisible(true);
    }
}
