package frontend.model.component;

import frontend.exception.ButtonException;
import frontend.view.Colors;

import javax.swing.*;
import java.awt.*;

final class JButtonActionFactory {
    private static final int HEIGHT = 30;
    private static final String[] NAMES = {"usar", "equipar", "combinar", "remover", "cancelar", "confirmar"};

    private JButtonActionFactory() {
    }

    static JButton getInstance(String name) {
        return switch (name) {
            case "usar" -> actionUsable(Colors.BLUE);
            case "equipar" -> actionEquipable(Colors.BLUE);
            case "combinar" -> actionCombinable(Colors.BLUE);
            case "remover" -> actionRemove(Colors.BLUE);
            case "cancelar" -> actionCancel(Colors.RED);
            case "confirmar" -> actionConfirm(Colors.GREEN);
            default -> throw new ButtonException("Nome do botão não encontrado");
        };
    }

    private static int calculateWidth(int type) {
        return type == 1 ? 75 : 130;
    }

    private static int calculateYPosition(int type) {
        return type == 1 ? 352 : 392;
    }

    private static int calculateXPosition(int type, int index) {
        var positionStartingX = type == 1 ? 12 : 30;
        var multiplier = type == 1 ? 80 : 150;
        return positionStartingX + multiplier * index;
    }

    private static JButton actionUsable(Color color) {
        var rectangle = new Rectangle(calculateXPosition(1, 0), calculateYPosition(1), calculateWidth(1), HEIGHT);
        return create(NAMES[0], color, rectangle);
    }

    private static JButton actionEquipable(Color color) {
        var rectangle = new Rectangle(calculateXPosition(1, 1), calculateYPosition(1), calculateWidth(1), HEIGHT);
        return create(NAMES[1], color, rectangle);
    }

    private static JButton actionCombinable(Color color) {
        var rectangle = new Rectangle(calculateXPosition(1, 2), calculateYPosition(1), calculateWidth(1), HEIGHT);
        return create(NAMES[2], color, rectangle);
    }

    private static JButton actionRemove(Color color) {
        var rectangle = new Rectangle(calculateXPosition(1, 3), calculateYPosition(1), calculateWidth(1), HEIGHT);
        return create(NAMES[3], color, rectangle);
    }

    private static JButton actionCancel(Color color) {
        var rectangle = new Rectangle(calculateXPosition(2, 0), calculateYPosition(2), calculateWidth(2), HEIGHT);
        return create(NAMES[4], color, rectangle);
    }

    private static JButton actionConfirm(Color color) {
        var rectangle = new Rectangle(calculateXPosition(2, 1), calculateYPosition(2), calculateWidth(2), HEIGHT);
        return create(NAMES[5], color, rectangle);
    }

    private static JButton create(String name, Color color, Rectangle rectangle) {
        var jButton = defaultSettings();
        jButton.setText(name.toUpperCase());
        jButton.setActionCommand(name);
        jButton.setBackground(color);
        jButton.setBounds(rectangle);
        return jButton;
    }

    private static JButton defaultSettings() {
        var textSize = 10;
        var fontName = "Arial";
        var jButton = new JButton();
        jButton.setAlignmentY(0.0f);
        jButton.setFont(new Font(fontName, Font.PLAIN, textSize));
        jButton.setMargin(new Insets(0, 0, 0, 0));
        jButton.setForeground(Colors.WHITE);
        jButton.setVisible(false);
        return jButton;
    }
}