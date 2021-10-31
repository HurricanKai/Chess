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
    SELECTED(new Color(224, 17, 34, 200)),
    POSSIBLE_MOVE(new Color(197, 49, 61, 200)),
    LAST_MOVE(new Color(155, 199, 0, 104));

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
