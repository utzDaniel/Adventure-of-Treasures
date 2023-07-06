package frontend.service;

import backend.controller.interfaces.IItemDTO;
import frontend.model.component.ComponentFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ButtonItem {

    private int index;
    private boolean isEnableIButtonItensNotCombinable;
    private final JButton[] buttonItens;
    private final List<Integer> indexCombinable;

    public ButtonItem() {
        this.buttonItens = new JButton[24];
        this.indexCombinable = new ArrayList<>();
        this.isEnableIButtonItensNotCombinable = false;
    }

    public void create(IItemDTO item) {
        this.buttonItens[this.index] = ComponentFactory.getJButton(item, this.index);
        if (item.specialization().contains("ItemCombinable")) this.indexCombinable.add(this.index);
        this.index++;
    }

    public void defaultJButtonSelected() {
        Arrays.stream(this.buttonItens).limit(this.index)
                .forEach(jButton -> {
                    jButton.setBackground(Colors.DEFAULT_BACKGROUND);
                    jButton.setEnabled(true);
                });
        this.isEnableIButtonItensNotCombinable = false;
    }

    public JButton getLast() {
        if (this.index == 0)
            return null;
        return this.buttonItens[index - 1];
    }

    public JButton[] getButtonItens() {
        JButton[] buttons = new JButton[this.index];
        System.arraycopy(this.buttonItens, 0, buttons, 0, this.index);
        return buttons;
    }

    public void enableIButtonItensNotCombinable() {
        if (!this.isEnableIButtonItensNotCombinable) {
            for (int i = 0; i < this.index; i++) {
                if (!(this.indexCombinable.contains(i))) {
                    this.buttonItens[i].setEnabled(false);
                    this.buttonItens[i].setBackground(Colors.GREY);
                }
            }
            this.isEnableIButtonItensNotCombinable = true;
        }
    }

    public void selectButtonItem(IItemDTO item) {
        Arrays.stream(getButtonItens())
                .filter(jButton -> jButton.getName().equals(item.name()))
                .findFirst().ifPresent(jButton -> jButton.setBackground(Colors.GREEN));
    }
}
