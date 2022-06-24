package service;

import model.Item;
import model.Player;

public final class DropItem {

    private final Player player;
    private final Item item;

    public DropItem(Player player, Item item) {
        this.player = player;
        this.item = item;
    }
    public boolean run() {
        if (!removeItemInventory()) return false;
        addItemCurrentMap();
        return true;
    }

    public boolean removeItemInventory(){
        return new RemoveItem(this.player.getInventory(), this.item).run();
    }

    private void addItemCurrentMap() {
        this.player.getCurrentMap().
                setItemRemove(this.item, this.player.getPositionPlayerX(), this.player.getPositionPlayerY());
    }

}
