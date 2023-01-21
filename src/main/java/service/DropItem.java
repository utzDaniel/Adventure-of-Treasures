package service;

import model.builder.item.Item;
import model.Player;

public final class DropItem {

    private final Player player;
    private final Item item;

    public DropItem(Item item) {
        this.player = Player.getInstance();
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
                addItem(this.item);
    }

}
