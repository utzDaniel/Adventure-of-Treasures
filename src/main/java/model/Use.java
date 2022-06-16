package model;

public final class Use {

    private final Player player;

    public Use(Player player) {
        this.player = player;
    }

    public boolean execute(Item item) {
        return this.validItemUsable(item) &&
                this.validLocalItemUsable((IUsable) item) &&
                this.useItemUsable(item);
    }

    private boolean validItemUsable(Item item) {
        return item instanceof IUsable;
    }

    private boolean validLocalItemUsable(IUsable itemUsable) {
        return itemUsable.getLocalUse().equals(player.getCurrentMap().getName());
    }

    private boolean useItemUsable(Item itemUsable) {
        for (int i = 0; i < ItemsUsable.values().length; i++) {
            if(itemUsable.getName().equals(ItemsUsable.values()[i].getLabel())){
                if (!ItemsUsable.values()[i].use(player)) return false;
            }
        }
        removeItemUsable(itemUsable);
        return true;
    }

    private void removeItemUsable(Item itemUsable) {
        player.getInventory().removeItem(itemUsable);
    }
}
