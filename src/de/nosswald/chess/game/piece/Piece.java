package de.nosswald.chess.game.piece;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public abstract class Piece
{
    protected final Side side;
    protected int col, row;

    protected final Board board;

    private BufferedImage image;

    public Piece(String fileName, Side side, int col, int row)
    {
        this.side = side;
        this.col = col;
        this.row = row;

        board = Chess.getInstance().getBoard();

        // TODO error print
        try { image = ImageIO.read(new ResourceLocation(fileName, ResourceLocation.Type.PIECE).getFile()); } catch (IOException ignored) { }
    }

    public void paint(Graphics2D graphics, int x, int y, int size)
    {
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.drawImage(image, x, y, size, size, null, null);
    }

    protected boolean onBoard(int col, int row)
    {
        return col >= 0 && row >= 0 && col < 8 && row < 8;
    }

    protected boolean canPath(List<int[]> moves, int col, int row)
    {
        if (onBoard(col, row))
        {
            if (board.hasPiece(col, row))
            {
                if (board.getPiece(col, row).getSide() != this.side)
                    moves.add(new int[]{ col, row });
                return true;
            }
            moves.add(new int[]{ col, row });
        }
        return false;
    }

    public abstract List<int[]> getPossibleMoves();

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

    public void setCol(int col)
    {
        this.col = col;
    }

    public void setRow(int row)
    {
        this.row = row;
    }
}
