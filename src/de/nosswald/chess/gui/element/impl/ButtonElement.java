package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Element;

import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public abstract class ButtonElement extends Element
{
    private Action action;
    private CustomGraphics graphics;

    /**
     * @param x         the x position
     * @param y         the y position
     * @param width     the width
     * @param height    the height
     */
    public ButtonElement(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);
    }

    @Override
    public void onPaint(CustomGraphics g)
    {
        graphics = g;
    }

    /**
     * Holds the function that is being called when the button is clicked
     */
    public interface Action
    {
        void onAction();
    }

    @Override
    public void onClick(MouseEvent event)
    {
        if (isHovered())
            action.onAction();
    }

    /**
     * @return if the mouse cursor is currently hovering the button
     */
    public boolean isHovered()
    {
        return (mouseX >= graphics.getOffX() + x.get(graphics.getWidth())
                && mouseX <= graphics.getOffX() + x.get(graphics.getWidth()) + width.get(graphics.getWidth())
                && mouseY >= graphics.getOffY() + y.get(graphics.getHeight())
                && mouseY <= graphics.getOffY() + y.get(graphics.getHeight()) + height.get(graphics.getHeight()));
    }

    /**
     * Sets the click action
     *
     * @param action the action
     * @return itself but with the click action
     */
    public ButtonElement setAction(Action action)
    {
        this.action = action;
        return this;
    }
}
