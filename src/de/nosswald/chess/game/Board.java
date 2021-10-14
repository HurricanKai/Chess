package de.nosswald.chess.game;

import com.sun.istack.internal.Nullable;
import de.nosswald.chess.Chess;
import de.nosswald.chess.game.piece.Piece;
import de.nosswald.chess.game.piece.impl.*;
import de.nosswald.chess.logger.LoggerLevel;
import de.nosswald.chess.utils.FieldColor;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Board
{
    /**
     * contains every {@link Piece} on the board
     */
    private final List<Piece> pieces = new ArrayList<>();

    /**
     * contains every move that has been done on the board<br>
     * structure: int[fromCol, fromRow, toCol, toRow]
     */
    private final List<int[]> history = new ArrayList<>();

    /**
     * stores the {@link Side} which moves next
     */
    private Side nextMove = Side.WHITE;

    /**
     * stores the currently selected {@link Piece}<br> (null if nothing is selected)
     */
    @Nullable
    private Piece selected;

    private boolean gameOver;
    private boolean legitimacyChecking = true;

    /**
     * Initializes every {@link Piece} on the board
     */
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

        Chess.getInstance().getLogger().print(LoggerLevel.INFO, "Added all pieces to the board");
    }

    /**
     * Paints the board
     *
     * @param graphics  the graphics object
     * @param boardSize the total board size (width = height)
     */
    public void paint(Graphics2D graphics, int boardSize)
    {
        final int fieldSize = boardSize / 8;
        final boolean isDebugMode = Chess.DEBUG_MODE;

        if (isDebugMode)
            graphics.setFont(new Font("Arial", Font.PLAIN, boardSize / 50));

        // paint board
        for (int c = 0; c < 8; c++)
        {
            for (int r = 0; r < 8; r++)
            {
                graphics.setColor((c + r) % 2 == 1 ? FieldColor.BLACK.getColor() : FieldColor.WHITE.getColor());
                graphics.fillRect(fieldSize * c, fieldSize * r, fieldSize, fieldSize);

                if (isDebugMode)
                {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString("(" + c + "|" + r + ")", fieldSize* c, (fieldSize * r) + fieldSize);
                }
            }
        }

        // paint piece
        if (selected != null)
        {
            graphics.setColor(FieldColor.SELECTED.getColor());
            graphics.fillRect(fieldSize * selected.getCol(), fieldSize * selected.getRow(), fieldSize, fieldSize);

            if (!selected.getPossibleMoves().isEmpty())
            {
                selected.getPossibleMoves().forEach(move -> {
                    graphics.setColor(FieldColor.POSSIBLE_MOVE.getColor());
                    graphics.fillRect(fieldSize * move[0], fieldSize * move[1], fieldSize, fieldSize);
                });
            }
        }

        pieces.forEach(piece ->
                piece.paint(graphics, fieldSize * piece.getCol(), fieldSize * piece.getRow(), fieldSize));
    }

    /**
     * Handles the click event based on the {@link Piece#col} and the {@link Piece#col}
     *
     * @param col   the clicked column
     * @param row   the clicked row
     */
    public void onClick(int col, int row)
    {
        if (gameOver)
            return;

        if (selected == null)
        {
            // select piece
            Piece clicked = getPiece(col, row);

            if (clicked != null && clicked.getSide() == nextMove)
            {
                final List<int[]> possibleMoves = clicked.getPossibleMoves();

                selected = clicked;

                Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                        "Selected %s on (%d|%d)", clicked.getClass().getSimpleName(), col, row);
                Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                        "%d possible moves %s", possibleMoves.size(), possibleMoves.stream().map(
                                Arrays::toString).collect(Collectors.joining()));
            }
        }
        else
        {
            selected.getPossibleMoves().stream().filter(move -> move[0] == col && move[1] == row).findFirst().ifPresent(move ->
            {
                Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                        "Moved %s from (%d|%d) to (%d|%d)", selected.getClass().getSimpleName(),
                        selected.getCol(), selected.getRow(), move[0], move[1]);

                history.add(new int[]{ selected.getCol(), selected.getRow(), move[0], move[1] });
                selected.setPosition(move[0], move[1]);

                // flip sides
                nextMove = nextMove.flip();

                // check if draw
                if (isStaleMate())
                {
                    gameOver = true;
                    nextMove = null;

                    Chess.getInstance().getLogger().print(LoggerLevel.INFO, "The match has ended in a draw");
                }

                // check if checkmate
                if (isCheckMate(nextMove))
                {
                    gameOver = true;

                    Chess.getInstance().getLogger().printFormat(LoggerLevel.INFO,
                            "%s has won the match", nextMove.flip().toString());
                }
            });

            selected = null;

            Chess.getInstance().getLogger().print(LoggerLevel.DEBUG, "Unselected piece");
        }
    }

    /**
     * Checks if the {@link King} of the given {@link Side} is in check
     *
     * @param side the side to be checked
     * @return whether the sides king is in check or not
     */
    public boolean isInCheck(Side side)
    {
        List<Piece> piecesClone = new ArrayList<>(pieces);
        List<Piece> anotherPiecesClone = new ArrayList<>(pieces);

        return piecesClone.stream()
                .filter(piece -> piece instanceof King)
                .anyMatch(king -> anotherPiecesClone.stream()
                        .filter(piece -> piece.getSide() != side)
                        .anyMatch(piece -> piece.getPossibleMoves().stream()
                                .anyMatch(move -> king.getCol() == move[0] && king.getRow() == move[1])));
    }

    /**
     * Checks if the given {@link Side} is checkmate
     *
     * @param side the side to be checked
     * @return whether it is checkmate for the given side or not
     */
    public boolean isCheckMate(Side side)
    {
        List<Piece> piecesClone = new ArrayList<>(pieces);

        return piecesClone.stream()
                .filter(piece -> piece.getSide() == side)
                .allMatch(piece -> piece.getPossibleMoves().isEmpty()) && isInCheck(side);
    }

    /**
     * Checks if both sides are unable to move<br>
     * TODO check for maximum moves and other draw stuff
     *
     * @return true if both sides are unable to move
     */
    private boolean isStaleMate()
    {
        List<Piece> piecesClone = new ArrayList<>(pieces);

        return piecesClone.stream()
                .filter(piece -> piece.getSide() == nextMove)
                .allMatch(piece -> piece.getPossibleMoves().isEmpty()) && !isInCheck(nextMove);
    }

    /**
     * Returns the piece on the given {@link Piece#col} and {@link Piece#row}
     *
     * @param col   the column
     * @param row   the row
     * @return the piece on the given col and row (null if the field is empty)
     */
    @Nullable
    public Piece getPiece(int col, int row)
    {
        return pieces.stream().filter(piece -> piece.getCol() == col && piece.getRow() == row).findFirst().orElse(null);
    }

    /**
     * Checks if there is a piece on the given {@link Piece#col} and {@link Piece#row}
     *
     * @param col   the column
     * @param row   the row
     * @return whether the field has a piece or not
     */
    public boolean hasPiece(int col, int row)
    {
        return pieces.stream().anyMatch(piece -> piece.getCol() == col && piece.getRow() == row);
    }

    /**
     * @return a list of all pieces on the board
     */
    public List<Piece> getPieces()
    {
        return pieces;
    }

    /**
     * @return every move that has been done on the board<br>(structure: int[fromCol, fromRow, toCol, toRow])
     */
    public List<int[]> getHistory()
    {
        return history;
    }

    /**
     * @return the side which has the next move
     */
    public Side getNextMove()
    {
        return nextMove;
    }

    public void setLegitimacyChecking(boolean legitimacyChecking)
    {
        this.legitimacyChecking = legitimacyChecking;
    }

    public boolean isLegitimacyChecking()
    {
        return legitimacyChecking;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }
}
