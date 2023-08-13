package frontend.service;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.ISpecialization;
import frontend.model.component.ComponentFactory;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class ButtonAction {

    private int index;
    private IItemDTO item;
    private final JButton[] buttonActions;
    private final Map<Integer, List<ISpecialization>> specialization;

    public ButtonAction(Map<Integer, List<ISpecialization>> specialization) {
        this.specialization = specialization;
        this.buttonActions = new JButton[6];
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

    private void validEnable() {
        Arrays.stream(buttonActions).limit(4)
                .forEach(jButton -> {
                    jButton.setVisible(true);
                    jButton.setEnabled(false);
                    jButton.setBackground(Colors.GREY);
                });
        var listActionType = this.specialization.get(this.item.id());

        for (int i = 0; i < 4; i++) {
            this.buttonActions[i].setText(listActionType.get(i).text());
            if (listActionType.get(i).enabled()) {
                this.buttonActions[i].setBackground(Colors.BLUE);
                this.buttonActions[i].setEnabled(true);
            }
        }
    }


    public JButton[] getButtonActions() {
        return this.buttonActions;
    }

    public IItemDTO getUseItem() {
        return this.item;
    }

    public void setUseItem(IItemDTO item) {
        invisibleCancelAndConfirm();
        this.item = item;
        validEnable();
    }
}
