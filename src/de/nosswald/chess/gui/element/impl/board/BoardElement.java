package de.nosswald.chess.gui.element.impl.board;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Element;

import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class BoardElement extends Element
{
    private CustomGraphics graphics;

    /**
     * @param x     the x position
     * @param y     the y position
     * @param size  the width and the height
     */
    public BoardElement(SizeReference x, SizeReference y, SizeReference size)
    {
        super(x, y, size, size);
    }

    @Override
    public void onPaint(CustomGraphics g)
    {
        graphics = g.translate(x, y).clip(g, width, height);

        graphics.drawBoard(Chess.getInstance().getBoard());
    }

    @Override
    public void onClick(MouseEvent event)
    {
        final int col = (this.mouseX - graphics.getOffX()) / (graphics.getHeight() / 8);
        final int row = (this.mouseY - graphics.getOffY()) / (graphics.getHeight() / 8);

        Chess.getInstance().getBoard().onClick(col , row);
    }
}
