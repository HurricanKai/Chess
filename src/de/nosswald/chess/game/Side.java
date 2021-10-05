package de.nosswald.chess.game;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public enum Side
{
    WHITE,
    BLACK;

    /**
     * Flips the side
     *
     * @return black if white or white if black
     */
    public Side flip()
    {
        return this == WHITE ? BLACK : WHITE;
    }
}
