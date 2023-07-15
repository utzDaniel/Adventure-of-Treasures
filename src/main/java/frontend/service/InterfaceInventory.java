package frontend.service;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import frontend.event.Keyboard;
import frontend.mapper.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class InterfaceInventory {

    private final InterfaceGame interfaceGame;
    private JLabel labelSideEast;
    private PanelInventory panelInventory;
    private List<String> items;
    private ButtonItem buttonItem;
    private LabelInformation labelInformation;
    private ButtonAction buttonAction;
    private final Keyboard keyboard;

    public InterfaceInventory(InterfaceGame interfaceGame, Keyboard keyboard) {
        this.interfaceGame = interfaceGame;
        this.keyboard = keyboard;
    }

    public void open(IInventoryResponse inventory) {
        this.panelInventory = new PanelInventory();
        this.panelInventory.create();
        this.labelSideEast = this.panelInventory.getLabel();
        this.items = new ArrayList<>();
        this.buttonItem = new ButtonItem();
        this.labelInformation = new LabelInformation();
        this.buttonAction = new ButtonAction();
        setSettings(inventory);
    }

    private void setSettings(IInventoryResponse inventory) {
        this.panelInventory.getButton().addActionListener(e -> quit());
        setItens(inventory);
        setInfoItens(inventory.capacity(), inventory.maxCapacity());
        setButtonsActions();
        this.interfaceGame.getFrame().add(this.panelInventory.getPanel(), -1);
        this.panelInventory.getPanel().requestFocus();
        this.interfaceGame.getFrame().setVisible(true);
    }

    private void setItens(IInventoryResponse inventory) {
        inventory.itens().forEach(item -> {
            this.buttonItem.create(item);
            this.buttonItem.getLast().addActionListener(e -> actionButtonItem(item));
            this.labelSideEast.add(this.buttonItem.getLast());
        });
    }

    private void setItens(List<IItemDTO> itens) {
        itens.forEach(item -> {
            this.buttonItem.create(item);
            this.buttonItem.getLast().addActionListener(e -> actionButtonItem(item));
            this.labelSideEast.add(this.buttonItem.getLast());
        });
    }

    private void setInfoItens(int capacity, int maxCapacity) {
        String capicadade = String.format("Capacidade do inventario %d/%d", capacity, maxCapacity);
        List.of(capicadade, "Nome", "Peso", "Descrição").forEach(this.labelInformation::create);
        Arrays.stream(this.labelInformation.getInfoLabel())
                .forEach(jLabel -> this.labelSideEast.add(jLabel));
    }

    private void setButtonsActions() {
        List.of("usar", "equipar", "combinar", "remover", "cancelar", "confirmar").forEach(buttonAction::create);
        JButton[] buttons = this.buttonAction.getButtonActions();
        Arrays.stream(buttons).limit(4).forEach(jButton -> {
            jButton.addActionListener(e -> setConfirm(e.getActionCommand()));
            this.labelSideEast.add(jButton);
        });
        buttons[4].addActionListener(e -> setActionCancel());
        this.labelSideEast.add(buttons[4]);
        buttons[5].addActionListener(e -> setActionConfirm(e.getActionCommand()));
        this.labelSideEast.add(buttons[5]);
    }

    private void actionButtonItem(IItemDTO item) {
        this.buttonAction.setUseItem(item);
        this.labelInformation.updateText(item);
    }

    private void setConfirm(String command) {
        this.buttonAction.visibleCancelAndConfirm(command);
        if (command.equals("combinar")) {
            IItemDTO item = this.buttonAction.getUseItem();
            addListItem(item.name());
            this.buttonItem.enableIButtonItensNotCombinable();
            this.buttonItem.selectButtonItem(item);
            if (this.items.size() > 1) {
                this.buttonAction.visibleConfirmCombine(command);
            }
        }
    }

    private void addListItem(String name) {
        var addItem = this.items.stream()
                .anyMatch(item1 -> item1.equals(name));
        if (!addItem) {
            this.items.add(name);
        }
    }

    private void setActionConfirm(String command) {
        IItemDTO item = this.buttonAction.getUseItem();
        switch (command) {
            case "remover" -> eventActionRemove(item.name());
            case "equipar" -> eventActionEquip(item.name());
            case "usar" -> eventActionUse(item.name());
            case "combinar" -> eventActionCombination(this.items);
            default -> System.err.println("comando invalido!");
        }
        setActionCancel();
    }

    private void setActionCancel() {
        this.buttonAction.invisible();
        this.labelInformation.resetText();
        this.buttonItem.defaultJButtonSelected();
        this.items.clear();//Limpar itens combinaveis
        this.labelSideEast.repaint();//Atualizar os itens combinaveis
    }

    private void eventActionRemove(String name) {

        var inventoryRes = this.keyboard.executa(String.format("/action/drop?arg0=%s", name));

        var inventory = new InventoryMapper().apply(inventoryRes);

        if (inventory.message().sucess()) {
            this.labelInformation.updateTextCapacity(inventory.capacity(), inventory.maxCapacity());
            updateAllItensComponents(inventory.itens());
        }
        this.interfaceGame.playEffects(inventory.message().effect(), null);
    }

    private void eventActionUse(String name) {

        var inventoryRes = this.keyboard.executa(String.format("/action/use?arg0=%s", name));
        var inventory = new InventoryMapper().apply(inventoryRes);

        if (inventory.message().sucess()) {
            this.labelInformation.updateTextCapacity(inventory.capacity(), inventory.maxCapacity());
            updateAllItensComponents(inventory.itens());
            this.interfaceGame.playEffects("Usar", inventory.message().effect());
        } else {
            this.interfaceGame.playEffects(inventory.message().effect(), null);
        }
    }

    private void eventActionEquip(String name) {

        var inventoryRes = this.keyboard.executa(String.format("/action/equip?arg0=%s", name));
        var inventory = new InventoryMapper().apply(inventoryRes);

        if (inventory.message().sucess()) {
            this.labelInformation.updateTextCapacity(inventory.capacity(), inventory.maxCapacity());
            updateAllItensComponents(inventory.itens());
            this.interfaceGame.playEffects("Equipar", inventory.message().effect());
        } else {
            this.interfaceGame.playEffects(inventory.message().effect(), null);
        }
    }

    private void eventActionCombination(List<String> items) {

        var limit = items.size() - 1;
        var list = new StringBuilder();
        items.stream().limit(limit).forEach(n -> list.append(n).append(","));
        list.append(items.get(limit));

        var inventoryRes = this.keyboard.executa(String.format("/action/combination?arg0=%s", list));
        var inventory = new InventoryMapper().apply(inventoryRes);

        if (inventory.message().sucess()) {
            this.labelInformation.updateTextCapacity(inventory.capacity(), inventory.maxCapacity());
            updateAllItensComponents(inventory.itens());
            this.interfaceGame.playEffects("Combinar", inventory.message().effect());
        } else {
            this.interfaceGame.playEffects(inventory.message().effect(), null);
        }
    }

    public void quit() {

        var inventoryQuitRes = this.keyboard.executa("/action/inventoryQuit");
        var inventoryQuit = new InventoryQuitMapper().apply(inventoryQuitRes);

        if (inventoryQuit.message().sucess()) {
            this.interfaceGame.getFrame().getContentPane().remove(this.panelInventory.getPanel());
            this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(inventoryQuit.iconMap()));
            if (Objects.nonNull(inventoryQuit.itens())) {
                this.interfaceGame.clearJLabelItens();
                this.interfaceGame.setItensJLabel(inventoryQuit.itens(), inventoryQuit.indexItens());
                this.interfaceGame.getMapGameJLabel().repaint();
            }
            this.interfaceGame.getFrame().repaint();
            this.interfaceGame.getFrame().requestFocus();
        }
    }

    private void updateAllItensComponents(List<IItemDTO> itens) {
        setActionCancel();
        removeAllItensComponents();
        this.buttonItem = new ButtonItem();
        setItens(itens);
    }

    private void removeAllItensComponents() {
        for (Component component : this.labelSideEast.getComponents()) {
            if (component instanceof JButton && Objects.nonNull(component.getName())) {
                this.labelSideEast.remove(component);
            }
        }
    }
}
