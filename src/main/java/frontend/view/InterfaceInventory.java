package frontend.view;

import backend.model.Player;
import backend.model.interfaces.ICombinable;
import backend.model.builder.item.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterfaceInventory {

    private final InterfaceGame interfaceGame;
    private final Player player;
    private JLabel labelSideEast;
    private PanelInventory panelInventory;
    private List<ICombinable> items;
    private ButtonItem buttonItem;
    private LabelInformation labelInformation;
    private ButtonAction buttonAction;

    public InterfaceInventory(InterfaceGame interfaceGame) {
        this.interfaceGame = interfaceGame;
        this.player = Player.getInstance();
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
        player.getInventory().getItemVisible().forEach(item -> {
            buttonItem.create(item);
            buttonItem.getLast().addActionListener(e ->
                    actionButtonItem(item));
            labelSideEast.add(buttonItem.getLast());
        });
    }

    private void setInfoItens() {
        String capicadade = String.format("Capacidade do inventario %d/%d",player.getInventory().getCapacity(),player.getInventory().getMaxCapacity());
        List.of(capicadade,"Nome: ","Peso: ","Descrição: ").forEach(labelInformation::create);
        Arrays.stream(labelInformation.getInfoLabel())
                .forEach(jLabel -> labelSideEast.add(jLabel, BorderLayout.NORTH, 0));
    }

    private void setButtonsActions() {
        List.of("usar","equipar","combinar","remover","cancelar","confirmar").forEach(buttonAction::create);
        JButton[] buttons = buttonAction.getButtonActions();
        Arrays.stream(buttons).limit(4).forEach(jButton -> {
            jButton.addActionListener(e -> setConfirm(e.getActionCommand()));
            labelSideEast.add(jButton);
        });
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
            addListItem((ICombinable)item);
            buttonItem.enableIButtonItensNotCombinable();
            buttonItem.selectButtonItem(item);
            if (items.size() > 1) {
                buttonAction.visibleConfirmCombine(command);
            }
        }
    }

    private void addListItem(ICombinable item) {
        var addItem = this.items.stream()
                .anyMatch(item1 -> item1.getName().equals(item.getName()));
        if (!addItem) {
            this.items.add(item);
        }
    }

    private void setActionConfirm(String command) {
        boolean success;
        Item item = buttonAction.getUseItem();
        success = switch (command) {
            case "remover" -> player.dropItem(item);
            case "usar", "equipar" ->  item.action();
            case "combinar" -> item.action(items);
            default -> false;
        };
        if(command.equals("usar") && success) updateItensMapGame();
        playEffects(command, success, item.getEffect());//TODO item.getEffect() resolver depois
        setActionCancel();
    }

    private void playEffects (String command, boolean success, String itemEffect){
        String commandEffect;
        if (success) {
            commandEffect = command;
            if (command.equals("remover")) {
                updateItensMapGame();
                itemEffect = null;
            }
        } else {
            commandEffect = "erro";
        }
        interfaceGame.playEffects(commandEffect, itemEffect);
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
        interfaceGame.setItensJLabel(player.getCurrentMap().getItemVisible(), 2);
        interfaceGame.getMapGameJLabel().repaint();
        interfaceGame.getMapGameJLabel().setIcon(player.getCurrentMap().getIcon());
    }

    public void quit() {
        this.player.getInventory().setOpenInventory();
        interfaceGame.getFrame().getContentPane().remove(panelInventory.getPanel());
        interfaceGame.getFrame().repaint();
        this.interfaceGame.getFrame().requestFocus();
    }
}
