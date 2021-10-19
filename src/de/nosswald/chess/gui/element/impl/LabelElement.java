package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.gui.*;
import de.nosswald.chess.gui.element.Element;

import java.awt.*;
import java.awt.event.MouseEvent;

public final class LabelElement extends Element
{
    private final String title;
    private final Anchor xAnchor, yAnchor;

    private Color color;
    private Font font;

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
        graphics.clip(graphics, width, height).translate(x, y).drawString(title, xAnchor, yAnchor, color, font);
    }

    public LabelElement setColor(Color color)
    {
        this.color = color;

        return this;
    }

    public LabelElement setFont(Font font)
    {
        this.font = font;

        return this;
    }

    @Override
    public void onClick(MouseEvent event) { }
}
