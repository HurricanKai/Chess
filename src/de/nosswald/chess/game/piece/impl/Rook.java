package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Rook extends Piece
{
    public Rook(Side side, int col, int row)
    {
        super("rook_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }

    // TODO check if path is not blocked by any piece
    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();
        // final Board board = Chess.getInstance().getBoard();

        // down
        for (int r = this.row + 1; r < 8; r++)
        {
            if (onBoard(this.col, r))
                moves.add(new int[]{ this.col, r });
        }

        // up
        for (int r = this.row - 1; r >= 0; r--)
        {
            if (onBoard(this.col, r))
                moves.add(new int[]{ this.col, r });
        }

        // right
        for (int c = this.col + 1; c < 8; c++)
        {
            if (onBoard(c, this.row))
                moves.add(new int[]{ c, this.row });
        }

        // left
        for (int c = this.col - 1; c >= 0; c--)
        {
            if (onBoard(c, this.row))
                moves.add(new int[]{ c, this.row });
        }

        return moves;
    }
}
