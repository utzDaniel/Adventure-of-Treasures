package rules.service;

import backend.model.builder.item.Item;
import backend.model.Player;

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
