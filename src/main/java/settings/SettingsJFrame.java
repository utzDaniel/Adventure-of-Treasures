package settings;

import javax.swing.*;
import java.awt.*;

public class SettingsJFrame {

    private final String VERSION = "1.0";
    private final String TITULO = String.format("Adventure of Treasures - Version %s",VERSION);
    private final int WIDTH = 810;
    private final int HEIGHT = 662;

    public int closeOperation(){
        return JFrame.EXIT_ON_CLOSE;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public boolean isVisible() {
        return true;
    }

    public LayoutManager getLayout() {
        return null;
    }

    //Deixar a tela no meio automatizamente
    public Component getLocationRelativeTo() {
        return null;
    }

    public String getTitulo() {
        return TITULO;
    }

    ////Disabilitar o maximizar e aumentar as lateral
    public boolean getResizable() {
        return false;
    }
}
