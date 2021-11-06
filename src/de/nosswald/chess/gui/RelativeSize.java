package de.nosswald.chess.gui;

/**
 * Represents a {@link SizeReference} that is relative to its parent
 * {@link de.nosswald.chess.gui.element.Component} or {@link de.nosswald.chess.gui.screen.Screen}
 *
 * @author Nils Osswald
 * @author Noah Gerber
 */
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
