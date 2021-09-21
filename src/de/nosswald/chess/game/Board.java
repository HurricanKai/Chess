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
        // TODO paint board

        // paint pieces
        pieces.forEach(piece -> piece.paint(graphics, boardSize / 9));
    }
}
