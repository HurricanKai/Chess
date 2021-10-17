package de.nosswald.chess.gui;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.utils.FieldColor;

import java.awt.*;

public final class CustomGraphics
{
    private final Graphics graphics;
    private final int offX, offY, width, height;

    public CustomGraphics(Graphics graphics)
    {
        final Rectangle bounds = graphics.getClipBounds();

        this.graphics = graphics;
        this.offX = bounds.x;
        this.offY = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
    }

    public CustomGraphics(Graphics graphics, int offX, int offY, int width, int height)
    {
        this.graphics = graphics;
        this.offX = offX;
        this.offY = offY;
        this.width = width;
        this.height = height;
    }

    public CustomGraphics clip(CustomGraphics g, SizeReference width, SizeReference height)
    {
        return new CustomGraphics(g.graphics, offX, offY, width.get(g.width), height.get(g.height));
    }

    public CustomGraphics translate(SizeReference x, SizeReference y)
    {
        return new CustomGraphics(graphics, offX + x.get(width), offY + y.get(height), width - x.get(width), height - y.get(height));
    }

    public void drawRect(SizeReference x, SizeReference y, SizeReference width, SizeReference height, Color color)
    {
        graphics.setColor(color);
        graphics.fillRect(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height));
    }

    public void drawRoundRect(SizeReference x, SizeReference y, SizeReference width, SizeReference height, SizeReference argWidth, SizeReference argHeight, Color color)
    {
        graphics.setColor(color);
        graphics.fillRoundRect(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height), argWidth.get(this.width), argHeight.get(this.height));
    }

    public void drawOval(SizeReference x, SizeReference y, SizeReference width, SizeReference height, Color color)
    {
        graphics.setColor(color);
        graphics.fillOval(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height));
    }

    public void drawImage(Image image, SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        graphics.drawImage(image, offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height), null, null);
    }

    /**
     * Draws a chess board on the given context
     *
     * @param board the board
     */
    public void drawBoard(Board board)
    {
        final int fieldSize = height / 8;
        final Piece selected = board.getSelected();

        // draw fields
        for (int col = 0; col < 8; col++)
        {
            for (int row = 0; row < 8; row++)
            {
                graphics.setColor((col + row) % 2 == 1 ? FieldColor.BLACK.getColor() : FieldColor.WHITE.getColor());
                graphics.fillRect(offX + (fieldSize * col), offY + (fieldSize * row), fieldSize, fieldSize);

                if (Chess.DEBUG_MODE)
                {
                    graphics.setColor(Color.BLACK);
                    graphics.setFont(new Font("Arial", Font.PLAIN, fieldSize / 5));
                    graphics.drawString("(" + col + "|" + row + ")", offX + (fieldSize * col), offY + fieldSize + (fieldSize * row));
                }
            }
        }

        // draw possible moves of the currently selected piece
        if (selected != null)
        {
            graphics.setColor(FieldColor.SELECTED.getColor());
            graphics.fillRect(offX + (fieldSize * selected.getCol()), offY + (fieldSize * selected.getRow()), fieldSize, fieldSize);

            selected.getPossibleMoves().forEach(move -> {
                graphics.setColor(FieldColor.POSSIBLE_MOVE.getColor());
                graphics.fillRect(offX + (fieldSize * move[0]), offY + (fieldSize * move[1]), fieldSize, fieldSize);
            });
        }

        // draw pieces
        board.getPieces().forEach(piece -> {
            piece.paint((Graphics2D)graphics, offX + (piece.getCol() * fieldSize), offY + (piece.getRow() * fieldSize), fieldSize);
        });
    }

    /**
     * Draws an image with even sides
     *
     * @param image     the image
     * @param size      the width and the height of the image
     * @param xAnchor   the anchor on the x-axis
     * @param yAnchor   the anchor of the y-axis
     */
    public void drawSquareImage(Image image, SizeReference size, Anchor xAnchor, Anchor yAnchor)
    {
        int posX = offX;
        int posY = offY;

        if (xAnchor == Anchor.CENTER)
            posX = offX + (width - size.get(this.height) / 2);
        else if (xAnchor == Anchor.POSITIVE)
            posX = offX + width - size.get(this.height);

        if (yAnchor == Anchor.CENTER)
            posY = offY + (height - size.get(this.height) / 2);
        else if (yAnchor == Anchor.POSITIVE)
            posY = offY + height - size.get(this.height);

        graphics.drawImage(image, posX, posY, size.get(this.height), size.get(this.height), null, null);
    }

    // TODO make relative to current context
    public void drawString(String string, SizeReference x, SizeReference y, SizeReference width, SizeReference height, Anchor xAnchor, Anchor yAnchor, Color color, Font font)
    {
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int posX = offX + x.get(this.width);
        int posY = offY + y.get(this.height);

        if (xAnchor == Anchor.CENTER)
            posX = offX + x.get(this.width) + (width.get(this.width) - fontMetrics.stringWidth(string)) / 2;
        else if (xAnchor == Anchor.POSITIVE)
            posX = x.get(this.width) + width.get(this.width) - fontMetrics.stringWidth(string);

        if (yAnchor == Anchor.CENTER)
            posY = offY + y.get(this.height) + (height.get(this.height) - fontMetrics.getHeight()) / 2;
        else if (yAnchor == Anchor.POSITIVE)
            posY = offY + y.get(this.height) + height.get(this.height) - fontMetrics.getHeight();

        posY += fontMetrics.getAscent();

        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(string, posX, posY);
    }

//    public void drawString(String string, SizeReference x, SizeReference y, Color color, Font font)
//    {
//        graphics.setColor(color);
//        graphics.setFont(font);
//        graphics.drawString(string, offX + x.get(width), offY + y.get(height));
//    }

    public int getOffX()
    {
        return offX;
    }

    public int getOffY()
    {
        return offY;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
