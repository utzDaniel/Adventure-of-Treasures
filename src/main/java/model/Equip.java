package model;

public final class Equip <T extends Item> {

    private final Player player;

    public Equip(Player player) {
        this.player = player;
    }

    public boolean validItemEquipable(T item) {
        return item instanceof IEquipable && equipItem(item);
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private boolean equipItem(T itemEquipable) {
        for (ItemsEquipable equipable : ItemsEquipable.values()) {
            if (equipable.getLabel().equals(itemEquipable.getName())) {
                return equipable.equip(player);
            }
        }
        return false;
    }
}
