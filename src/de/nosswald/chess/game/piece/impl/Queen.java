package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.Locale;

/**
 * @author Noah Gerber
 * @author Nils Osswald
 */
public class Queen extends Piece
{
    public Queen(Side side, int col, int row)
    {
        super("queen_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }
}
