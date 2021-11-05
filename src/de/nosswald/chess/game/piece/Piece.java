package de.nosswald.chess.game.piece;

import com.sun.istack.internal.NotNull;
import de.nosswald.chess.Chess;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Move;
import de.nosswald.chess.game.Position;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.impl.King;
import de.nosswald.chess.logger.LoggerLevel;
import de.nosswald.chess.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public abstract class Piece
{
    protected final Side side;
    protected Position position;

    protected final Board board;

    private BufferedImage image;

    /**
     * @param fileName  the name of the image file
     * @param side      the {@link Side}
     * @param position  the {@link Position}
     */
    public Piece(String fileName, Side side, Position position)
    {
        this.side = side;
        this.position = position;

        board = Chess.getInstance().getBoard();

        try
        {
            image = ImageIO.read(new ResourceLocation(fileName, ResourceLocation.Type.PIECE).getFile());
        }
        catch (IOException e)
        {
            Chess.getInstance().getLogger().printFormat(LoggerLevel.ERROR, "Image for %s not found!\n%s",
                    getClass().getSimpleName(), e.getMessage());
        }
    }

    /**
     * Paints the {@link Piece}
     *
     * @param graphics  the graphics object
     * @param x         the x position measured in pixels
     * @param y         the y position measured in pixels
     * @param size      the width and the height of the piece measured in pixels
     */
    public void paint(Graphics2D graphics, int x, int y, int size)
    {
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.drawImage(image, x, y, size, size, null, null);
    }

    /**
     * @param position the {@link Position}
     * @return Whether the {@link Position} is in the {@link Board}'s bounds or not
     */
    protected boolean onBoard(@NotNull Position position)
    {
        return position.getCol() >= 0 && position.getRow() >= 0 && position.getCol() < 8 && position.getRow() < 8;
    }

    /**
     * @param moves     the moves {@link List}
     * @param from      the {@link Position} to move from
     * @param to        the {@link Position} to move to
     * @return Whether the {@link Piece} can move to the given {@link Position} or not
     */
    protected boolean canPath(List<Move> moves, Position from, Position to)
    {
        if (onBoard(to))
        {
            if (board.hasPiece(to))
            {
                if (board.getPiece(to).getSide() != this.side)
                    moves.add(new Move(from, to));
                return false;
            }
            moves.add(new Move(from, to));
        }
        return true;
    }

    /**
     * Filters out every illegal {@link Move}
     *
     * @param pseudoLegalMoves the {@link Move}'s to check
     * @return A {@link List} of all legal {@link Move}'s
     */
    protected List<Move> filterLegalMoves(List<Move> pseudoLegalMoves)
    {
        if (!board.isLegitimacyChecking())
            return pseudoLegalMoves;

        board.setLegitimacyChecking(false);

        final List<Move> legalMoves = new ArrayList<>();

        for (Move pseudoLegalMove : pseudoLegalMoves)
        {
            // check if castling is possible
            if (this instanceof King && !board.isInCheck(side))
            {
                final int startRow = side == Side.WHITE ? 7 : 0;

                // short castle
                if (pseudoLegalMove.getTo().equals(new Position(6, startRow)))
                {
                    if (!IntStream.range(5, 6)
                            .anyMatch(col -> board.getPieces().stream()
                                    .filter(p -> p.side != side)
                                    .anyMatch(p -> p.getPossibleMoves().stream()
                                            .anyMatch(m -> m.getTo().equals(new Position(col, startRow)))))
                    )
                        legalMoves.add(pseudoLegalMove);

                    continue;
                }

                // long castle
                if (pseudoLegalMove.getTo().equals(new Position(2, startRow)))
                {
                    if (!IntStream.range(1, 3)
                            .anyMatch(col -> board.getPieces().stream()
                                    .filter(p -> p.side != side)
                                    .anyMatch(p -> p.getPossibleMoves().stream()
                                            .anyMatch(m -> m.getTo().equals(new Position(col, startRow)))))
                    )
                        legalMoves.add(pseudoLegalMove);

                    continue;
                }
            }

            final Position position = this.position;
            final Piece piece = board.getPiece(position);
            final List<Piece> oldPieces = new ArrayList<>(board.getPieces());

            piece.setPosition(pseudoLegalMove.getTo());

            if (!board.isInCheck(side))
                legalMoves.add(pseudoLegalMove);

            // reset
            setPosition(position);
            board.getPieces().clear();
            board.getPieces().addAll(oldPieces);
        }

        board.setLegitimacyChecking(true);

        return legalMoves;
    }

    /**
     * Places the {@link Piece} on the given {@link Position}.
     * Also removes a {@link Piece} if there is already a {@link Piece} on the given {@link Position}.
     *
     * @param position the new {@link Position}
     */
    public void setPosition(@NotNull Position position)
    {
        board.getPieces().removeIf(p -> p.getPosition().equals(position));
        this.position = position;
    }

    /**
     * @return An unfiltered {@link List} of all possible {@link Position}'s
     */
    public abstract List<Move> getPossibleMoves();

    public Side getSide()
    {
        return side;
    }

    /**
     * @return The current {@link Position}
     */
    public Position getPosition()
    {
        return position;
    }
}
