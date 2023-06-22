package frontend.event;

import backend.controller.interfaces.IOpenResponse;
import backend.controller.interfaces.ITakeResponse;
import backend.controller.model.EventAction;
import backend.service.enums.Direction;
import frontend.mapper.InventoryMapper;
import frontend.mapper.MoveMapper;
import frontend.mapper.OpenMapper;
import frontend.mapper.TakeMapper;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.request.InventoryRequest;
import frontend.request.MoveRequest;
import frontend.request.OpenRequest;
import frontend.request.TakeRequest;
import frontend.service.InterfaceGame;
import frontend.service.InterfaceInventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Keyboard {

    private final Song song;
    private final InterfaceGame interfaceGame;
    private final SoundEffects soundEffects;
    private final InterfaceInventory interfaceInventory;


    public Keyboard(InterfaceGame interfaceGame, Song song, SoundEffects soundEffects) {
        this.song = song;
        this.interfaceGame = interfaceGame;
        this.soundEffects = soundEffects;
        interfaceInventory = new InterfaceInventory(interfaceGame);
    }

    public void run() {
        interfaceGame.getFrame().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent event) {

                var keyCode = event.getKeyCode();

                if (Direction.containsKeyCode(keyCode)) {

                    var width = interfaceGame.getMapGameJLabel().getWidth();
                    var height = interfaceGame.getMapGameJLabel().getHeight();
                    var direction = Direction.getLabel(keyCode);
                    var moveReq = new MoveRequest("Move", direction, width, height);

                    var moveRes = new EventAction().run(moveReq);

                    var move = new MoveMapper().apply(moveRes);

                    interfaceGame.updateComponentsMove(move);


                    //ENTRA NA PORTA
                } else if (keyCode == 97) {

                    var openReq = new OpenRequest("Open");
                    var openRes = new EventAction().run(openReq);

                    var open = new OpenMapper().apply(openRes);

                    if (Objects.isNull(open.songMap())) updateItensMapGame(open);

                    else if (open.songMap().equals("finish")) finish("finish");

                    else if (open.songMap().equals("erro"))
                        soundEffects.play(commandEffects("erro"));
                    else
                        updateItensMapGame(open);

                    //PEGAR ITEM
                } else if (keyCode == 98) {

                    var takeReq = new TakeRequest("Take");
                    var takeRes = new EventAction().run(takeReq);
                    var take = new TakeMapper().apply(takeRes);

                    if (Objects.nonNull(take.effect())) {
                        if (take.effect().equals("erro"))
                            soundEffects.play(commandEffects("erro"));
                        else {
                            soundEffects.play(commandEffects(take.effect()));
                            updateItensMapGame(take);
                        }
                    }

                    //INVENTARIO
                } else if (keyCode == 99) {

                    var inventoryReq = new InventoryRequest("Inventory");
                    var inventoryRes = new EventAction().run(inventoryReq);
                    var inventory = new InventoryMapper().apply(inventoryRes);

                    if (inventory.isOpen()) {
                        interfaceInventory.quit();
                    } else {
                        interfaceInventory.open(inventory);
                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {


            }
        });
    }

    private void updateItensMapGame(IOpenResponse open) {
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(open.iconMap()));
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(open.itens(), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(open.coordinatePlayer().x(),
                open.coordinatePlayer().y()));
    }

    private void updateItensMapGame(ITakeResponse take) {
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(take.iconMap()));
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(take.itens(), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(take.coordinatePlayer().x(),
                take.coordinatePlayer().y()));
    }

    private void finish(String song) {
        this.song.closePlay();
        this.soundEffects.play(commandEffects(song));
        System.exit(0);
    }

    private String commandEffects(String command) {
        return String.format("src/main/resources/audio/effects/%s.wav",
                switch (command) {
                    case "pegar", "remover ", "finish" -> command;
                    default -> "erro";
                });
    }
}
