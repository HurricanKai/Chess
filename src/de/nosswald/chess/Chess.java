package de.nosswald.chess;

import de.nosswald.chess.game.Board;
import de.nosswald.chess.gui.GameFrame;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Chess
{
    /**
     * TODO en passent
     *      castling
     *      checkmate + show winner
     */

    public static final String APP_NAME = "Chess";
    public static final String APP_VERSION = "0.1";
    public static final String[] APP_AUTHORS = { "Noah Gerber", "Nils Osswald" };

    public static boolean DEBUG_MODE;

    private static Chess instance;

    private Board board;

    public Chess(boolean debug)
    {
        if (debug)
            System.out.println("[DEBUG] Using debug mode");
        DEBUG_MODE = debug;

        instance = this;
        board = new Board();

        board.initialize();

        System.out.println("Loading user interface..");
        new GameFrame();
    }

    public Board getBoard()
    {
        return board;
    }

    public static Chess getInstance()
    {
        return instance;
    }
}
