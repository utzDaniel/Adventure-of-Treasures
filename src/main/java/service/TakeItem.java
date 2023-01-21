package service;

import model.builder.item.Item;
import model.Player;

public final class TakeItem {

    private final Player player;
    private final Item item;

    public TakeItem(Item item) {
        this.player = Player.getInstance();
        this.item = item;
    }

    public boolean run() {
        addItemInventory();
        removeItemCurrentMap();
        return true;
    }

    private void addItemInventory() {
        new AddItemInventory(this.player.getInventory(), this.item).run();
    }

    private void removeItemCurrentMap() {
        this.player.getCurrentMap().removeItem(this.item);
    }
}
