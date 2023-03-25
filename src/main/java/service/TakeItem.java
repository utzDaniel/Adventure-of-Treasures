package service;

import model.builder.item.Item;
import model.Player;
import model.interfaces.Command;

public final class TakeItem implements Command {

    private final Player player;
    private final Item item;

    public TakeItem(Player player, Item item) {
        this.player = player;
        this.item = item;
    }

    public boolean run() {
        addItemInventory();
        removeItemCurrentMap();
        return true;
    }

    @Override
    public String getName() {
        return "take";
    }

    private void addItemInventory() {
        new AddItemInventory(this.player.getInventory(), this.item).run();
    }

    private void removeItemCurrentMap() {
        this.player.getCurrentMap().removeItem(this.item);
    }
}
