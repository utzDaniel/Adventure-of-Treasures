package service;

import exception.ItemUsableException;
import model.IUsable;
import model.Item;
import model.ItemsUsable;
import model.Player;

public final class Use<T extends IUsable> {

    private final Player player;
    private final Item item;

    public Use(Player player, Item item) {
        this.player = player;
        this.item = item;
    }

    public boolean run() {
        checkItensIUsable();
        checkCurrentMap();
        useItem();
        removeItem();
        return true;
    }

    private void checkItensIUsable() {
        if (!(this.item instanceof IUsable))
            throw new ItemUsableException("Item não usavél!");
    }

    private void checkCurrentMap() {
        if (!(((T) this.item).getLocalUse().equals(this.player.getCurrentMap().getName())))
            throw new ItemUsableException("Esse item não pode ser usado nesse mapa!");
    }

    private void useItem() {
        for (ItemsUsable value : ItemsUsable.values()) {
            if (this.item.getName().equals(value.getLabel())) {
                value.use(this.player);
                return;
            }
        }
        throw new ItemUsableException("Não foi possível usar esse item!");
    }

    private void removeItem() {
        player.getInventory().removeItem(this.item);
    }
}
