package frontend.settings;

import javax.swing.*;
import java.awt.*;

public class SettingsMapGame implements ISettingsJLabel {

    private final Rectangle rectangle;

    public SettingsMapGame() {
        int x = 0;
        int y = 0;
        int WIDTH = 800;
        int HEIGHT = 600;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("src/main/resources/map/cais.png");
    }

    @Override
    public String getName() {
        return "mapa";
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
}
