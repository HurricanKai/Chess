package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.Frame;
import de.nosswald.chess.gui.SizeReference;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class ResizeElement extends ButtonElement
{
    private int posX, posY;

    /**
     * @param x      the x position
     * @param y      the y position
     * @param width  the width
     * @param height the height
     */
    public ResizeElement(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);
    }

    @Override
    public void onPaint(CustomGraphics g)
    {
        super.onPaint(g);

        g.drawRect(x, y, width, height, isHovered() ? Color.BLACK : new Color(0, 0, 0, 50));
    }

    /**
     * Called when the mouse is dragging the frame
     *
     * @param event the event
     */
    public void onDrag(MouseEvent event)
    {
        final Frame frame = Chess.getInstance().getFrame();

        if (isHovered())
        {
            frame.setSize(
                    frame.getWidth() + event.getX() - posX,
                    frame.getHeight() + event.getY() - posY
            );
        }
    }

    /**
     * Called when a mouse button is hold down
     *
     * @param event the event
     */
    public void onPress(MouseEvent event)
    {
        if (!isHovered()) return;
        posX = event.getX();
        posY = event.getY();
    }

    @Override
    public void onClick(MouseEvent event) { }
}
