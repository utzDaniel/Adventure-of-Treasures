package frontend.model.component;

import frontend.exception.LabelException;

import javax.swing.*;
import java.awt.*;

final class InventoryJLabelFactory {

    private InventoryJLabelFactory() {
    }

    static JLabel getInstance(String name) {
        return switch (name) {
            case "norte" -> northLabel();
            case "leste" -> eastLabel();
            case "oeste" -> westLabel();
            default -> throw new LabelException("Nome da label não encontrado");
        };
    }

    private static JLabel northLabel() {
        var label = new JLabel();
        label.setName("label_norte");
        label.setIcon(new ImageIcon("src/main/resources/image/inventario/top.png"));
        label.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        label.setLayout(new BorderLayout(0, 0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setBounds(0, 0, 0, 0);
        label.setVisible(true);
        return label;
    }

    private static JLabel eastLabel() {
        var label = new JLabel();
        label.setName("label_leste");
        label.setIcon(new ImageIcon("src/main/resources/image/inventario/icons.png"));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 19));
        label.setLayout(null);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setBounds(0, 0, 0, 0);
        label.setVisible(true);
        return label;
    }

    private static JLabel westLabel() {
        var label = new JLabel();
        label.setName("label_oeste");
        label.setIcon(new ImageIcon("src/main/resources/image/inventario/player.png"));
        label.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        label.setLayout(new BorderLayout(0, 0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setBounds(0, 0, 0, 0);
        label.setVisible(true);
        return label;
    }
}
