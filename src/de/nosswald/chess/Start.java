package de.nosswald.chess;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Start
{
    /**
     * The entry point of the application
     *
     * @param args the program arguments
     */
    public static void main(String[] args)
    {
        System.out.printf("Starting %s v%s..\n", Chess.APP_NAME, Chess.APP_VERSION);
        new Chess(args.length != 0 && args[0].equals("debug"));
    }
}
