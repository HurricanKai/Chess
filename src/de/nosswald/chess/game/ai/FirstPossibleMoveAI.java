package de.nosswald.chess.game.ai;

/**
 * @author Kai Jellinghaus
 */
public final class FirstPossibleMoveAI implements OpponentPlayer
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
                .findFirst().orElse(null);
        return move;
    }
}