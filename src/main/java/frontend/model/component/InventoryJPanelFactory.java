package frontend.model.component;

import frontend.exception.PanelException;
import frontend.view.Colors;

import javax.swing.*;
import java.awt.*;

final class InventoryJPanelFactory {
    private InventoryJPanelFactory() {
    }

    static JPanel getInstance(String name) {
        return switch (name) {
            case "principal" -> mainPanel();
            case "norte" -> northPanel();
            case "leste" -> eastPanel();
            case "sul" -> southPanel();
            case "oeste" -> westPanel();
            default -> throw new PanelException("Nome do painel não encontrado");
        };
    }

    private static JPanel mainPanel() {
        var jPanel = new JPanel();
        var x = 40;
        var y = 30;
        var width = 794 - x * 2;
        var height = 600 - y * 2;
        jPanel.setName("painel_principal");
        jPanel.setBackground(Colors.BROWN_1);
        jPanel.setLayout(new BorderLayout(0, 0));
        jPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel.setBounds(x, y, width, height);
        return jPanel;
    }

    private static JPanel northPanel() {
        var jPanel = new JPanel();
        jPanel.setName("painel_norte");
        jPanel.setOpaque(false);
        jPanel.setLayout(new BorderLayout(0, 0));
        jPanel.setBounds(0, 0, 0, 61);
        return jPanel;
    }

    private static JPanel eastPanel() {
        var jPanel = new JPanel();
        jPanel.setName("painel_leste");
        jPanel.setOpaque(false);
        jPanel.setLayout(new BorderLayout(0, 0));
        jPanel.setBounds(0, 0, 0, 0);
        return jPanel;
    }

    private static JPanel southPanel() {
        var jPanel = new JPanel();
        jPanel.setName("painel_sul");
        jPanel.setOpaque(false);
        jPanel.setLayout(new BorderLayout(0, 0));
        jPanel.setBounds(0, 0, 0, 32);
        return jPanel;
    }

    private static JPanel westPanel() {
        var jPanel = new JPanel();
        jPanel.setName("painel_oeste");
        jPanel.setOpaque(false);
        jPanel.setLayout(new BorderLayout(0, 0));
        jPanel.setBounds(0, 0, 0, 0);
        return jPanel;
    }
}
