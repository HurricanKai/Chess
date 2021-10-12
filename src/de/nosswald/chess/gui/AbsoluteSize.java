package de.nosswald.chess.gui;

import java.awt.*;

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
        return (int)(size * (Toolkit.getDefaultToolkit().getScreenResolution() / 92F));
    }
}
