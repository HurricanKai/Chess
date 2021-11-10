package de.nosswald.chess.game.ai;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Move;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.logger.LoggerLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 * @author Kai Jellinghaus
 */
public abstract class OpponentPlayer
{
    private final Side side;

    /**
     * @param side the {@link Side} to control
     */
    public OpponentPlayer(Side side)
    {
        this.side = side;
    }

    /**
     * Responds with the best possible {@link Move}
     */
    public void awaitResponse()
    {
        // perform first possible move
        final Board board = Chess.getInstance().getBoard();
        final Move move = selectMove(board);

        // send the move to the board
        Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                "AI moved %s from %s to %s", piece.getClass().getSimpleName(),
                piece.getPosition(), move.getTo().toString()
        );
        board.makeMove(move, false);
    }

    protected abstract Move selectMove(Board board);
}
