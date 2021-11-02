import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import de.nosswald.chess.game.Board;
import de.nosswald.chess.game.Side;
import de.nosswald.chess.game.piece.Piece;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class ChessTest
{
    Board board;

    @BeforeEach
    void setup()
    {
        board = new Board();
    }

    @Test
    @DisplayName("Moves shoulnt make selfchess")
    public void testSelfChess()
    {
        assertEquals(board.isInCheck(Side.WHITE),
                board.getPieces().stream().filter(piece -> piece.getSide() == Side.BLACK).anyMatch(
                        piece -> piece.getPossibleMoves().stream().anyMatch(

                        )
                )
        );
    }
}
