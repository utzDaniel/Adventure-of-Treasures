package frontend.model;

import backend.model.builder.item.Item;
import frontend.view.Colors;

import javax.swing.*;
import java.awt.*;

public final class ButtonItemFactory {

    private static int calcY(int index) {
        var y = 14;
        int mult = index / 6;
        if (index % 6 == 0)
            y = index <= 5 ? 14 + 55 * mult : 14 + 51 * mult;
        return y;
    }

    private static int calcX(int index) {
        var x = 17 + index * 53;
        if (index % 6 == 0)
            x = 17;
        return x;
    }

    public static JButton getInstance(Item item, int index) {
        var rectangle = new Rectangle(calcX(index), calcY(index), 37, 38);
        return create(item, rectangle);
    }

    private static JButton create(Item item, Rectangle rectangle) {
        var jButton = defaultSettings(item);
        jButton.setBounds(rectangle);
        return jButton;
    }

    private static JButton defaultSettings(Item item) {
        var jButton = new JButton();
        jButton.setActionCommand(item.getName());
        jButton.setName(item.getName());
        jButton.setIcon(item.getIcon());
        jButton.setBackground(Colors.BROWN_2);
        return jButton;
    }
}