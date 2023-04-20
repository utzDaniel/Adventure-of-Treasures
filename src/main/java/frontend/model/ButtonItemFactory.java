package frontend.model;

import backend.model.builder.item.Item;
import frontend.view.Colors;

import javax.swing.*;
import java.awt.*;

public final class ButtonItemFactory {

    private static final int BUTTON_PER_LINE = 6;
    private static final int DISTANCE_PER_BUTTON = 53;
    private static final int POSITION_STARTING_Y = 14;
    private static final int POSITION_STARTING_X = 16;


    private static int calcY(int index) {
        int mult = index / BUTTON_PER_LINE;
        return POSITION_STARTING_Y + mult * DISTANCE_PER_BUTTON;
    }

    private static int calcX(int index) {
        int mult = index % BUTTON_PER_LINE;
        return POSITION_STARTING_X + mult * DISTANCE_PER_BUTTON;
    }

    public static JButton getInstance(Item item, int index) {
        var rectangle = new Rectangle(calcX(index), calcY(index), 40, 40);
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