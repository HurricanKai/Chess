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
public final class Knight extends Piece
{
    /**
     * @param side      the {@link Side}
     * @param position  the {@link Position}
     */
    public Knight(Side side, Position position)
    {
        super("knight_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, position);
    }

    /**
     * <code>|_|_|_|_|_|_|_|_|<br>
     * |_|_|*|_|*|_|_|_|<br>
     * |_|*|_|_|_|*|_|_|<br>
     * |_|_|_|K|_|_|_|_|<br>
     * |_|*|_|_|_|*|_|_|<br>
     * |_|_|*|_|*|_|_|_|<br>
     * |_|_|_|_|_|_|_|_|<br>
     * |_|_|_|_|_|_|_|_|</code>
     *
     * @return An unfiltered list of all possible {@link Move}'s
     */
    @Override
    public List<Move> getPossibleMoves()
    {
        final List<Move> moves = new ArrayList<>();

        addMoves(moves, 2, 4, 1, 2);
        addMoves(moves, 1, 2, 2, 4);

        return filterLegalMoves(moves);
    }

    private void addMoves(List<Move> moves, int minFirst, int maxFirst, int minSecond, int maxSecond)
    {
        for (int colOffset = -minFirst; colOffset <= minFirst; colOffset += maxFirst)
        {
            for (int rowOffset = -minSecond; rowOffset <= minSecond; rowOffset += maxSecond)
            {
                Position to = new Position(this.position.getCol() + colOffset, this.position.getRow() + rowOffset);

                if (this.onBoard(to))
                {
                    if (this.board.hasPiece(to) && this.board.getPiece(to).getSide() == this.side)
                        continue;

                    moves.add(new Move(this.position, to));
                }
            }
        }
    }
}
