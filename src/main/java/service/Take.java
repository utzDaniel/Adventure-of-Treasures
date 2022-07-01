package service;

import model.Item;
import model.Player;
import model.Song;
import model.SoundEffects;
import view.InterfaceGame;

public class Take {

    private final Player player;
    private final InterfaceGame interfaceGame;
    private final SoundEffects soundEffects;

    public Take(Player player, InterfaceGame interfaceGame, SoundEffects soundEffects) {
        this.player = player;
        this.interfaceGame = interfaceGame;
        this.soundEffects = soundEffects;
    }

    public void run() {
        Item item = this.player.lookItem();
        if (item != null) {
            if (player.takeItem(item)) {
                soundEffects.play("pegar");
                updateItensMapGame();
            } else {
                soundEffects.play("erro");
            }
        }
    }

    private void updateItensMapGame() {
        interfaceGame.clearItensJLabel();
        for (Item itens : player.getCurrentMap().getItemVisible()) {
            interfaceGame.setItensJLabel(itens, 1);
        }
        interfaceGame.getMapGameJLabel().repaint();
    }
}
