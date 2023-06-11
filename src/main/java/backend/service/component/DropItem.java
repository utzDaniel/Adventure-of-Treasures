package backend.service.component;

import backend.service.model.Player;
import backend.service.model.builder.Item;

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

    public void removeItemInventory() {
        new RemoveItem(this.player.getInventory(), this.item).run();
    }

    private void addItemCurrentMap() {
        this.player.getCurrentMap().
                addItem(this.item, this.player.getLocation());
    }

}
