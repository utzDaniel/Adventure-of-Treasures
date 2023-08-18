package frontend.service;

import backend.controller.interfaces.IItemDTO;
import frontend.model.component.ComponentFactory;

import javax.swing.*;
import java.util.List;

public final class LabelInformation {

    private int index;
    private final JLabel[] infoLabel;

    public LabelInformation() {
        this.infoLabel = new JLabel[4];
    }

    public void create(String info) {
        this.infoLabel[this.index] = ComponentFactory.getJLabel(info, this.index);
        this.index++;
    }

    public void updateText(IItemDTO item) {
        this.infoLabel[1].setText(String.format("%s: %s", this.infoLabel[1].getName(), item.name()));
        this.infoLabel[2].setText(String.format("%s: %s", this.infoLabel[2].getName(), item.weight()));
        this.infoLabel[3].setText(String.format("<html>%s: %s</html>", this.infoLabel[3].getName(), item.description()));
    }

    public void setText(List<String> information) {
        for (int i = 0; i < infoLabel.length; i++) {
            this.infoLabel[i].setText(information.get(i));
        }
    }

    public void resetText() {
        for (int i = 1; i < this.infoLabel.length; i++) {
            this.infoLabel[i].setText(this.infoLabel[i].getName());
        }
    }

    public JLabel[] getInfoLabel() {
        return this.infoLabel;
    }

}
