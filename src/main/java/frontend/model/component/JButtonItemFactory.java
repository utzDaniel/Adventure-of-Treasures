package frontend.model.component;

import backend.model.builder.item.Item;
import frontend.view.Colors;

import javax.swing.*;
import java.awt.*;

final class JButtonItemFactory {

    private static final int BUTTON_PER_LINE = 6;
    private static final int DISTANCE_PER_BUTTON = 53;

    private JButtonItemFactory() {
    }

    static JButton getInstance(Item item, int index) {
        var width = 40;
        var height = 40;
        var rectangle = new Rectangle(calculateXPosition(index), calculateYPosition(index), width, height);
        return create(item, rectangle);
    }

    private static int calculateYPosition(int index) {
        var positionStartingY = 14;
        int multiplier = index / BUTTON_PER_LINE;
        return positionStartingY + multiplier * DISTANCE_PER_BUTTON;
    }

    private static int calculateXPosition(int index) {
        var positionStartingX = 16;
        int multiplier = index % BUTTON_PER_LINE;
        return positionStartingX + multiplier * DISTANCE_PER_BUTTON;
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