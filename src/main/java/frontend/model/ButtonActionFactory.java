package frontend.model;

import frontend.exception.ButtonException;
import frontend.view.Colors;

import javax.swing.*;
import java.awt.*;

public final class ButtonActionFactory{

    public static JButton getInstance(String name) {
        return switch (name) {
            case "usar" -> actionUsable();
            case "equipar" -> actionEquipable();
            case "combinar" -> actionCombinable();
            case "remover" -> actionRemove();
            case "cancelar" -> actionCancel();
            case "confirmar" -> actionConfirm();
            default -> throw new ButtonException("Nome do botão não encontrado");
        };
    }

    private static JButton actionUsable() {
        var name = "usar";
        var color = Colors.BLUE;
        var rectangle = new Rectangle(12, 320, 75, 30);
        return create(name, color, rectangle);
    }

    private static JButton actionEquipable() {
        var name = "equipar";
        var color = Colors.BLUE;
        var rectangle = new Rectangle(92, 320, 75, 30);
        return create(name, color, rectangle);
    }

    private static JButton actionCombinable() {
        var name = "combinar";
        var color = Colors.BLUE;
        var rectangle = new Rectangle(172, 320, 75, 30);
        return create(name, color, rectangle);
    }

    private static JButton actionRemove() {
        var name = "remover";
        var color = Colors.BLUE;
        var rectangle = new Rectangle(252, 320, 75, 30);
        return create(name, color, rectangle);
    }

    private static JButton actionCancel() {
        var name = "cancelar";
        var color = Colors.RED;
        var rectangle = new Rectangle(30, 360, 130, 30);
        return create(name, color, rectangle);
    }

    private static JButton actionConfirm() {
        var name = "confirmar";
        var color = Colors.GREEN;
        var rectangle = new Rectangle(180, 360, 130, 30);
        return create(name, color, rectangle);
    }

    private static JButton create(String name, Color color, Rectangle rectangle) {
        var jButton = defaultSettings(name);
        jButton.setBackground(color);
        jButton.setBounds(rectangle);
        return jButton;
    }

    private static JButton defaultSettings(String name) {
        var jButton = new JButton();
        jButton.setText(name.toUpperCase());
        jButton.setActionCommand(name);
        jButton.setAlignmentY(0.0f);
        jButton.setFont(new Font("Arial", Font.PLAIN, 10));
        jButton.setMargin(new Insets(0, 0, 0, 0));
        jButton.setForeground(Colors.WHITE);
        jButton.setVisible(false);
        return jButton;
    }
}