package view;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class InterfaceInventory {

    private final InterfaceGame interfaceGame;
    private final JLabel labelSideEast;
    private final JLabel[] infoLabel;
    private final JButton[] buttonActions;
    private final List<Item> items;
    private final Player player;
    private final SoundEffects soundEffects;
    private final ButtonItem buttonItem;

    public InterfaceInventory(InterfaceGame interfaceGame, Player player, SoundEffects soundEffects) {
        this.interfaceGame = interfaceGame;
        this.player = player;
        this.player.getInventory().setOpenInventory();
        this.soundEffects = soundEffects;
        labelSideEast = new JLabel();
        infoLabel = new JLabel[4];
        buttonActions = new JButton[6];
        items = new ArrayList<>();
        buttonItem = new ButtonItem();
        setPanel();
    }

    private void setPanel() {
        PanelIventory panel = new PanelIventory(labelSideEast);
        JPanel jPanel = panel.create();
        panel.getButton().addActionListener(e -> quit(jPanel));
        setItens();
        setInfoItens();
        setButtonsActions();
        interfaceGame.getFrame().add(jPanel, 0);
        interfaceGame.getFrame().setVisible(true);
    }

    private void removeItens() {
        buttonItem.remove(labelSideEast);
        setItens();
    }

    private void setItens() {
        JButton button;
        for (Item item : player.getInventory().getItemVisible()) {
            button = buttonItem.create(item);
            button.addActionListener(this::setActions);
            labelSideEast.add(button);
        }
    }

    private void setInfoItens() {
        LabelInformation label = new LabelInformation();

        infoLabel[0] = label.create("Capacidade do inventario " + player.getInventory().getCapacity() + "/" + player.getInventory().getMaxCapacity());
        labelSideEast.add(infoLabel[0], 0);

        infoLabel[1] = label.create("Nome: ");
        labelSideEast.add(infoLabel[1], 0);

        infoLabel[2] = label.create("Peso: ");
        labelSideEast.add(infoLabel[2], 0);

        infoLabel[3] = label.create("Descrição: ");
        labelSideEast.add(infoLabel[3], 0);
    }

    private void setButtonsActions() {
        ButtonAction buttonAction = new ButtonAction();

        buttonActions[0] = buttonAction.create("usar");
        buttonActions[0].addActionListener(this::setConfirm);
        labelSideEast.add(buttonActions[0]);

        buttonActions[1] = buttonAction.create("equipar");
        buttonActions[1].addActionListener(this::setConfirm);
        labelSideEast.add(buttonActions[1]);

        buttonActions[2] = buttonAction.create("combinar");
        buttonActions[2].addActionListener(this::setConfirm);
        labelSideEast.add(buttonActions[2]);

        buttonActions[3] = buttonAction.create("remover");
        buttonActions[3].addActionListener(this::setConfirm);
        labelSideEast.add(buttonActions[3]);

        buttonActions[4] = buttonAction.create("cancelar");
        buttonActions[4].addActionListener(e -> setActionCancel());
        labelSideEast.add(buttonActions[4]);

        buttonActions[5] = buttonAction.create("confirmar");
        buttonActions[5].addActionListener(e -> setActionConfirm());
        labelSideEast.add(buttonActions[5]);

    }

    private void setActions(ActionEvent e) {
        buttonActions[4].setVisible(false);
        buttonActions[5].setVisible(false);
        Item item = player.getInventory().getItem(e.getActionCommand());
        if (!(item instanceof ItemCombinable)) {
            this.items.clear();
        } else if (this.items.size() == 1 && !(this.items.get(0) instanceof ItemCombinable)) {
            this.items.remove(0);
        }
        boolean addItem = true;
        for (Item itens : this.items) {
            if (itens.getName().equals(item.getName())) {
                addItem = false;
                break;
            }
        }
        if (addItem) {
            this.items.add(item);
        }
        if (item != null) {
            infoLabel[1].setText("Nome: " + item.getName());
            infoLabel[2].setText("Peso: " + item.getWeight());
            infoLabel[3].setText("Descrição: " + item.getDescription());
            setValidButton(item);
        }
    }

    private void setValidButton(Item item) {
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


    private void setConfirm(ActionEvent e) {
        buttonActions[4].setActionCommand(e.getActionCommand());
        buttonActions[4].setVisible(true);
        if (e.getActionCommand().equals("combinar")) {
            for (JButton jButton : buttonItem.getButtonItens()) {
                if (items.size() == 1) {
                    if (player.getInventory().getItem(jButton.getActionCommand()) instanceof ItemCombinable) {
                        if (items.get(0).getName().equals(jButton.getActionCommand())) {
                            jButton.setBackground(Colors.GREEN);
                        }
                    } else {
                        jButton.setEnabled(false);
                        jButton.setBackground(Colors.GREY);
                    }
                } else {
                    if (items.get(items.size() - 1).getName().equals(jButton.getActionCommand())) {
                        jButton.setBackground(Colors.GREEN);
                        buttonActions[5].setActionCommand(e.getActionCommand());
                        buttonActions[5].setVisible(true);
                        break;
                    }
                }
            }
        } else {
            buttonActions[5].setActionCommand(e.getActionCommand());
            buttonActions[5].setVisible(true);
        }
    }

    private void setActionConfirm() {
        boolean success = false;
        switch (buttonActions[5].getActionCommand()) {
            case "remover" -> success = player.dropItem(items.get(0));
            case "usar" -> {
                success = ((ItemUsable) items.get(0)).use(items.get(0), player);
                updateItensMapGame();
            }
            case "equipar" -> {
                if (((ItemEquipable) items.get(0)).isEquipped()) {
                    success = ((ItemEquipable) items.get(0)).unequip(items.get(0), player);
                } else {
                    success = ((ItemEquipable) items.get(0)).equip(items.get(0), player);
                }
            }
            case "combinar" -> success = ((ItemCombinable) items.get(0)).combination(items, player);
        }
        if (success) {
            if (buttonActions[5].getActionCommand().equals("remover")) {
                updateItensMapGame();
                soundEffects.play(buttonActions[5].getActionCommand());
            } else {
                soundEffects.play(buttonActions[5].getActionCommand(), items.get(0).getName());
            }
        } else {
            soundEffects.play("erro");
        }
        setActionCancel();
    }

    private void setActionCancel() {
        for (JButton buttonAction : buttonActions) {
            buttonAction.setVisible(false);
        }
        infoLabel[1].setText("Nome: ");
        infoLabel[2].setText("Peso: ");
        infoLabel[3].setText("Descrição: ");
        removeItens();
        infoLabel[0].setText("Capacidade do inventario " + player.getInventory().getCapacity() + "/" + player.getInventory().getMaxCapacity());
        items.clear();
        interfaceGame.getFrame().repaint();
    }

    private void updateItensMapGame() {
        interfaceGame.clearJLabelItens();
        for (Item itens : player.getCurrentMap().getItemVisible()) {
            interfaceGame.setItensJLabel(itens, 2);
        }
        interfaceGame.getMapGameJLabel().repaint();
        interfaceGame.getMapGameJLabel().setIcon(player.getCurrentMap().getImagemIcon());
    }

    private void quit(JPanel panel) {
        interfaceGame.getFrame().remove(panel);
        interfaceGame.getFrame().repaint();
        player.getInventory().setOpenInventory();
        this.interfaceGame.getFrame().requestFocus();
    }
}


