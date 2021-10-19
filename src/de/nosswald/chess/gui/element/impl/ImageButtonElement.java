package de.nosswald.chess.gui.element.impl;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.logger.LoggerLevel;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class ImageButtonElement extends ButtonElement
{
    private BufferedImage image;

    /**
     * @param file      the image file
     * @param x         the x position
     * @param y         the y position
     * @param width     the height of the image and the button
     * @param height    the width of the image and the button
     */
    public ImageButtonElement(File file, SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);

        try
        {
            image = ImageIO.read(file);
        }
        catch (IOException e)
        {
            Chess.getInstance().getLogger().printFormat(LoggerLevel.ERROR, "Image for %s not found!\n%s",
                    getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        super.onPaint(graphics);
        graphics.drawImage(image, x, y, width, height);
    }

    @Override
    public void onClick(MouseEvent event)
    {
        super.onClick(event);
    }
}
