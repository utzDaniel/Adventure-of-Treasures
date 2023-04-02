package frontend.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelInventory {

    private final JPanel panelMain;
    private final JLabel labelSideEast;
    private final JButton button;

    public PanelInventory(JLabel labelSideEast) {
        this.labelSideEast = labelSideEast;
        button = new JButton("SAIR");
        panelMain = new JPanel();
    }

    public void create() {
        panelMain.setBackground(Colors.BROWN_1);
        panelMain.setName("painel");
        panelMain.setBorder(new EmptyBorder(8, 10, 10, 10));
        panelMain.setLayout(new BorderLayout(6, 6));
        panelMain.setBounds(50, 50, 706, 500);//816 660
        setNorthPanel();
        setSouthPanel();
        setWestPanel();
        setEastPanel();
        labelSideEast.setIcon(new ImageIcon("src/main/resources/inventario/icons.png"));
    }

    private void setNorthPanel() {
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("src/main/resources/inventario/top.png"));
        label.setBounds(300, 0, 100, 100);
        panelMain.add(label, BorderLayout.NORTH);
    }

    private void setSouthPanel() {
        button.setBackground(Colors.RED);
        button.setForeground(Colors.SILVER);
        panelMain.add(button, BorderLayout.SOUTH);
    }

    private void setWestPanel() {
        JLabel label = new JLabel();
        label.setLayout(new BorderLayout(8, 8));
        label.setIcon(new ImageIcon("src/main/resources/inventario/player.png"));
        label.setBounds(0, 100, 100, 100);
        panelMain.add(label, BorderLayout.WEST);
    }

    private void setEastPanel() {
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout(8, 8));
        eastPanel.add(labelSideEast, BorderLayout.NORTH);
        panelMain.add(eastPanel, BorderLayout.EAST);
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getPanel() {
        return panelMain;
    }
}
