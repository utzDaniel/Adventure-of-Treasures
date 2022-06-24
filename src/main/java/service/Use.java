package service;

import model.IUsable;
import model.Item;
import model.ItemsUsable;
import model.Player;

public final class Use <T extends IUsable> {

    private final Player player;
    private final Item item;

    public Use(Player player, Item item) {
        this.player = player;
        this.item = item;
    }

    public boolean run() {
        if(!(checkItensIUsable() && checkCurrentMap() && useItem())){
            return false;
        }
        removeItem();
        return true;
    }

    private boolean checkItensIUsable() {
        return this.item instanceof IUsable;
    }

    private boolean checkCurrentMap() {
        return ((T) this.item).getLocalUse().equals(this.player.getCurrentMap().getName());
    }

    private boolean useItem() {
        for (ItemsUsable value : ItemsUsable.values()) {
            if(this.item.getName().equals(value.getLabel())){
                return value.use(this.player);
            }
        }
        return false;
    }

    private void removeItem() {
        player.getInventory().removeItem(this.item);
    }
}
