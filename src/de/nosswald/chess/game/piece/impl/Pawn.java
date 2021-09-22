package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.Locale;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Pawn extends Piece
{
    public Pawn(Side side, int col, int row)
    {
        super("pawn_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }
}
