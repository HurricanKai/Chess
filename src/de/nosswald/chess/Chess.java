package de.nosswald.chess;

import de.nosswald.chess.game.Board;
import de.nosswald.chess.gui.GameFrame;
import de.nosswald.chess.gui.screen.impl.MainMenuScreen;
import de.nosswald.chess.logger.Logger;
import de.nosswald.chess.logger.LoggerLevel;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class Chess
{
    public static final String APP_NAME = "Chess";
    public static final String APP_VERSION = "0.1";
    public static final String[] APP_AUTHORS = { "Nils Osswald", "Noah Gerber" };

    public static boolean DEBUG_MODE;

    private static Chess instance;

    private final Logger logger;
    private final GameFrame frame;
    private Board board;

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

        // create board
        logger.print(LoggerLevel.INFO, "Creating chess board..");
        board = new Board();
        board.initialize();

        // create user interface
        logger.print(LoggerLevel.INFO, "Creating user interface..");
        frame = new GameFrame();

        // open main menu
        frame.setScreen(new MainMenuScreen());
    }

    public static Chess getInstance()
    {
        return instance;
    }

    public Logger getLogger()
    {
        return logger;
    }

    public GameFrame getFrame()
    {
        return frame;
    }

    public Board getBoard()
    {
        return board;
    }
}
