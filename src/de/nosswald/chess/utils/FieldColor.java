package de.nosswald.chess.utils;

import java.awt.*;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public enum FieldColor
{
    WHITE(new Color(237, 216, 191)),
    BLACK(new Color(170, 119, 102)),
    SELECTED(new Color(148, 219, 147, 150)),
    POSSIBLE_MOVE(new Color(197, 49, 61, 200));

    private final Color color;

    FieldColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
}
