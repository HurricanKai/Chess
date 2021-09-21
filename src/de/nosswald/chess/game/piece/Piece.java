package de.nosswald.chess.game.piece;

import de.nosswald.chess.game.Side;

import java.awt.*;

/**
 * @author Nils Osswald
 */
public abstract class Piece
{
    private Side side;
    private int col, row;

    public Piece(Side side, int col, int row)
    {
        this.side = side;
        this.col = col;
        this.row = row;
    }

    public abstract void paint(Graphics2D graphics, int size);

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
}
