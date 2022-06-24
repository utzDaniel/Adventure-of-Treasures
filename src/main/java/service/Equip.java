package service;

import model.IEquipable;
import model.Item;
import model.ItemsEquipable;
import model.Player;

public final class Equip <T extends Item> {

    private final Player player;
    private final T item;

    public Equip(Player player, T item) {
        this.player = player;
        this.item = item;
    }
    public boolean run() {
        return checkItemIEquipable() && equipItem();
    }

    private boolean checkItemIEquipable() {
        return this.item instanceof IEquipable;
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private boolean equipItem() {
        for (ItemsEquipable equipable : ItemsEquipable.values()) {
            if (equipable.getLabel().equals(this.item.getName())) {
                return equipable.equip(this.player);
            }
        }
        return false;
    }
}
