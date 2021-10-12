package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Element;
import de.nosswald.chess.logger.Logger;

import java.awt.event.MouseEvent;

public abstract class ButtonElement extends Element
{
    private Action action;
    private CustomGraphics g;

    public ButtonElement(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        g = graphics;
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
        return (mouseX >= g.getOffX() + x.get(g.getWidth())
                && mouseX <= g.getOffX() + x.get(g.getWidth()) + width.get(g.getWidth())
                && mouseY >= g.getOffY() + y.get(g.getHeight())
                && mouseY <= g.getOffY() + y.get(g.getHeight()) + height.get(g.getHeight()));
    }

    public ButtonElement setAction(Action action)
    {
        this.action = action;
        return this;
    }
}
