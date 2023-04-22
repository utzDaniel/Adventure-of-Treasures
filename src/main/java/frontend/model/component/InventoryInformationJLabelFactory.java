package frontend.model.component;

import frontend.view.Colors;

import javax.swing.*;
import java.awt.*;

final class InventoryInformationJLabelFactory {

    //TODO colocar variaveis globais EX: textSize, fontName

    private InventoryInformationJLabelFactory() {
    }

    static JLabel getInstance(String name, int index) {
        var width = 305;
        var x = 17;
        var rectangle = new Rectangle(x, calculateYPosition(index), width, calculateHeight(name));
        var jLabel = defaultSettings();
        jLabel.setText(name);
        jLabel.setBounds(rectangle);
        jLabel.setHorizontalAlignment(selectHorizontalAlignment(name));
        return jLabel;
    }

    private static int calculateHeight(String name) {
        return name.contains("Descrição") ? 14 * 2 : 14;
    }

    private static int selectHorizontalAlignment(String name) {
        return name.contains("Capacidade do inventario") ? SwingConstants.RIGHT : SwingConstants.LEFT;
    }

    private static int calculateYPosition(int index) {
        var positionStartingY = 254;
        return positionStartingY + index * 20;
    }

    private static JLabel defaultSettings() {
        var textSize = 12;
        var fontName = "Arial";
        var jLabel = new JLabel();
        jLabel.setFont(new Font(fontName, Font.PLAIN, textSize));
        jLabel.setForeground(Colors.WHITE);
        jLabel.setVerticalAlignment(SwingConstants.TOP);
        jLabel.setAlignmentY(0.0f);
        return jLabel;
    }
}
