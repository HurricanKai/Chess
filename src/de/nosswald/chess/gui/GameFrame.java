package de.nosswald.chess.gui;

import com.sun.istack.internal.NotNull;
import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.screen.Screen;
import de.nosswald.chess.logger.LoggerLevel;

import javax.swing.*;
import java.awt.*;

public final class GameFrame extends JFrame
{
    private Screen currentScreen;

    public GameFrame()
    {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        final int frameWidth = (int)(screenSize.width * .5F);
        final int frameHeight = (int)(screenSize.height * .65F);

        Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                "Determined screen size: %dx%d", (int)screenSize.getWidth(), (int)screenSize.getHeight());
        Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                "Determined screen resolution: %d", screenResolution);
        Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                "Using frame size of %dx%d", frameWidth, frameHeight);

        this.setTitle(Chess.APP_NAME);
        this.setSize(frameWidth, frameHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // center frame on screen
//        this.setUndecorated(true);
        this.setLayout(null);

        Chess.getInstance().getLogger().print(LoggerLevel.DEBUG, "Successfully created game frame");
        this.setVisible(true);
    }

    public void setScreen(@NotNull Screen screen)
    {
        Chess.getInstance().getLogger().printFormat(LoggerLevel.DEBUG,
                "Switching screen to %s", screen.getClass().getSimpleName());

        currentScreen = screen;
        this.setContentPane(screen);

        // paint and validate the new screen
        this.repaint();
        this.revalidate();
    }

    public Screen getCurrentScreen()
    {
        return currentScreen;
    }
}
