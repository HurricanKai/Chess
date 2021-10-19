package de.nosswald.chess.gui.element.impl.titlebar;

import de.nosswald.chess.Chess;
import de.nosswald.chess.gui.*;
import de.nosswald.chess.gui.element.Component;
import de.nosswald.chess.gui.element.impl.LabelElement;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class TitleBarComponent extends Component
{
    private int posX, posY;
    private CustomGraphics graphics;

    /**
     * @param x         the x position
     * @param y         the y position
     * @param width     the width
     * @param height    the height
     */
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
                        .setAction(() -> Chess.getInstance().getFrame().setExtendedState(
                                Chess.getInstance().getFrame().getExtendedState() == Frame.NORMAL ? Frame.MAXIMIZED_BOTH : Frame.NORMAL))
        );
    }

    @Override
    public void onPaint(CustomGraphics graphics)
    {
        graphics.drawRect(x, y, width, height, new Color(242, 242, 242));

        this.graphics = graphics;

        super.onPaint(graphics);
    }

    @Override
    public void onClick(MouseEvent event)
    {
        posX = event.getX();
        posY = event.getY();

        super.onClick(event);
    }

    /**
     * Called when the mouse is dragging the frame
     *
     * @param event the event
     */
    public void onDrag(MouseEvent event)
    {
        if (isHovered())
            Chess.getInstance().getFrame().setLocation(event.getXOnScreen() - posX, event.getYOnScreen() - posY);
    }

    /**
     * @return if the mouse cursor is currently hovering the button
     */
    private boolean isHovered()
    {
        return (mouseX >= graphics.getOffX() + x.get(graphics.getWidth())
                && mouseX <= graphics.getOffX() + x.get(graphics.getWidth()) + width.get(graphics.getWidth())
                && mouseY >= graphics.getOffY() + y.get(graphics.getHeight())
                && mouseY <= graphics.getOffY() + y.get(graphics.getHeight()) + height.get(graphics.getHeight()));
    }
}
