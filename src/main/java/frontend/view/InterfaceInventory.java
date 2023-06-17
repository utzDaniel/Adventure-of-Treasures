package frontend.view;

import backend.service.component.ServiceDropItem;
import backend.service.model.Player;
import backend.service.interfaces.ICombinable;
import frontend.model.component.ComponentFactory;
import frontend.model.vo.ItemVO;
import frontend.model.vo.OpenInventoryVO;
import backend.controller.interfaces.IItemDTO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class InterfaceInventory {

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

    public void open(OpenInventoryVO openInventoryVO) {
        this.player.getInventory().setOpenInventory();
        this.panelInventory = new PanelInventory();
        this.panelInventory.create();
        this.labelSideEast = this.panelInventory.getLabel();
        this.items = new ArrayList<>();
        this.buttonItem = new ButtonItem();
        this.labelInformation = new LabelInformation();
        this.buttonAction = new ButtonAction();
        setSettings(openInventoryVO);
    }

    private void setSettings(OpenInventoryVO openInventoryVO) {
        this.panelInventory.getButton().addActionListener(e -> quit());
        setItens(openInventoryVO);
        setInfoItens();
        setButtonsActions();
        this.interfaceGame.getFrame().add(this.panelInventory.getPanel(), 1);
        this.panelInventory.getPanel().requestFocus();
        this.interfaceGame.getFrame().setVisible(true);
    }

    private void setItens(OpenInventoryVO openInventoryVO) {
        openInventoryVO.getItens().forEach(item -> {
            this.buttonItem.create(item);
            this.buttonItem.getLast().addActionListener(e -> actionButtonItem(item));
            this.labelSideEast.add(this.buttonItem.getLast());
        });
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
            addListItem((ICombinable) item);
            this.buttonItem.enableIButtonItensNotCombinable();
            this.buttonItem.selectButtonItem(item);
            if (this.items.size() > 1) {
                this.buttonAction.visibleConfirmCombine(command);
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
        IItemDTO item = this.buttonAction.getUseItem();
        success = switch (command) {
            case "remover" -> new ServiceDropItem().run(player,item.name());
//            case "usar", "equipar" -> item.action();
//            case "combinar" -> item.action(this.items);
            default -> false;
        };
        if (success)
            this.labelInformation.updateTextCapacity(this.player.getInventory().getCapacity(), this.player.getInventory().getMaxCapacity());

        if (success && command.equals("remover")) {
            this.interfaceGame.getFrame().getContentPane().add(ComponentFactory.getJLabel(
                    new ItemVO(item.icon().toString(), item.coordinate(), item.name(), item.description(),
                            item.effect(), item.weight(), item.specialization(), item.isEquipped())), 1);
        }

        if (command.equals("usar") && success) {//usar pá
            this.interfaceGame.getMapGameJLabel().setIcon(this.player.getCurrentMap().getIcon());
            this.panelInventory.getPanel().requestFocus();
        }

        if (command.equals("combinar") && success) {//combinar papel e livro, na praia
            this.interfaceGame.getMapGameJLabel().setIcon(this.player.getCurrentMap().getIcon());
            this.panelInventory.getPanel().requestFocus();
        }

        playEffects(command, success, item.effect());//TODO item.getEffect() resolver depois
        setActionCancel();
    }

    private void playEffects(String command, boolean success, String itemEffect) {
        String commandEffect;
        if (success) {
            commandEffect = command;
            if (command.equals("remover")) {
                itemEffect = null;
            }
        } else {
            commandEffect = "erro";
        }
        this.interfaceGame.playEffects(commandEffect, itemEffect);
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
        this.interfaceGame.getFrame().repaint();
        this.interfaceGame.getFrame().requestFocus();
    }
}
