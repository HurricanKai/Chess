package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Element;

import java.awt.event.MouseEvent;

public abstract class ButtonElement extends Element
{
    private Action action;
    private CustomGraphics graphics;

    public ButtonElement(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);
    }

    @Override
    public void onPaint(CustomGraphics g)
    {
        graphics = g;
    }

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

    protected boolean isHovered()
    {
        return (mouseX >= graphics.getOffX() + x.get(graphics.getWidth())
                && mouseX <= graphics.getOffX() + x.get(graphics.getWidth()) + width.get(graphics.getWidth())
                && mouseY >= graphics.getOffY() + y.get(graphics.getHeight())
                && mouseY <= graphics.getOffY() + y.get(graphics.getHeight()) + height.get(graphics.getHeight()));
    }

    public ButtonElement setAction(Action action)
    {
        this.action = action;
        return this;
    }
}
