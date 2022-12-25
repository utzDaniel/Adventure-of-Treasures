package view;

import model.*;
import repository.CreateImageInventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class InterfaceInventory {

    private final InterfaceGame interfaceGame;
    private final JPanel panelMain;
    private final JLabel labelSideEast;
    private final JButton[][] buttonItens;
    private final JLabel[] infoLabel;
    private final JButton[] buttonActions;
    private final List<Item> items;
    private final Player player;
    private final SoundEffects soundEffects;

    private final CreateImageInventory imageInventory;

    public InterfaceInventory(InterfaceGame interfaceGame, Player player, SoundEffects soundEffects) {
        this.interfaceGame = interfaceGame;
        this.player = player;
        this.player.getInventory().setOpenInventory();
        this.soundEffects = soundEffects;
        panelMain = new JPanel();
        imageInventory = new CreateImageInventory();
        labelSideEast = new JLabel(imageInventory.selectImage("icons"));
        buttonItens = new JButton[4][6];
        infoLabel = new JLabel[4];
        buttonActions = new JButton[6];
        items = new ArrayList<>();
        settingsPanel();
    }

    private void settingsPanel() {
        panelMain.setBackground(Colors.BROWN_1);
        panelMain.setBorder(new EmptyBorder(8, 10, 10, 10));
        panelMain.setLayout(new BorderLayout(6, 6));
        setNorthInterface();
        setSouthInterface();
        setWestInterface();
        setEastInterface();
        interfaceGame.getFrame().add(panelMain, 0);
        panelMain.setBounds(50, 50, 706, 500);//816 660
        interfaceGame.getFrame().setVisible(true);
    }

    private void setNorthInterface() {
        JLabel label = new JLabel();
        label.setIcon(imageInventory.selectImage("top"));
        label.setBounds(300, 0, 100, 100);
        panelMain.add(label, BorderLayout.NORTH);
    }

    private void setSouthInterface() {
        JButton button = new JButton("SAIR");
        button.setBackground(Colors.RED);
        button.setForeground(Colors.SILVER);
        button.addActionListener(e -> quit());
        panelMain.add(button, BorderLayout.SOUTH);
    }

    private void setWestInterface() {
        JLabel label = new JLabel();
        label.setLayout(new BorderLayout(8, 8));
        label.setIcon(imageInventory.selectImage("player"));
        label.setBounds(0, 100, 100, 100);
        panelMain.add(label, BorderLayout.WEST);
    }

    private void setEastInterface() {
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout(8, 8));
        setItens();
        setInfoItens();
        setButtonsActions();
        eastPanel.add(labelSideEast, BorderLayout.NORTH);
        panelMain.add(eastPanel, BorderLayout.EAST);
    }

    private void setItens() {
        int positionY = 14;
        int positionX = 17;
        int cont = 0;
        List<Item> itens = player.getInventory().getItemVisible();
        for (int line = 0; line < buttonItens.length; line++) {
            for (int column = 0; column < buttonItens[line].length; column++) {
                if (cont < itens.size()) {
                    buttonItens[line][column] = new JButton();
                    buttonItens[line][column].setActionCommand(itens.get(cont).getName());
                    buttonItens[line][column].setIcon(itens.get(cont).getImagemIcon());
                    buttonItens[line][column].setBackground(Colors.BROWN_2);
                    buttonItens[line][column].setBounds(positionX, positionY, 37, 38);
                    buttonItens[line][column].addActionListener(this::setActions);
                    labelSideEast.add(buttonItens[line][column]);
                    positionX += 53;
                    cont++;
                } else {
                    break;
                }
            }
            if (line < 1) {
                positionY += 55;
            } else {
                positionY += 51;
            }
            positionX = 17;
        }
    }

    private void setInfoItens() {
        int height = 100;
        int somaY = 100;

    }

    private void setButtonsActions() {
        buttonActions[0] = new JButton();
        buttonActions[0].setIcon(imageInventory.selectImage("usar"));
        buttonActions[0].setBounds(15, 320, 70, 30);
        buttonActions[0].setActionCommand("usar");
        buttonActions[0].addActionListener(this::setConfirm);
        buttonActions[0].setVisible(false);
        labelSideEast.add(buttonActions[0]);

        buttonActions[1] = new JButton();
        buttonActions[1].setIcon(imageInventory.selectImage("equipar"));
        buttonActions[1].setBounds(92, 320, 70, 30);
        buttonActions[1].setActionCommand("equipar");
        buttonActions[1].addActionListener(this::setConfirm);
        buttonActions[1].setVisible(false);
        labelSideEast.add(buttonActions[1]);

        buttonActions[2] = new JButton();
        buttonActions[2].setIcon(imageInventory.selectImage("combinar"));
        buttonActions[2].setBounds(170, 320, 70, 30);
        buttonActions[2].setActionCommand("combinar");
        buttonActions[2].addActionListener(this::setConfirm);
        buttonActions[2].setVisible(false);
        labelSideEast.add(buttonActions[2]);

        buttonActions[3] = new JButton();
        buttonActions[3].setIcon(imageInventory.selectImage("remover"));
        buttonActions[3].setBounds(250, 320, 70, 30);
        buttonActions[3].setActionCommand("remover");
        buttonActions[3].addActionListener(this::setConfirm);
        buttonActions[3].setVisible(false);
        labelSideEast.add(buttonActions[3]);

        buttonActions[4] = new JButton("CANCELAR");
        buttonActions[4].setBounds(30, 360, 130, 30);
        buttonActions[4].setBackground(Colors.RED);
        buttonActions[4].setForeground(Colors.WHITE);
        buttonActions[4].addActionListener(e -> setActionCancel());
        buttonActions[4].setVisible(false);
        labelSideEast.add(buttonActions[4]);

        buttonActions[5] = new JButton("CONFIRMAR");
        buttonActions[5].setBounds(180, 360, 130, 30);
        buttonActions[5].setBackground(Colors.GREEN);
        buttonActions[5].setForeground(Colors.WHITE);
        buttonActions[5].addActionListener(e -> setActionConfirm());
        buttonActions[5].setVisible(false);
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
            for (JButton[] buttonIten : buttonItens) {
                for (JButton jButton : buttonIten) {
                    if (jButton != null) {
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
        setCapacity();
        items.clear();
        interfaceGame.getFrame().repaint();
    }

    private void removeItens() {
        for (JButton[] buttonIten : buttonItens) {
            for (JButton jButton : buttonIten) {
                if (jButton != null) {
                    labelSideEast.remove(jButton);
                } else {
                    break;
                }
            }
        }
        setItens();
    }

    private void setCapacity() {
        infoLabel[0].setText("Capacidade do inventario " + player.getInventory().getCapacity() + "/" + player.getInventory().getMaxCapacity());
    }

    private void updateItensMapGame() {
        interfaceGame.clearJLabelItens();
        for (Item itens : player.getCurrentMap().getItemVisible()) {
            interfaceGame.setItensJLabel(itens, 2);
        }
        interfaceGame.getMapGameJLabel().repaint();
        interfaceGame.getMapGameJLabel().setIcon(player.getCurrentMap().getImagemIcon());
    }

    private void quit() {
        interfaceGame.getFrame().remove(panelMain);
        interfaceGame.getFrame().repaint();
        player.getInventory().setOpenInventory();
        this.interfaceGame.getFrame().requestFocus();
    }
}


