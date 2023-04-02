package frontend.view;

import frontend.exception.ButtonException;
import backend.model.builder.item.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ButtonAction {

    private int index;
    private int positionX;
    private Item item;
    private final JButton[] buttonActions;

    public ButtonAction() {
        this.positionX = 12;
        this.buttonActions = new JButton[6];
        this.index = 0;
    }

    public void create(String name) {
        setButtonActions(name);
        style(name);
        update();
    }

    private void setButtonActions(String name) {
        this.buttonActions[index] = new JButton(name.toUpperCase());
        this.buttonActions[index].setActionCommand(name);
        this.buttonActions[index].setAlignmentY(0.0f);
        this.buttonActions[index].setFont(new Font("Arial", Font.PLAIN, 10));
        this.buttonActions[index].setMargin(new Insets(0, 0, 0, 0));
        this.buttonActions[index].setForeground(Colors.WHITE);
        this.buttonActions[index].setVisible(false);
    }

    private void style(String name) {
        switch (name) {
            case "usar", "equipar", "combinar", "remover" -> action();
            case "cancelar" -> actionCancel();
            case "confirmar" -> actionConfirm();
            default -> throw new ButtonException("Nome do botão não encontrado");
        }
    }

    private void action() {
        this.buttonActions[index].setBackground(Colors.BLUE);
        this.buttonActions[index].setBounds(positionX, 320, 75, 30);
    }

    private void actionCancel() {
        this.buttonActions[index].setBackground(Colors.RED);
        this.buttonActions[index].setBounds(30, 360, 130, 30);
    }

    private void actionConfirm() {
        this.buttonActions[index].setBackground(Colors.GREEN);
        this.buttonActions[index].setBounds(180, 360, 130, 30);
    }

    private void update() {
        this.index++;
        this.positionX += 80;
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

    public void visibleConfirmCombine (String command) {
        this.buttonActions[5].setActionCommand(command);
        this.buttonActions[5].setVisible(true);
    }

    private void validEnable() {
        boolean remove = false;
        Arrays.stream(buttonActions).limit(4)
                .forEach(jButton ->{
                    jButton.setVisible(true);
                    jButton.setEnabled(false);
                    jButton.setBackground(Colors.GREY);});
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

    private void setTextEquipable(boolean isEquipped){
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
