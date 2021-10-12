package de.nosswald.chess.gui.element.impl.mainmenu;

import de.nosswald.chess.gui.*;
import de.nosswald.chess.gui.element.impl.ButtonElement;

import java.awt.*;
import java.awt.event.MouseEvent;

public final class MainMenuButtonElement extends ButtonElement
{
    private final String title;

    public MainMenuButtonElement(String title, SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);

        this.title = title;
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        super.onPaint(graphics);
        graphics.drawRoundRect(x, y, width, height, new AbsoluteSize(15), new AbsoluteSize(15), isHovered() ? new Color(170, 119, 102) : new Color(237, 216, 191));
        graphics.drawString(title, x, y, width, height, Anchor.CENTER, Anchor.CENTER, Color.BLACK, new Font("Courier New", Font.PLAIN, new AbsoluteSize(36).get(0)));
    }

    @Override
    public void onClick(MouseEvent event)
    {
        super.onClick(event);
    }
}
