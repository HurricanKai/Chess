package de.nosswald.chess.gui;

public final class AbsoluteSize implements SizeReference
{
    private final int size;

    public AbsoluteSize(int size)
    {
        this.size = size;
    }

    @Override
    public int get(int relativeTo)
    {
        return size;
    }
}
