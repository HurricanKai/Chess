package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.Locale;

/**
 * @author Noah Gerber
 */
public class Bishop extends Piece
{
    public Bishop(Side side, int col, int row)
    {
        super("bishop_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }
}
