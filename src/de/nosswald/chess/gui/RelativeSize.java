package de.nosswald.chess.gui;

public final class RelativeSize implements SizeReference
{
    private final float factor;

    public RelativeSize(float factor)
    {
        this.factor = factor;
    }

    @Override
    public int get(int relativeTo)
    {
        return (int)(relativeTo * factor);
    }
}
