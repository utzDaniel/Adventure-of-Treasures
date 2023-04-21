package frontend.model.component;

import frontend.exception.LabelException;

import javax.swing.*;
import java.awt.*;

final class JLabelInventoryFactory {

    private JLabelInventoryFactory() {
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
        label.setName("norte");
        label.setIcon(new ImageIcon("src/main/resources/inventario/top.png"));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
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
        label.setName("leste");
        label.setIcon(new ImageIcon("src/main/resources/inventario/icons.png"));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 19));
        label.setLayout(new BorderLayout(0, 0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setBounds(0, 0, 0, 0);
        label.setVisible(true);
        return label;
    }

    private static JLabel westLabel() {
        var label = new JLabel();
        label.setName("oeste");
        label.setIcon(new ImageIcon("src/main/resources/inventario/player.png"));
        label.setBorder(BorderFactory.createEmptyBorder(42, 0, 0, 0));
        label.setLayout(new BorderLayout(0, 0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setBounds(0, 0, 0, 0);
        label.setVisible(true);
        return label;
    }
}
