package frontend.service;

import frontend.model.component.ComponentFactory;

import javax.swing.*;
import java.awt.*;

public final class PanelInventory {

    private JPanel panelMain;
    private JLabel labelEast;
    private final JButton button;

    public PanelInventory() {
        button = new JButton();
    }

    public void create() {
        var name = "principal";
        panelMain = ComponentFactory.getJPanel(name);
        setNorthPanel();
        setEastPanel();
        setSouthPanel();
        setWestPanel();
    }

    private void setNorthPanel() {
        var name = "norte";
        var panel = ComponentFactory.getJPanel(name);
        var label = ComponentFactory.getJLabel(name);

        //TODO criar uma generico ComponentsFactory
        button.setName("sair");
        button.setIcon(new ImageIcon("src/main/resources/image/inventario/botao_fechar.png"));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setAlignmentX(0.0f);
        button.setAlignmentY(0.0f);
        button.setBounds(0, 0, 0, 0);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setLayout(new BorderLayout(0, 0));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
        button.setVerticalAlignment(SwingConstants.TOP);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);

        panel.add(label, BorderLayout.WEST);
        panel.add(button, BorderLayout.EAST);
        panelMain.add(panel, BorderLayout.NORTH);
    }

    private void setEastPanel() {
        var name = "leste";
        var panel = ComponentFactory.getJPanel(name);
        labelEast = ComponentFactory.getJLabel(name);
        panel.add(labelEast, BorderLayout.CENTER);
        panelMain.add(panel, BorderLayout.EAST);
    }

    private void setSouthPanel() {
        //TODO colocar futuramento os log aqui com uma JLabel

        var name = "sul";
        var panel = ComponentFactory.getJPanel(name);
        panelMain.add(panel, BorderLayout.SOUTH);
    }

    private void setWestPanel() {
        var name = "oeste";
        var panel = ComponentFactory.getJPanel(name);
        var label = ComponentFactory.getJLabel(name);
        panel.add(label, BorderLayout.CENTER);
        panelMain.add(panel, BorderLayout.WEST);
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getPanel() {
        return panelMain;
    }

    public JLabel getLabel() {
        return labelEast;
    }
}
