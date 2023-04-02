package frontend.settings;

import javax.swing.*;
import java.awt.*;

public class SettingsPlayer implements ISettingsJLabel {

    private final Rectangle rectangle;

    public SettingsPlayer() {
        int x = 300;
        int y = 470;
        int WIDTH = 32;
        int HEIGHT = 47;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("src/main/resources/player/baixo_C.png");
    }

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
}
