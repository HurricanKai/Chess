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
public final class Pawn extends Piece
{
    /**
     * @param side      the {@link Side}
     * @param position  the {@link Position}
     */
    public Pawn(Side side, Position position)
    {
        super("pawn_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, position);
    }

    /**
     * <code>|_|_|_|_|_|_|_|_|<br>
     * |_|_|_|_|_|_|_|_|<br>
     * |_|_|_|_|*|_|_|_|<br>
     * |_|_|_|p|P|_|_|_|<br>
     * |*|_|_|#|#|_|*|_|<br>
     * |P|_|_|_|_|_|*|_|<br>
     * |_|_|_|_|_|_|P|_|<br>
     * |_|_|_|_|_|_|_|_|</code>
     *
     * @return An unfiltered list of all possible {@link Move}'s
     */
    @Override
    public List<Move> getPossibleMoves()
    {
        final List<Move> moves = new ArrayList<>();
        final boolean isFirstMove = this.side == Side.WHITE ? this.position.getRow() == 6 : this.position.getRow() == 1;
        final int rowForward = this.side == Side.WHITE ? this.position.getRow() - 1 : this.position.getRow() + 1;
        final int rowFirst = this.side == Side.WHITE ? this.position.getRow() - 2 : this.position.getRow() + 2;
        final int lastRow = this.side == Side.WHITE ? 0 : 7;
        final Move.Flag flag = lastRow == rowForward ? Move.Flag.PROMOTION : Move.Flag.NONE;
        Piece targetPiece;
        Move lastMove;

        // single step forward
        if (!this.board.hasPiece(new Position(this.position.getCol(), rowForward))
                && this.onBoard(new Position(this.position.getCol(), rowForward))
        )
            moves.add(new Move(this.position, new Position(this.position.getCol(), rowForward), flag, null));

        // double step forward
        if (isFirstMove && !this.board.hasPiece(new Position(this.position.getCol(), rowForward))
                && !this.board.hasPiece(new Position(this.position.getCol(), rowFirst))
                && this.onBoard(new Position(this.position.getCol(), rowFirst))
        )
            moves.add(new Move(this.position, new Position(this.position.getCol(), rowFirst),
                    Move.Flag.DOUBLE_FORWARD, null));

        // opponent attack right
        if (this.onBoard(new Position(this.position.getCol() + 1, rowForward))
                && this.board.hasPiece(new Position(this.position.getCol() + 1, rowForward))
                && this.board.getPiece(new Position(this.position.getCol() + 1, rowForward)).getSide() != this.side
        )
            moves.add(new Move(this.position, new Position(this.position.getCol() + 1, rowForward), flag,
                    this.board.getPiece(new Position(this.position.getCol() + 1, rowForward))));

        // opponent attack left
        if (this.onBoard(new Position(this.position.getCol() - 1, rowForward))
                && this.board.hasPiece(new Position(this.position.getCol() - 1, rowForward))
                && this.board.getPiece(new Position(this.position.getCol() - 1, rowForward)).getSide() != this.side
        )
            moves.add(new Move(this.position, new Position(this.position.getCol() - 1, rowForward), flag,
                    this.board.getPiece(new Position(this.position.getCol() - 1, rowForward))));

        // en passant
        for (int i = -1; i < 3; i += 2)
        {
            targetPiece = this.board.getPiece(new Position(this.position.getCol() + i, this.position.getRow()));

            if (this.board.getHistory().isEmpty())
                break;

            lastMove = this.board.getHistory().get(this.board.getHistory().size() - 1);

            if (targetPiece instanceof Pawn
                    && this.side != targetPiece.getSide()
                    && lastMove.getTo().equals(targetPiece.getPosition())
                    && lastMove.getFlag() == Move.Flag.DOUBLE_FORWARD
            )
                moves.add(new Move(this.position, new Position(this.getPosition().getCol() + i, rowForward),
                        Move.Flag.EN_PASSANT, targetPiece));
        }

        return filterLegalMoves(moves);
    }
}
