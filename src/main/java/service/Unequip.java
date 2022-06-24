package service;

import model.IEquipable;
import model.Item;
import model.ItemsEquipable;
import model.Player;

public final class Unequip <T extends Item>{

    private final Player player;
    private final T item;

    public Unequip(Player player, T item) {
        this.player = player;
        this.item = item;
    }

    public boolean run() {
        return checkItemIEquipable() && unequipItem();
    }

    public boolean checkItemIEquipable() {
        return this.item instanceof IEquipable;
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private boolean unequipItem() {
        for (ItemsEquipable equipable : ItemsEquipable.values()) {
            if (equipable.getLabel().equals(this.item.getName())) {
                return equipable.equip(this.player);
            }
        }
        return false;
    }
}
