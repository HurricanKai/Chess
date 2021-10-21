package de.nosswald.chess.gui.element.impl.board;

import de.nosswald.chess.game.Board;
import de.nosswald.chess.gui.CustomGraphics;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Element;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class BoardElement extends Element
{
    private final Board board;
    private CustomGraphics graphics;

    /**
     * @param x     the x position
     * @param y     the y position
     * @param size  the width and the height
     */
    public BoardElement(Board board, SizeReference x, SizeReference y, SizeReference size)
    {
        super(x, y, size, size);

        this.board = board;
    }

    @Override
    public void onPaint(CustomGraphics g)
    {
        g.drawRect(x, y, width, height, Color.ORANGE);
        graphics = g.translate(x, y).clip(g, width, height);

        graphics.drawBoard(board);
    }

    @Override
    public void onClick(MouseEvent event)
    {
        final int col = (this.mouseX - graphics.getOffX()) / (graphics.getHeight() / 8);
        final int row = (this.mouseY - graphics.getOffY()) / (graphics.getHeight() / 8);

        board.onClick(col, row);
    }
}
