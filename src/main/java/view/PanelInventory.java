package view;

import repository.CreateImageInventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelInventory {

    private final JPanel panelMain;
    private final CreateImageInventory imageInventory;
    private final JLabel labelSideEast;
    private final JButton button;

    public PanelInventory(JLabel labelSideEast){
        this.labelSideEast = labelSideEast;
        button = new JButton("SAIR");
        panelMain = new JPanel();
        imageInventory = new CreateImageInventory();
    }

    public void create(){
        panelMain.setBackground(Colors.BROWN_1);
        panelMain.setBorder(new EmptyBorder(8, 10, 10, 10));
        panelMain.setLayout(new BorderLayout(6, 6));
        panelMain.setBounds(50, 50, 706, 500);//816 660
        setNorthPanel();
        setSouthPanel();
        setWestPanel();
        setEastPanel();
        labelSideEast.setIcon(imageInventory.selectImage("icons"));
    }

    private void setNorthPanel() {
        JLabel label = new JLabel();
        label.setIcon(imageInventory.selectImage("top"));
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
        label.setIcon(imageInventory.selectImage("player"));
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
