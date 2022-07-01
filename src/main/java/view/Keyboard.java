package view;

import model.Player;
import model.Song;
import model.SoundEffects;
import service.NextDoor;
import service.NextScenery;
import service.Take;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                if(keyCode >= 37 && keyCode <=40){
                    movePlayer(keyCode);
                }
                 else if (keyCode == 97) {
                    new NextDoor(player, interfaceGame, song, soundEffects).run();
                } else if (keyCode == 98) {
                   new Take(player, interfaceGame, soundEffects).run();
                } else if (keyCode == 99) {
                    if(!player.getInventory().openInventory()){
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
        if (keyCode == 38) {
            if (player.getPositionPlayerY() > 0) {
                player.walk("norte", interfaceGame.getPlayerJLabel());
            } else {
                new NextScenery(player, interfaceGame, song).run("norte");
            }
        } else if (keyCode == 40) {
            if (player.getPositionPlayerY() < interfaceGame.getMapGameJLabel().getHeight() - 50) {
                player.walk("sul", interfaceGame.getPlayerJLabel());
            } else {
                new NextScenery(player, interfaceGame, song).run("sul");
            }
        } else if (keyCode== 37) {
            if (player.getPositionPlayerX() > 0) {
                player.walk("oeste", interfaceGame.getPlayerJLabel());
            } else {
                new NextScenery(player, interfaceGame, song).run("oeste");
            }
        } else if (keyCode == 39) {
            if (player.getPositionPlayerX() < interfaceGame.getMapGameJLabel().getWidth() - 30) {
                player.walk("leste", interfaceGame.getPlayerJLabel());
            } else {
                new NextScenery(player, interfaceGame, song).run("leste");
            }
        }

    }

}
