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
    private PanelInventory panelInventory;
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
        panelInventory = new PanelInventory(labelSideEast);
        items = new ArrayList<>();
        buttonItem = new ButtonItem();
        labelInformation = new LabelInformation();
        buttonAction = new ButtonAction();
        setSettings();
        System.out.println(interfaceGame.getFrame().getContentPane());
    }

    private void setSettings(){
        panelInventory.create();
        panelInventory.getButton().addActionListener(e -> quit());
        setItens();
        setInfoItens();
        setButtonsActions();
        interfaceGame.getFrame().getContentPane().add(panelInventory.getPanel(), 0);
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
        String capicadade = String.format("Capacidade do inventario %d/%d",player.getInventory().getCapacity(),player.getInventory().getMaxCapacity());
        var labels = List.of(capicadade,"Nome: ","Peso: ","Descrição: ");
        labels.forEach(labelInformation::create);
        for (JLabel label : labelInformation.getInfoLabel()) {
            labelSideEast.add(label, BorderLayout.NORTH, 0);
        }
    }

    private void setButtonsActions() {
        var names = List.of("usar","equipar","combinar","remover","cancelar","confirmar");
        names.forEach(buttonAction::create);
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
            buttonItem.enableIButtonItensNotCombinable(player);
            buttonItem.selectButtonItem(item);
            if (items.size() > 1) {
                buttonAction.visibleConfirmCombine(command);
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
        boolean success;
        Item item = buttonAction.getUseItem();
        success = switch (command) {
            case "remover" -> player.dropItem(item);
            case "usar", "equipar" ->  item.action(item, player);
            case "combinar" -> item.action(items, player);
            default -> false;
        };
        if(command.equals("usar") && success) updateItensMapGame();
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
        interfaceGame.getFrame().getContentPane().remove(panelInventory.getPanel());
        interfaceGame.getFrame().repaint();
        this.interfaceGame.getFrame().requestFocus();
    }
}
