package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.gui.*;
import de.nosswald.chess.gui.element.Element;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class LabelElement extends Element
{
    private final String title;
    private final Anchor xAnchor, yAnchor;

    private Color color;
    private Font font;

    /**
     * @param title     the title to paint
     * @param x         the x position
     * @param y         the y position
     * @param width     the width
     * @param height    the height
     * @param xAnchor   the horizontal anchor
     * @param yAnchor   the vertical anchor
     */
    public LabelElement(String title, SizeReference x, SizeReference y, SizeReference width, SizeReference height, Anchor xAnchor, Anchor yAnchor)
    {
        super(x, y, width, height);

        this.title = title;
        this.xAnchor = xAnchor;
        this.yAnchor = yAnchor;

        // default values
        color = Color.BLACK;
        font = new Font("Arial", Font.PLAIN, new AbsoluteSize(12).get(0));
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        graphics.translate(x, y).clip(graphics, width, height).drawString(title, xAnchor, yAnchor, color, font);
    }

    /**
     * Sets the text color
     *
     * @param color the color
     * @return itself but with the new color
     */
    public LabelElement setColor(Color color)
    {
        this.color = color;

        return this;
    }

    /**
     * Sets the text font
     *
     * @param font the font
     * @return itself but with the new font
     */
    public LabelElement setFont(Font font)
    {
        this.font = font;

        return this;
    }

    @Override
    public void onClick(MouseEvent event) { }
}
