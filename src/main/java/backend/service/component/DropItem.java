package backend.service.component;

import backend.service.model.Player;

public final class DropItem {

    private final Player player;
    private final IItemDomain item;

    public DropItem(Player player, IItemDomain item) {
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
