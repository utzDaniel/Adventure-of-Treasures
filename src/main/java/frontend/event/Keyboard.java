package frontend.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.vo.MovePlayerVO;
import frontend.model.vo.OpenInventoryVO;
import frontend.model.vo.OpenVO;
import frontend.model.vo.TakeVO;
import frontend.util.JsonConverter;
import frontend.view.InterfaceGame;
import frontend.view.InterfaceInventory;
import rules.enums.Direction;
import rules.interfaces.ICoordinate;
import rules.model.EventAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Keyboard {

    private final Song song;
    private final InterfaceGame interfaceGame;
    private final SoundEffects soundEffects;
    private final InterfaceInventory inventory;


    public Keyboard(InterfaceGame interfaceGame, Song song, SoundEffects soundEffects) {
        this.song = song;
        this.interfaceGame = interfaceGame;
        this.soundEffects = soundEffects;
        inventory = new InterfaceInventory(interfaceGame);
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

                    try {
                        var jsonReq = JsonConverter.getJson(ICoordinate.getInstance(width, height));
                        var jsonRes = new EventAction().run(keyCode, jsonReq);
                        var movePlayerVO = JsonConverter.getObjetc(jsonRes, MovePlayerVO.class);

                        interfaceGame.updateComponentsMove(movePlayerVO);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    //ENTRA NA PORTA
                } else if (keyCode == 97) {

                    try {
                        var jsonRes = new EventAction().run(keyCode, null);
                        var openVO = JsonConverter.getObjetc(jsonRes, OpenVO.class);

                        if (Objects.isNull(openVO.getSongMap())) updateItensMapGame(openVO);

                        else if (openVO.getSongMap().equals("finish")) finish("finish");

                        else if (openVO.getSongMap().equals("erro"))
                            soundEffects.play(commandEffects("erro"));
                        else
                            updateItensMapGame(openVO);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    //PEGAR ITEM
                } else if (keyCode == 98) {

                    try {
                        var jsonRes = new EventAction().run(keyCode, null);
                        var takeVO = JsonConverter.getObjetc(jsonRes, TakeVO.class);

                        if (Objects.nonNull(takeVO.getEffects())) {
                            if (takeVO.getEffects().equals("erro"))
                                soundEffects.play(commandEffects("erro"));
                            else {
                                soundEffects.play(commandEffects(takeVO.getEffects()));
                                updateItensMapGame(takeVO);
                            }
                        }

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    //INVENTARIO
                } else if (keyCode == 99) {

                    try {
                        var jsonRes = new EventAction().run(keyCode, null);
                        var openInventoryVO = JsonConverter.getObjetc(jsonRes, OpenInventoryVO.class);

                        if (openInventoryVO.isOpen()) {
                            inventory.quit();
                        } else {
                            inventory.open();
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {


            }
        });
    }

    private void updateItensMapGame(OpenVO openVO) {
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(openVO.getIconMap()));
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(openVO.getItens(), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(openVO.getCoordinatePlayer().getX(),
                openVO.getCoordinatePlayer().getY()));
    }

    private void updateItensMapGame(TakeVO takeVO) {
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(takeVO.getIconMap()));
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(takeVO.getItens(), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(takeVO.getCoordinatePlayer().getX(),
                takeVO.getCoordinatePlayer().getY()));
    }

    private void finish(String song) {
        this.song.closePlay();
        this.soundEffects.play(commandEffects(song));
        System.exit(0);
    }

    private String commandEffects(String command) {
        return "src/main/resources/audio/effects/" +
                switch (command) {
                    case "pegar" -> "pegar.wav";
                    case "remover" -> "remover.wav";
                    case "finish" -> "finish.wav";
                    default -> "erro.wav";
                };
    }
}
