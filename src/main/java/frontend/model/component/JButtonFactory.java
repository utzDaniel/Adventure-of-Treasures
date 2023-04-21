package frontend.model.component;

import backend.model.builder.item.Item;

import javax.swing.*;

public final class JButtonFactory {

    private JButtonFactory() {
    }

    public static JButton getInstance(Item item, int index) {
        return JButtonItemFactory.getInstance(item, index);
    }

    public static JButton getInstance(String name) {
        return JButtonActionFactory.getInstance(name);
    }
}
