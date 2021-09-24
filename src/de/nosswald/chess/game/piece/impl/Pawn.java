package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.*;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Pawn extends Piece
{
    public Pawn(Side side, int col, int row)
    {
        super("pawn_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }

    // TODO en passant rule
    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();
        final Board board = Chess.getInstance().getBoard();
        final boolean isFirstMove = this.side == Side.WHITE ? this.getActualRow() == 2 : this.getActualRow() == 7;
        final int rowForward = this.side == Side.WHITE ? this.row - 1 : this.row + 1;
        final int rowFirst = this.side == Side.WHITE ? this.row - 2 : this.row + 2;

        // single step forward
        if (!board.hasPiece(col, rowForward) && onBoard(col, rowForward))
            moves.add(new int[]{ col, rowForward });

        // double step forward
        if (isFirstMove && !board.hasPiece(col, rowForward) && !board.hasPiece(col, rowFirst) && onBoard(col, rowFirst))
            moves.add(new int[]{ col, rowFirst });

        // opponent attack right
        if (onBoard(col + 1, rowForward) && board.hasPiece(col + 1, rowForward)
                && board.getPiece(col + 1, rowForward).getSide() != this.side)
            moves.add(new int[]{ col + 1, rowForward });

        // opponent attack left
        if (onBoard(col - 1, rowForward) && board.hasPiece(col - 1, rowForward)
                && board.getPiece(col - 1, rowForward).getSide() != this.side)
            moves.add(new int[]{ col - 1, rowForward });

        return moves;
    }
}
