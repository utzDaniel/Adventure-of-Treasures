package frontend.model.component;

import javax.swing.*;

public final class JMenuFactory {

    private JMenuFactory() {
    }

    public static JMenu getInstance(String name) {
        var menu = new JMenu();
        menu.setText(name);
        return menu;
    }
}
