package frontend.settings;

import javax.swing.*;
import java.awt.*;

public class SettingsItem implements ISettingsJLabel {

    private final Rectangle rectangle;

    public SettingsItem() {
        int x = 0;
        int y = 0;
        int WIDTH = 80;
        int HEIGHT = 50;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public ImageIcon getIcon() {
        return null;
    }

    @Override
    public String getName() {
        return "item";
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
}
