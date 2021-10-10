package de.nosswald.chess.gui.element;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;

import java.awt.event.MouseEvent;

public abstract class Element
{
    protected SizeReference x, y, width, height;

    public Element(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void onPaint(CustomGraphics graphics);

    public abstract void onClick(MouseEvent event);
}
