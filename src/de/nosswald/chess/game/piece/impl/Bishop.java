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
public class Bishop extends Piece
{
    public Bishop(Side side, int col, int row)
    {
        super("bishop_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }

    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();
        int r;

        // right down
        r = getRow() + 1;
        for (int c = getCol() + 1; c < 8; c++)
        {
            if (!this.canPath(moves, c, r) || r == 7) break;
            r++;
        }

        // right up
        r = getRow() - 1;
        for (int c = getCol() + 1; c < 8; c++)
        {
            if (!this.canPath(moves, c, r) || r == 0) break;
            r--;
        }

        // left down
        r = getRow() + 1;
        for (int c = getCol() - 1; c >= 0; c--)
        {
            if (!this.canPath(moves, c, r) || r == 7) break;
            r++;
        }

        // left up
        r = getRow() - 1;
        for (int c = getCol() - 1; c >= 0; c--)
        {
            if (!this.canPath(moves, c, r) || r == 0) break;
            r--;
        }

        return filterLegalMoves(moves);
    }
}
