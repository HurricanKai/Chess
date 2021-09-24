package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

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
        return null;
    }
}
