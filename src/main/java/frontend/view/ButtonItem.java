package frontend.view;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.item.ItemCombinable;
import frontend.model.ButtonItemFactory;

import javax.swing.*;
import java.util.Arrays;

public class ButtonItem {

    private int index;
    private boolean isEnableIButtonItensNotCombinable;
    private final JButton[] buttonItens;

    public ButtonItem() {
        buttonItens = new JButton[24];
    }

    public void create(Item item) {
        buttonItens[index] = ButtonItemFactory.getInstance(item, index);
        isEnableIButtonItensNotCombinable = false;
        index++;
    }

    public void remove(JLabel labelSideEast) {
        Arrays.stream(buttonItens).limit(index)
                .forEach(labelSideEast::remove);
        index = 0;
    }

    public JButton getLast() {
        if (index == 0)
            return null;
        return buttonItens[index - 1];
    }

    public JButton[] getButtonItens() {
        JButton[] buttons = new JButton[index];
        System.arraycopy(buttonItens, 0, buttons, 0, index);
        return buttons;
    }

    public void enableIButtonItensNotCombinable() {
        if (!isEnableIButtonItensNotCombinable) {
            Arrays.stream(getButtonItens()).forEach(jButton -> {
                Item item = Player.getInstance().getInventory().getItem(jButton.getName());
                if (!(item instanceof ItemCombinable)) {
                    jButton.setEnabled(false);
                    jButton.setBackground(Colors.GREY);
                }
            });
            isEnableIButtonItensNotCombinable = true;
        }
    }

    public void selectButtonItem(Item item) {
        Arrays.stream(getButtonItens())
                .filter(jButton -> jButton.getName().equals(item.getName()))
                .findFirst().ifPresent(jButton -> jButton.setBackground(Colors.GREEN));
    }
}