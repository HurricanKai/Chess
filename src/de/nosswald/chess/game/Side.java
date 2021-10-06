package de.nosswald.chess.game;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Locale;

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

    @Override
    public String toString()
    {
        return StringUtils.capitalize(super.toString().toLowerCase(Locale.ROOT));
    }
}
