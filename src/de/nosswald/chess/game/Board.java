package de.nosswald.chess.game;

import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.game.piece.impl.*;

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

        pieces.forEach(piece -> System.out.println(
                piece.getSide() + " " + piece.getCol() + " " + piece.getRow() + " " + piece.getClass().getSimpleName())
        );
    }

    public void paint(Graphics2D graphics, int boardSize)
    {
        graphics.fillRect(0, 0, 100, 100);

        for (int c = 0; c < 8; c++)
        {
            for (int r = 0; r < 8; r++)
            {
                graphics.setColor((c + r) % 2 == 1 ? new Color(186, 120, 63) : new Color(240, 217, 181));
                graphics.fillRect((boardSize / 8) * r, (boardSize / 8) * c, boardSize / 8, boardSize / 8);

                // paint piece
                for (Piece piece : pieces)
                {
                    if (piece.getCol() == c && piece.getRow() == r)
                        piece.paint(graphics, (boardSize / 8) * r, (boardSize / 8) * c, boardSize / 8);
                }
            }
        }
    }
}
