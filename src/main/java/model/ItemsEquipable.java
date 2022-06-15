package model;

public enum ItemsEquipable {

    MOCHILA("mochila") {
        @Override
        public boolean equip(Player player) {
            player.getInventory().setMaxCapacity(5);
            return true;
        }

        @Override
        public boolean unequip(Player player) {
            if (!(player.getInventory().getCapacity() <= (player.getInventory().getMaxCapacity() - 5)))
                return false;
            player.getInventory().setMaxCapacity(-5);
            return true;
        }
    }, TOCHA("tocha") {
        @Override
        public boolean equip(Player player) {
            Door openDoor = player.getCurrentMap().getDoorMap(90, 240);
            openDoor.setOpen(true);
            return true;
        }

        @Override
        public boolean unequip(Player player) {
            Door openDoor = player.getCurrentMap().getDoorMap(90, 240);
            openDoor.setOpen(false);
            return true;
        }
    };

    private final String label;

    public abstract boolean equip(Player player);

    public abstract boolean unequip(Player player);

    ItemsEquipable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
