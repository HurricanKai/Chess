package de.nosswald.chess.game.piece;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public abstract class Piece
{
    private final Side side;
    private int col, row;

    private BufferedImage image;
    private boolean selected;

    public Piece(String fileName, Side side, int col, int row)
    {
        this.side = side;
        this.col = col;
        this.row = row;

        // TODO error print
        try { image = ImageIO.read(new ResourceLocation(fileName, ResourceLocation.Type.PIECE).getFile()); } catch (IOException ignored) { }
    }

    public void paint(Graphics2D graphics, int x, int y, int size)
    {
        graphics.drawImage(image, x, y, size, size, null, null);
    }

    public Side getSide()
    {
        return side;
    }

    public int getCol()
    {
        return col;
    }

    public int getRow()
    {
        return row;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public void setRow(int row)
    {
        this.row = row;
    }
}
