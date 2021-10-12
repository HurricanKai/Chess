package de.nosswald.chess.gui;

import java.awt.*;

public final class CustomGraphics
{
    private final Graphics graphics;
    private final int offX, offY, width, height;

    public CustomGraphics(Graphics graphics)
    {
        final Rectangle bounds = graphics.getClipBounds();

        this.graphics = graphics;
        this.offX = bounds.x;
        this.offY = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
    }

    public CustomGraphics(Graphics graphics, int offX, int offY, int width, int height)
    {
        this.graphics = graphics;
        this.offX = offX;
        this.offY = offY;
        this.width = width;
        this.height = height;
    }

    public CustomGraphics clip(CustomGraphics g, SizeReference width, SizeReference height)
    {
        return new CustomGraphics(g.graphics, offX, offY, width.get(g.width), height.get(g.height));
    }

    public CustomGraphics translate(SizeReference x, SizeReference y)
    {
        return new CustomGraphics(graphics, offX + x.get(width), offY + y.get(height), width - x.get(width), height - y.get(height));
    }

    public void drawRect(SizeReference x, SizeReference y, SizeReference width, SizeReference height, Color color)
    {
        graphics.setColor(color);
        graphics.fillRect(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height));
    }

    public void drawRoundRect(SizeReference x, SizeReference y, SizeReference width, SizeReference height, SizeReference argWidth, SizeReference argHeight, Color color)
    {
        graphics.setColor(color);
        graphics.fillRoundRect(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height), argWidth.get(this.width), argHeight.get(this.height));
    }

    public void drawOval(SizeReference x, SizeReference y, SizeReference width, SizeReference height, Color color)
    {
        graphics.setColor(color);
        graphics.fillOval(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height));
    }

    public void drawImage(Image image, SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        graphics.drawImage(image, offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height), null, null);
    }

    public void drawSquareImage(Image image, SizeReference x, SizeReference y, SizeReference size)
    {
        graphics.drawImage(image, offX + x.get(this.width), offY + y.get(this.height), size.get(this.height), size.get(this.height), null, null);
    }

    public void drawString(String string, SizeReference x, SizeReference y, SizeReference width, SizeReference height, Anchor xAnchor, Anchor yAnchor, Color color, Font font)
    {
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int posX = offX + x.get(this.width);
        int posY = offY + y.get(this.height);

        if (xAnchor == Anchor.CENTER)
            posX = offX + x.get(this.width) + (width.get(this.width) - fontMetrics.stringWidth(string)) / 2;
        else if (xAnchor == Anchor.POSITIVE)
            posX = x.get(this.width) + width.get(this.width) - fontMetrics.stringWidth(string);

        if (yAnchor == Anchor.CENTER)
            posY = offY + y.get(this.height) + (height.get(this.height) - fontMetrics.getHeight()) / 2;
        else if (yAnchor == Anchor.POSITIVE)
            posY = offY + y.get(this.height) + height.get(this.height) - fontMetrics.getHeight();

        posY += fontMetrics.getAscent();

        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(string, posX, posY);
    }

    public int getOffX()
    {
        return offX;
    }

    public int getOffY()
    {
        return offY;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
