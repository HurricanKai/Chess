package de.nosswald.chess;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Start
{
    public static void main(String[] args)
    {
        System.out.printf("Starting %s v%s..\n", Chess.APP_NAME, Chess.APP_VERSION);
        new Chess();
    }
}
