package service;

import model.Item;
import model.Player;

public class DropItem {

    private final Player player;
    private final Item item;

    public DropItem(Player player, Item item) {
        this.player = player;
        this.item = item;
    }
    public boolean run() {
        if (!new RemoveItem(this.player.getInventory(), this.item).run()) return false;
        this.player.getCurrentMap().setItemRemove(this.item, this.player.getPositionPlayerX(), this.player.getPositionPlayerY());
        return true;
    }
}
