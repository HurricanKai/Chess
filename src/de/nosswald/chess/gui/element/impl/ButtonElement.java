package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Element;

import java.awt.event.MouseEvent;

public abstract class ButtonElement extends Element
{
    private Action action;

    public ButtonElement(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);
    }

    public interface Action
    {
        void onAction();
    }

    @Override
    public void onClick(MouseEvent event)
    {
        if (isHovered(event))
            action.onAction();
    }

    protected boolean isHovered(MouseEvent event)
    {
        return false; // event.getX() >= x && event.getX() <= x + width && event.getY() >= y && event.getY() <= y + height;
    }

    public ButtonElement setAction(Action action)
    {
        this.action = action;

        return this;
    }
}
