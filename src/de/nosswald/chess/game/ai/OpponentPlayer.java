package de.nosswald.chess.game.ai;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.logger.LoggerLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class OpponentPlayer
{
    private final Board board;
    private final Side side;

    public OpponentPlayer(Board board, Side side)
    {
        this.board = board;
        this.side = side;
    }

    public void awaitResponse()
    {
        // perform random move with random piece
        List<Piece> piecesClone = new ArrayList(board.getPieces());
        Piece piece = piecesClone
                .stream().filter(p -> p.getSide() == side)
                .filter(p -> !p.getPossibleMoves().isEmpty())
                .min((p1, p2) -> p1 == p1 ? 0 : (ThreadLocalRandom.current().nextBoolean() ? -1 : 1))
                .get();

        int[] move = piece.getPossibleMoves().stream()
                .min((m1, m2) -> m1 == m2 ? 0 : (ThreadLocalRandom.current().nextBoolean() ? -1 : 1))
                .get();

        Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                "AI moved %s from (%d|%d) to (%d|%d)", piece.getClass().getSimpleName(),
                piece.getCol(), piece.getRow(), move[0], move[1]);
        // send the move to the board
        board.makeMove(piece, move[0], move[1]);
    }
}
