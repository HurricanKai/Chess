package de.nosswald.chess.game.ai;

import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.piece.impl.Bishop;

/**
 * @author Kai Jellinghaus
 */
public final class BasicRecursiveNumericRatingAI implements OpponentPlayer
{
    private final int maxDepth;

    public BasicRecursiveNumericRatingAI(Side side, int maxDepth)
    {
        super(side);
        this.maxDepth = maxDepth;
    }

    @Override
    protected Move selectMove(Board board)
    {
        return selectMoveCore(board, 0);
    }

    private Move selectMoveCore(Board board, int rec)
    {
        final List<Piece> piecesClone = new ArrayList(board.getPieces());
        final Piece piece = piecesClone
                .stream().filter(p -> p.getSide() == side)
                .filter(p -> !p.getPossibleMoves().isEmpty())
                .findFirst().orElse(null);
        final Move move = piece.getPossibleMoves().stream()
                .sorted((a, b) -> rateMove(board, a, rec) > rateMove(board, b, rec))
                .findFirst().orElse(null);

        return move;
    }

    private int rateMove(Board board, Move move, int rec)
    {
        final int baseValue = getValue(board.getPiece(move.getFrom()));
        if (rec > maxDepth) return baseValue;
        board.makeMove(move, true);
        final int value = baseValue - rateMove(selectMove(board));
        board.unmakeMove(move, true);
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