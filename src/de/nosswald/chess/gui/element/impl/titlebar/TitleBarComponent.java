package de.nosswald.chess.gui.element.impl.titlebar;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.*;
import de.nosswald.chess.gui.element.Component;
import de.nosswald.chess.gui.element.impl.LabelElement;

import java.awt.*;
import java.awt.event.MouseEvent;

public final class TitleBarComponent extends Component
{
    public TitleBarComponent(SizeReference x, SizeReference y, SizeReference width, SizeReference height)
    {
        super(x, y, width, height);

        this.elements.add(
                new LabelElement(Chess.APP_NAME, x, y, width, height, Anchor.CENTER, Anchor.CENTER)
                        .setColor(Color.BLACK)
                        .setFont(new Font("Verdana", Font.PLAIN, new AbsoluteSize(18).get(0)))
        );
        this.elements.add(
                new TitleBarButtonElement(new AdditionSize(new AdditionSize(x, width), new AbsoluteSize(-25)), new AbsoluteSize(5), new AbsoluteSize(20), new Color(255, 84, 77))
                        .setAction(() -> System.exit(0))
        );
        this.elements.add(
                new TitleBarButtonElement(new AdditionSize(new AdditionSize(x, width), new AbsoluteSize(-56)), new AbsoluteSize(5), new AbsoluteSize(20), new Color(254, 180, 41))
                        .setAction(() -> Chess.getInstance().getFrame().setState(Frame.ICONIFIED))
        );
        this.elements.add(
                new TitleBarButtonElement(new AdditionSize(new AdditionSize(x, width), new AbsoluteSize(-87)), new AbsoluteSize(5), new AbsoluteSize(20), new Color(36, 193, 56))
                        .setAction(() -> Chess.getInstance().getFrame().setExtendedState(Frame.MAXIMIZED_BOTH))
        );
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        graphics.drawRect(x, y, width, height, new Color(242, 242, 242));

        super.onPaint(graphics);
    }

    @Override
    public void onClick(MouseEvent event)
    {
        // TODO window movement

        super.onClick(event);
    }
}
