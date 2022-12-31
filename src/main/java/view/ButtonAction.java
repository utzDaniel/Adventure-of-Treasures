package view;

import exception.ButtonException;
import model.*;

import javax.swing.*;
import java.awt.*;

public class ButtonAction {

    private int index;
    private int positionX;
    private Item item;
    private final JButton[] buttonActions;

    public ButtonAction() {
        positionX = 12;
        buttonActions = new JButton[6];
        this.index = 0;
    }

    public void create(String name) {
        setButtonActions(name);
        style(name);
        update();
    }

    private void setButtonActions(String name) {
        buttonActions[index] = new JButton(name.toUpperCase());
        buttonActions[index].setActionCommand(name);
        buttonActions[index].setAlignmentY(0.0f);
        buttonActions[index].setFont(new Font("Arial", Font.PLAIN, 10));
        buttonActions[index].setMargin(new Insets(0, 0, 0, 0));
        buttonActions[index].setForeground(Colors.WHITE);
        buttonActions[index].setVisible(false);
    }

    private void style(String name) {
        switch (name) {
            case "usar" -> action();
            case "equipar" -> action();
            case "combinar" -> action();
            case "remover" -> action();
            case "cancelar" -> actionCancel();
            case "confirmar" -> actionConfirm();
            default -> throw new ButtonException("Nome do botão não encontrado");
        }
    }

    private void action() {
        buttonActions[index].setBackground(Colors.BLUE);
        buttonActions[index].setBounds(positionX, 320, 75, 30);
    }

    private void actionCancel() {
        buttonActions[index].setBackground(Colors.RED);
        buttonActions[index].setBounds(30, 360, 130, 30);
    }

    private void actionConfirm() {
        buttonActions[index].setBackground(Colors.GREEN);
        buttonActions[index].setBounds(180, 360, 130, 30);
    }

    private void update() {
        index++;
        positionX += 80;
    }

    public void invisible() {
        for (JButton buttonAction : buttonActions) {
            buttonAction.setVisible(false);
        }
    }

    private void invisibleCancelAndConfirm() {
        buttonActions[4].setVisible(false);
        buttonActions[5].setVisible(false);
    }

    public void visibleCancelAndConfirm(String command) {
        buttonActions[4].setActionCommand(command);
        buttonActions[4].setVisible(true);
        if (!command.equals("combinar")) {
            buttonActions[5].setActionCommand(command);
            buttonActions[5].setVisible(true);
        }
    }

    public void visibleConfirmCombine (String command) {
        buttonActions[5].setActionCommand(command);
        buttonActions[5].setVisible(true);
    }

    private void validEnable() {
        boolean remove = false;
        for (int i = 0; i < buttonActions.length - 2; i++) {
            buttonActions[i].setVisible(true);
            buttonActions[i].setEnabled(false);
            buttonActions[i].setBackground(Colors.GREY);
        }
        if (item instanceof ItemUsable) {
            buttonActions[0].setBackground(Colors.BLUE);
            buttonActions[0].setEnabled(true);
        }
        if (item instanceof ItemEquipable) {
            buttonActions[1].setBackground(Colors.BLUE);
            buttonActions[1].setEnabled(true);
            remove = ((ItemEquipable) item).isEquipped();
            setTextEquipable(remove);
        }
        if (item instanceof ItemCombinable) {
            buttonActions[2].setBackground(Colors.BLUE);
            buttonActions[2].setEnabled(true);
        }
        if (!(item instanceof ItemNotRemove) && !remove) {
            buttonActions[3].setBackground(Colors.BLUE);
            buttonActions[3].setEnabled(true);
        }
    }

    private void setTextEquipable(boolean isEquipped){
        buttonActions[1].setText(isEquipped ? "DESEQUIPAR" : "EQUIPAR");
    }

    public JButton[] getButtonActions() {
        return buttonActions;
    }

    public Item getUseItem() {
        return item;
    }

    public void setUseItem(Item item) {
        invisibleCancelAndConfirm();
        this.item = item;
        validEnable();
    }
}
