package de.nosswald.chess.gui.element.impl.titlebar;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.impl.ButtonElement;

import java.awt.*;
import java.awt.event.MouseEvent;

public final class TitleBarButtonElement extends ButtonElement
{
    private final Color color;

    public TitleBarButtonElement(SizeReference x, SizeReference y, SizeReference size, Color color)
    {
        super(x, y, size, size);

        this.color = color;
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        super.onPaint(graphics);
        graphics.drawOval(x, y, width, height, color);
    }

    @Override
    public void onClick(MouseEvent event)
    {
        super.onClick(event);
    }
}
