package de.nosswald.chess.gui.element;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Component extends Element
{
    protected final List<Element> elements = new ArrayList<>();

    public Component(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        elements.forEach(element -> element.onPaint(graphics));
    }

    @Override
    public void onClick(MouseEvent event)
    {
        elements.forEach(element -> element.onClick(event));
    }

    @Override
    public void setMousePos(int x, int y)
    {
        elements.forEach(element -> element.setMousePos(x, y));
    }
}
