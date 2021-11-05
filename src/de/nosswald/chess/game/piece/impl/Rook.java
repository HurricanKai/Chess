package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Move;
import de.nosswald.chess.game.Position;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class Rook extends Piece
{
    /**
     * @param side      the {@link Side}
     * @param position  the {@link Position}
     */
    public Rook(Side side, Position position)
    {
        super("rook_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, position);
    }

    /**
     * <code>|_|_|_|*|_|_|_|_|<br>
     * |_|_|_|*|_|_|_|_|<br>
     * |_|_|_|*|_|_|_|_|<br>
     * |*|*|*|R|*|*|*|*|<br>
     * |_|_|_|*|_|_|_|_|<br>
     * |_|_|_|*|_|_|_|_|<br>
     * |_|_|_|*|_|_|_|_|<br>
     * |_|_|_|*|_|_|_|_|</code>
     *
     * @return An unfiltered list of all possible {@link Move}'s
     */
    @Override
    public List<Move> getPossibleMoves()
    {
        final List<Move> moves = new ArrayList<>();

        // down
        for (int r = this.position.getRow() + 1; r < 8; r++)
            if (!this.canPath(moves, this.position, new Position(this.position.getCol(), r))) break;

        // up
        for (int r = this.position.getRow() - 1; r >= 0; r--)
            if (!this.canPath(moves, this.position, new Position(this.position.getCol(), r))) break;

        // right
        for (int c = this.position.getCol() + 1; c < 8; c++)
            if (!this.canPath(moves, this.position, new Position(c, this.position.getRow()))) break;

        // left
        for (int c = this.position.getCol() - 1; c >= 0; c--)
            if (!this.canPath(moves, this.position, new Position(c, this.position.getRow()))) break;

        return filterLegalMoves(moves);
    }
}
