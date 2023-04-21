package frontend.model.component;

import javax.swing.*;

public final class JMenuItemFactory {

    private JMenuItemFactory() {
    }

    public static JMenuItem getInstance(String name) {
        var nameItem = validName(name);
        var jMenuItem = new JMenuItem();
        jMenuItem.setText(nameItem);
        return jMenuItem;
    }

    private static String validName(String name) {
        if (name.equals("Musica") || name.equals("Efeitos"))
            return "On/Off";
        else
            return name;
    }

}
