package de.nosswald.chess.gui.screen.impl;

import de.nosswald.chess.gui.RelativeSize;
import de.nosswald.chess.gui.element.impl.board.BoardElement;
import de.nosswald.chess.gui.screen.Screen;

public final class BoardScreen extends Screen
{
    public BoardScreen()
    {
        elements.add(new BoardElement(new RelativeSize(.05F), new RelativeSize(.05F), new RelativeSize(.9F)));
    }
}
