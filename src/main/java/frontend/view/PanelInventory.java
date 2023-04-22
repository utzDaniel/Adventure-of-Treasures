package frontend.view;

import frontend.model.component.JLabelFactory;
import frontend.model.component.JPanelFactory;

import javax.swing.*;
import java.awt.*;

public class PanelInventory {

    private JPanel panelMain;
    private JLabel labelEast;
    private final JButton button;

    public PanelInventory() {
        button = new JButton();
    }

    public void create() {
        panelMain = JPanelFactory.getInstance("principal");
        setNorthPanel();
        setEastPanel();
        setSouthPanel();
        setWestPanel();
    }

    private void setNorthPanel() {
        var name = "norte";
        var panel = JPanelFactory.getInstance(name);
        var label = JLabelFactory.getInstance(name);

        //TODO criar uma generico ComponentsFactory
        button.setName("sair");
        button.setIcon(new ImageIcon("src/main/resources/inventario/botao_fechar.png"));
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
        var panel = JPanelFactory.getInstance(name);
        labelEast = JLabelFactory.getInstance(name);
        panel.add(labelEast, BorderLayout.CENTER);
        panelMain.add(panel, BorderLayout.EAST);
    }

    private void setSouthPanel() {
        //TODO colocar futuramento os log aqui com uma JLabel

        var name = "sul";
        var panel = JPanelFactory.getInstance(name);
        panelMain.add(panel, BorderLayout.SOUTH);
    }

    private void setWestPanel() {
        var name = "oeste";
        var panel = JPanelFactory.getInstance(name);
        var label = JLabelFactory.getInstance(name);
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
