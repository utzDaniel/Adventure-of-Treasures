package service;

import model.*;
import view.InterfaceGame;

public class NextDoor {

    private final Player player;
    private final InterfaceGame interfaceGame;
    private final Song song;
    private final SoundEffects soundEffects;

    public NextDoor(Player player, InterfaceGame interfaceGame, Song song, SoundEffects soundEffects) {
        this.player = player;
        this.interfaceGame = interfaceGame;
        this.song = song;
        this.soundEffects = soundEffects;
    }

    public void run() {
        Door door = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
        if (door != null && door.isOpen()) {
            MapGame mapGame = player.getCurrentMap().getMapDoor(door);
            if (door.getPositionInsideX() == player.getPositionPlayerX() && door.getPositionInsideY() == player.getPositionPlayerY()) {
                player.setPositionPlayerX(door.getPositionOutsideX(), interfaceGame.getPlayerJLabel());
                player.setPositionPlayerY(door.getPositionOutsideY(), interfaceGame.getPlayerJLabel());
            } else {
                player.setPositionPlayerX(door.getPositionInsideX(), interfaceGame.getPlayerJLabel());
                player.setPositionPlayerY(door.getPositionInsideY(), interfaceGame.getPlayerJLabel());
            }
            player.setCurrentMap(mapGame);
            interfaceGame.getMapGameJLabel().setIcon(mapGame.getImagemIcon());
        } else if (player.getPositionPlayerX() == 710 && player.getPositionPlayerY() == 280) {
            finish();
        }
        updateItensMapGame();

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
