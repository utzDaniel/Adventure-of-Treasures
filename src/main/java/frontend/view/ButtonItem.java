package frontend.view;

import backend.service.interfaces.ICombinable;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import frontend.model.component.ComponentFactory;
import backend.controller.interfaces.IItemDTO;

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
    public void create(IItemDTO item) {
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
                Item item = Player.getInstance().getInventory().getItem(jButton.getName());
                if (!(item instanceof ICombinable)) {
                    jButton.setEnabled(false);
                    jButton.setBackground(Colors.GREY);
                }
            });
            this.isEnableIButtonItensNotCombinable = true;
        }
    }

    public void selectButtonItem(IItemDTO item) {
        Arrays.stream(getButtonItens())
                .filter(jButton -> jButton.getName().equals(item.name()))
                .findFirst().ifPresent(jButton -> jButton.setBackground(Colors.GREEN));
    }
}
