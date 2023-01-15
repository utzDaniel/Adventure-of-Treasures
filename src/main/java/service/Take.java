package service;

import model.Item;
import model.Player;

import java.util.Objects;

public class Take {

    private final Player player;

    public Take() {
        this.player = Player.getInstance();
    }

    public boolean run() {
        Item item = this.player.lookItem();
        if (Objects.isNull(item)) return false;
        player.takeItem(item);
        return true;
    }
}
