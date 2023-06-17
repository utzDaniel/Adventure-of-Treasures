package backend.service.component.take;

import backend.service.component.take.valid.TakeItemValidIsExist;
import backend.service.component.take.valid.TakeItemValidIsFull;
import backend.service.model.Inventory;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.MapGame;

public final class TakeItem {
    private final Item item;
    private final MapGame mapGame;
    private final Inventory inventory;

    public TakeItem(Player player, Item item) {
        this.item = item;
        this.mapGame = player.getCurrentMap();
        this.inventory = player.getInventory();
    }

    public void run() {
        new TakeItemValidIsExist().valid(this.item);
        new TakeItemValidIsFull().valid(this.item, this.inventory);
        this.inventory.addItem(this.item);
        this.mapGame.removeItem(this.item);
    }
}
