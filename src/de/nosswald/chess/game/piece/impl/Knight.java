package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Knight extends Piece
{
    public Knight(Side side, int col, int row)
    {
        super("knight_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }

    @Override
    public List<int[]> getPossibleMoves()
    {
        final List<int[]> moves = new ArrayList<>();

        addMoves(moves, 2, 4, 1, 2);
        addMoves(moves, 1, 2, 2, 4);

        return moves;
    }

    private void addMoves(List<int[]> moves, int minFirst, int maxFirst, int minSecond, int maxSecond)
    {
        for (int colOffset = -minFirst; colOffset <= minFirst; colOffset += maxFirst)
        {
            for (int rowOffset = -minSecond; rowOffset <= minSecond; rowOffset += maxSecond)
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
}
