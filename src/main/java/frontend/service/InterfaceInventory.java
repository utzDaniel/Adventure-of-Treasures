package frontend.service;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import frontend.enums.ComponentsProperties;
import frontend.enums.NameButtonAction;
import frontend.event.Keyboard;
import frontend.mapper.ActionMapper;
import frontend.mapper.InventoryMapper;
import frontend.mapper.MessageMapper;

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
        this.buttonAction = new ButtonAction(inventory.specialization());
        setSettings(inventory);
    }

    private void setSettings(IInventoryResponse inventory) {
        this.panelInventory.getButton().addActionListener(e -> quit());
        setItens(inventory);
        setInfoItens(inventory.information());
        setButtonsActions();
        this.interfaceGame.getFrame().add(this.panelInventory.getPanel(), -1);
        this.panelInventory.getPanel().requestFocus();
        this.interfaceGame.getFrame().setVisible(true);
    }

    private void setItens(IInventoryResponse inventory) {
        inventory.items().forEach(item -> {
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

    private void setInfoItens(List<String> information) {
        //TODO LabelInfoItens.CAPICIDADE deixa sempre aparecer e os depois so quando selecionar
        //TODO LabelInfoItens.CAPICIDADE ficar junto com o itens porque estão relacionado

        information.forEach(v -> this.labelInformation.create(v));
        Arrays.stream(this.labelInformation.getInfoLabel())
                .forEach(jLabel -> this.labelSideEast.add(jLabel));
    }

    private void updateInfoItens(List<String> information) {
        for (JLabel jLabel : this.labelInformation.getInfoLabel()) {
            this.labelSideEast.remove(jLabel);
        }
        this.labelInformation = new LabelInformation();
        setInfoItens(information);
    }

    private void setButtonsActions() {
        Arrays.stream(NameButtonAction.values()).forEach(b -> buttonAction.create(b.getName()));
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
            addListItem(String.valueOf(item.id()));
            this.buttonItem.enableIButtonItensNotCombinable();
            this.buttonItem.selectButtonItem(item);
            if (this.items.size() > 1) {
                this.buttonAction.visibleConfirmCombine(command);
            }
        }
    }

    private void addListItem(String idItem) {
        var addItem = this.items.stream()
                .anyMatch(item1 -> item1.equals(idItem));
        if (!addItem) {
            this.items.add(idItem);
        }
    }

    private void setActionConfirm(String command) {
        IItemDTO item = this.buttonAction.getUseItem();
        switch (command) {
            case "remover" -> eventActionRemove(String.valueOf(item.id()));
            case "equipar" -> eventActionEquip(String.valueOf(item.id()));
            case "usar" -> eventActionUse(String.valueOf(item.id()));
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

    private void eventActionRemove(String idItem) {

        var inventoryRes = this.keyboard.executa(String.format("/inventory/drop?arg0=%s", idItem));
        var message = new MessageMapper().apply(inventoryRes);
        var inventory = new InventoryMapper().apply(inventoryRes);

        if (message.success()) {
            updateAllItensComponents(inventory);
        }
        this.interfaceGame.playEffects(message.effect(), null);
    }

    private void eventActionUse(String idItem) {

        var inventoryRes = this.keyboard.executa(String.format("/inventory/use?arg0=%s", idItem));
        var message = new MessageMapper().apply(inventoryRes);
        var inventory = new InventoryMapper().apply(inventoryRes);

        if (message.success()) {
            updateAllItensComponents(inventory);
            this.interfaceGame.playEffects("Usar", message.effect());
        } else {
            this.interfaceGame.playEffects(message.effect(), null);
        }
    }

    private void eventActionEquip(String idItem) {

        var inventoryRes = this.keyboard.executa(String.format("/inventory/equip?arg0=%s", idItem));
        var message = new MessageMapper().apply(inventoryRes);
        var inventory = new InventoryMapper().apply(inventoryRes);

        if (message.success()) {
            updateAllItensComponents(inventory);
            this.interfaceGame.playEffects("Equipar", message.effect());
        } else {
            this.interfaceGame.playEffects(message.effect(), null);
        }
    }

    private void eventActionCombination(List<String> items) {

        var limit = items.size() - 1;
        var list = new StringBuilder();
        items.stream().limit(limit).forEach(n -> list.append(n).append(","));
        list.append(items.get(limit));

        var inventoryRes = this.keyboard.executa(String.format("/inventory/combination?arg0=%s", list));
        var message = new MessageMapper().apply(inventoryRes);
        var inventory = new InventoryMapper().apply(inventoryRes);

        if (message.success()) {
            updateAllItensComponents(inventory);
            this.interfaceGame.playEffects("Combinar", message.effect());
        } else {
            this.interfaceGame.playEffects(message.effect(), null);
        }
    }

    public void quit() {

        var actionRes = this.keyboard.executa("/action/refresh");
        var message = new MessageMapper().apply(actionRes);
        var action = new ActionMapper().apply(actionRes);
        var index = 1;

        if (message.success()) {
            this.interfaceGame.getFrame().getContentPane().remove(this.panelInventory.getPanel());

            action.components().forEach(info -> {
                if (info.name().equals(ComponentsProperties.MAP.name())) {
                    this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(info.image()));
                }
            });
            var lisItem = action.components().stream()
                    .filter(v -> v.name().equals(ComponentsProperties.ITEM.name()))
                    .toList();

            if(!lisItem.isEmpty()) {
                this.interfaceGame.clearJLabelItens();
                this.interfaceGame.setItensJLabel(lisItem, index);
                this.interfaceGame.getMapGameJLabel().repaint();
            }

            var decorations = action.components().stream()
                    .filter(v -> v.name().equals(ComponentsProperties.DECORATION.name()))
                    .toList();

            if (!decorations.isEmpty()) {
                this.interfaceGame.clearJLabelDecoration();
                this.interfaceGame.setDecorationJLabel(decorations, index);
                this.interfaceGame.getMapGameJLabel().repaint();
            }

            this.interfaceGame.getFrame().repaint();
            this.interfaceGame.getFrame().requestFocus();
        }
    }

    private void updateAllItensComponents(IInventoryResponse inventory) {
        this.labelInformation.setText(inventory.information());
        this.buttonAction.setSpecialization(inventory.specialization());
        setActionCancel();
        removeAllItensComponents();
        this.buttonItem = new ButtonItem();
        setItens(inventory.items());
    }

    private void removeAllItensComponents() {
        for (Component component : this.labelSideEast.getComponents()) {
            if (component instanceof JButton && Objects.nonNull(component.getName())) {
                this.labelSideEast.remove(component);
            }
        }
    }
}
