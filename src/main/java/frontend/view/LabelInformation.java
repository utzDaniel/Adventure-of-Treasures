package frontend.view;

import backend.model.builder.item.Item;
import frontend.model.component.ComponentFactory;

import javax.swing.*;

public class LabelInformation {

    private int index;
    private final JLabel[] infoLabel;

    public LabelInformation() {
        this.infoLabel = new JLabel[4];
        this.index = 0;
    }

    public void create(String info) {
        infoLabel[this.index] = ComponentFactory.getJLabel(info, this.index);
        this.index++;
    }


    public void updateText(Item item) {
        this.infoLabel[1].setText("Nome: " + item.getName());
        this.infoLabel[2].setText("Peso: " + item.getWeight());
        this.infoLabel[3].setText("<html>Descrição: " + item.getDescription() + "</html>");
    }

    public void resetText(int capacity, int maxCapacity) {
        this.infoLabel[0].setText(String.format("Capacidade do inventario %d/%d", capacity, maxCapacity));
        this.infoLabel[1].setText("Nome: ");
        this.infoLabel[2].setText("Peso: ");
        this.infoLabel[3].setText("Descrição: ");
    }

    public JLabel[] getInfoLabel() {
        return this.infoLabel;
    }

}
