package de.nosswald.chess.gui;

import de.nosswald.chess.Chess;

import javax.swing.*;
import java.awt.*;

/**
 * @author Noah Gerber
 * @author Nils Osswald
 */
public class GameScreen extends JPanel
{
    @Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        Chess.getInstance().getBoard().paint(g2d, getHeight());
    }
}
