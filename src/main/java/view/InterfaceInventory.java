package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InterfaceInventory {

    private final InterfaceGame interfaceGame;
    private final Player player;
    private JLabel labelSideEast;
    private PanelIventory panelIventory;
    private List<Item> items;
    private ButtonItem buttonItem;
    private LabelInformation labelInformation;
    private ButtonAction buttonAction;

    public InterfaceInventory(InterfaceGame interfaceGame, Player player) {
        this.interfaceGame = interfaceGame;
        this.player = player;
    }

    public void open() {
        this.player.getInventory().setOpenInventory();
        labelSideEast = new JLabel();
        panelIventory = new PanelIventory(labelSideEast);
        items = new ArrayList<>();
        buttonItem = new ButtonItem();
        labelInformation = new LabelInformation();
        buttonAction = new ButtonAction();
        setSettings();
    }

    private void setSettings(){
        panelIventory.create();
        panelIventory.getButton().addActionListener(e -> quit());
        setItens();
        setInfoItens();
        setButtonsActions();
        interfaceGame.getFrame().add(panelIventory.getPanel(), 0);
        interfaceGame.getFrame().setVisible(true);
    }

    private void removeItens() {
        buttonItem.remove(labelSideEast);
        setItens();
    }

    private void setItens() {
        for (Item item : player.getInventory().getItemVisible()) {
            buttonItem.create(item);
            buttonItem.getLast().addActionListener(e ->
                    actionButtonItem(item));
            labelSideEast.add(buttonItem.getLast());
        }
    }

    private void setInfoItens() {
        labelInformation.create("Capacidade do inventario " + player.getInventory().getCapacity() + "/" + player.getInventory().getMaxCapacity());
        labelInformation.create("Nome: ");
        labelInformation.create("Peso: ");
        labelInformation.create("Descrição: ");
        for (JLabel label : labelInformation.getInfoLabel()) {
            labelSideEast.add(label, BorderLayout.NORTH, 0);
        }
    }

    private void setButtonsActions() {
        buttonAction.create("usar");
        buttonAction.create("equipar");
        buttonAction.create("combinar");
        buttonAction.create("remover");
        buttonAction.create("cancelar");
        buttonAction.create("confirmar");
        JButton[] buttons = buttonAction.getButtonActions();
        for (int i = 0; i < 4; i++) {
            buttons[i].addActionListener(e -> setConfirm(e.getActionCommand()));
            labelSideEast.add(buttons[i]);
        }
        buttons[4].addActionListener(e -> setActionCancel());
        labelSideEast.add(buttons[4]);
        buttons[5].addActionListener(e -> setActionConfirm(e.getActionCommand()));
        labelSideEast.add(buttons[5]);
    }

    private void actionButtonItem(Item item) {
        buttonAction.setUseItem(item);
        labelInformation.updateText(item);
    }

    private void setConfirm(String command) {
        buttonAction.visibleCancelAndConfirm(command);
        if (command.equals("combinar")) {
            Item item = buttonAction.getUseItem();
            addListItem(item);
            enableIButtonItens(item);
            for (JButton jButton : buttonItem.getButtonItens()) {
                if (item.getName().equals(jButton.getName())) {
                    jButton.setBackground(Colors.GREEN);
                    break;
                }
            }
            if (items.size() > 1) {
                buttonAction.visibleConfirmCombine(command);
            }
        }
    }

    private void enableIButtonItens(Item item) {
        for (JButton jButton : buttonItem.getButtonItens()) {
            if (!(item instanceof ItemCombinable)) {
                jButton.setEnabled(false);
                jButton.setBackground(Colors.GREY);
            }
        }
    }

    private void addListItem(Item item) {
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
    }

    private void setActionConfirm(String command) {
        boolean success = false;
        Item item = buttonAction.getUseItem();
        switch (command) {
            case "remover" -> success = player.dropItem(item);
            case "usar" -> {
                success = ((ItemUsable) item).use(item, player);
                updateItensMapGame();
            }
            case "equipar" -> {
                if (((ItemEquipable) item).isEquipped()) {
                    success = ((ItemEquipable) item).unequip(item, player);
                } else {
                    success = ((ItemEquipable) item).equip(item, player);
                }
            }
            case "combinar" -> success = ((ItemCombinable) item).combination(items, player);
        }
        playEffects(command, success, item.getName());
        setActionCancel();
    }

    private void playEffects (String command, boolean success, String itemName){
        String effect;
        if (success) {
            effect = command;
            if (command.equals("remover")) {
                updateItensMapGame();
                itemName = null;
            }
        } else {
            effect = "erro";
        }
        interfaceGame.playEffects(effect, itemName);
    }

    private void setActionCancel() {
        buttonAction.invisible();
        removeItens();
        labelInformation.resetText(player.getInventory().getCapacity(), player.getInventory().getMaxCapacity());
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

    public void quit() {
        this.player.getInventory().setOpenInventory();
        interfaceGame.getFrame().remove(panelIventory.getPanel());
        interfaceGame.getFrame().repaint();
        this.interfaceGame.getFrame().requestFocus();
    }
}
