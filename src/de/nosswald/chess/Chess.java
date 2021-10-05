package de.nosswald.chess;

import de.nosswald.chess.game.Board;
import de.nosswald.chess.gui.GameFrame;
import de.nosswald.chess.logger.Logger;
import de.nosswald.chess.logger.LoggerLevel;

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
     *      Draw
     */

    public static final String APP_NAME = "Chess";
    public static final String APP_VERSION = "0.1";
    public static final String[] APP_AUTHORS = { "Noah Gerber", "Nils Osswald" };

    public static boolean DEBUG_MODE;

    private static Chess instance;

    private Board board;
    private Logger logger;

    /**
     * @param debug whether to run in debug mode or not
     */
    public Chess(boolean debug)
    {
        instance = this;

        // create logger
        logger = new Logger();
        logger.print(LoggerLevel.INFO, "Created logger");

        DEBUG_MODE = debug;
        logger.print(LoggerLevel.DEBUG, "Using debug mode");

        // load board
        logger.print(LoggerLevel.INFO, "Creating chess board..");
        board = new Board();
        board.initialize();

        // load user interface
        logger.print(LoggerLevel.INFO, "Loading user interface..");
        new GameFrame();
    }

    public static Chess getInstance()
    {
        return instance;
    }

    public Board getBoard()
    {
        return board;
    }

    public Logger getLogger()
    {
        return logger;
    }
}
