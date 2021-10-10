package de.nosswald.chess.gui.screen.impl;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.AbsoluteSize;
import de.nosswald.chess.gui.RelativeSize;
import de.nosswald.chess.gui.element.impl.mainmenu.MainMenuButtonElement;
import de.nosswald.chess.gui.element.impl.mainmenu.MainMenuCopyrightComponent;
import de.nosswald.chess.gui.screen.Screen;

public final class MainMenuScreen extends Screen
{
    public MainMenuScreen()
    {
        this.elements.add(
                new MainMenuButtonElement("New Game", new RelativeSize(.5F), new RelativeSize(.3F), new AbsoluteSize(400), new AbsoluteSize(64))
                        .setAction(() -> Chess.getInstance().getFrame().setScreen(new BoardScreen()))
        );
        this.elements.add(
                new MainMenuButtonElement("Load Game", new RelativeSize(.5F), new RelativeSize(.5F), new AbsoluteSize(400), new AbsoluteSize(64))
                        .setAction(() -> Chess.getInstance().getFrame().setScreen(new MainMenuScreen())) // TODO open load game screen here
        );
        this.elements.add(
                new MainMenuButtonElement("Options", new RelativeSize(.5F), new RelativeSize(.7F), new AbsoluteSize(400), new AbsoluteSize(64))
                        .setAction(() -> Chess.getInstance().getFrame().setScreen(new MainMenuScreen())) // TODO open options screen here
        );
        this.elements.add(
                new MainMenuCopyrightComponent(new RelativeSize(0), new RelativeSize(0.5F), new AbsoluteSize(170), new AbsoluteSize(35))
        );
    }
}
