package de.nosswald.chess.gui.element.impl.titlebar;

import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.impl.ButtonElement;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class TitleBarButtonElement extends ButtonElement
{
    private final Color color;

    /**
     * @param x     the x position
     * @param y     the y position
     * @param size  the width and the height
     * @param color the {@link Color} to fill the button with
     */
    public TitleBarButtonElement(SizeReference x, SizeReference y, SizeReference size, Color color)
    {
        super(x, y, size, size);

        this.color = color;
    }

    /**
     * Paints the element
     *
     * @param graphics the {@link CustomGraphics}
     */
    @Override
    public void onPaint(CustomGraphics graphics)
    {
        super.onPaint(graphics);
        graphics.drawOval(x, y, width, height, color);
    }

    /**
     * Called if a mouse button was clicked
     *
     * @param event the {@link MouseEvent}
     */
    @Override
    public void onClick(MouseEvent event)
    {
        super.onClick(event);
    }
}
