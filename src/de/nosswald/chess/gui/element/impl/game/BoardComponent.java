package de.nosswald.chess.gui.element.impl.game;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.RelativeSize;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Element;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BoardComponent extends Element
{
    public BoardComponent(SizeReference x, SizeReference y, SizeReference size)
    {
        super(x, y, size, size);
    }

    @Override
    public void onPaint(CustomGraphics g)
    {
        CustomGraphics graphics = g.translate(x,y).clip(g, width, height);
        graphics.drawRect(new RelativeSize(0), new RelativeSize(0), new RelativeSize(1), new RelativeSize(1), Color.RED);

        Chess.getInstance().getBoard().paint((Graphics2D) graphics.getGraphics(), graphics, graphics.getHeight());
    }

    @Override
    public void onClick(MouseEvent event)
    {

    }
}
