package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Move;
import de.nosswald.chess.game.Position;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Noah Gerber
 * @author Nils Osswald
 */
public final class Bishop extends Piece
{
    /**
     * @param side      the {@link Side}
     * @param position  the {@link Position}
     */
    public Bishop(Side side, Position position)
    {
        super("bishop_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, position);
    }

    /**
     * <code>|*|_|_|_|_|_|*|_|<br>
     * |_|*|_|_|_|*|_|_|<br>
     * |_|_|*|_|*|_|_|_|<br>
     * |_|_|_|B|_|_|_|_|<br>
     * |_|_|*|_|*|_|_|_|<br>
     * |_|*|_|_|_|*|_|_|<br>
     * |*|_|_|_|_|_|*|_|<br>
     * |_|_|_|_|_|_|_|*|</code>
     *
     * @return An unfiltered list of all possible {@link Move}'s
     */
    @Override
    public List<Move> getPossibleMoves()
    {
        final List<Move> moves = new ArrayList<>();
        int r;

        // right down
        r = this.position.getRow() + 1;
        for (int c = this.position.getCol() + 1; c < 8; c++)
        {
            if (!this.canPath(moves, this.position, new Position(c, r)) || r == 7) break;
            r++;
        }

        // right up
        r = this.position.getRow() - 1;
        for (int c = this.position.getCol() + 1; c < 8; c++)
        {
            if (!this.canPath(moves, this.position, new Position(c, r)) || r == 0) break;
            r--;
        }

        // left down
        r = this.position.getRow() + 1;
        for (int c = this.position.getCol() - 1; c >= 0; c--)
        {
            if (!this.canPath(moves, this.position, new Position(c, r)) || r == 7) break;
            r++;
        }

        // left up
        r = this.position.getRow() - 1;
        for (int c = this.position.getCol() - 1; c >= 0; c--)
        {
            if (!this.canPath(moves, this.position, new Position(c, r)) || r == 0) break;
            r--;
        }

        return filterLegalMoves(moves);
    }
}
