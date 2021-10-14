package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class King extends Piece
{
    private final int startRow;

    public King(Side side, int col, int row)
    {
        super("king_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);

        startRow = side == Side.WHITE ? 7 : 0;
    }

    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();

        // basic movement
        for (int colOffset = -1; colOffset <= 1; colOffset++)
        {
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++)
            {
                if (colOffset != 0 || rowOffset != 0)
                {
                    int c = this.col + colOffset;
                    int r = this.row + rowOffset;

                    if (this.onBoard(c, r))
                    {
                        if (this.board.hasPiece(c, r) && board.getPiece(c, r).getSide() == this.side)
                            continue;

                        moves.add(new int[]{ c, r });
                    }
                }
            }
        }

        // castling
        if (this.board.getHistory().stream().noneMatch(move ->
                move[0] == 4 && move[1] == startRow))
        {
            // short castle
            if (this.board.getHistory().stream().noneMatch(move ->
                    move[0] == 7 && move[1] == startRow)
                    && board.getPiece(7, startRow) instanceof Rook
                    && board.getPiece(7, startRow).getSide() == side
                    && IntStream.range(5, 6).noneMatch(it -> board.hasPiece(it, startRow)))
                moves.add(new int[]{ 6, startRow });

            // long castle
            if (this.board.getHistory().stream().noneMatch(move ->
                    move[0] == 0 && move[1] == startRow)
                    && board.getPiece(0, startRow) instanceof Rook
                    && board.getPiece(0, startRow).getSide() == side
                    && IntStream.range(1, 3).noneMatch(it -> board.hasPiece(it, startRow)))
                moves.add(new int[]{ 2, startRow });
        }

        return filterLegalMoves(moves);
    }

    @Override
    public void setPosition(int col, int row)
    {
        // handle castle movement
        if (this.col == 4)
        {
            // short castle
            if (col == 6)
                board.getPiece(7, startRow).setPosition(5, startRow);
            // long castle
            else if (col == 2)
                board.getPiece(0, startRow).setPosition(3, startRow);
        }

        super.setPosition(col, row);
    }
}
