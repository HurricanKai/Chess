package de.nosswald.chess.game.piece.impl;

import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;

import java.util.Locale;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class King extends Piece
{
    public King(Side side, int col, int row)
    {
        super("king_" + side.name().toLowerCase(Locale.ROOT) + ".png", side, col, row);
    }
}
