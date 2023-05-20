package frontend.event;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;
import backend.model.dto.ItemDTO;
import backend.model.dto.MovePlayerDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.util.JsonConverter;
import frontend.view.InterfaceGame;
import frontend.view.InterfaceInventory;
import rules.enums.Direction;
import rules.exception.InventoryException;
import rules.exception.MapGameException;
import rules.interfaces.ICoordinate;
import rules.interfaces.IItemDTO;
import rules.model.EventAction;
import rules.service.NextDoor;
import rules.service.Take;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Keyboard {

    private final Song song;
    private final InterfaceGame interfaceGame;
    private final Player player;
    private final SoundEffects soundEffects;
    private final InterfaceInventory inventory;


    public Keyboard(InterfaceGame interfaceGame, Song song, SoundEffects soundEffects) {
        this.song = song;
        this.interfaceGame = interfaceGame;
        this.player = Player.getInstance();
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
                        var movePlayerDTO = JsonConverter.getObjetc(jsonRes, MovePlayerDTO.class);

                        interfaceGame.updateComponentsMove(movePlayerDTO);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                } else if (keyCode == 97) {

                    if (player.getLocation().getX() == 710 && player.getLocation().getX() == 280
                            && Objects.nonNull(player.getInventory().getItem("tesouro")))
                        finish();
                    try {
                        MapGame atual = player.getCurrentMap();
                        new NextDoor(interfaceGame).run();
                        if (atual != player.getCurrentMap())
                            updateItensMapGame();
                    } catch (MapGameException ex) {
                        //TODO porta fechada
                        soundEffects.play(commandEffects("erro"));
                    }


                } else if (keyCode == 98) {

                    try {
                        if (new Take().run()) {
                            soundEffects.play(commandEffects("pegar"));
                            updateItensMapGame();
                        }

                    } catch (InventoryException ex) {
                        //TODO inventario cheio
                        soundEffects.play(commandEffects("erro"));
                    }


                } else if (keyCode == 99) {
                    if (player.getInventory().openInventory()) {
                        inventory.quit();
                    } else {
                        inventory.open();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {


            }
        });
    }

    private void updateItensMapGame() {
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(getIItemDTO(this.player.getCurrentMap().getItemVisible()), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(player.getLocation().getX(), player.getLocation().getY()));
    }

    private void finish() {
        this.song.closePlay();
        this.soundEffects.play(commandEffects("finish"));
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

    private List<IItemDTO> getIItemDTO(List<Item> itens) {
        return new ArrayList<>(itens.stream()
                .map(item -> new ItemDTO(item.getIcon().toString(), item.getLocation()))
                .toList());
    }
}
