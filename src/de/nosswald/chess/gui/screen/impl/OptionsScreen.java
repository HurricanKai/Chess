package de.nosswald.chess.gui.screen.impl;

import com.sun.istack.internal.NotNull;
import de.nosswald.chess.gui.AbsoluteSize;
import de.nosswald.chess.gui.Anchor;
import de.nosswald.chess.gui.RelativeSize;
import de.nosswald.chess.gui.element.impl.LabelElement;
import de.nosswald.chess.gui.screen.Screen;

import java.awt.*;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class OptionsScreen extends Screen
{
    /**
     * @param parent the parent screen to return to after closing this one
     */
    public OptionsScreen(@NotNull Screen parent)
    {
        this.elements.add(
                new LabelElement("Not implemented yet!", new RelativeSize(0), new RelativeSize(0), new RelativeSize(1), new RelativeSize(1), Anchor.CENTER, Anchor.CENTER)
                        .setColor(Color.BLACK)
                        .setFont(new Font("Courier New", Font.PLAIN, new AbsoluteSize(32).get(0)))
        );
    }
}
