package de.nosswald.chess.game;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class Move
{
    private Position from, to;

    /**
     * @param from  the {@link Position} to move from
     * @param to    the {@link Position} to move to
     */
    public Move(Position from, Position to)
    {
        this.from = from;
        this.to = to;
    }

    /**
     * @return The starting {@link Position}
     */
    public Position getFrom()
    {
        return from;
    }

    /**
     * @return The ending {@link Position}
     */
    public Position getTo()
    {
        return to;
    }
}
