package de.nosswald.chess.gui;

import com.sun.xml.internal.ws.message.RootElementSniffer;
import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.elements.InfoPanel;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JPanel
{
    private JPanel infoPanel;

    public UserInterface()
    {
        infoPanel = new InfoPanel();
        //infoPanel.setBounds(getHeight(), 0, getWidth() - getHeight(), getHeight());
        add(infoPanel);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //Chess.getInstance().getBoard().paint(g2d);

    }
}
