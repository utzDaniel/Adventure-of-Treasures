package settings;

import javax.swing.*;
import java.awt.*;

public class SettingsJFrame {

    private final String VERSION = "1.0";
    private final String TITULO = String.format("Adventure of Treasures - Version %s",VERSION);
    private final int WIDTH = 816;
    private final int HEIGHT = 660;
    private int EixoX;
    private int EixoY;

    public SettingsJFrame() {
        location();
    }

    private void location(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int divisor = 2;
        EixoX = (dimension.width - getWidth()) / divisor ;
        EixoY = (dimension.height - getHeight()) / divisor;
    }

    public int closeOperation(){
        return WindowConstants.EXIT_ON_CLOSE;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getEixoX() {
        return EixoX;
    }

    public int getEixoY() {
        return EixoY;
    }

    public boolean isVisible() {
        return true;
    }

    public LayoutManager getLayout() {
        return null;
    }

    public String getTitulo() {
        return TITULO;
    }

    public String getVersion() {
        return VERSION;
    }
}
