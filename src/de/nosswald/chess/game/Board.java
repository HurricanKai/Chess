package de.nosswald.chess.game;

import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.game.piece.impl.Pawn;

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
            pieces.add(new Pawn(b ? Side.WHITE : Side.BLACK, b ? 1 : 7 , i / 2));
        });

        pieces.forEach(piece -> System.out.println(piece.getSide() + " " + piece.getCol() + " " + piece.getRow()));
    }

    public void paint(Graphics2D graphics, int boardSize)
    {
        graphics.fillRect(0, 0, 100, 100);

        for (int c = 0; c < 8; c++)
        {
            for (int r = 0; r < 8; r++)
            {
                graphics.setColor((c + r) % 2 == 1 ? new Color(240, 217, 181) : new Color(186, 120, 63));
                graphics.fillRect((boardSize / 10) * r, (boardSize / 10) * c, boardSize / 10, boardSize / 10);
            }
        }

        // paint pieces
        pieces.forEach(piece -> piece.paint(graphics, boardSize / 10));
    }
}
