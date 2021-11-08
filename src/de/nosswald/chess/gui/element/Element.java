package de.nosswald.chess.gui.element;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;

import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public abstract class Element
{
    protected SizeReference x, y, width, height;
    protected int mouseX, mouseY;

    /**
     * @param x         the x position
     * @param y         the y position
     * @param width     the width
     * @param height    the height
     */
    public Element(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Paints the element
     *
     * @param graphics the {@link CustomGraphics}
     */
    public abstract void onPaint(CustomGraphics graphics);

    /**
     * Called if a mouse button was clicked
     *
     * @param event the {@link MouseEvent}
     */
    public abstract void onClick(MouseEvent event);

    /**
     * Updates the mouse position
     *
     * @param x the x position
     * @param y the y position
     */
    public void setMousePos(int x, int y)
    {
        mouseX = x;
        mouseY = y;
    }
}
