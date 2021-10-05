package de.nosswald.chess.logger;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public enum LoggerLevel
{
    INFO("[INFO]"),
    WARNING("[WARNING]"),
    ERROR("[ERROR]"),
    DEBUG("[DEBUG]");

    private String prefix;

    LoggerLevel(String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return prefix + " ";
    }
}
