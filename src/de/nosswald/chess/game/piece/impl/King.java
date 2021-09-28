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

        for (int colOffset = -1; colOffset <= 1; colOffset++)
        {
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++)
            {
                if (colOffset != 0 || rowOffset != 0)
                {
                    int c = this.col + colOffset;
                    int r = this.row + rowOffset;

                    if (this.onBoard(c, r))
                    {
                        if (this.board.hasPiece(c, r) && board.getPiece(c, r).getSide() == this.side)
                            continue;

                        moves.add(new int[]{ c, r });
                    }
                }
            }
        }
        return moves;
    }
}
