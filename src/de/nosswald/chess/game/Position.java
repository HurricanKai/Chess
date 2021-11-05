package de.nosswald.chess.game;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public class Position
{
    private int col, row;

    /**
     * @param col the column
     * @param row the row
     */
    public Position(int col, int row)
    {
        this.col = col;
        this.row = row;
    }

    public int getCol()
    {
        return col;
    }

    public int getRow()
    {
        return row;
    }

    @Override
    public String toString()
    {
        return "(" + col + "|" + row + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return col == position.col && row == position.row;
    }
}
