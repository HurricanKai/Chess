package de.nosswald.chess.gui;

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
