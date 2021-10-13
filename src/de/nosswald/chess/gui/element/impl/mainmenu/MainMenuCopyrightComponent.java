package de.nosswald.chess.gui.element.impl.mainmenu;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.Anchor;
import de.nosswald.chess.gui.SizeReference;
import de.nosswald.chess.gui.element.Component;
import de.nosswald.chess.gui.element.impl.ImageButtonElement;
import de.nosswald.chess.gui.element.impl.LabelElement;
import de.nosswald.chess.utils.ResourceLocation;

import java.awt.*;

public class MainMenuCopyrightComponent extends Component
{
    public MainMenuCopyrightComponent(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);

        this.elements.add(
                new ImageButtonElement(new ResourceLocation("github.png", ResourceLocation.Type.TEXTURE).getFile(), x, y, height, height)
        );
        this.elements.add(
                new LabelElement("X", x, y, width, height, Anchor.CENTER, Anchor.CENTER)
                        .setColor(Color.BLACK)
                        .setFont(new Font("Verdana", Font.PLAIN, 36))
        );
        this.elements.add(
                new LabelElement(Chess.APP_AUTHORS[0], x, y, width, height, Anchor.POSITIVE, Anchor.NEGATIVE)
                        .setColor(Color.BLACK)
                        .setFont(new Font("Verdana", Font.PLAIN, 11))
        );
        this.elements.add(
                new LabelElement(Chess.APP_AUTHORS[1], x, y, width, height, Anchor.POSITIVE, Anchor.POSITIVE)
                        .setColor(Color.BLACK)
                        .setFont(new Font("Verdana", Font.PLAIN, 11))
        );
    }
}
