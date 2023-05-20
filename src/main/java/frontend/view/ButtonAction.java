package frontend.view;

import backend.model.builder.item.*;
import frontend.model.component.ComponentFactory;

import javax.swing.*;
import java.util.Arrays;

public final class ButtonAction {

    private int index;
    private Item item;
    private final JButton[] buttonActions;

    public ButtonAction() {
        int MAX_BUTTON = 6;
        this.buttonActions = new JButton[MAX_BUTTON];
        this.index = 0;
    }

    public void create(String name) {
        this.buttonActions[index] = ComponentFactory.getJButton(name);
        update();
    }

    private void update() {
        this.index++;
    }

    public void invisible() {
        Arrays.stream(buttonActions)
                .forEach(jButton -> jButton.setVisible(false));
    }

    private void invisibleCancelAndConfirm() {
        Arrays.stream(buttonActions).skip(4)
                .forEach(jButton -> jButton.setVisible(false));
    }

    public void visibleCancelAndConfirm(String command) {
        this.buttonActions[4].setActionCommand(command);
        this.buttonActions[4].setVisible(true);
        if (!command.equals("combinar")) {
            this.buttonActions[5].setActionCommand(command);
            this.buttonActions[5].setVisible(true);
        }
    }

    public void visibleConfirmCombine(String command) {
        this.buttonActions[5].setActionCommand(command);
        this.buttonActions[5].setVisible(true);
    }

    // TODO resolver de outra forma o instanceof
    private void validEnable() {
        boolean remove = false;
        Arrays.stream(buttonActions).limit(4)
                .forEach(jButton -> {
                    jButton.setVisible(true);
                    jButton.setEnabled(false);
                    jButton.setBackground(Colors.GREY);
                });
        if (item instanceof ItemUsable) {
            this.buttonActions[0].setBackground(Colors.BLUE);
            this.buttonActions[0].setEnabled(true);
        }
        if (item instanceof ItemEquipable) {
            this.buttonActions[1].setBackground(Colors.BLUE);
            this.buttonActions[1].setEnabled(true);
            remove = ((ItemEquipable) item).isEquipped();
            setTextEquipable(remove);
        }
        if (item instanceof ItemCombinable) {
            this.buttonActions[2].setBackground(Colors.BLUE);
            this.buttonActions[2].setEnabled(true);
        }
        if (!(item instanceof ItemMission) && !remove) {
            this.buttonActions[3].setBackground(Colors.BLUE);
            this.buttonActions[3].setEnabled(true);
        }
    }

    private void setTextEquipable(boolean isEquipped) {
        this.buttonActions[1].setText(isEquipped ? "DESEQUIPAR" : "EQUIPAR");
    }

    public JButton[] getButtonActions() {
        return this.buttonActions;
    }

    public Item getUseItem() {
        return this.item;
    }

    public void setUseItem(Item item) {
        invisibleCancelAndConfirm();
        this.item = item;
        validEnable();
    }
}
