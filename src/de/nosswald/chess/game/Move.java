package de.nosswald.chess.game;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class Move
{
    private final Position from, to;
    private final Flag flag;

    /**
     * @param from  the {@link Position} to move from
     * @param to    the {@link Position} to move to
     */
    public Move(Position from, Position to)
    {
        this.from = from;
        this.to = to;

        this.flag = Flag.NONE;
    }

    /**
     * @param from  the {@link Position} to move from
     * @param to    the {@link Position} to move to
     * @param flag  the {@link Flag}
     */
    public Move(Position from, Position to, Flag flag)
    {
        this.from = from;
        this.to = to;
        this.flag = flag;
    }

    public enum Flag
    {
        NONE,
        DOUBLE_FORWARD,
        PROMOTION,
        EN_PASSANT,
        CASTLING
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

    /**
     * @return The {@link Flag}
     */
    public Flag getFlag()
    {
        return flag;
    }
}
