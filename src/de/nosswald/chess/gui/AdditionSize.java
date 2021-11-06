package de.nosswald.chess.gui;

/**
 * Represents a {@link SizeReference} that is an addition of two other {@link SizeReference}'s
 *
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class AdditionSize implements SizeReference
{
    private final SizeReference a;
    private final SizeReference b;

    public AdditionSize(SizeReference a, SizeReference b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public int get(int relativeTo)
    {
        return a.get(relativeTo) + b.get(relativeTo);
    }
}
