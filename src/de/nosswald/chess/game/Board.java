package de.nosswald.chess.game;

import com.sun.istack.internal.Nullable;
import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.game.piece.impl.*;
import de.nosswald.chess.utils.FieldColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Board
{
    private final List<Piece> pieces = new ArrayList<>();

    @Nullable
    private Piece selected;

    public Board()
    {
        initialize();
    }

    public void initialize()
    {
        IntStream.range(0, 16).forEach(i -> {
            boolean b = i % 2 == 0;

            pieces.add(new Pawn(b ? Side.BLACK : Side.WHITE, b ? 1 : 6, i / 2));

            if (i < 4)
            {
                pieces.add(new Rook(b ? Side.BLACK : Side.WHITE, b ? 0 : 7, i / 2 == 0 ? 0 : 7));
                pieces.add(new Knight(b ? Side.BLACK : Side.WHITE, b ? 0 : 7, i / 2 == 0 ? 1 : 6));
                pieces.add(new Bishop(b ? Side.BLACK : Side.WHITE, b ? 0 : 7, i / 2 == 0 ? 2 : 5));
            }
            if (i < 2)
            {
                pieces.add(new Queen(b ? Side.BLACK : Side.WHITE, b ? 0 : 7,3));
                pieces.add(new King(b ? Side.BLACK : Side.WHITE, b ? 0 : 7, 4));
            }
        });
    }

    public void paint(Graphics2D graphics, int boardSize)
    {
        final int fieldSize = boardSize / 8;

        // paint board
        for (int c = 0; c < 8; c++)
        {
            for (int r = 0; r < 8; r++)
            {
                graphics.setColor((c + r) % 2 == 1 ? FieldColor.BLACK.getColor() : FieldColor.WHITE.getColor());
                graphics.fillRect(fieldSize * r, fieldSize * c, fieldSize, fieldSize);
            }
        }

        // paint piece
        if (selected != null)
        {
            graphics.setColor(FieldColor.SELECTED.getColor());
            graphics.fillOval(fieldSize * selected.getRow(), fieldSize * selected.getCol(), fieldSize, fieldSize);
        }

        pieces.forEach(piece ->
                piece.paint(graphics, fieldSize * piece.getRow(), fieldSize * piece.getCol(), fieldSize));
    }

    public void onClick(int boardSize, int row, int col)
    {
        if (selected == null)
        {
            Piece clicked = getPiece(row, col);

            if (clicked != null)
                selected = clicked;
        }
        else
            selected = null;
    }

    @Nullable
    private Piece getPiece(int row, int col)
    {
        return pieces.stream().filter(piece -> piece.getCol() == col && piece.getRow() == row).findFirst().orElse(null);
    }
}
