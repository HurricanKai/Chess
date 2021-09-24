package de.nosswald.chess.utils;

import java.awt.*;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public enum FieldColor
{
    WHITE(new Color(240, 217, 181)),
    BLACK(new Color(186, 120, 63)),

    SELECTED(new Color(255, 0, 0, 80));

    private Color color;

    FieldColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
}
