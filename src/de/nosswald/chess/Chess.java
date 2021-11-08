package de.nosswald.chess;

import de.nosswald.chess.game.Board;
import de.nosswald.chess.gui.Frame;
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
    private final Frame frame;
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

        // create user interface
        logger.print(LoggerLevel.INFO, "Creating user interface..");
        frame = new Frame();

        // open main menu
        frame.setScreen(new MainMenuScreen());

        // add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.print(LoggerLevel.INFO, "Shutting down..");
        }));
    }

    public static Chess getInstance()
    {
        return instance;
    }

    public Logger getLogger()
    {
        return logger;
    }

    public Frame getFrame()
    {
        return frame;
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        logger.print(LoggerLevel.INFO, "Creating new chess board..");
        this.board = board;
        board.initialize();
    }
}
