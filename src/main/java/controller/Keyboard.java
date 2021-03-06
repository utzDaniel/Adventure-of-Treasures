package controller;

import exception.InventoryException;
import exception.MapGameException;
import model.*;
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


    public Keyboard(InterfaceGame interfaceGame, Player player, Song song, SoundEffects soundEffects) {
        this.song = song;
        this.interfaceGame = interfaceGame;
        this.player = player;
        this.soundEffects = soundEffects;
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

                    if (player.getPositionPlayerX() == 710 && player.getPositionPlayerY() == 280
                            && Objects.nonNull(player.getInventory().getItem("tesouro")))
                        finish();
                    try {
                        MapGame atual = player.getCurrentMap();
                        new NextDoor(player, interfaceGame).run();
                        if (atual != player.getCurrentMap())
                            updateItensMapGame();
                    } catch (MapGameException ex) {
                        //TODO porta fechada
                        soundEffects.play("erro");
                    }


                } else if (keyCode == 98) {

                    try {
                        if (new Take(player).run()) {
                            soundEffects.play("pegar");
                            updateItensMapGame();
                        }

                    } catch (InventoryException ex) {
                        //TODO inventario cheio
                        soundEffects.play("erro");
                    }


                } else if (keyCode == 99) {
                    if (!player.getInventory().openInventory()) {
                        new InterfaceInventory(interfaceGame, player, soundEffects);
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
            if (player.getPositionPlayerY() > 0) {
                player.walk("norte", interfaceGame.getPlayerJLabel());
            } else {
                sucess = new NextScenery(player, interfaceGame).run("norte");
            }
        } else if (keyCode == 40) {
            if (player.getPositionPlayerY() < interfaceGame.getMapGameJLabel().getHeight() - 50) {
                player.walk("sul", interfaceGame.getPlayerJLabel());
            } else {
                sucess = new NextScenery(player, interfaceGame).run("sul");
            }
        } else if (keyCode == 37) {
            if (player.getPositionPlayerX() > 0) {
                player.walk("oeste", interfaceGame.getPlayerJLabel());
            } else {
                sucess = new NextScenery(player, interfaceGame).run("oeste");
            }
        } else if (keyCode == 39) {
            if (player.getPositionPlayerX() < interfaceGame.getMapGameJLabel().getWidth() - 30) {
                player.walk("leste", interfaceGame.getPlayerJLabel());
            } else {
                sucess = new NextScenery(player, interfaceGame).run("leste");
            }
        }
        if (sucess) {
            song.play(player.getCurrentMap().getName());
            updateItensMapGame();
        }
    }

    private void updateItensMapGame() {
        interfaceGame.clearItensJLabel();
        for (Item itens : player.getCurrentMap().getItemVisible()) {
            interfaceGame.setItensJLabel(itens, 1);
        }
        interfaceGame.getMapGameJLabel().repaint();
    }

    private void finish() {
        song.closePlay();
        soundEffects.play("finish");
        System.exit(0);
    }

}
