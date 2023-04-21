package frontend.model.component;

import javax.swing.*;

public final class JMenuBarFactory {

    private JMenuBarFactory() {
    }

    public static JMenuBar getInstance() {
        return new JMenuBar();
    }

}
