package frontend.model.component;

import frontend.service.Colors;
import backend.controller.interfaces.IItemDTO;

import javax.swing.*;
import java.awt.*;

final class InventoryJButtonItemFactory {

    private static final int BUTTON_PER_LINE = 6;
    private static final int DISTANCE_PER_BUTTON = 53;

    private InventoryJButtonItemFactory() {
    }

    static JButton getInstance(IItemDTO item, int index) {
        var width = 40;
        var height = 40;
        var rectangle = new Rectangle(calculateXPosition(index), calculateYPosition(index), width, height);
        return create(item, rectangle);
    }

    private static int calculateYPosition(int index) {
        var positionStartingY = 46;
        int multiplier = index / BUTTON_PER_LINE;
        return positionStartingY + multiplier * DISTANCE_PER_BUTTON;
    }

    private static int calculateXPosition(int index) {
        var positionStartingX = 16;
        int multiplier = index % BUTTON_PER_LINE;
        return positionStartingX + multiplier * DISTANCE_PER_BUTTON;
    }

    private static JButton create(IItemDTO item, Rectangle rectangle) {
        var jButton = defaultSettings(item);
        jButton.setBounds(rectangle);
        return jButton;
    }

    private static JButton defaultSettings(IItemDTO item) {
        var jButton = new JButton();
        jButton.setActionCommand(item.name());
        jButton.setName(item.name());
        jButton.setIcon(new ImageIcon(item.icon()));
        jButton.setBackground(Colors.BROWN_2);
        return jButton;
    }
}