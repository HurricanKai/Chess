package de.nosswald.chess.gui.element.impl.mainmenu;

import de.nosswald.chess.gui.*;
import de.nosswald.chess.gui.element.impl.ButtonElement;
import de.nosswald.chess.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

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
        graphics.drawString(title, x, y, width, height, Anchor.CENTER, Anchor.CENTER, Color.BLACK, new Font("Courier New", Font.PLAIN, new AbsoluteSize(36).get(0)));


        System.out.println("graphics: " + graphics.getWidth() + "  " + graphics.getHeight());

        CustomGraphics graphics2 = graphics.translate(this.x, this.y).clip(graphics, this.width, this.height);


        System.out.println(graphics2.getWidth() + "  " + graphics2.getHeight());


        if (isHovered())
        {
            String[] colors = { "pawn_white.png" , "pawn_black.png"};
            Image image = null;

            for (int i = 0; i < colors.length; i++)
            {
                File pawnFile = new ResourceLocation(colors[i], ResourceLocation.Type.PIECE).getFile();
                try
                {
                    image = ImageIO.read(pawnFile);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                //graphics2.drawRect(new RelativeSize(0), new RelativeSize(0), new RelativeSize(1), new RelativeSize(1), Color.GREEN);
                graphics2.drawSquareImage(image, new RelativeSize((i == 0 ? .2F : .8F)), new RelativeSize(0), new RelativeSize(1));
            }
        }
    }

    @Override
    public void onClick(MouseEvent event)
    {
        super.onClick(event);
    }
}
