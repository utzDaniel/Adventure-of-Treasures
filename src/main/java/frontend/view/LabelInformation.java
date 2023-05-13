package frontend.view;

import backend.model.builder.item.Item;
import frontend.model.component.ComponentFactory;

import javax.swing.*;

public class LabelInformation {

    private int index;
    private final JLabel[] infoLabel;

    public LabelInformation() {
        int MAX_LABEL = 4;
        this.infoLabel = new JLabel[MAX_LABEL];
    }

    public void create(String info) {
        infoLabel[this.index] = ComponentFactory.getJLabel(info, this.index);
        this.index++;
    }

    public void updateText(Item item) {
        this.infoLabel[1].setText(String.format("%s: %s",this.infoLabel[1].getName(), item.getName()));
        this.infoLabel[2].setText(String.format("%s: %s",this.infoLabel[2].getName(), item.getWeight()));
        this.infoLabel[3].setText(String.format("<html>%s: %s</html>",this.infoLabel[3].getName(), item.getDescription()));
    }

    public void updateTextCapacity(int capacity, int maxCapacity) {
        var space = this.infoLabel[0].getName().lastIndexOf(" ");
        var text = String.format("%s %d/%d",this.infoLabel[0].getName().substring(0, space), capacity, maxCapacity);
        this.infoLabel[0].setText(text);
        this.infoLabel[0].setName(text);
    }

    public void resetText() {
        for (JLabel jLabel : this.infoLabel) jLabel.setText(jLabel.getName());
    }

    public JLabel[] getInfoLabel() {
        return this.infoLabel;
    }

}
