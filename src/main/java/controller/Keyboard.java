package controller;

import exception.InventoryException;
import exception.MapGameException;
import model.*;
import model.builder.map.MapGame;
import service.NextDoor;
import service.NextScenery;
import service.Take;
import view.InterfaceGame;
import view.InterfaceInventory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode >= 37 && keyCode <= 40) {
                    movePlayer(keyCode);
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

    private void movePlayer(int keyCode) {
        boolean sucess = false;
        if (keyCode == 38) {
            if (player.getLocation().getY()  > 0) {
                player.walk("norte");
            } else {
                sucess = new NextScenery(this.interfaceGame).run("norte");
            }
        } else if (keyCode == 40) {
            if (player.getLocation().getY()  < this.interfaceGame.getMapGameJLabel().getHeight() - 50) {
                player.walk("sul");
            } else {
                sucess = new NextScenery(this.interfaceGame).run("sul");
            }
        } else if (keyCode == 37) {
            if (player.getLocation().getX() > 0) {
                player.walk("oeste");
            } else {
                sucess = new NextScenery(this.interfaceGame).run("oeste");
            }
        } else if (keyCode == 39) {
            if (player.getLocation().getX() < this.interfaceGame.getMapGameJLabel().getWidth() - 30) {
                player.walk("leste");
            } else {
                sucess = new NextScenery(this.interfaceGame).run("leste");
            }
        }
        if (sucess) {
            this.song.play(this.player.getCurrentMap().getSong());
            updateItensMapGame();
        }
    }

    private void updateItensMapGame() {
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(this.player.getCurrentMap().getItemVisible(), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
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
}
