package de.nosswald.chess.gui.element;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an element but can also contain other elements
 *
 * @author Nils Osswald
 * @author Noah Gerber
 */
public abstract class Component extends Element
{
    protected final List<Element> elements = new ArrayList<>();

    /**
     * @param x         the x position
     * @param y         the y position
     * @param width     the width
     * @param height    the height
     */
    public Component(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);
    }

    /**
     * Paints the element
     *
     * @param graphics the custom graphics object
     */
    @Override
    public void onPaint(CustomGraphics graphics)
    {
        elements.forEach(element -> element.onPaint(graphics));
    }

    /**
     * Called when a mouse button is being clicked
     *
     * @param event the mouse event
     */
    @Override
    public void onClick(MouseEvent event)
    {
        elements.forEach(element -> element.onClick(event));
    }

    /**
     * Updates the mouse position
     *
     * @param x the x position
     * @param y the y position
     */
    @Override
    public void setMousePos(int x, int y)
    {
        mouseX = x;
        mouseY = y;

        elements.forEach(element -> element.setMousePos(x, y));
    }
}
