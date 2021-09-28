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
public class King extends Piece
{
    public King(Side side, int col, int row)
    {
        super("king_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }

    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();

        for (int c = getCol() - 1; c <= getCol() + 1; c++)
        {
            for (int r = getRow() - 1; r <= getRow() + 1; r++)
            {
                if(c == getCol() && r == getRow())
                    break;

                if (!this.canPath(moves, c, r)) break;
            }
        }
        return moves;
    }
}
