package de.nosswald.chess.game.piece;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.impl.King;
import de.nosswald.chess.logger.LoggerLevel;
import de.nosswald.chess.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

    /**
     * @param fileName  the name of the image file
     * @param side      the side
     * @param col       the column
     * @param row       the row
     */
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
            Chess.getInstance().getLogger().printFormat(LoggerLevel.ERROR, "Image for %s not found!\n%s",
                    getClass().getSimpleName(), e.getMessage());
        }
    }

    /**
     * Paints the piece
     *
     * @param graphics  the graphics object
     * @param x         the x position measured in pixels
     * @param y         the y position measured in pixels
     * @param size      the width and the height of the piece measured in pixels
     */
    public void paint(Graphics2D graphics, int x, int y, int size)
    {
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.drawImage(image, x, y, size, size, null, null);
    }

    /**
     * Checks if the given column and row is on the board
     *
     * @param col   the column
     * @param row   the row
     * @return whether the row and the col is on the board or not
     */
    protected boolean onBoard(int col, int row)
    {
        return col >= 0 && row >= 0 && col < 8 && row < 8;
    }

    /**
     * Checks if the piece is able to move to the given {@link #col} and {@link #row}<br>
     * Also adds the move to the given list reference
     *
     * @param moves the reference to the moves list
     * @param col   the column to path to
     * @param row   the row to path to
     * @return whether the piece can move to the given column and row or not
     */
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

    /**
     * Filters out every illegal move
     *
     * @param pseudoLegalMoves the moves to check
     * @return all legal moves
     */
    protected List<int[]> filterLegalMoves(List<int[]> pseudoLegalMoves)
    {
        if (!board.isLegitimacyChecking())
            return pseudoLegalMoves;

        board.setLegitimacyChecking(false);

        final List<int[]> legalMoves = new ArrayList<>();

        for (int[] pseudoLegalMove : pseudoLegalMoves)
        {
            // check if castling is possible
            if (this instanceof King && !board.isInCheck(side))
            {
                final int startRow = side == Side.WHITE ? 7 : 0;

                // short castle
                if (pseudoLegalMove[0] == 6 && pseudoLegalMove[1] == startRow)
                {
                    if (!IntStream.range(5, 6).anyMatch(c -> board.getPieces().stream().filter(piece ->
                            piece.side != side).anyMatch(piece -> piece.getPossibleMoves().stream().anyMatch(move ->
                            move[0] == c && move[1] == startRow))))
                        legalMoves.add(pseudoLegalMove);

                    continue;
                }

                // long castle
                if (pseudoLegalMove[0] == 2 && pseudoLegalMove[1] == startRow)
                {
                    if (!IntStream.range(1, 3).anyMatch(c -> board.getPieces().stream().filter(piece ->
                            piece.side != side).anyMatch(piece -> piece.getPossibleMoves().stream().anyMatch(move ->
                            move[0] == c && move[1] == startRow))))
                        legalMoves.add(pseudoLegalMove);

                    continue;
                }
            }

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

    /**
     * Places the piece on the given column and row<br>
     * Also removes a piece if there is already a piece on the given position
     *
     * @param col   the new column
     * @param row   the new row
     */
    public void setPosition(int col, int row)
    {
        board.getPieces().removeIf(piece -> piece.getCol() == col && piece.getRow() == row);
        this.col = col;
        this.row = row;
    }

    /**
     * @return an unfiltered list of all possible moves
     */
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
