package frontend.service;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.model.EventAction;
import backend.service.model.Player;
import frontend.mapper.CombinationItemMapper;
import frontend.mapper.DropItemMapper;
import frontend.mapper.EquipItemMapper;
import frontend.mapper.UseItemMapper;
import frontend.request.CombinationItemRequest;
import frontend.request.DropItemRequest;
import frontend.request.EquipItemRequest;
import frontend.request.UseItemRequest;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class InterfaceInventory {

    private final InterfaceGame interfaceGame;
    private final Player player;
    private JLabel labelSideEast;
    private PanelInventory panelInventory;
    private List<String> items;
    private ButtonItem buttonItem;
    private LabelInformation labelInformation;
    private ButtonAction buttonAction;
    private String iconMap;

    public InterfaceInventory(InterfaceGame interfaceGame) {
        this.interfaceGame = interfaceGame;
        this.player = Player.getInstance();
    }

    public void open(IInventoryResponse inventory) {
        this.iconMap = this.interfaceGame.getMapGameJLabel().getIcon().toString();
        this.player.getInventory().setOpenInventory();
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
        setInfoItens();
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

    private void setItemEquip(IItemDTO item) {
        this.buttonItem.create(item);
        this.buttonItem.getLast().addActionListener(e -> actionButtonItem(item));
        this.labelSideEast.add(this.buttonItem.getLast());
    }

    private void setInfoItens() {
        String capicadade = String.format("Capacidade do inventario %d/%d", this.player.getInventory().getCapacity(), this.player.getInventory().getMaxCapacity());
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

    public void quit() {
        this.player.getInventory().setOpenInventory();
        this.interfaceGame.getFrame().getContentPane().remove(this.panelInventory.getPanel());
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(iconMap));
        this.interfaceGame.getFrame().repaint();
        this.interfaceGame.getFrame().requestFocus();
    }

    private void eventActionRemove(String name) {
        var dropItemReq = new DropItemRequest("Remover", name);
        var dropItemRes = new EventAction().run(dropItemReq);
        var dropItem = new DropItemMapper().apply(dropItemRes);

        if (dropItem.message().sucess()) {
            this.interfaceGame.setItemJLabel(dropItem.item(), dropItem.indexItem());
            this.labelInformation.updateTextCapacity(dropItem.capacity(), dropItem.maxCapacity());
            for (int i = 0; i < this.labelSideEast.getComponents().length; i++) {
                if (Objects.nonNull(this.labelSideEast.getComponents()[i].getName()) &&
                        this.labelSideEast.getComponents()[i].getName().equalsIgnoreCase(dropItem.item().name())) {
                    this.labelSideEast.remove(i);
                    break;
                }
            }
            this.buttonItem.removeButtonItem(dropItem.item());
        }
        this.interfaceGame.playEffects(dropItem.message().effect(), null);
    }

    private void eventActionUse(String name) {
        var useItemReq = new UseItemRequest("Usar", name);
        var useItemRes = new EventAction().run(useItemReq);
        var useItem = new UseItemMapper().apply(useItemRes);

        if (useItem.message().sucess()) {
            iconMap = useItem.iconMap();
            this.labelInformation.updateTextCapacity(useItem.capacity(), useItem.maxCapacity());
            for (int i = 0; i < this.labelSideEast.getComponents().length; i++) {
                if (Objects.nonNull(this.labelSideEast.getComponents()[i].getName()) &&
                        this.labelSideEast.getComponents()[i].getName().equalsIgnoreCase(useItem.item().name())) {
                    this.labelSideEast.remove(i);
                    break;
                }
            }
            this.buttonItem.removeButtonItem(useItem.item());
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
            for (int i = 0; i < this.labelSideEast.getComponents().length; i++) {
                if (Objects.nonNull(this.labelSideEast.getComponents()[i].getName()) &&
                        this.labelSideEast.getComponents()[i].getName().equalsIgnoreCase(equipItem.item().name())) {
                    this.labelSideEast.remove(i);
                    break;
                }
            }
            this.buttonItem.removeButtonItem(equipItem.item());
            this.setItemEquip(equipItem.item());
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
            setActionCancel();
            iconMap = combinationItem.iconMap();
            this.labelInformation.updateTextCapacity(combinationItem.capacity(), combinationItem.maxCapacity());
            for (int i = 0; i < this.labelSideEast.getComponents().length; i++) {
                if (this.labelSideEast.getComponents()[i] instanceof JButton && Objects.nonNull(this.labelSideEast.getComponents()[i].getName())) {
                    this.labelSideEast.remove(i);
                }
            }
            this.buttonItem = new ButtonItem();
            setItens(combinationItem.itens());
            this.interfaceGame.playEffects("Combinar", combinationItem.message().effect());
        } else {
            this.interfaceGame.playEffects(combinationItem.message().effect(), null);
        }
    }
}
/*
 * Será que o front tem que saber se deve deletar? atualizar o mapa?
 * Será que nao seria mais facil pra ele apenas atualizar os componentes do inventario?
 * Acredito que tenha uma quebra de dependencia, por ter que saber o que fazer em caso de remover, equipar, usar e combinar
 * */
