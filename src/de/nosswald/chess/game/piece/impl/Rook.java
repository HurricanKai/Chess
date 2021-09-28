package de.nosswald.chess.game.piece.impl;

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

    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();

        // down
        for (int r = this.row + 1; r < 8; r++)
            if (!this.canPath(moves, this.col, r)) break;

        // up
        for (int r = this.row - 1; r >= 0; r--)
            if (!this.canPath(moves, this.col, r)) break;

        // right
        for (int c = this.col + 1; c < 8; c++)
            if (!this.canPath(moves, c, this.row)) break;

        // left
        for (int c = this.col - 1; c >= 0; c--)
            if (!this.canPath(moves, c, this.row)) break;

        return moves;
    }
}
