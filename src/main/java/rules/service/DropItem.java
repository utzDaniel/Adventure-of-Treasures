package rules.service;

import backend.model.builder.item.Item;
import backend.model.Player;

public final class DropItem {

    private final Player player;
    private final Item item;

    public DropItem(Player player, Item item) {
        this.player = player;
        this.item = item;
    }
    public boolean run() {
        removeItemInventory();
        addItemCurrentMap();
        return true;
    }

    public void removeItemInventory(){
        new RemoveItem(this.player.getInventory(), this.item).run();
    }

    private void addItemCurrentMap() {
        this.player.getCurrentMap().
                addItem(this.item, this.player.getLocation());
    }

}
