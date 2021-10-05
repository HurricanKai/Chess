package de.nosswald.chess.game.piece;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.impl.Pawn;
import de.nosswald.chess.game.piece.impl.Queen;
import de.nosswald.chess.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
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

        try
        {
            image = ImageIO.read(new ResourceLocation(fileName, ResourceLocation.Type.PIECE).getFile());
        }
        catch (IOException e)
        {
            System.out.printf("[ERROR] Image for %s not found!\n%s\n", getClass().getSimpleName(), e.getMessage());
        }
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
                return false;
            }
            moves.add(new int[]{ col, row });
        }
        return true;
    }

    protected List<int[]> filterLegalMoves(List<int[]> pseudoLegalMoves)
    {
        if (!board.isLegitimacyChecking())
            return pseudoLegalMoves;

        board.setLegitimacyChecking(false);

        final List<int[]> legalMoves = new ArrayList<>();

        for (int[] pseudoLegalMove : pseudoLegalMoves)
        {
            final int col = this.col;
            final int row = this.row;
            final Piece piece = board.getPiece(col, row);
            final List<Piece> oldPieces = new ArrayList<>(board.getPieces());

            piece.setPosition(pseudoLegalMove[0], pseudoLegalMove[1]);

            if (!board.isInCheck(side))
                legalMoves.add(pseudoLegalMove);

            // reset
            setPosition(col, row);
            board.getPieces().clear();
            board.getPieces().addAll(oldPieces);
        }

        board.setLegitimacyChecking(true);

        return legalMoves;
    }

    public void setPosition(int col, int row)
    {
        board.getPieces().removeIf(piece -> piece.getCol() == col && piece.getRow() == row);
        this.col = col;
        this.row = row;

        if (this instanceof Pawn && side == Side.WHITE ? row == 0 : row == 7)
        {
            // TODO make piece selectable
            board.getPieces().remove(this);
            board.getPieces().add(new Queen(side, col, row));
        }
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
}
