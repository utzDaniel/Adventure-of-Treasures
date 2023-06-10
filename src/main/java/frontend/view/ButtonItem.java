package frontend.view;

import backend.service.model.Player;
import frontend.model.component.ComponentFactory;
import backend.controller.interfaces.IItem;

import javax.swing.*;
import java.util.Arrays;

public final class ButtonItem {

    private int index;
    private boolean isEnableIButtonItensNotCombinable;
    private final JButton[] buttonItens;

    public ButtonItem() {
        int MAX_BUTTON = 24;
        this.buttonItens = new JButton[MAX_BUTTON];
        this.isEnableIButtonItensNotCombinable = false;
    }
    public void create(IItem item) {
        this.buttonItens[this.index] = ComponentFactory.getJButton(item, this.index);
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

    // TODO resolver de outra forma o instanceof
    public void enableIButtonItensNotCombinable() {
        if (!this.isEnableIButtonItensNotCombinable) {
            Arrays.stream(getButtonItens()).forEach(jButton -> {
                IItemDomain item = Player.getInstance().getInventory().getItem(jButton.getName());
                if (!(item.getSpecialization().contains("ItemCombinable"))) {
                    jButton.setEnabled(false);
                    jButton.setBackground(Colors.GREY);
                }
            });
            this.isEnableIButtonItensNotCombinable = true;
        }
    }

    public void selectButtonItem(IItem item) {
        Arrays.stream(getButtonItens())
                .filter(jButton -> jButton.getName().equals(item.getName()))
                .findFirst().ifPresent(jButton -> jButton.setBackground(Colors.GREEN));
    }
}
