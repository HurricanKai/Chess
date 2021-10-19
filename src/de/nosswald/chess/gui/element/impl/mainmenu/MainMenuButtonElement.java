package de.nosswald.chess.gui.element.impl.mainmenu;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.*;
import de.nosswald.chess.gui.element.impl.ButtonElement;
import de.nosswald.chess.logger.LoggerLevel;
import de.nosswald.chess.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public final class MainMenuButtonElement extends ButtonElement
{
    private final String title;

    public MainMenuButtonElement(String title, SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);

        this.title = title;
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        super.onPaint(graphics);

        graphics.drawRoundRect(x, y, width, height, new AbsoluteSize(15), new AbsoluteSize(15), isHovered() ? new Color(170, 119, 102) : new Color(237, 216, 191));
        getContextGraphics(graphics).drawString(title, Anchor.CENTER, Anchor.CENTER, Color.BLACK, new Font("Courier New", Font.PLAIN, new AbsoluteSize(36).get(0)));

        if (!isHovered()) return;
        Arrays.stream(new String[]{ "pawn_white.png", "pawn_black.png" })
                .collect(HashMap<Integer, BufferedImage>::new, (map, fileName) -> {
                    try {map.put(map.size(), ImageIO.read(new ResourceLocation(fileName, ResourceLocation.Type.PIECE).getFile()));}
                    catch (IOException e) {
                        Chess.getInstance().getLogger().printFormat(LoggerLevel.ERROR, "Image for %s not found!\n%s",
                                getClass().getSimpleName(), e.getMessage());
                    }
                }, (i, j) -> { }).forEach((i, image) ->
                        getContextGraphics(graphics).drawSquareImage(
                                image, new RelativeSize(1), i == 0 ? Anchor.NEGATIVE : Anchor.POSITIVE, Anchor.POSITIVE)); // TODO WHY POSITIVE AND NOT CENTER
    }

    private CustomGraphics getContextGraphics(CustomGraphics graphics)
    {
        return graphics.translate(this.x, this.y).clip(graphics, this.width, this.height);
    }

    @Override
    public void onClick(MouseEvent event)
    {
        super.onClick(event);
    }
}
