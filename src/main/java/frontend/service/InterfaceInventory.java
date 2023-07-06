package frontend.service;

import backend.controller.interfaces.IInventoryOpenResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.model.EventAction;
import frontend.mapper.*;
import frontend.request.*;

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

    public InterfaceInventory(InterfaceGame interfaceGame) {
        this.interfaceGame = interfaceGame;
    }

    public void open(IInventoryOpenResponse inventory) {
        this.panelInventory = new PanelInventory();
        this.panelInventory.create();
        this.labelSideEast = this.panelInventory.getLabel();
        this.items = new ArrayList<>();
        this.buttonItem = new ButtonItem();
        this.labelInformation = new LabelInformation();
        this.buttonAction = new ButtonAction();
        setSettings(inventory);
    }

    private void setSettings(IInventoryOpenResponse inventory) {
        this.panelInventory.getButton().addActionListener(e -> quit());
        setItens(inventory);
        setInfoItens(inventory.capacity(), inventory.maxCapacity());
        setButtonsActions();
        this.interfaceGame.getFrame().add(this.panelInventory.getPanel(), -1);
        this.panelInventory.getPanel().requestFocus();
        this.interfaceGame.getFrame().setVisible(true);
    }

    private void setItens(IInventoryOpenResponse inventory) {
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
        var dropItemReq = new DropItemRequest("Remover", name);
        var dropItemRes = new EventAction().run(dropItemReq);
        var dropItem = new DropItemMapper().apply(dropItemRes);

        if (dropItem.message().sucess()) {
            this.labelInformation.updateTextCapacity(dropItem.capacity(), dropItem.maxCapacity());
            updateAllItensComponents(dropItem.itens());
        }
        this.interfaceGame.playEffects(dropItem.message().effect(), null);
    }

    private void eventActionUse(String name) {
        var useItemReq = new UseItemRequest("Usar", name);
        var useItemRes = new EventAction().run(useItemReq);
        var useItem = new UseItemMapper().apply(useItemRes);

        if (useItem.message().sucess()) {
            this.labelInformation.updateTextCapacity(useItem.capacity(), useItem.maxCapacity());
            updateAllItensComponents(useItem.itens());
            this.interfaceGame.playEffects("Usar", useItem.message().effect());
        } else {
            this.interfaceGame.playEffects(useItem.message().effect(), null);
        }
    }

    private void eventActionEquip(String name) {
        var equipItemReq = new EquipItemRequest("Equipar", name);
        var equipItemRes = new EventAction().run(equipItemReq);
        var equipItem = new EquipItemMapper().apply(equipItemRes);

        if (equipItem.message().sucess()) {
            this.labelInformation.updateTextCapacity(equipItem.capacity(), equipItem.maxCapacity());
            updateAllItensComponents(equipItem.itens());
            this.interfaceGame.playEffects("Equipar", equipItem.message().effect());
        } else {
            this.interfaceGame.playEffects(equipItem.message().effect(), null);
        }
    }

    private void eventActionCombination(List<String> items) {
        var combinationItemReq = new CombinationItemRequest("Combinar", items);
        var combinationItemRes = new EventAction().run(combinationItemReq);
        var combinationItem = new CombinationItemMapper().apply(combinationItemRes);

        if (combinationItem.message().sucess()) {
            this.labelInformation.updateTextCapacity(combinationItem.capacity(), combinationItem.maxCapacity());
            updateAllItensComponents(combinationItem.itens());
            this.interfaceGame.playEffects("Combinar", combinationItem.message().effect());
        } else {
            this.interfaceGame.playEffects(combinationItem.message().effect(), null);
        }
    }

    public void quit() {
        var inventoryQuitReq = new InventoryQuitRequest("InventoryQuit");
        var inventoryQuitRes = new EventAction().run(inventoryQuitReq);
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
