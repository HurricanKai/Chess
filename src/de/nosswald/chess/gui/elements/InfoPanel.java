package de.nosswald.chess.gui.elements;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel
{
    public InfoPanel()
    {
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(210, 194, 128));
        g2d.fillRect(getX(),getY(),getWidth(),getHeight());
        super.paint(g);
    }
}
