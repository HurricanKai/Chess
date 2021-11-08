package de.nosswald.chess.gui;

import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Move;
import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.utils.FieldColor;

import java.awt.*;

/**
 * A graphics resembling {@link Graphics} but with {@link SizeReference} instead of pixels
 *
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class CustomGraphics
{
    private final Graphics graphics;
    private final int offY, width, height;
    private int offX;

    /**
     * @param graphics the graphics object of the parent screen or component
     */
    public CustomGraphics(Graphics graphics)
    {
        final Rectangle bounds = graphics.getClipBounds();

        this.graphics = graphics;
        this.offX = bounds.x;
        this.offY = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
    }

    /**
     * @param graphics  the graphics instances of the parent screen or component
     * @param offX      the x offset
     * @param offY      the y offset
     * @param width     the width
     * @param height    the height
     */
    public CustomGraphics(Graphics graphics, int offX, int offY, int width, int height)
    {
        this.graphics = graphics;
        this.offX = offX;
        this.offY = offY;
        this.width = width;
        this.height = height;
    }

    /**
     * Clips the width and the height
     *
     * @param g
     * @param width
     * @param height
     * @return
     */
    public CustomGraphics clip(CustomGraphics g, SizeReference width, SizeReference height)
    {
        return new CustomGraphics(g.graphics, offX, offY, width.get(g.width), height.get(g.height));
    }

    /**
     * Translates the position to its parent position plus the new position
     *
     * @param x the x position to be added
     * @param y the y position to be added
     * @return itself but translated
     */
    public CustomGraphics translate(SizeReference x, SizeReference y)
    {
        return new CustomGraphics(graphics, offX + x.get(width), offY + y.get(height), width - x.get(width), height - y.get(height));
    }

    /**
     * Fills a specific rectangle
     * The left and the right edges of the rectangle are x and x + width.
     * The top and the bottom edges of the rectangle are y and y + height.
     *
     * @param x         the x position
     * @param y         the y position
     * @param width     the width
     * @param height    the height
     * @param color     the color to fill with
     */
    public void drawRect(SizeReference x, SizeReference y, SizeReference width, SizeReference height, Color color)
    {
        graphics.setColor(color);
        graphics.drawRect(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height));
        graphics.fillRect(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height));
    }

    /**
     * Fills a specific round rectangle
     * The left and the right edges of the rectangle are x and x + width.
     * The top and the bottom edges of the rectangle are y and y + height.
     *
     * @param x             the x position
     * @param y             the y position
     * @param width         the width
     * @param height        the height
     * @param argWidth      the horizontal diameter of the arc at the four corners
     * @param argHeight     the vertical diameter of the arc at the four corners
     * @param color         the color to fill with
     */
    public void drawRoundRect(SizeReference x, SizeReference y, SizeReference width, SizeReference height, SizeReference argWidth, SizeReference argHeight, Color color)
    {
        graphics.setColor(color);
        graphics.fillRoundRect(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height), argWidth.get(this.width), argHeight.get(this.height));
    }

    /**
     * Fills a specific round oval
     * The left and the right edges of the oval are x and x + width.
     * The top and the bottom edges of the oval are y and y + height.
     *
     * @param x         the x position
     * @param y         the y position
     * @param width     the width
     * @param height    the height
     * @param color     the color to fill with
     */
    public void drawOval(SizeReference x, SizeReference y, SizeReference width, SizeReference height, Color color)
    {
        graphics.setColor(color);
        graphics.fillOval(offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height));
    }

    /**
     * Draws an image
     *
     * @param image     the image
     * @param x         the x position
     * @param y         the y position
     * @param width     the width to scale to
     * @param height    the height to scale to
     */
    public void drawImage(Image image, SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        graphics.drawImage(image, offX + x.get(this.width), offY + y.get(this.height), width.get(this.width), height.get(this.height), null, null);
    }

    /**
     * Draws a chess board
     *
     * @param board the board
     */
    public void drawBoard(Board board)
    {
        final int fieldSize = height / 8;
        final Piece selected = board.getSelected();
        final Move lastMove = board.getHistory().isEmpty() ? null : board.getHistory().get(board.getHistory().size() - 1);

        offX += (width - height) / 2;

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
            graphics.fillRect(offX + (fieldSize * selected.getPosition().getCol()), offY + (fieldSize * selected.getPosition().getRow()), fieldSize, fieldSize);

            selected.getPossibleMoves().forEach(move -> {
                graphics.setColor(FieldColor.POSSIBLE_MOVE.getColor());
                graphics.fillRect(offX + (fieldSize * move.getTo().getCol()), offY + (fieldSize * move.getTo().getRow()), fieldSize, fieldSize);
            });
        }

        // draw last move
        if (lastMove != null)
        {
            graphics.setColor(FieldColor.LAST_MOVE.getColor());
            graphics.fillRect(offX + (fieldSize * lastMove.getFrom().getCol()), offY + (fieldSize * lastMove.getFrom().getRow()), fieldSize, fieldSize);
            graphics.fillRect(offX + (fieldSize * lastMove.getTo().getCol()), offY + (fieldSize * lastMove.getTo().getRow()), fieldSize, fieldSize);
        }

        // draw pieces
        board.getPieces().forEach(piece ->
                piece.paint((Graphics2D)graphics, offX + (piece.getPosition().getCol() * fieldSize), offY + (piece.getPosition().getRow() * fieldSize), fieldSize));
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
            posX = offX + (width - size.get(this.height)) / 2;
        else if (xAnchor == Anchor.POSITIVE)
            posX = offX + width - size.get(this.height);

        if (yAnchor == Anchor.CENTER)
            posY = offY + (height - size.get(this.height)) / 2;
        else if (yAnchor == Anchor.POSITIVE)
            posY = offY + height - size.get(this.height);

        graphics.drawImage(image, posX, posY, size.get(this.height), size.get(this.height), null, null);
    }

    /**
     * Draws a string
     *
     * @param string    the string
     * @param xAnchor   the horizontal anchor
     * @param yAnchor   the vertical anchor
     * @param color     the color
     * @param font      the font
     */
    public void drawString(String string, Anchor xAnchor, Anchor yAnchor, Color color, Font font)
    {
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int posX = offX;
        int posY = offY;

        if (xAnchor == Anchor.CENTER)
            posX = offX + (width - fontMetrics.stringWidth(string)) / 2;
        else if (xAnchor == Anchor.POSITIVE)
            posX = offX + width - fontMetrics.stringWidth(string);

        if (yAnchor == Anchor.CENTER)
            posY = offY + (height - fontMetrics.getHeight()) / 2;
        else if (yAnchor == Anchor.POSITIVE)
            posY = offY + height - fontMetrics.getHeight();

        posY += fontMetrics.getAscent();

        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(string, posX, posY);
    }

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
