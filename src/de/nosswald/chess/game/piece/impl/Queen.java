package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Noah Gerber
 * @author Nils Osswald
 */
public class Queen extends Piece
{
    public Queen(Side side, int col, int row)
    {
        super("queen_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }

    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();
        int r;

        // right down
        r = this.getRow() + 1;
        for (int c = this.getCol() + 1; c < 8; c++)
        {
            if (!this.canPath(moves, c, r) || r == 7) break;
            r++;
        }

        // right up
        r = this.getRow() - 1;
        for (int c = this.getCol() + 1; c < 8; c++)
        {
            if (!this.canPath(moves, c, r) || r == 0) break;
            r--;
        }

        // left down
        r = this.getRow() + 1;
        for (int c = this.getCol() - 1; c >= 0; c--)
        {
            if (!this.canPath(moves, c, r) || r == 7) break;
            r++;
        }

        // left up
        r = this.getRow() - 1;
        for (int c = this.getCol() - 1; c >= 0; c--)
        {
            if (!this.canPath(moves, c, r) || r == 0) break;
            r--;
        }

        // down
        for (r = this.row + 1; r < 8; r++)
            if (!this.canPath(moves, this.col, r)) break;

        // up
        for (r = this.row - 1; r >= 0; r--)
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
