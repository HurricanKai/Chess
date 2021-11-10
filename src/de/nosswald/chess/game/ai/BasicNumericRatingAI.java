package de.nosswald.chess.game.ai;

import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Move;
import de.nosswald.chess.game.piece.impl.Bishop;

/**
 * @author Kai Jellinghaus
 */
public final class BasicNumericRatingAI implements OpponentPlayer
{
    @Override
    protected Move selectMove(Board board)
    {
        final List<Piece> piecesClone = new ArrayList(board.getPieces());
        final Piece piece = piecesClone
                .stream().filter(p -> p.getSide() == side)
                .filter(p -> !p.getPossibleMoves().isEmpty())
                .findFirst().orElse(null);
        final Move move = piece.getPossibleMoves().stream()
                .sorted((a, b) -> rateMove(board, a) > rateMove(board, b))
                .findFirst().orElse(null);

        return move;
    }

    private int rateMove(Board board, Move move)
    {
        return getValue(board.getPiece(move.getFrom()));
    }

    private int getValue(Piece piece)
    {
        return 
            (piece instanceof Pawn) ? 100
            :   (piece instanceof Knight) ? 300
            :   (piece instanceof Bishop) ? 300
            :   (piece instanceof Rook) ? 500
            :   (piece instanceof Queen) ? 900
            :   (piece instanceof King) ? Integer.MAX_VALUE
            : 0;
    }
}