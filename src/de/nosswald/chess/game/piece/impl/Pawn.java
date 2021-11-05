package de.nosswald.chess.game.piece.impl;

import com.sun.istack.internal.NotNull;
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
        Piece targetPiece;
        Move lastMove;

        // single step forward
        if (!this.board.hasPiece(new Position(this.position.getCol(), rowForward))
                && this.onBoard(new Position(this.position.getCol(), rowForward))
        )
            moves.add(new Move(this.position, new Position(this.position.getCol(), rowForward)));

        // double step forward
        if (isFirstMove && !this.board.hasPiece(new Position(this.position.getCol(), rowForward))
                && !this.board.hasPiece(new Position(this.position.getCol(), rowFirst))
                && this.onBoard(new Position(this.position.getCol(), rowFirst))
        )
            moves.add(new Move(this.position, new Position(this.position.getCol(), rowFirst)));

        // opponent attack right
        if (this.onBoard(new Position(this.position.getCol() + 1, rowForward))
                && this.board.hasPiece(new Position(this.position.getCol() + 1, rowForward))
                && this.board.getPiece(new Position(this.position.getCol() + 1, rowForward)).getSide() != this.side
        )
            moves.add(new Move(this.position, new Position(this.position.getCol() + 1, rowForward)));

        // opponent attack left
        if (this.onBoard(new Position(this.position.getCol() - 1, rowForward))
                && this.board.hasPiece(new Position(this.position.getCol() - 1, rowForward))
                && this.board.getPiece(new Position(this.position.getCol() - 1, rowForward)).getSide() != this.side
        )
            moves.add(new Move(this.position, new Position(this.position.getCol() - 1, rowForward)));

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
                    && lastMove.getFrom().getRow() == (targetPiece.getSide() == Side.WHITE ? 6 : 1)
                    && lastMove.getTo().getRow() == (targetPiece.getSide() == Side.WHITE ? 4 : 3)
            )
                moves.add(new Move(this.position, new Position(this.getPosition().getCol() + i, rowForward)));
        }

        return filterLegalMoves(moves);
    }

    /**
     * Places the {@link Piece} on the given {@link Position}.
     * Also removes a {@link Piece} if there is already a {@link Piece} on the given {@link Position}
     *
     * @param position the new {@link Position}
     */
    @Override
    public void setPosition(@NotNull Position position)
    {
        super.setPosition(position);

        if (this.board.getHistory().size() < 2) return;
        final Move lastMove = this.board.getHistory().get(this.board.getHistory().size() - 2);

        this.board.getPieces().removeIf(p ->
                p instanceof Pawn
                        && this.side != p.getSide()
                        && lastMove.getTo().equals(p.getPosition())
                        && lastMove.getFrom().getRow() == (p.getSide() == Side.WHITE ? 6 : 1)
                        && lastMove.getTo().getRow() == (p.getSide() == Side.WHITE ? 4 : 3)
                        && this.getPosition().getCol() == p.getPosition().getCol()
                        && this.getPosition().getRow() == (p.getSide() == Side.WHITE ? 5 : 2)
        );

        if (this.getPosition().getRow() == (this.side == Side.WHITE ? 0 : 7))
        {
            // TODO make piece selectable
            this.board.getPieces().remove(this);
            this.board.getPieces().add(new Queen(this.side, position));
        }
    }
}
