package de.nosswald.chess.game;

import com.sun.istack.internal.Nullable;
import de.nosswald.chess.Chess;
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

            pieces.add(new Pawn(b ? Side.BLACK : Side.WHITE, i / 2, b ? 1 : 6));

            if (i < 4)
            {
                pieces.add(new Rook(b ? Side.BLACK : Side.WHITE, i / 2 == 0 ? 0 : 7, b ? 0 : 7));
                pieces.add(new Knight(b ? Side.BLACK : Side.WHITE, i / 2 == 0 ? 1 : 6, b ? 0 : 7));
                pieces.add(new Bishop(b ? Side.BLACK : Side.WHITE, i / 2 == 0 ? 2 : 5, b ? 0 : 7));
            }
            if (i < 2)
            {
                pieces.add(new Queen(b ? Side.BLACK : Side.WHITE, 3, b ? 0 : 7));
                pieces.add(new King(b ? Side.BLACK : Side.WHITE, 4, b ? 0 : 7));
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
                graphics.fillRect(fieldSize * c, fieldSize * r, fieldSize, fieldSize);
            }
        }

        // paint piece
        if (selected != null)
        {
            graphics.setColor(FieldColor.SELECTED.getColor());
            graphics.fillOval(fieldSize * selected.getCol(), fieldSize * selected.getRow(), fieldSize, fieldSize);

            if (!selected.getPossibleMoves().isEmpty())
            {
                selected.getPossibleMoves().forEach(move -> {
                    graphics.setColor(FieldColor.POSSIBLE_MOVE.getColor());
                    graphics.fillOval(fieldSize * move[0], fieldSize * move[1], fieldSize, fieldSize);
                });
            }
        }

        pieces.forEach(piece ->
                piece.paint(graphics, fieldSize * piece.getCol(), fieldSize * piece.getRow(), fieldSize));
    }

    public void onClick(int boardSize, int col, int row)
    {
        // TODO check if the player has the next move
        if (selected == null)
        {
            Piece clicked = getPiece(col, row);

            if (clicked != null)
            {
                selected = clicked;

                if (Chess.DEBUG_MODE)
                    System.out.printf("[DEBUG] Selected %s on col %d and row %d\n", clicked.getClass().getSimpleName(), col, row);
            }
        }
        else
        {
            selected.getPossibleMoves().stream().filter(move -> move[0] == col && move[1] == row).findFirst().ifPresent(move ->
            {
                if (Chess.DEBUG_MODE)
                    System.out.printf("[DEBUG] Moved %s from col %d and row %d to col %d and row %d\n", selected.getClass().getSimpleName(), selected.getCol(), selected.getRow(), move[0], move[1]);

                pieces.removeIf(piece -> piece.getCol() == move[0] && piece.getRow() == move[1]);
                selected.setCol(move[0]);
                selected.setRow(move[1]);
            });

            selected = null;
        }
    }

    @Nullable
    public Piece getPiece(int col, int row)
    {
        return pieces.stream().filter(piece -> piece.getCol() == col && piece.getRow() == row).findFirst().orElse(null);
    }

    public boolean hasPiece(int col, int row)
    {
        return pieces.stream().anyMatch(piece -> piece.getCol() == col && piece.getRow() == row);
    }
}
