package de.nosswald.chess.logger;

import de.nosswald.chess.Chess;

import java.text.SimpleDateFormat;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Logger
{
    /**
     * Prints the given message
     *
     * @param level     the logger level
     * @param message   the message to print
     */
    public void print(LoggerLevel level, String message)
    {
        if (level == LoggerLevel.DEBUG && !Chess.DEBUG_MODE)
            return;

        System.out.println(getTime() + level.getPrefix() + message);
    }

    /**
     * Prints the given message
     *
     * @param level     the logger level
     * @param message   the message to print
     * @param args      additional arguments
     */
    public void printFormat(LoggerLevel level, String message, Object... args)
    {
        if (level == LoggerLevel.DEBUG && !Chess.DEBUG_MODE)
            return;

        System.out.printf(getTime() + level.getPrefix() + message + "\n", args);
    }

    private String getTime()
    {
        return new SimpleDateFormat("[HH:mm:ss]").format(System.currentTimeMillis());
    }
}
