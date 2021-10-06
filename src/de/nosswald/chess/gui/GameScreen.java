package de.nosswald.chess.gui;

import com.sun.xml.internal.ws.util.StringUtils;
import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Side;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author Noah Gerber
 * @author Nils Osswald
 */
public class GameScreen extends JPanel
{
    public GameScreen()
    {
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Chess.getInstance().getBoard().onClick(
                        e.getX() / (GameScreen.this.getHeight() / 8), e.getY() / (GameScreen.this.getHeight() / 8));
            }
        });
    }

    @Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        if (Chess.getInstance().getBoard().isGameOver())
        {
            final Side nextMove = Chess.getInstance().getBoard().getNextMove();
            final String message = nextMove == null ?
                    "Stalemate" : nextMove.flip().toString() + " won";

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));
            g2d.drawString(message, (this.getHeight() - g2d.getFontMetrics().stringWidth(message)) / 2,
                    (this.getHeight() + g2d.getFontMetrics().getHeight()) / 2);

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .25F));
        }

        Chess.getInstance().getBoard().paint(g2d, this.getHeight());

        if (Chess.DEBUG_MODE)
        {
            int y = 25;

            for (int[] move : Chess.getInstance().getBoard().getHistory())
            {
                g2d.drawString(Arrays.toString(move), this.getHeight() + 10, y);
                y+=35;
            }
        }

        repaint();
    }
}
