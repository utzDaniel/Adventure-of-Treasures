package service;

import model.Item;
import model.Player;

public class TakeItem {

    private final Player player;
    private final Item item;

    public TakeItem(Player player, Item item) {
        this.player = player;
        this.item = item;
    }

    public boolean run() {
        if (!new AddItem(this.player.getInventory(),this.item).run()) return false;
        this.player.getCurrentMap().removeItem(this.item);
        return true;
    }

}
