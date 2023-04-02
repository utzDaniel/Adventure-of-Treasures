package frontend.view;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.item.ItemCombinable;

import javax.swing.*;
import java.util.Arrays;

public class ButtonItem {

    private int index;
    private int positionX;
    private int positionY;
    private boolean isEnableIButtonItensNotCombinable;
    private final JButton[] buttonItens;

    public ButtonItem() {
        buttonItens = new JButton[24];
        reset();
    }

    public void create(Item item) {
        setButtonItens(item);
        isEnableIButtonItensNotCombinable = false;
        update();
    }

    private void setButtonItens(Item item) {
        buttonItens[index] = new JButton();
        buttonItens[index].setActionCommand(item.getName());
        buttonItens[index].setName(item.getName());
        buttonItens[index].setIcon(item.getImage());
        buttonItens[index].setBackground(Colors.BROWN_2);
        buttonItens[index].setBounds(positionX, positionY, 37, 38);
    }

    private void update() {
        index++;
        positionX += 53;
        if (index % 6 == 0) updateLine();
    }

    private void updateLine() {
        positionY += index <= 5 ? 55 : 51;
        positionX = 17;
    }

    private void reset() {
        index = 0;
        positionX = 17;
        positionY = 14;
    }

    public void remove(JLabel labelSideEast) {
        Arrays.stream(buttonItens).limit(index)
                .forEach(labelSideEast::remove);
        reset();
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
