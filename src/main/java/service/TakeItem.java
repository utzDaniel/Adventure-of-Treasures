package service;

import model.Item;
import model.MovePlayer;
import model.Player;

public final class TakeItem {

    private final Player player;
    private final Item item;

    public TakeItem(Player player, Item item) {
        this.player = player;
        this.item = item;
    }

    public boolean run() {
        if (!addItemInventory()) return false;
        removeItemCurrentMap();
        return true;
    }

    private boolean addItemInventory() {
        return new AddItem(this.player.getInventory(), this.item).run();
    }

    private void removeItemCurrentMap() {
        this.player.getCurrentMap().removeItem(this.item);
    }
}
