package de.nosswald.chess;

public class Start
{
    public static void main(String[] args)
    {
        System.out.printf("Starting %s v%s..", Chess.APP_NAME, Chess.APP_VERSION);
        new Chess();
    }
}
